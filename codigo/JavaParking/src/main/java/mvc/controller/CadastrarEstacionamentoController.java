package mvc.controller;

import mvc.view.CadastrarEstacionamentoView;

import javax.swing.JOptionPane;

import mvc.dao.EstacionamentoDAO;
import mvc.dao.VagaDAO;
import mvc.exceptions.CampoInvalidoException;
import mvc.exceptions.ObjetoJaCadastradoException;
import mvc.exceptions.CampoInvalidoException;
import mvc.model.Estacionamento;

public class CadastrarEstacionamentoController {
    private EstacionamentoDAO estacionamentoDAO;
    private VagaDAO vagaDAO;
    private CadastrarEstacionamentoView view;

    public CadastrarEstacionamentoController() {
        this.view = new CadastrarEstacionamentoView();
        this.estacionamentoDAO = EstacionamentoDAO.getInstance();
        this.vagaDAO = VagaDAO.getInstance();

        this.view.btnCadastrarEstacionamento().addActionListener((e) -> {
            addEstacionamento();
        });

        this.view.setVisible(true);
    }

    public void addEstacionamento() {
        String nome = view.getTxtNomeEstacionamento().getText();
        String numeroDeVagas = view.getTxtNumeroDeVagas().getText();

        if (nome.isEmpty() || numeroDeVagas.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Todos os campos devem ser preenchidos!", "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int numero;
            try {
                numero = Integer.parseInt(numeroDeVagas);
            } catch (NumberFormatException ex) {
                throw new CampoInvalidoException("Número de vagas inválido. Insira um valor numérico.");
            }

            if(this.estacionamentoDAO.verificarEstacionamentoJaCadastrado(nome)){
                throw new ObjetoJaCadastradoException("O nome " + nome + " ja esta cadastrado.");
            }

            Estacionamento e = new Estacionamento(numero, nome);
            this.vagaDAO.cadastrarVagas(e, e.ListaDeVagas());
            estacionamentoDAO.adicionarEstacionamento(e);

            JOptionPane.showMessageDialog(view, "Estacionamento salvo com sucesso!");

        } catch (CampoInvalidoException ex) {
            JOptionPane.showMessageDialog(view, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch(ObjetoJaCadastradoException e){
            JOptionPane.showMessageDialog(view, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }finally{
            limparTela();
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
