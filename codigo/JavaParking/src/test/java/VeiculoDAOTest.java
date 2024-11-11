import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import mvc.model.Veiculo;
import mvc.dao.VeiculoDAO;

public class VeiculoDAOTest {

    private VeiculoDAO veiculoDAO;
    private Veiculo veiculo;

    @BeforeEach
    public void setUp() {
        // Inicializa o VeiculoDAO
        veiculoDAO = VeiculoDAO.getInstance();
        
        // Cria um novo veículo para os testes
        veiculo = new Veiculo("ABC-1234");
    }

    @Test
    public void testCadastrarVeiculo() {
        // Cadastra o veículo
        veiculoDAO.cadastrarVeiculo(veiculo);
        
        // Verifica se o veículo foi cadastrado corretamente
        Veiculo veiculoBuscado = veiculoDAO.pesquisarVeiculoPorPlaca("ABC-1234");
        
        assertNotNull(veiculoBuscado, "Veículo não encontrado após o cadastro.");
        assertEquals("ABC-1234", veiculoBuscado.getPlaca(), "A placa do veículo não corresponde.");
    }

    @Test
    public void testPesquisarVeiculoPorPlaca() {
        // Cadastra o veículo
        veiculoDAO.cadastrarVeiculo(veiculo);
        
        // Pesquisa o veículo pela placa
        Veiculo veiculoBuscado = veiculoDAO.pesquisarVeiculoPorPlaca("ABC-1234");
        
        // Verifica se o veículo foi encontrado corretamente
        assertNotNull(veiculoBuscado, "Veículo não encontrado.");
        assertEquals("ABC-1234", veiculoBuscado.getPlaca(), "A placa do veículo não corresponde.");
        
        // Tenta buscar um veículo inexistente
        Veiculo veiculoNaoExistente = veiculoDAO.pesquisarVeiculoPorPlaca("XYZ-0000");
        
        // Verifica se o veículo inexistente retorna null
        assertNull(veiculoNaoExistente, "Veículo inexistente não deveria ser encontrado.");
    }
}
