package mvc.dao;

import mvc.model.Vaga;
import java.io.Serializable;
import java.util.List;

public class VagaDAO extends AbstractDAO<Vaga> implements Serializable {
    private static VagaDAO instance;

    // Construtor privado para implementação do Singleton
    private VagaDAO() {
        super("./src/main/java/mvc/data/Vagas.dat");
    }

    // Método Singleton para garantir uma única instância
    public static VagaDAO getInstance() {
        if (instance == null) {
            instance = new VagaDAO();
        }
        return instance;
    }

    // Método para cadastrar uma nova vaga
    public void cadastrarVaga(Vaga vaga) {
        cadastrar(vaga);  // Chama o método da classe pai AbstractDAO
    }

    // Método para remover uma vaga
    public void removerVaga(Vaga vaga) {
        remover(vaga);  // Chama o método da classe pai AbstractDAO
    }

    // Método para pesquisar vaga por número
    public Vaga pesquisarVagaPorNumero(String numeroVaga) {
        return listarTodos().stream()
                .filter(vaga -> vaga.getNumeroVaga().equals(numeroVaga))
                .findFirst()
                .orElse(null);
    }

    // Método para listar todas as vagas
    public List<Vaga> listarVagas() {
        return listarTodos();  // Chama o método da classe pai AbstractDAO
    }

    // Método para atualizar uma vaga
    public void atualizarVaga(Vaga vagaAntiga, Vaga vagaNova) {
        atualizar(vagaAntiga, vagaNova);  // Chama o método da classe pai AbstractDAO
    }
}
