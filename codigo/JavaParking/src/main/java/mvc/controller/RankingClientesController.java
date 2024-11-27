package mvc.controller;

import java.util.ArrayList;

import mvc.dao.UsoDeVagaDAO;
import mvc.view.*;
import mvc.model.*;
import java.util.List;
import java.util.Map;

import javax.swing.table.DefaultTableModel;

public class RankingClientesController {
    private RankingClientesView view;
    private UsoDeVagaDAO usoDeVagaDAO;
    private Estacionamento estacionamentoUsado;
    private ListaDeEstacionamentosController estacionamentoSelecionado;

    public RankingClientesController() {
        this.usoDeVagaDAO = UsoDeVagaDAO.getInstance();
        this.view = new RankingClientesView();
        this.estacionamentoSelecionado = telaPrincipalView.lista();
        this.estacionamentoUsado = this.estacionamentoSelecionado.getEstacionamentoSelecionado();

        this.view.setVisible(true);

        this.view.getBtnFiltrar().addActionListener((e) -> {
            filtrar();
        });
    }

    public void filtrar() {

        int mes = this.view.getMesEscolhido().getMonth() + 1;
        int anoEscolhido = this.view.getAnoEscolhido().getYear();

        List<Map<String, Object>> rankingClientes = usoDeVagaDAO.rankingDeClientes(estacionamentoUsado, mes, anoEscolhido);

        DefaultTableModel model = (DefaultTableModel) this.view.getTbRanking().getModel();
        model.setRowCount(0);
    
        for (Map<String, Object> registro : rankingClientes) {
            String nome = (String) registro.get("nome");
            double valorPago = (double) registro.get("valorPago");
        
            Object[] row = {nome, valorPago};
            model.addRow(row);
        }

    }

}
