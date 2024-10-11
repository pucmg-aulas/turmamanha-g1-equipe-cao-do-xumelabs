package mvc.dao;

import java.util.ArrayList;

import mvc.model.Estacionamento;
import mvc.model.UsoDeVaga;

public class EstacionamentoDAO {

     private ArrayList<Estacionamento> estacionamentos;
    private static EstacionamentoDAO instance;
    
    private EstacionamentoDAO(){
        this.estacionamentos = new ArrayList<>();
    }
    
    public static EstacionamentoDAO getInstance(){
        if(instance == null)
            instance = new EstacionamentoDAO();
        return instance;
    }

    public void adicionarEstacionamento(Estacionamento estacionamento){
        estacionamentos.add(estacionamento);
    }

    public void removerEstacionamento(Estacionamento estacionamento){
        estacionamentos.remove(estacionamento);
    }

    public ArrayList<Estacionamento> listaDeEstacionamentos(){
        return estacionamentos;
    }

}
