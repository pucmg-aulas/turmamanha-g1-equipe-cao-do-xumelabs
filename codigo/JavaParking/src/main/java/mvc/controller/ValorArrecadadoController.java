package mvc.controller;

import mvc.dao.EstacionamentoDAO;
import java.time.YearMonth;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import mvc.model.Estacionamento;
import mvc.view.*;

public class ValorArrecadadoController {
    private ValorArrecadadoView view;
    private EstacionamentoDAO estacionamentoDAO;
    private Estacionamento estacionamentoUsado;
    private ListaDeEstacionamentosController estacionamentoSelecionado;

    // Modificando o construtor para receber a lista existente
    public ValorArrecadadoController() {
        this.view = new ValorArrecadadoView();
        this.estacionamentoDAO = EstacionamentoDAO.getInstance();
        this.estacionamentoSelecionado = telaPrincipalView.lista();
        this.estacionamentoUsado = this.estacionamentoSelecionado.getEstacionamentoSelecionado();

        this.view.getBtnFiltrar().addActionListener((e) -> {
            filtrar();
        });


        this.view.setVisible(true);
    }

    public void filtrar(){
        double valorTotal = estacionamentoDAO.calcularValorTotalArrecadado(estacionamentoUsado);

        JLabel labelValorTotal = this.view.getValorTotal();
        labelValorTotal.setText(String.format("%.2f", valorTotal));

        int mes = this.view.getMesEscolhido().getMonth() + 1;
        int anoEscolhido = this.view.getAnoEscolhido().getYear();

        YearMonth yearMonth = YearMonth.of(anoEscolhido, mes);

        double valorMes = estacionamentoDAO.calcularValorArrecadadoNoMes(estacionamentoUsado, yearMonth);
        JLabel labelValorMes = this.view.getValorMes();
        labelValorMes.setText(String.format("%.2f", valorMes));

        double valorMedio = estacionamentoDAO.calcularValorMedioPorUso(estacionamentoUsado);
        JLabel labelValorMedio = this.view.getValorMedio();
        labelValorMedio.setText(String.format("%.2f", valorMedio));

        this.view.revalidate();
        this.view.repaint();
    }
}
