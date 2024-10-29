package mvc.dao;

import mvc.model.Vaga;
import java.io.Serializable;
import java.util.List;

public class VagaDAO extends AbstractDAO<Vaga> implements Serializable {
    private static VagaDAO instance;

    // Construtor privado para implementação do Singleton
    private VagaDAO() {
        super("C:\\Users\\paulo\\Desktop\\JavaPark\\turmamanha-g1-equipe-cao-do-xumelabs\\codigo\\JavaParking\\src\\main\\java\\mvc\\data\\Vagas.dat");
    }

    // Método Singleton para garantir uma única instância
    public static VagaDAO getInstance() {
        if (instance == null) {
            instance = new VagaDAO();
        }
        return instance;
    }

    public void cadastrarVaga(Vaga vaga) {
        cadastrar(vaga);  
    }

    public void removerVaga(Vaga vaga) {
        remover(vaga);  
    }

    public Vaga pesquisarVagaPorNumero(String numeroVaga) {
        return listarTodos().stream()
                .filter(vaga -> vaga.getNumeroVaga().equals(numeroVaga))
                .findFirst()
                .orElse(null);
    }

    public List<Vaga> listarVagas() {
        return listarTodos();  
    }

    public void atualizarVaga(Vaga vagaAntiga, Vaga vagaNova) {
        atualizar(vagaAntiga, vagaNova);  
    }
}
