package mvc.controller;

import java.util.List;
import java.util.Map;

import javax.swing.table.DefaultTableModel;

import mvc.dao.UsoDeVagaDAO;
import mvc.model.Estacionamento;
import mvc.view.TaxaDeVagasOcupadasPorTipoView;
import mvc.view.telaPrincipalView;

public class TaxaDeVagasOcupadasPorTipoController {
    private UsoDeVagaDAO usoDeVagaDAO;
    private TaxaDeVagasOcupadasPorTipoView view;
    private Estacionamento estacionamentoUsado;
    private ListaDeEstacionamentosController estacionamentoSelecionado;

    public TaxaDeVagasOcupadasPorTipoController(){
        this.view = new TaxaDeVagasOcupadasPorTipoView();
        this.usoDeVagaDAO = UsoDeVagaDAO.getInstance();
        this.estacionamentoSelecionado = telaPrincipalView.lista();
        this.estacionamentoUsado = this.estacionamentoSelecionado.getEstacionamentoSelecionado();

        this.view.getBtnFiltrar().addActionListener((e) ->{
            filtrar();
        });

        this.view.setVisible(true);
    }

    public void filtrar(){
        int mesEscolhido = this.view.getMesEscolhido().getMonth() + 1;
        int anoEscolhido = this.view.getAnoEscolhido().getYear();

        List<Map<String, Object>> listaDeVagasOcupadas = usoDeVagaDAO.taxaDeVagasObtidasPorTipo(estacionamentoUsado, anoEscolhido, mesEscolhido);

        DefaultTableModel model = (DefaultTableModel) this.view.getTbVagasOcupadas().getModel();
        model.setRowCount(0);
    
        for (Map<String, Object> lista : listaDeVagasOcupadas) {
            String tipoVaga = (String) lista.get("tipoVaga");
            int qntVagas = (int) lista.get("numeroDeVagasUsadas");
            double porcentagem = (double) lista.get("porcentagem");
            double valorArrecadado = (double) lista.get("valorArrecadado");
        
            Object[] row = {tipoVaga, qntVagas, porcentagem, valorArrecadado};
            model.addRow(row);
        }
    }
}
