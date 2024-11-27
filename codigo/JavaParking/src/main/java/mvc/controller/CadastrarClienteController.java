package mvc.controller;

import javax.swing.JOptionPane;

import mvc.dao.*;
import mvc.exceptions.ObjetoJaCadastradoException;
import mvc.exceptions.CampoInvalidoException;
import mvc.model.Cliente;
import mvc.model.Estacionamento;
import mvc.model.Veiculo;
import mvc.view.*;

public class CadastrarClienteController {
    private ClienteDAO clienteDAO;
    private VeiculoDAO veiculoDAO;
    private CadastrarClienteView view;
    private ListaDeEstacionamentosController estacionamentoSelecionado;
    private Estacionamento estacionamento;

    public CadastrarClienteController() {
        this.clienteDAO = ClienteDAO.getInstance();
        this.veiculoDAO = VeiculoDAO.getInstance();
        this.estacionamentoSelecionado = telaPrincipalView.lista();
        this.view = new CadastrarClienteView();
        this.estacionamento = this.estacionamentoSelecionado.getEstacionamentoSelecionado();

        this.view.btnCadastrarCliente().addActionListener((e) -> {
            cadastrarCliente();
        });

        this.view.setVisible(true);
    }

    public void cadastrarCliente() {
        String nome = this.view.getNome().getText();
        String cpf = this.view.getCpf().getText();

        try {
            if (clienteDAO.pesquisarPorCpf(cpf, estacionamento) != null) {
                throw new ObjetoJaCadastradoException("Cliente com CPF " + cpf + " já está cadastrado.");
            }

            Cliente c = new Cliente(nome, cpf);
            this.clienteDAO.cadastrarCliente(c, estacionamento);

            JOptionPane.showMessageDialog(view, "Cliente salvo com sucesso!");

            int opcao = JOptionPane.showConfirmDialog(view, "Deseja cadastrar um veículo para este cliente?",
                    "Cadastrar Veículo", JOptionPane.YES_NO_OPTION);

            if (opcao == JOptionPane.YES_OPTION) {
                String placa = JOptionPane.showInputDialog(view, "Informe a placa do veículo:");

                if (placa == null || placa.trim().isEmpty()) {
                    throw new CampoInvalidoException("Placa não informada. O cadastro do veículo foi cancelado.");
                } else if (veiculoDAO.pesquisarVeiculoPorPlaca(placa) != null) {
                    throw new ObjetoJaCadastradoException("Já existe um veículo cadastrado com a placa " + placa);
                } else {
                    Veiculo veiculo = new Veiculo(placa);
                    this.veiculoDAO.cadastrarVeiculo(veiculo, c);

                    JOptionPane.showMessageDialog(view, "Veículo cadastrado com sucesso!");
                }
            }

        } catch (ObjetoJaCadastradoException e) {
            JOptionPane.showMessageDialog(view, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);

        } catch (CampoInvalidoException e) {
            JOptionPane.showMessageDialog(view, e.getMessage(), "Erro", JOptionPane.WARNING_MESSAGE);

        } finally {
            limparTela();
        }
    }

    private void limparTela() {
        try {
            this.view.getNome().setText("");
            this.view.getCpf().setText("");
        } catch (Exception e) {
            System.err.println("Erro ao limpar campos: " + e.getMessage());
        }
    }
}
