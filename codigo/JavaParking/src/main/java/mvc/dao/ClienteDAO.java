package mvc.dao;
import mvc.model.Cliente;
import mvc.model.Veiculo;
import java.util.ArrayList;

public class ClienteDAO {
    private ArrayList<Cliente> clientes;
    private static ClienteDAO instance;

    private ClienteDAO(){
        this.clientes =new ArrayList<>();
    }

    public static ClienteDAO getInstance(){
        if(instance == null)
            instance = new ClienteDAO();
        return instance;
    }

    public void cadastarCLiente(Cliente cliente){
        clientes.add(cliente);
    }

    public void removerCliente(Cliente cliente){
        clientes.remove(cliente);
    }

    public Cliente pesquisarCliente(String nome){
        for(Cliente cliente : clientes){
            if(cliente.getNome().equals(nome)){
                return cliente;
            }
        }
        return null;
    }

    public ArrayList<Cliente> listaDeCliente(){
        return clientes;
    }

    public void atualizarCliente(Cliente ClienteAntigo, Cliente ClienteNovo){
        for(Cliente cliente: clientes){
            if(cliente.equals(ClienteAntigo)){
                cliente = ClienteNovo;
            }
        }
    }
}
