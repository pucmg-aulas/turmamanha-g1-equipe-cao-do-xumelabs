package mvc.controller;

import java.util.List;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

import mvc.dao.*;
import mvc.model.*;
import mvc.view.*;

public class VagasUsadasPorPeriodoDoDiaController {
    private UsoDeVagaDAO usoDeVagaDAO;
    private VagasUsadasPorPeriodoDoDIaView view;
    private Estacionamento estacionamentoUsado;
    private ListaDeEstacionamentosController estacionamentoSelecionado;

    public VagasUsadasPorPeriodoDoDiaController(){
        this.view = new VagasUsadasPorPeriodoDoDIaView();
        this.usoDeVagaDAO = UsoDeVagaDAO.getInstance();
        this.estacionamentoSelecionado = telaPrincipalView.lista();
        this.estacionamentoUsado = estacionamentoSelecionado.getEstacionamentoSelecionado();

        this.view.getBtnFiltrar().addActionListener((e) ->{
            filtrar();
        });
        this.view.setVisible(true);
    }

    public void filtrar(){
        JComboBox<String> comboBox = this.view.getTurnoSelecionado();
        String turno = (String) comboBox.getSelectedItem();

        int mesEscolhido = this.view.getMesEscolhido().getMonth() + 1;
        int anoEscolhido = this.view.getAnoEscolhido().getYear();

        List<Map<String, Object>> listaDeVagas = usoDeVagaDAO.vagasUtilizadasPorPeriodo(estacionamentoUsado, turno, anoEscolhido, mesEscolhido);

        DefaultTableModel model = (DefaultTableModel) this.view.getTbVagas().getModel();
        model.setRowCount(0);
    
        for (Map<String, Object> lista : listaDeVagas) {
            String tipoVaga = (String) lista.get("tipoVaga");
            int qntVagas = (int) lista.get("quantidadeDeVagas");
            double valorArrecadado = (double) lista.get("valorArrecadado");
        
            Object[] row = {tipoVaga, qntVagas, valorArrecadado};
            model.addRow(row);
        }
    }
}
