package mvc.controller;

import javax.swing.JOptionPane;

import mvc.dao.VeiculoDAO;
import mvc.model.Veiculo;
import mvc.view.cadastrarEstacionamentoView;
import mvc.view.cadastrarVeiculoView;

public class CadastrarVeiculoController {
private cadastrarVeiculoView view;
private VeiculoDAO veiculoDAO;

public  CadastrarVeiculoController(){
    this.view = new cadastrarVeiculoView();
    this.veiculoDAO = VeiculoDAO.getInstance();

    this.view.btnCadastrarVeiculo().addActionListener((e)->{
        addVeiculo();
    });
}

public void addVeiculo(){
    String placa = view.getTxtPlaca().getText();
    String IdCliente = view.getTxtIdCliente().getText();

    int Id = Integer.parseInt(IdCliente);

    Veiculo v = new Veiculo(placa, Id);
    veiculoDAO.cadastrarVeiculo(v);

    JOptionPane.showMessageDialog(view, "Carro salvo com sucesso!");
        
    limparTela();
}

private void limparTela(){  
    this.view.getTxtPlaca().setText("");
    this.view.getTxtIdCliente().setText("");
}
}
