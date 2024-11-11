package mvc.controller;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import mvc.dao.*;
import mvc.model.*;
import mvc.view.*;

public class EstacionarCarroController {
    private EstacionarCarroView view;
    private ListaDeEstacionamentosController estacionamentoSelecionado;
    private EstacionamentoDAO estacionamentoDAO;
    private Estacionamento estacionamentoUsado;
    private VeiculoDAO  veiculoDAO;

    public EstacionarCarroController() {
        this.view = new EstacionarCarroView();
        this.estacionamentoDAO = EstacionamentoDAO.getInstance();
        this.estacionamentoSelecionado = telaPrincipalView.lista();
        this.estacionamentoUsado = this.estacionamentoSelecionado.getEstacionamentoSelecionado();
        this.veiculoDAO = VeiculoDAO.getInstance();

        inserirVagasNaTabela();

        this.view.getTbVagas().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                capturarCliqueNaTabela(evt);
            }
        });

        this.view.btnEstacionar().addActionListener((e) -> {
            estacionar();
        });

        this.view.setVisible(true);
    }

    private void inserirVagasNaTabela() {
        DefaultTableModel model = (DefaultTableModel) this.view.getTbVagas().getModel();
        model.setRowCount(0);

        for (Vaga vaga : estacionamentoDAO.listaDeVagas(estacionamentoUsado)) {
            String tipoVaga = "";
            String disponibilidade = vaga.isOcupada() ? "Ocupada" : "Livre";

            if (vaga instanceof VagaDefault) {
                tipoVaga = "Default";
            } else if (vaga instanceof VagaVip) {
                tipoVaga = "VIP";
            } else if (vaga instanceof VagaPcd) {
                tipoVaga = "PCD";
            } else if (vaga instanceof VagaIdoso) {
                tipoVaga = "Idoso";
            }

            Object[] row = { vaga.getNumeroVaga(), tipoVaga, disponibilidade };
            model.addRow(row);
        }
    }

    private void capturarCliqueNaTabela(java.awt.event.MouseEvent evt) {
        int row = this.view.getTbVagas().getSelectedRow();
        if (row != -1) {
            String numeroVaga = this.view.getTbVagas().getValueAt(row, 0).toString();
            Vaga vagaSelecionada = this.estacionamentoDAO.pesquisarVaga(estacionamentoUsado, numeroVaga);
        }
    }

    public void estacionar() {
        int row = this.view.getTbVagas().getSelectedRow();
        if (row != -1) {
            String numeroVaga = this.view.getTbVagas().getValueAt(row, 0).toString();
            String placa = this.view.getTxtPlaca().getText();
            Vaga vagaSelecionada = this.estacionamentoDAO.pesquisarVaga(estacionamentoUsado, numeroVaga);
            Veiculo veiculo;
    
            if (vagaSelecionada == null) {
                JOptionPane.showMessageDialog(view, "Vaga não encontrada!");
                return;
            }
    
            if (vagaSelecionada.isOcupada()) {
                JOptionPane.showMessageDialog(view, "A vaga já está ocupada!");
                return;
            }
    
            if (placa.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Placa não pode estar vazia!");
                return;
            }
    
            // Buscar ou cadastrar o veículo
            if (this.veiculoDAO.pesquisarVeiculoPorPlaca(placa) == null) {
                veiculo = new Veiculo(placa);
                this.veiculoDAO.cadastrar(veiculo);
            } else {
                veiculo = this.veiculoDAO.pesquisarVeiculoPorPlaca(placa);
            }
    
            // Criar o objeto UsoDeVaga conforme o tipo da vaga selecionada
            UsoDeVaga usoDeVaga;
            if (vagaSelecionada instanceof VagaDefault) {
                usoDeVaga = new UsoDeVaga(veiculo, new VagaDefault(numeroVaga));
            } else if (vagaSelecionada instanceof VagaIdoso) {
                usoDeVaga = new UsoDeVaga(veiculo, new VagaIdoso(numeroVaga));
            } else if (vagaSelecionada instanceof VagaVip) {
                usoDeVaga = new UsoDeVaga(veiculo, new VagaVip(numeroVaga));
            } else if (vagaSelecionada instanceof VagaPcd) {
                usoDeVaga = new UsoDeVaga(veiculo, new VagaPcd(numeroVaga));
            } else {
                JOptionPane.showMessageDialog(view, "Tipo de vaga não encontrado!");
                return;
            }
    
            vagaSelecionada.alterarDisponibilidade(true); 
    
            estacionamentoUsado.estacionar(usoDeVaga);
    
            estacionamentoDAO.atualizar(estacionamentoUsado, estacionamentoUsado); // Passa o objeto antigo e novo, ambos sendo o atualizado
    
            JOptionPane.showMessageDialog(view, "Estacionado com sucesso!");
    
            inserirVagasNaTabela();
            limparTela();
        } else {
            JOptionPane.showMessageDialog(view, "Selecione uma vaga!");
        }
    }    


    public void limparTela() {
        this.view.getTxtPlaca().setText("");
    }
}
