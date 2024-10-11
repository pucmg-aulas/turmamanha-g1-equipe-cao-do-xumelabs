package mvc.dao;

import java.util.ArrayList;

import mvc.model.UsoDeVaga;
import mvc.model.Veiculo;

public class UsoDeVagaDAO {
    private ArrayList<UsoDeVaga> usoDeVagas;
    private static UsoDeVagaDAO instance;
    
    private UsoDeVagaDAO(){
        this.usoDeVagas = new ArrayList<>();
    }
    
    public static UsoDeVagaDAO getInstance(){
        if(instance == null)
            instance = new UsoDeVagaDAO();
        return instance;
    }

    public void criarUsoDeVaga(UsoDeVaga usoDeVaga){
        usoDeVagas.add(usoDeVaga);
    }

    public void removerUsoDeVaga(UsoDeVaga usoDeVaga){
        usoDeVagas.remove(usoDeVaga);
    }
}
