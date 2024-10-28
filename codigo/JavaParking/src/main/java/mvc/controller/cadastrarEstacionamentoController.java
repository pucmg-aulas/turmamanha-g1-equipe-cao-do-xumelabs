package mvc.controller;

import mvc.view.cadastrarEstacionamentoView;

import javax.swing.JOptionPane;

import mvc.dao.EstacionamentoDAO;
import mvc.model.Estacionamento;

public class cadastrarEstacionamentoController {
private EstacionamentoDAO estacionamentoDAO;
private cadastrarEstacionamentoView view;

public  cadastrarEstacionamentoController(){
    this.view = new cadastrarEstacionamentoView();
    this.estacionamentoDAO = EstacionamentoDAO.getInstance();

    this.view.btnCadastrarEstacionamento().addActionListener((e)->{
        addEstacionamento();
    });
}

public void addEstacionamento(){
    String nome = view.getTxtNomeEstacionamento().getText();
    String numeroDeVagas = view.getTxtNumeroDeVagas().getText();

    int numero = Integer.parseInt(numeroDeVagas);

    Estacionamento e = new Estacionamento(numero, nome);
    estacionamentoDAO.adicionarEstacionamento(e);

    JOptionPane.showMessageDialog(view, "Estacionamento salvo com sucesso!");
        
    limparTela();
}

private void limparTela(){
        
    this.view.getTxtNumeroDeVagas().setText("");
    this.view.getTxtNomeEstacionamento().setText("");
}

}
