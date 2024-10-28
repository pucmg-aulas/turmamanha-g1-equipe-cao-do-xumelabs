package mvc.dao;

import mvc.model.Estacionamento;
import mvc.model.UsoDeVaga;

import java.io.Serializable;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EstacionamentoDAO extends AbstractDAO<Estacionamento> implements Serializable {
    private static EstacionamentoDAO instance;

    // Construtor privado para implementação do Singleton
    private EstacionamentoDAO() {
        super("./src/main/java/mvc/data/Estacionamento.dat");
    }

    // Método Singleton para garantir uma única instância
    public static EstacionamentoDAO getInstance() {
        if (instance == null) {
            instance = new EstacionamentoDAO();
        }
        return instance;
    }

    // Método para adicionar um novo estacionamento
    public void adicionarEstacionamento(Estacionamento estacionamento) {
        cadastrar(estacionamento);  // Chama o método da classe pai AbstractDAO
    }

    // Método para remover um estacionamento
    public void removerEstacionamento(Estacionamento estacionamento) {
        remover(estacionamento);  // Chama o método da classe pai AbstractDAO
    }

    // Método para listar todos os estacionamentos
    public List<Estacionamento> listaDeEstacionamentos() {
        return listarTodos();  // Chama o método da classe pai AbstractDAO
    }

    public Estacionamento pesquisarPorNumero(int numero) {
        return listarTodos().stream()
                .filter(estacionamento -> estacionamento.getNumeroEstacionamento() == numero)
                .findFirst()
                .orElse(null);
    }

    // Método para atualizar um estacionamento
    public void atualizarEstacionamento(Estacionamento estacionamentoAntigo, Estacionamento estacionamentoNovo) {
        atualizar(estacionamentoAntigo, estacionamentoNovo);  // Chama o método da classe pai AbstractDAO
    }
}
