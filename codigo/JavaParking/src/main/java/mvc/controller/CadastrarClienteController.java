package mvc.controller;

import mvc.dao.ClienteDAO;
import mvc.model.Cliente;
import mvc.view.CadastrarClienteView;
import javax.swing.JOptionPane;

public class CadastrarClienteController {
private ClienteDAO clienteDAO;
private CadastrarClienteView view;

public CadastrarClienteController(){
    this.clienteDAO = ClienteDAO.getInstance();
    this.view = new CadastrarClienteView();

    this.view.btnCadastrarCliente().addActionListener((e) -> {
        addCliente();
    });

    this.view.setVisible(true);
}

public void addCliente(){

    if(this.view.btnAnonimo().isSelected()){
        Cliente c = new Cliente();
        clienteDAO.cadastrarCliente(c);
        JOptionPane.showMessageDialog(view, "Cliente salvo com sucesso!");
        limparTela();
        view.btnAnonimo().setSelected(false);
    }else{
        String nome = this.view.getNome().getText();
        Cliente c = new Cliente(nome);
        clienteDAO.cadastrarCliente(c);
        JOptionPane.showMessageDialog(view, "Cliente salvo com sucesso!");
        limparTela();
    }
}

private void limparTela() {
    try {
        this.view.getNome().setText("");
    } catch (Exception e) {
        System.err.println("Erro ao limpar campos: " + e.getMessage());
    }
}
}
