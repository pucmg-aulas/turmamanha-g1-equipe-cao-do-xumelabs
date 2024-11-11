import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import mvc.model.Veiculo;

public class VeiculoTest {

    private Veiculo veiculo;

    @BeforeEach
    public void setUp() {
        // Inicializa um novo veículo com uma placa antes de cada teste
        veiculo = new Veiculo("ABC-1234");
    }

    @Test
    public void testGetPlaca() {
        // Verifica se o método getPlaca retorna corretamente a placa do veículo
        assertEquals("ABC-1234", veiculo.getPlaca());
    }

    @Test
    public void testSetPlaca() {
        // Define uma nova placa para o veículo
        veiculo.setPlaca("XYZ-5678");

        // Verifica se a placa foi atualizada corretamente
        assertEquals("XYZ-5678", veiculo.getPlaca());
    }
}
