package mvc.dao;

import mvc.model.UsoDeVaga;
import java.io.Serializable;
import java.util.List;

public class UsoDeVagaDAO extends AbstractDAO<UsoDeVaga> implements Serializable {
    private static UsoDeVagaDAO instance;

    // Construtor privado para implementação do Singleton
    private UsoDeVagaDAO() {
        super("./src/main/java/mvc/data/UsoDeVaga.dat");
    }

    // Método Singleton para garantir uma única instância
    public static UsoDeVagaDAO getInstance() {
        if (instance == null) {
            instance = new UsoDeVagaDAO();
        }
        return instance;
    }

    // Método para criar um novo uso de vaga
    public void criarUsoDeVaga(UsoDeVaga usoDeVaga) {
        cadastrar(usoDeVaga);  // Chama o método da classe pai AbstractDAO
    }

    // Método para listar todos os usos de vaga
    public List<UsoDeVaga> listarUsosDeVaga() {
        return listarTodos();  // Chama o método da classe pai AbstractDAO
    }

    // Método para pesquisar uso de vaga por veículo
    public UsoDeVaga pesquisarUsoDeVagaPorVeiculo(String placa) {
        return listarTodos().stream()
                .filter(uso -> uso.getVeiculo().getPlaca().equals(placa))
                .findFirst()
                .orElse(null);
    }

    // Método para atualizar um uso de vaga
    public void atualizarUsoDeVaga(UsoDeVaga usoAntigo, UsoDeVaga usoNovo) {
        atualizar(usoAntigo, usoNovo);  // Chama o método da classe pai AbstractDAO
    }

    public void removerUsoDeVaga(UsoDeVaga usoDeVaga) {
        remover(usoDeVaga);  // Chama o método da classe pai AbstractDAO
    }
}
