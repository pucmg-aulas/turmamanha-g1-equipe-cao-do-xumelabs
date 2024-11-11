import mvc.model.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

public class ClienteTest {

    private Cliente cliente;

    @BeforeEach
    public void setUp() {
        // Inicializa um novo cliente antes de cada teste
        cliente = new Cliente("João", "12345678900");
    }

    @Test
    public void testCadastrarVeiculo() {
        // Cria um novo veículo apenas com a placa e cadastra no cliente
        Veiculo veiculo = new Veiculo("ABC-1234");
        cliente.cadastrarVeiculo(veiculo);

        // Verifica se o veículo foi adicionado à lista do cliente
        assertEquals(1, cliente.getVeiculos().size());
        assertEquals(veiculo, cliente.getVeiculos().get(0));
    }

    @Test
    public void testGetVeiculos() {
        // Adiciona dois veículos ao cliente
        Veiculo veiculo1 = new Veiculo("ABC-1234");
        Veiculo veiculo2 = new Veiculo("XYZ-5678");
        cliente.cadastrarVeiculo(veiculo1);
        cliente.cadastrarVeiculo(veiculo2);

        // Verifica se os veículos foram corretamente adicionados
        List<Veiculo> veiculos = cliente.getVeiculos();
        assertEquals(2, veiculos.size());
        assertTrue(veiculos.contains(veiculo1));
        assertTrue(veiculos.contains(veiculo2));
    }
}
