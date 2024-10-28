package mvc.controller;

import mvc.view.CadastrarEstacionamentoView;

import javax.swing.JOptionPane;

import mvc.dao.EstacionamentoDAO;
import mvc.model.Estacionamento;

public class CadastrarEstacionamentoController {
    private EstacionamentoDAO estacionamentoDAO;
    private CadastrarEstacionamentoView view;

    public CadastrarEstacionamentoController() {
        this.view = new CadastrarEstacionamentoView();
        this.estacionamentoDAO = EstacionamentoDAO.getInstance();

        this.view.btnCadastrarEstacionamento().addActionListener((e) -> {
            addEstacionamento();
        });

        this.view.setVisible(true);
    }

    public void addEstacionamento() {
        String nome = view.getTxtNomeEstacionamento().getText();
        String numeroDeVagas = view.getTxtNumeroDeVagas().getText();
    
        if (nome.isEmpty() || numeroDeVagas.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Todos os campos devem ser preenchidos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        try {
            int numero = Integer.parseInt(numeroDeVagas);
    
            Estacionamento e = new Estacionamento(numero, nome);
            estacionamentoDAO.adicionarEstacionamento(e);
    
            JOptionPane.showMessageDialog(view, "Estacionamento salvo com sucesso!");
            limparTela();
    
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(view, "Número de vagas inválido. Insira um valor numérico.", "Erro", JOptionPane.ERROR_MESSAGE);
        }

    }
    

    private void limparTela() {
        try {
            this.view.getTxtNumeroDeVagas().setText("");
            this.view.getTxtNomeEstacionamento().setText("");
        } catch (Exception e) {
            System.err.println("Erro ao limpar campos: " + e.getMessage());
        }
    }   
}
