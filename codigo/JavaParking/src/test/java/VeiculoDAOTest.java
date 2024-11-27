import org.junit.jupiter.api.*;

import mvc.dao.VeiculoDAO;
import mvc.model.*;

import static org.junit.jupiter.api.Assertions.*;

class VeiculoDAOTest {

    private VeiculoDAO dao;

    @BeforeEach
    void setup() {
        dao = VeiculoDAO.getInstance();// Classe que contém os métodos a serem testados
    }

    @Test
    void testPesquisarVeiculoPorPlaca_Existe() {
        String placa = "abc123";
        Veiculo veiculo = dao.pesquisarVeiculoPorPlaca(placa);

        assertNotNull(veiculo, "O veículo não deve ser nulo.");
        assertEquals("abc123", veiculo.getPlaca(), "A placa do veículo deve ser abc123.");
    }

    @Test
    void testPesquisarVeiculoPorPlaca_NaoExiste() {
        String placa = "inexistente";
        Veiculo veiculo = dao.pesquisarVeiculoPorPlaca(placa);

        assertNull(veiculo, "O veículo deve ser nulo para placas inexistentes.");
    }

    @Test
    void testPesquisarCliente_Existe() {
        String cpf = "123";
        Cliente cliente = dao.pesquisarCliente(cpf);

        assertNotNull(cliente, "O cliente não deve ser nulo.");
        assertEquals("123", cliente.getCpf(), "O CPF do cliente deve ser 123.");
        assertEquals("123", cliente.getNome(), "O nome do cliente deve ser 123.");
    }

    @Test
    void testPesquisarCliente_NaoExiste() {
        String cpf = "inexistente";
        Cliente cliente = dao.pesquisarCliente(cpf);

        assertNull(cliente, "O cliente deve ser nulo para CPFs inexistentes.");
    }
}
