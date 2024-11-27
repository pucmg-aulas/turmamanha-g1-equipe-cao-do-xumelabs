import org.junit.jupiter.api.*;
import mvc.dao.*;
import mvc.model.*;
import static org.junit.jupiter.api.Assertions.*;

class UsoDeVagaDAOTest {

    private UsoDeVagaDAO usoDeVagaDAO;
    private Estacionamento estacionamento;

    @BeforeEach
    void setup() {
        usoDeVagaDAO = UsoDeVagaDAO.getInstance();
        estacionamento = new Estacionamento(20, "XumeLabs");
    }

    @Test
    void testCalcularValorTotalArrecadado() {
        double valorEsperado = 302.08; // Soma de todos os valores no exemplo fornecido
        double valorCalculado = usoDeVagaDAO.calcularValorTotalArrecadado(estacionamento);

        assertEquals(valorEsperado, valorCalculado, 0.01, 
            "O valor total arrecadado deve ser igual à soma dos valores no banco de dados.");
    }

    @Test
    void testCalcularValorMedioPorUso() {
        double valorEsperado = 33.564; // Média dos valores no exemplo fornecido
        double valorCalculado = usoDeVagaDAO.calcularValorMedioPorUso(estacionamento);

        assertEquals(valorEsperado, valorCalculado, 0.01,
            "O valor médio por uso deve ser igual à média dos valores no banco de dados.");
    }
}
