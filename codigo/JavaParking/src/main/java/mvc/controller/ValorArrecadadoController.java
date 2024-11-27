package mvc.controller;

import mvc.dao.EstacionamentoDAO;
import mvc.dao.UsoDeVagaDAO;

import java.time.YearMonth;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import mvc.model.Estacionamento;
import mvc.view.*;

public class ValorArrecadadoController {
    private ValorArrecadadoView view;
    private UsoDeVagaDAO usoDeVagaDAO;
    private Estacionamento estacionamentoUsado;
    private ListaDeEstacionamentosController estacionamentoSelecionado;

    // Modificando o construtor para receber a lista existente
    public ValorArrecadadoController() {
        this.view = new ValorArrecadadoView();
        this.usoDeVagaDAO = UsoDeVagaDAO.getInstance();
        this.estacionamentoSelecionado = telaPrincipalView.lista();
        this.estacionamentoUsado = this.estacionamentoSelecionado.getEstacionamentoSelecionado();

        this.view.getBtnFiltrar().addActionListener((e) -> {
            filtrar();
        });


        this.view.setVisible(true);
    }

    public void filtrar(){
        double valorTotal = usoDeVagaDAO.calcularValorTotalArrecadado(estacionamentoUsado);

        JLabel labelValorTotal = this.view.getValorTotal();
        labelValorTotal.setText(String.format("%.2f", valorTotal));

        int mes = this.view.getMesEscolhido().getMonth() + 1;
        int anoEscolhido = this.view.getAnoEscolhido().getYear();

        double valorMes = usoDeVagaDAO.calcularValorTotalArrecadadoPorMes(estacionamentoUsado, anoEscolhido, mes);
        JLabel labelValorMes = this.view.getValorMes();
        labelValorMes.setText(String.format("%.2f", valorMes));

        double valorMedio = usoDeVagaDAO.calcularValorMedioPorUso(estacionamentoUsado);
        JLabel labelValorMedio = this.view.getValorMedio();
        labelValorMedio.setText(String.format("%.2f", valorMedio));

        this.view.revalidate();
        this.view.repaint();
    }
}
