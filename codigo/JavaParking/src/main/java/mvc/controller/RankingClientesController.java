package mvc.controller;

import java.time.YearMonth;
import java.util.ArrayList;

import mvc.dao.EstacionamentoDAO;
import mvc.view.*;
import mvc.model.*;
import java.util.List;

public class RankingClientesController {
    private RankingClientesView view;
    private EstacionamentoDAO estacionamentoDAO;
    private Estacionamento estacionamentoUsado;
    private ListaDeEstacionamentosController estacionamentoSelecionado;

    public RankingClientesController(){
        this.estacionamentoDAO = EstacionamentoDAO.getInstance();
        this.view = new RankingClientesView();
        this.estacionamentoSelecionado = telaPrincipalView.lista();
        this.estacionamentoUsado = this.estacionamentoSelecionado.getEstacionamentoSelecionado();

        this.view.setVisible(true);

        this.view.getBtnFiltrar().addActionListener((e) -> {
            filtrar();
        });
    }

    public void filtrar(){

        int mes = this.view.getMesEscolhido().getMonth() + 1; 
        int anoEscolhido = this.view.getAnoEscolhido().getYear(); 

        YearMonth yearMonth = YearMonth.of(anoEscolhido, mes);

        List<Cliente> clientes = new ArrayList<Cliente>();
        clientes = estacionamentoDAO.getRankingDeClientesPorMes(estacionamentoUsado, yearMonth);

        this.view.setlistaDeClientes(clientes);
    }

}
