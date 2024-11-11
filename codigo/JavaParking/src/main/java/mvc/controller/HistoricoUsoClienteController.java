package mvc.controller;

import mvc.dao.ClienteDAO;
import mvc.dao.EstacionamentoDAO;
import mvc.model.Cliente;
import mvc.model.Estacionamento;
import mvc.model.UsoDeVaga;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import mvc.view.*;

public class HistoricoUsoClienteController {
    private HistoricoUsoClienteView view;
    private EstacionamentoDAO estacionamentoDAO;
    private ClienteDAO clienteDAO;
    private ListaDeEstacionamentosController estacionamentoSelecionado;
    private Estacionamento estacionamentoUsado;

    public HistoricoUsoClienteController(){
        
        this.estacionamentoSelecionado = telaPrincipalView.lista();
        this.estacionamentoUsado = this.estacionamentoSelecionado.getEstacionamentoSelecionado();
        this.estacionamentoDAO = EstacionamentoDAO.getInstance();
        this.clienteDAO = ClienteDAO.getInstance();
        this.view = new HistoricoUsoClienteView();

        this.view.btnFiltrar().addActionListener((e) -> {
            filtrar();
        });

        this.view.setVisible(true);
    }

    public void filtrar(){
        Object colunas[] = {"Nome", "Placa", "Valor Pago", "Data"};
        DefaultTableModel tm = new DefaultTableModel(colunas, 0);

        tm.setNumRows(0);
        String cpf = this.view.getTxtCpfCliente().getText();
        
        LocalDate dataInicio = view.getDataInicio().getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate dataFim = view.getDataFim().getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        
        List<UsoDeVaga> usos = estacionamentoDAO.listarUsoDeVagasPorClienteEData(estacionamentoUsado, cpf, dataInicio, dataFim);
        
        for (UsoDeVaga uso : usos) {
            String placa = uso.getVeiculo().getPlaca();
            Cliente cliente = clienteDAO.pesquisarPorPlaca(placa);  
            
        String nomeCliente = cliente.getNome();

        double valorPago = uso.calcularCobranca();

        LocalDate data = uso.getData();

        tm.addRow(new Object[]{nomeCliente, placa, valorPago, data.toString()});
        } 

        view.getTbCliente().setModel(tm);
    }
        
}

