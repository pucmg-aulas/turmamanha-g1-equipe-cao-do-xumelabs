package mvc.dao;

import mvc.model.Estacionamento;

import java.io.Serializable;
import java.util.List;

public class EstacionamentoDAO extends AbstractDAO<Estacionamento> implements Serializable {
    private static EstacionamentoDAO instance;

    // Construtor privado para implementação do Singleton
    private EstacionamentoDAO() {
        super("C:\\Users\\paulo\\Desktop\\JavaPark\\turmamanha-g1-equipe-cao-do-xumelabs\\codigo\\JavaParking\\src\\main\\java\\mvc\\data\\Estacionamentos.dat");
    }

    // Método Singleton para garantir uma única instância
    public static EstacionamentoDAO getInstance() {
        if (instance == null) {
            instance = new EstacionamentoDAO();
        }
        return instance;
    }

    public void adicionarEstacionamento(Estacionamento estacionamento) {
        cadastrar(estacionamento);  
    }

    public void removerEstacionamento(Estacionamento estacionamento) {
        remover(estacionamento);  
    }

    public List<Estacionamento> listaDeEstacionamentos() {
        return listarTodos();  
    }

    public Estacionamento pesquisarPorNumero(String nome) {
        return listarTodos().stream()
                .filter(estacionamento -> estacionamento.getNomeEstacionamento() == nome)
                .findFirst()
                .orElse(null);
    }

    public void atualizarEstacionamento(Estacionamento estacionamentoAntigo, Estacionamento estacionamentoNovo) {
        atualizar(estacionamentoAntigo, estacionamentoNovo);  
    }
}
