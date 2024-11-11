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
    String cpf = view.getTxtCpfCliente().getText();

    Cliente clienteAntigo = this.clienteDAO.pesquisarPorCpf(cpf);
    Veiculo v = new Veiculo(placa);

    Cliente clienteNovo = clienteAntigo;
    clienteNovo.cadastrarVeiculo(v);

    this.veiculoDAO.cadastrar(v);
    this.clienteDAO.atualizar(clienteAntigo, clienteNovo);;

    JOptionPane.showMessageDialog(view, "Carro salvo com sucesso!");

    limparTela();
}

private void limparTela(){  
    this.view.getTxtPlaca().setText("");
    this.view.getTxtCpfCliente().setText("");
}
}
