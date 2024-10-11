package mvc.dao;

import java.util.ArrayList;

import mvc.model.Veiculo;

public class VeiculoDAO {
    private ArrayList<Veiculo> veiculos;
    private static VeiculoDAO instance;
    
    private VeiculoDAO(){
        this.veiculos = new ArrayList<>();
    }
    
    public static VeiculoDAO getInstance(){
        if(instance == null)
            instance = new VeiculoDAO();
        return instance;
    }

    public void cadastrarVeiculo(Veiculo veiculo){
        veiculos.add(veiculo);
    }

    public void removerVeiculo(Veiculo veiculo){
        veiculos.remove(veiculo);
    }

    public Veiculo pesquisarVeiculo(String Placa){
        for(Veiculo veiculo : veiculos){
            if(veiculo.getPlaca().equals(Placa)){
                return veiculo;
            }
        }
        return null;
    }

    
}
