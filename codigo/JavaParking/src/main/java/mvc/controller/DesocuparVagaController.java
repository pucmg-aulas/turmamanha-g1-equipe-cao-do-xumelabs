package mvc.controller;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import mvc.dao.*;
import mvc.model.*;
import mvc.view.*;

public class DesocuparVagaController {
    private EstacionamentoDAO estacionamentoDAO;
    private Estacionamento estacionamentoUsado;
    private ListaDeEstacionamentosController estacionamentoSelecionado;
    private DesocuparVagaView view;
    private VeiculoDAO veiculoDAO;

    public DesocuparVagaController(){
        this.view = new DesocuparVagaView();
        this.estacionamentoDAO = EstacionamentoDAO.getInstance();
        this.veiculoDAO = VeiculoDAO.getInstance();
        this.estacionamentoSelecionado = telaPrincipalView.lista();
        this.estacionamentoUsado = this.estacionamentoSelecionado.getEstacionamentoSelecionado();
    
        inserirUsosDeVagaNaTela();

        this.view.getBtnFiltrar().addActionListener((e)->{
            filtrar();
        });
    
        this.view.getBtnPagar().addActionListener((e)->{
            btnPagar();
        });
    
        this.view.setVisible(true);
    }

    public void inserirUsosDeVagaNaTela() {
    DefaultTableModel model = (DefaultTableModel) this.view.getTbUsosDeVaga().getModel();
    model.setRowCount(0); 

    for (UsoDeVaga usoDeVaga : estacionamentoUsado.ListaDeUsoDeVagas()) {
        if(!usoDeVaga.getStatus()){
            Veiculo veiculo = usoDeVaga.getVeiculo();
        Vaga vaga = usoDeVaga.getVaga();
        String tipoVaga = "";

        if (vaga instanceof VagaDefault) {
            tipoVaga = "Default";
        } else if (vaga instanceof VagaVip) {
            tipoVaga = "VIP";
        } else if (vaga instanceof VagaPcd) {
            tipoVaga = "PCD";
        } else if (vaga instanceof VagaIdoso) {
            tipoVaga = "Idoso";
        }

        Object[] row = { vaga.getNumeroVaga(), tipoVaga, veiculo.getPlaca()};
        model.addRow(row);
        }
        }
    }  

    public void filtrar() {
        int selectedRow = this.view.getTbUsosDeVaga().getSelectedRow();
        
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(view, "Por favor, selecione uma linha antes de filtrar.", "Erro", JOptionPane.ERROR_MESSAGE);
            return; 
        }

        DefaultTableModel model = (DefaultTableModel) this.view.getTbUsosDeVaga().getModel();
        String placaVeiculo = model.getValueAt(selectedRow, 0).toString();
        String numeroVaga = model.getValueAt(selectedRow, 2).toString();

        Vaga vagaUsada = this.estacionamentoDAO.pesquisarVaga(estacionamentoUsado, numeroVaga);
        UsoDeVaga u = estacionamentoDAO.pesquisarUsoDeVagaPorPlaca(estacionamentoUsado, placaVeiculo);
        vagaUsada.alterarDisponibilidade(false);
    
        double valorAPagar = this.estacionamentoUsado.sairDaVaga(u);
        LocalDateTime horaEntrada = u.getHoraEntrada();
        LocalDateTime horaSaida = u.getHoraSaida();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        String horaEntradaStr = (horaEntrada != null) ? horaEntrada.format(formatter) : "N/A";
        String horaSaidaStr = (horaSaida != null) ? horaSaida.format(formatter) : "N/A";

        String valorAPagarStr = String.format("%.2f", valorAPagar);
        estacionamentoDAO.atualizar(estacionamentoUsado, estacionamentoUsado);

        this.view.getTxtHoraEntrada().setText(horaEntradaStr);
        this.view.getTxtHoraSaida().setText(horaSaidaStr);
        this.view.getValorAPagar().setText(valorAPagarStr);
    }
    

    public void btnPagar(){
        JOptionPane.showMessageDialog(view, "Pago com sucesso!");
        inserirUsosDeVagaNaTela();
        limparTela();
    }

    private void limparTela(){
        this.view.getTxtHoraEntrada().setText("");
        this.view.getTxtHoraSaida().setText("");
        this.view.getValorAPagar().setText("");
    }
    
}
