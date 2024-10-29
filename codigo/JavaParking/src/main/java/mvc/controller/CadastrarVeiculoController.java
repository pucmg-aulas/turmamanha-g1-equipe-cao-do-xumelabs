package mvc.controller;

import javax.swing.JOptionPane;

import mvc.dao.ClienteDAO;
import mvc.dao.VeiculoDAO;
import mvc.model.Cliente;
import mvc.model.Veiculo;
import mvc.view.CadastrarVeiculoView;

public class CadastrarVeiculoController {
private CadastrarVeiculoView view;
private VeiculoDAO veiculoDAO;
private ClienteDAO clienteDAO;

public  CadastrarVeiculoController(){
    this.view = new CadastrarVeiculoView();
    this.veiculoDAO = VeiculoDAO.getInstance();
    this.clienteDAO = ClienteDAO.getInstance();

    this.view.btnCadastrarVeiculo().addActionListener((e)->{
        addVeiculo();
    });

    this.view.setVisible(true);
}

public void addVeiculo(){
    String placa = view.getTxtPlaca().getText();
    String IdCliente = view.getTxtIdCliente().getText();
    Cliente c = new Cliente();

    int Id = Integer.parseInt(IdCliente);

    c = clienteDAO.pesquisarPorId(Id);

    Veiculo v = new Veiculo(placa, Id);

    veiculoDAO.cadastrarVeiculo(v);
    clienteDAO.cadastrarVeiculo(c, v);

    JOptionPane.showMessageDialog(view, "Carro salvo com sucesso!");
        
    limparTela();
}

private void limparTela(){  
    this.view.getTxtPlaca().setText("");
    this.view.getTxtIdCliente().setText("");
}
}
