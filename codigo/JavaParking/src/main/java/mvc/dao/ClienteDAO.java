package mvc.dao;

import mvc.model.Cliente;
import java.io.Serializable;
import java.util.List;

public class ClienteDAO extends AbstractDAO<Cliente> implements Serializable {

    private static ClienteDAO instance;
    private final String localArquivo = "./src/main/java/mvc/data/Cliente.dat";

    // Construtor privado para implementação do Singleton
    private ClienteDAO() {
        super("./src/main/java/mvc/data/Clientes.dat");
    }

    // Método Singleton para garantir uma única instância
    public static ClienteDAO getInstance() {
        if (instance == null) {
            instance = new ClienteDAO();
        }
        return instance;
    }

    // Método para cadastrar um novo cliente
    public void cadastrarCliente(Cliente cliente) {
        cadastrar(cliente);  // Chama o método da classe pai AbstractDAO
    }

    // Método para remover um cliente
    public void removerCliente(Cliente cliente) {
        remover(cliente);  // Chama o método da classe pai AbstractDAO
    }

    // Método para pesquisar cliente por nome
    public Cliente pesquisarClienteNome(String nome) {
        return listarTodos().stream()
                .filter(cliente -> cliente.getNome().equals(nome))
                .findFirst()
                .orElse(null);
    }

    public Cliente pesquisarPorId(int id) {
        return listarTodos().stream()
                .filter(cliente -> cliente.getIdentificador() == id)
                .findFirst()
                .orElse(null);
    }

    // Método para listar todos os clientes
    public List<Cliente> listaDeClientes() {
        return listarTodos();  // Chama o método da classe pai AbstractDAO
    }

    // Método para atualizar um cliente
    public void atualizarCliente(Cliente clienteAntigo, Cliente clienteNovo) {
        atualizar(clienteAntigo, clienteNovo);  // Chama o método da classe pai AbstractDAO
    }
}
