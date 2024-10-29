package mvc.dao;

import mvc.model.Cliente;
import mvc.model.Veiculo;

import java.io.Serializable;
import java.util.List;

public class ClienteDAO extends AbstractDAO<Cliente> implements Serializable {

    private static ClienteDAO instance;

    // Construtor privado para implementação do Singleton
    private ClienteDAO() {
        super("C:\\Users\\paulo\\Desktop\\JavaPark\\turmamanha-g1-equipe-cao-do-xumelabs\\codigo\\JavaParking\\src\\main\\java\\mvc\\data\\Clientes.dat");
    }

    // Método Singleton para garantir uma única instância
    public static ClienteDAO getInstance() {
        if (instance == null) {
            instance = new ClienteDAO();
        }
        return instance;
    }

    public void cadastrarCliente(Cliente cliente) {
        cadastrar(cliente);  
    }

    public void removerCliente(Cliente cliente) {
        remover(cliente);  
    }

    public void cadastrarVeiculo(Cliente cliente, Veiculo veiculo){
        cliente.cadastrarVeiculo(veiculo);
        salvarObjetos();
    }

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

    public List<Cliente> listaDeClientes() {
        return listarTodos();  
    }

    public void atualizarCliente(Cliente clienteAntigo, Cliente clienteNovo) {
        atualizar(clienteAntigo, clienteNovo); 
    }
}
