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

        this.estacionamentos = estacionamentoDAO.listaDeEstacionamentos();
        this.view.setListaDeEstacionamentos(estacionamentos);  

        this.view.btnProsseguir().addActionListener((e) -> {
            selecionarEstacionamento();
        });

        this.view.setVisible(true);
    }

    public void selecionarEstacionamento() {
        int selectedIndex = view.getListaDeEstacionamentos().getSelectedIndex(); 

        if (selectedIndex >= 0 && selectedIndex < estacionamentos.size()) {
            estacionamentoSelecionado = estacionamentos.get(selectedIndex); 
            System.out.println("Estacionamento selecionado: " + estacionamentoSelecionado.getNomeEstacionamento());
        } else {
            System.out.println("Nenhum estacionamento foi selecionado.");
        }
    }

    public Estacionamento getEstacionamentoSelecionado(){
        return estacionamentoSelecionado;
    }
}
