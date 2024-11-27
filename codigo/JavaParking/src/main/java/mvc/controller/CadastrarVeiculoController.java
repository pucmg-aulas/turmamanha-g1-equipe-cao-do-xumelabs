package mvc.controller;

import javax.swing.JOptionPane;

import mvc.dao.*;
import mvc.model.*;
import mvc.view.*;
import mvc.exceptions.*;

public class CadastrarVeiculoController {
    private CadastrarVeiculoView view;
    private VeiculoDAO veiculoDAO;
    private ClienteDAO clienteDAO;
    private ListaDeEstacionamentosController estacionamentoSelecionado;

    public CadastrarVeiculoController() {
        this.view = new CadastrarVeiculoView();
        this.veiculoDAO = VeiculoDAO.getInstance();
        this.clienteDAO = ClienteDAO.getInstance();
        this.estacionamentoSelecionado = telaPrincipalView.lista();

        this.view.btnCadastrarVeiculo().addActionListener((e) -> {
            cadastrarVeiculo();
        });

        this.view.setVisible(true);
    }

    public void cadastrarVeiculo() {
        String placa = view.getTxtPlaca().getText();
        String cpf = view.getTxtCpfCliente().getText();
    
        Estacionamento e = this.estacionamentoSelecionado.getEstacionamentoSelecionado();
    
        try {
            if (cpf.isEmpty() || placa.isEmpty()) {
                throw new CampoInvalidoException("Campo CPF ou Placa não pode ser vazio.");
            }
    
            Cliente cliente = this.clienteDAO.pesquisarPorCpf(cpf, e);
    
            if (cliente == null) {
                throw new ObjetoNaoEncontradoException("Cliente não encontrado com o CPF digitado.");
            }
    
            if (this.veiculoDAO.pesquisarVeiculoPorPlaca(placa) != null) {
                throw new ObjetoJaCadastradoException("Veículo com a placa " + placa + " já está cadastrado.");
            }
    
            Veiculo v = new Veiculo(placa);
            this.veiculoDAO.cadastrarVeiculo(v, cliente);
    
            JOptionPane.showMessageDialog(view, "Carro salvo com sucesso!");
    
        } catch (CampoInvalidoException e1) {
            JOptionPane.showMessageDialog(view, e1.getMessage(), "Erro", JOptionPane.WARNING_MESSAGE);
    
        } catch (ObjetoNaoEncontradoException e2) {
            JOptionPane.showMessageDialog(view, e2.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    
        } catch (ObjetoJaCadastradoException e3) {
            JOptionPane.showMessageDialog(view, e3.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    
        } finally {
            limparTela();
        }
    }    

    private void limparTela() {
        this.view.getTxtPlaca().setText("");
        this.view.getTxtCpfCliente().setText("");
    }
}
