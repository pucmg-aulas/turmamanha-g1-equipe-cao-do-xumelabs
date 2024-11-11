package mvc.dao;

import mvc.model.Veiculo;
import java.io.Serializable;
import java.util.List;

public class VeiculoDAO extends AbstractDAO<Veiculo> implements Serializable {

    private static VeiculoDAO instance;

    // Construtor privado para implementação do Singleton
    private VeiculoDAO() {
        super("C:\\Users\\paulo\\Downloads\\JavaParkNovo(3)\\JavaParkNovo\\turmamanha-g1-equipe-cao-do-xumelabs\\codigo\\JavaParking\\src\\main\\java\\mvc\\data\\Veiculos.dat");  // Define o caminho do arquivo .dat
    }

    // Método Singleton para garantir uma única instância
    public static VeiculoDAO getInstance() {
        if (instance == null) {
            instance = new VeiculoDAO();
        }
        return instance;
    }

    public void cadastrarVeiculo(Veiculo veiculo) {
        cadastrar(veiculo); 
    }

    public void removerVeiculo(Veiculo veiculo) {
        remover(veiculo); 
    }

    public Veiculo pesquisarVeiculoPorPlaca(String placa) {
        return listarTodos().stream()
                .filter(veiculo -> veiculo.getPlaca().equals(placa))
                .findFirst()
                .orElse(null);
    }

    public List<Veiculo> listarVeiculos() {
        return listarTodos();  
    }

    public void atualizarVeiculo(Veiculo veiculoAntigo, Veiculo veiculoNovo) {
        atualizar(veiculoAntigo, veiculoNovo); 
    }
}
