package mvc.dao;
import mvc.model.Vaga;

import java.util.ArrayList;

public class VagaDAO {

    private ArrayList<Vaga> vagas;
    private static VagaDAO instance;
    
    private VagaDAO(){
        this.vagas = new ArrayList<>();
    }
    
    public static VagaDAO getInstance(){
        if(instance == null)
            instance = new VagaDAO();
        return instance;
    }

    public void cadastrarVaga(Vaga vaga){
        vagas.add(vaga);
    }

    public void removerVaga(Vaga vaga){
        vagas.remove(vaga);
    }

    public void atualizarVaga(Vaga vagaAntiga, Vaga vagaNova){
        for(Vaga v : vagas){
            if(v.equals(vagaAntiga)){
                v = vagaNova;
            }
        }
    }

    public String encontrarVagaPorNumero(Vaga vaga){
        for(Vaga v : vagas){
            if(v.equals(vaga)){
                return v.getNumeroVaga();
            }
        }
        return null;
    }

    public ArrayList<Vaga> vagasCadastradas(){
        return vagas;
    }

    public double valorAjuste(Vaga vaga){
        for(Vaga v : vagas){
            if(v.equals(vaga)){
                return v.calcularAjuste();
            }
        }
        return 1;
    }
}
