package mvc.controller;

import mvc.dao.VeiculoDAO;
import mvc.exceptions.CampoInvalidoException;
import mvc.exceptions.ObjetoNaoEncontradoException;
import mvc.dao.ClienteDAO;
import mvc.dao.EstacionamentoDAO;
import mvc.dao.UsoDeVagaDAO;
import mvc.model.Cliente;
import mvc.model.Estacionamento;
import mvc.model.UsoDeVaga;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import mvc.view.*;

public class HistoricoUsoClienteController {
    private HistoricoUsoClienteView view;
    private UsoDeVagaDAO usoDeVagaDAO;
    private VeiculoDAO veiculoDAO;
    private ListaDeEstacionamentosController estacionamentoSelecionado;
    private Estacionamento estacionamentoUsado;

    public HistoricoUsoClienteController() {
        this.estacionamentoSelecionado = telaPrincipalView.lista();
        this.estacionamentoUsado = this.estacionamentoSelecionado.getEstacionamentoSelecionado();
        this.usoDeVagaDAO = UsoDeVagaDAO.getInstance();
        this.veiculoDAO = VeiculoDAO.getInstance();
        this.view = new HistoricoUsoClienteView();

        this.view.btnFiltrar().addActionListener((e) -> {
            filtrar();
        });

        this.view.setVisible(true);
    }

    public void filtrar() {
        Object colunas[] = { "Nome", "Placa", "Valor Pago", "Data" };
        DefaultTableModel tm = new DefaultTableModel(colunas, 0);
        tm.setNumRows(0);

        String cpf = this.view.getTxtCpfCliente().getText();

        if (cpf.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Por favor, informe o CPF do cliente.");
            return;
        }

        if (view.getDataInicio().getDate() == null || view.getDataFim().getDate() == null) {
            JOptionPane.showMessageDialog(view, "Por favor, selecione as datas de início e término.");
            return;
        }

        try {
            LocalDate dataInicio = view.getDataInicio().getDate().toInstant().atZone(ZoneId.systemDefault())
                    .toLocalDate();
            LocalDate dataFim = view.getDataFim().getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            if (dataInicio.isAfter(dataFim)) {
                throw new CampoInvalidoException("A data de início não pode ser posterior à data de término.");
            }

            Cliente cliente = ClienteDAO.getInstance().pesquisarPorCpf(cpf, estacionamentoUsado);
            if (cliente == null) {
                throw new ObjetoNaoEncontradoException("Cliente não encontrado com o CPF fornecido.");
            }

            List<UsoDeVaga> usos = usoDeVagaDAO.listaUsoDeVagasPorClienteEData(estacionamentoUsado, cpf, dataInicio,
                    dataFim);
            if (usos.isEmpty()) {
                JOptionPane.showMessageDialog(view,
                        "Nenhum registro de uso de vaga encontrado para o cliente nesse período.");
                return;
            }

            for (UsoDeVaga uso : usos) {
                String placa = uso.getVeiculo().getPlaca();
                String nomeCliente = cliente.getNome();
                double valorPago = uso.calcularCobranca();
                LocalDate data = uso.getData();

                tm.addRow(new Object[] { nomeCliente, placa, valorPago, data.toString() });
            }

            view.getTbCliente().setModel(tm);

        } catch (CampoInvalidoException | ObjetoNaoEncontradoException e) {
            JOptionPane.showMessageDialog(view, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            limparCampos();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Ocorreu um erro inesperado: " + e.getMessage(), "Erro",
                    JOptionPane.ERROR_MESSAGE);
            limparCampos();
        }
    }

    private void limparCampos() {
        view.getTxtCpfCliente().setText(""); // Limpa o campo de CPF
        view.getDataInicio().setDate(null); // Limpa a data de início
        view.getDataFim().setDate(null); // Limpa a data de término
        DefaultTableModel tm = (DefaultTableModel) view.getTbCliente().getModel();
        tm.setNumRows(0); // Limpa a tabela
    }

}
