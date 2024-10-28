package mvc.dao;

import mvc.model.Veiculo;
import java.io.Serializable;
import java.util.List;

public class VeiculoDAO extends AbstractDAO<Veiculo> implements Serializable {

    private static VeiculoDAO instance;

    // Construtor privado para implementação do Singleton
    private VeiculoDAO() {
        super("./src/main/java/mvc/data/Veiculos.dat");  // Define o caminho do arquivo .dat
    }

    // Método Singleton para garantir uma única instância
    public static VeiculoDAO getInstance() {
        if (instance == null) {
            instance = new VeiculoDAO();
        }
        return instance;
    }

    // Método para cadastrar um novo veículo
    public void cadastrarVeiculo(Veiculo veiculo) {
        cadastrar(veiculo);  // Chama o método da classe pai AbstractDAO
    }

    // Método para remover um veículo
    public void removerVeiculo(Veiculo veiculo) {
        remover(veiculo);  // Chama o método da classe pai AbstractDAO
    }

    // Método para pesquisar veículo por placa
    public Veiculo pesquisarVeiculoPorPlaca(String placa) {
        return listarTodos().stream()
                .filter(veiculo -> veiculo.getPlaca().equals(placa))
                .findFirst()
                .orElse(null);
    }

    public Veiculo pesquisarPorId(int idCliente) {
        return listarTodos().stream()
                .filter(veiculo -> veiculo.getIdCliente() == idCliente)
                .findFirst()
                .orElse(null);
    }

    public List<Veiculo> listarVeiculos() {
        return listarTodos();  
    }

    // Método para atualizar um veículo
    public void atualizarVeiculo(Veiculo veiculoAntigo, Veiculo veiculoNovo) {
        atualizar(veiculoAntigo, veiculoNovo); 
    }
}
