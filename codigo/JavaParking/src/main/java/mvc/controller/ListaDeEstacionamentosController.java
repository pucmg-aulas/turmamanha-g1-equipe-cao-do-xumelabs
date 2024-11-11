package mvc.controller;

import mvc.dao.EstacionamentoDAO;
import mvc.model.Estacionamento;
import mvc.view.ListaDeEstacionamentosView;

import java.util.List;

public class ListaDeEstacionamentosController {
    private EstacionamentoDAO estacionamentoDAO;
    private ListaDeEstacionamentosView view;
    private List<Estacionamento> estacionamentos;  // Lista de estacionamentos
    private Estacionamento estacionamentoSelecionado;

    public ListaDeEstacionamentosController() {
        this.view = new ListaDeEstacionamentosView();
        this.estacionamentoDAO = EstacionamentoDAO.getInstance();

        // Carrega a lista de estacionamentos do DAO
        this.estacionamentos = estacionamentoDAO.listaDeEstacionamentos();
        this.view.setListaDeEstacionamentos(estacionamentos);  // Atualiza a view com os estacionamentos

        // Adiciona listener no botão "Prosseguir"
        this.view.btnProsseguir().addActionListener((e) -> {
            selecionarEstacionamento();
        });

        // Exibe a view
        this.view.setVisible(true);
    }

    // Método para selecionar o estacionamento
    public void selecionarEstacionamento() {
        int selectedIndex = view.getListaDeEstacionamentos().getSelectedIndex(); // Obtém o índice selecionado na View

        if (selectedIndex >= 0 && selectedIndex < estacionamentos.size()) {
            estacionamentoSelecionado = estacionamentos.get(selectedIndex); // Obtém o estacionamento pelo índice
            // Aqui você pode continuar com a lógica para o estacionamento selecionado
            System.out.println("Estacionamento selecionado: " + estacionamentoSelecionado.getNomeEstacionamento());
        } else {
            System.out.println("Nenhum estacionamento foi selecionado.");
        }
    }

    public Estacionamento getEstacionamentoSelecionado(){
        return estacionamentoSelecionado;
    }
}
