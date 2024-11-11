import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import mvc.model.Cliente;
import mvc.dao.ClienteDAO;

public class ClienteDAOTest {

    private ClienteDAO clienteDAO;
    private Cliente cliente;

    @BeforeEach
    public void setUp() {
        // Inicializa o ClienteDAO
        clienteDAO = ClienteDAO.getInstance();
        
        // Cria um novo cliente
        cliente = new Cliente("Carlos Silva", "123.456.789-00");
    }

    @Test
    public void testCadastrarCliente() {
        // Cadastra o cliente
        clienteDAO.cadastrarCliente(cliente);
        
        // Verifica se o cliente foi cadastrado corretamente
        Cliente clienteCadastrado = clienteDAO.pesquisarClienteNome("Carlos Silva");
        assertNotNull(clienteCadastrado, "Cliente não foi cadastrado.");
        assertEquals("Carlos Silva", clienteCadastrado.getNome(), "Nome do cliente não corresponde.");
    }

    @Test
    public void testPesquisarClienteNome() {
        // Cria um novo cliente e o cadastra
        Cliente cliente2 = new Cliente("Ana Costa", "987.654.321-00");
        clienteDAO.cadastrarCliente(cliente2);

        // Pesquisa o cliente pelo nome
        Cliente clientePesquisado = clienteDAO.pesquisarClienteNome("Ana Costa");
        
        // Verifica se o cliente foi encontrado corretamente
        assertNotNull(clientePesquisado, "Cliente não encontrado.");
        assertEquals("Ana Costa", clientePesquisado.getNome(), "Nome do cliente não corresponde.");
    }
}
