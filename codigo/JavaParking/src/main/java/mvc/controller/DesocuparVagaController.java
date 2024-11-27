package mvc.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import mvc.dao.*;
import mvc.exceptions.CampoInvalidoException;
import mvc.exceptions.ObjetoNaoEncontradoException;
import mvc.model.*;
import mvc.view.*;

public class DesocuparVagaController {
    private Estacionamento estacionamentoUsado;
    private ListaDeEstacionamentosController estacionamentoSelecionado;
    private DesocuparVagaView view;
    private VagaDAO vagaDAO;
    private UsoDeVaga usoDeVagaSelecionado;
    private Vaga vagaSelecionada;
    private UsoDeVagaDAO usoDeVagaDAO;

    public DesocuparVagaController() {
        this.view = new DesocuparVagaView();
        this.usoDeVagaDAO = UsoDeVagaDAO.getInstance();
        this.vagaDAO = VagaDAO.getInstance();
        this.estacionamentoSelecionado = telaPrincipalView.lista();
        this.estacionamentoUsado = this.estacionamentoSelecionado.getEstacionamentoSelecionado();

        inserirUsosDeVagaNaTela();

        this.view.getTbUsosDeVaga().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                filtrar(evt);
            }
        });

        this.view.getBtnPagar().addActionListener((e) -> {
            btnPagar();
        });

        this.view.setVisible(true);
    }

    public void inserirUsosDeVagaNaTela() {
        DefaultTableModel model = (DefaultTableModel) this.view.getTbUsosDeVaga().getModel();
        model.setRowCount(0);

        for (UsoDeVaga usoDeVaga : usoDeVagaDAO.listarUsosDeVaga(estacionamentoUsado)) {
            if (!usoDeVaga.getStatus()) {
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

                Object[] row = { vaga.getNumeroVaga(), tipoVaga, veiculo.getPlaca() };
                model.addRow(row);
            }
        }
    }

    public void filtrar(java.awt.event.MouseEvent evt) {
        int selectedRow = this.view.getTbUsosDeVaga().getSelectedRow();
        try {
            if (selectedRow == -1) {
                throw new CampoInvalidoException("Por favor, selecione uma linha.");
            }

            // Capturar dados da linha selecionada
            String numeroVaga = this.view.getTbUsosDeVaga().getValueAt(selectedRow, 0).toString();
            String placaVeiculo = this.view.getTbUsosDeVaga().getValueAt(selectedRow, 2).toString();

            // Buscar vaga e uso de vaga
            Vaga vagaUsada = this.vagaDAO.pesquisarVagaPorNumero(estacionamentoUsado, numeroVaga);
            UsoDeVaga usoDeVagaAntigo = usoDeVagaDAO.pesquisarUsoDeVagaNaoFinalizado(estacionamentoUsado, placaVeiculo);

            if (usoDeVagaAntigo == null) {
                throw new ObjetoNaoEncontradoException("Uso de vaga não encontrado.");
            }

            usoDeVagaAntigo.setHoraSaida(LocalDateTime.now());

            // Calcular valor a pagar e exibir informações na interface
            double valorAPagar = usoDeVagaAntigo.calcularCobranca();

            LocalDateTime horaEntrada = usoDeVagaAntigo.getHoraEntrada();
            LocalDateTime horaSaida = usoDeVagaAntigo.getHoraSaida();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            String horaEntradaStr = (horaEntrada != null) ? horaEntrada.format(formatter) : "N/A";
            String horaSaidaStr = (horaSaida != null) ? horaSaida.format(formatter) : "N/A";
            String valorAPagarStr = String.format("%.2f", valorAPagar);

            // Exibir informações na tela
            this.view.getValorAPagar().setText(valorAPagarStr);
            this.view.getTxtHoraEntrada().setText(horaEntradaStr);
            this.view.getTxtHoraSaida().setText(horaSaidaStr);

            // Armazenar objetos selecionados no controlador
            this.usoDeVagaSelecionado = usoDeVagaAntigo;
            this.vagaSelecionada = vagaUsada;

        } catch (CampoInvalidoException | ObjetoNaoEncontradoException e) {
            JOptionPane.showMessageDialog(this.view, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void btnPagar() {
        if (this.usoDeVagaSelecionado == null || this.vagaSelecionada == null) {
            JOptionPane.showMessageDialog(this.view, "Nenhuma vaga selecionada para pagamento.");
            return;
        }

        JOptionPane.showMessageDialog(view, "Pago com sucesso!");

        // Atualizar uso de vaga
        this.usoDeVagaSelecionado.desocuparVaga();
        this.usoDeVagaSelecionado.setStatus(true);
        this.usoDeVagaDAO.atualizarUsoDeVaga(this.usoDeVagaSelecionado, this.usoDeVagaSelecionado, estacionamentoUsado);

        // Atualizar vaga para disponível
        this.vagaSelecionada.alterarDisponibilidade(false);
        this.vagaDAO.atualizar(this.vagaSelecionada, this.vagaSelecionada, estacionamentoUsado);

        inserirUsosDeVagaNaTela();
        limparTela();

        this.usoDeVagaSelecionado = null;
        this.vagaSelecionada = null;
    }

    private void limparTela() {
        this.view.getTxtHoraEntrada().setText("");
        this.view.getTxtHoraSaida().setText("");
        this.view.getValorAPagar().setText("");
    }

}
