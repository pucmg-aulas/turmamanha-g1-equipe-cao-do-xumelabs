package mvc.dao;

import mvc.model.Cliente;
import mvc.model.Veiculo;

import java.io.Serializable;
import java.util.List;

public class ClienteDAO extends AbstractDAO<Cliente> implements Serializable {

    private static ClienteDAO instance;

    // Construtor privado para implementação do Singleton
    private ClienteDAO() {
        super("C:\\Users\\paulo\\OneDrive\\Área de Trabalho\\JavaParkNovo\\turmamanha-g1-equipe-cao-do-xumelabs\\codigo\\JavaParking\\src\\main\\java\\mvc\\data\\Clientes.dat");
    }

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

    public Cliente pesquisarClienteNome(String nome) {
        return listarTodos().stream()
                .filter(cliente -> cliente.getNome().equals(nome))
                .findFirst()
                .orElse(null);
    }

    public Cliente pesquisarPorCpf(String cpf) {
        return listarTodos().stream()
                .filter(cliente -> cliente.getCpf().equals(cpf))
                .findFirst()
                .orElse(null);
    }    

    public Cliente pesquisarPorPlaca(String placa) {
        return listarTodos().stream()
                .filter(cliente -> cliente.getVeiculos().stream()
                        .anyMatch(veiculo -> veiculo.getPlaca().equalsIgnoreCase(placa)))
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
