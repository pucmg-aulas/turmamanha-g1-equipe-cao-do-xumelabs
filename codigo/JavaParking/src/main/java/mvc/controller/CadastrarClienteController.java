package mvc.controller;

import javax.swing.JOptionPane;

import mvc.dao.ClienteDAO;
import mvc.dao.EstacionamentoDAO;
import mvc.model.Cliente;
import mvc.model.Estacionamento;
import mvc.view.*;

public class CadastrarClienteController {
private ClienteDAO clienteDAO;
private CadastrarClienteView view;
private EstacionamentoDAO estacionamentoDAO;
private ListaDeEstacionamentosController estacionamentoUsado; 

public CadastrarClienteController(){
    this.clienteDAO = ClienteDAO.getInstance();
    this.view = new CadastrarClienteView();
    this.estacionamentoDAO = EstacionamentoDAO.getInstance();
    this.estacionamentoUsado = telaPrincipalView.lista();

    this.view.btnCadastrarCliente().addActionListener((e) -> {
        addCliente();
    });

    this.view.setVisible(true);
}

public void addCliente(){
     String nome = this.view.getNome().getText();
     String cpf = this.view.getCpf().getText();

     Cliente c = new Cliente(nome, cpf);
     this.clienteDAO.cadastrar(c);

     Estacionamento e = this.estacionamentoUsado.getEstacionamentoSelecionado();
     e.addCiente(c);
     this.estacionamentoDAO.salvarObjetos();
     this.estacionamentoDAO.atualizar(this.estacionamentoUsado.getEstacionamentoSelecionado(), e);
     
     JOptionPane.showMessageDialog(view, "Cliente salvo com sucesso!");
     limparTela();
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
