package mvc.controller;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import mvc.dao.*;
import mvc.exceptions.CampoInvalidoException;
import mvc.exceptions.ObjetoJaCadastradoException;
import mvc.exceptions.ObjetoNaoEncontradoException;
import mvc.model.*;
import mvc.view.*;

public class EstacionarCarroController {
    private EstacionarCarroView view;
    private ListaDeEstacionamentosController estacionamentoSelecionado;
    private VagaDAO vagaDAO;
    private ClienteDAO clienteDAO;
    private Estacionamento estacionamentoUsado;
    private VeiculoDAO veiculoDAO;
    private UsoDeVagaDAO usoDeVagaDAO;

    public EstacionarCarroController() {
        this.view = new EstacionarCarroView();
        this.vagaDAO = VagaDAO.getInstance();
        this.clienteDAO = ClienteDAO.getInstance();
        this.usoDeVagaDAO = UsoDeVagaDAO.getInstance();
        this.estacionamentoSelecionado = telaPrincipalView.lista();
        this.estacionamentoUsado = this.estacionamentoSelecionado.getEstacionamentoSelecionado();
        this.veiculoDAO = VeiculoDAO.getInstance();

        inserirVagasNaTabela();

        this.view.btnEstacionar().addActionListener((e) -> {
            estacionar();
        });

        this.view.setVisible(true);
    }

    private void inserirVagasNaTabela() {
        DefaultTableModel model = (DefaultTableModel) this.view.getTbVagas().getModel();
        model.setRowCount(0);

        for (Vaga vaga : vagaDAO.listaDeVagas(estacionamentoUsado)) {
            if (!vaga.isOcupada()) {
                String tipoVaga = "";

                if (vaga instanceof VagaDefault) {
                    tipoVaga = "Default";
                } else if (vaga instanceof VagaVip) {
                    tipoVaga = "VIP";
                } else if (vaga instanceof VagaPcd) {
                    tipoVaga = "PCD";
                } else if (vaga instanceof VagaIdoso) {
                    tipoVaga = "Idoso";
                }

                String disponibilidade = "Livre";
                Object[] row = { vaga.getNumeroVaga(), tipoVaga, disponibilidade };
                model.addRow(row);
            }
        }
    }

    public void estacionar() {
        int row = this.view.getTbVagas().getSelectedRow();
        if (row != -1) {
            String numeroVaga = this.view.getTbVagas().getValueAt(row, 0).toString();
            String placa = this.view.getTxtPlaca().getText();
            Vaga vagaSelecionada = this.vagaDAO.pesquisarVagaPorNumero(estacionamentoUsado, numeroVaga);

            Veiculo veiculo;

            try {
                if (vagaSelecionada == null) {
                    throw new ObjetoNaoEncontradoException("Vaga não encontrada!");
                }

                if (placa.isEmpty()) {
                    throw new CampoInvalidoException("Placa não pode estar vazia!");
                }

                veiculo = this.veiculoDAO.pesquisarVeiculoPorPlaca(placa);

                String cpfAssociado = this.veiculoDAO.obterCpfPorVeiculo(placa);

                if (cpfAssociado == null && veiculo != null) {
                    int resposta = JOptionPane.showConfirmDialog(null,
                            "Este veículo está cadastrado, mas não está associado a um cliente. Deseja se cadastrar como cliente?",
                            "Cadastro de Cliente",
                            JOptionPane.YES_NO_OPTION);

                    if (resposta == JOptionPane.YES_OPTION) {
                        try {
                            String nome = JOptionPane.showInputDialog(view, "Informe o seu Nome:");
                            if (nome == null || nome.trim().isEmpty()) {
                                throw new CampoInvalidoException("Nome não pode estar vazio.");
                            }

                            String cpf = JOptionPane.showInputDialog(view, "Informe o seu CPF:");
                            if (cpf == null || cpf.trim().isEmpty()) {
                                throw new CampoInvalidoException("CPF não pode estar vazio.");
                            }
                            Cliente cliente = ClienteDAO.getInstance().pesquisarPorCpf(cpf, estacionamentoUsado);

                            if (cliente == null) {
                                cliente = new Cliente(nome, cpf);
                                ClienteDAO.getInstance().cadastrarCliente(cliente, estacionamentoUsado);
                                veiculoDAO.associarVeiculoAoCliente(placa, cpf);
                            } else {
                                throw new ObjetoJaCadastradoException("CPF já cadastrado no sistema.");
                            }

                        } catch (CampoInvalidoException e) {
                            JOptionPane.showMessageDialog(view, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                            return;
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(view,
                                    "Erro inesperado ao cadastrar cliente: " + e.getMessage(),
                                    "Erro", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                }

                if (veiculo == null) {
                    int resposta = JOptionPane.showConfirmDialog(null,
                            "Veículo com a placa '" + placa
                                    + "' não encontrado. Deseja cadastrar o veículo com o cliente como 'Anônimo'?",
                            "Cadastro de Veículo",
                            JOptionPane.YES_NO_OPTION);

                    if (resposta == JOptionPane.YES_OPTION) {
                        veiculo = new Veiculo(placa);
                        this.veiculoDAO.cadastrarVeiculo(veiculo);
                    } else {
                        throw new CampoInvalidoException("Veículo não cadastrado.");
                    }
                }

                UsoDeVaga usoDeVaga;
                if (vagaSelecionada instanceof VagaDefault || vagaSelecionada instanceof VagaIdoso
                        || vagaSelecionada instanceof VagaVip || vagaSelecionada instanceof VagaPcd) {
                    usoDeVaga = new UsoDeVaga(veiculo, vagaSelecionada);
                } else {
                    throw new ObjetoNaoEncontradoException("Tipo de vaga não encontrado!");
                }

                usoDeVaga.ocuparVaga();
                this.usoDeVagaDAO.cadastrar(estacionamentoUsado, usoDeVaga);

                vagaSelecionada.alterarDisponibilidade(true);
                vagaDAO.atualizar(vagaSelecionada, vagaSelecionada, estacionamentoUsado);

                JOptionPane.showMessageDialog(view, "Estacionado com sucesso!");

                this.inserirVagasNaTabela();

            } catch (ObjetoNaoEncontradoException | CampoInvalidoException e) {
                JOptionPane.showMessageDialog(view, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            } finally {
                limparTela();
            }
        } else {
            JOptionPane.showMessageDialog(view, "Selecione uma vaga!", "Erro", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void limparTela() {
        this.view.getTxtPlaca().setText("");
    }
}
