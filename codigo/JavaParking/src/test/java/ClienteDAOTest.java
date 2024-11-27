import mvc.dao.ClienteDAO;
import mvc.model.Cliente;
import mvc.model.Estacionamento;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class ClienteDAOTest {

    private static ClienteDAO clienteDAO;

    @BeforeAll
    static void setup() {
        // Inicializa o ClienteDAO
        clienteDAO = ClienteDAO.getInstance();
    }

    @Test
    void testPesquisarPorCpfExistente() {
        // Arrange
        Estacionamento estacionamento = new Estacionamento(30, "GuardaCarros");
        String cpfExistente = "123";

        // Act
        Cliente cliente = clienteDAO.pesquisarPorCpf(cpfExistente, estacionamento);

        // Assert
        assertNotNull(cliente, "O cliente deveria ser encontrado.");
        assertEquals("João Silva", cliente.getNome());
        assertEquals("123", cliente.getCpf());
    }

    @Test
    void testPesquisarPorCpfInexistente() {
        // Arrange
        Estacionamento estacionamento = new Estacionamento(25, "NorteMinas");
        String cpfInexistente = "99999";

        // Act
        Cliente cliente = clienteDAO.pesquisarPorCpf(cpfInexistente, estacionamento);

        // Assert
        assertNull(cliente, "Nenhum cliente deveria ser encontrado para o CPF inexistente.");
    }
}
