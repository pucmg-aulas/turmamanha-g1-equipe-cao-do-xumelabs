import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.time.LocalDate;
import mvc.model.*;

class UsoDeVagaTest {

    private UsoDeVaga usoDeVaga;
    private Veiculo veiculo;
    private Vaga vaga;

    // Classe concreta para simular Vaga abstrata
    class VagaTeste extends Vaga {
        public VagaTeste(String numeroVaga) {
            super(numeroVaga);
        }

        @Override
        public double calcularAjuste() {
            return 1.0; // Ajuste simples para teste
        }
    }

    @BeforeEach
    public void setUp() {
        veiculo = new Veiculo("ABC-1234");
        vaga = new VagaTeste("A01");
        usoDeVaga = new UsoDeVaga(veiculo, vaga);
    }

    @Test
    public void testOcuparVaga() {
        // Verifica se a vaga pode ser ocupada
        assertTrue(usoDeVaga.ocuparVaga());
        assertTrue(vaga.isOcupada()); // Verifica se a vaga está marcada como ocupada
        assertNotNull(usoDeVaga.getHoraEntrada()); // Verifica se a hora de chegada foi registrada
        assertEquals(LocalDate.now(), usoDeVaga.getData()); // Verifica se a data foi registrada corretamente
    }

    @Test
    public void testCalcularCobranca() {
        // Ocupa a vaga e define a hora de saída para calcular o tempo e a cobrança
        usoDeVaga.ocuparVaga();
        usoDeVaga.desocuparVaga(); // Desocupa a vaga e define a hora de saída fixa
        
        // Calcula a cobrança
        double valorCobranca = usoDeVaga.calcularCobranca();

        // Verifica se o valor calculado é menor ou igual ao máximo permitido
        assertTrue(valorCobranca <= 50);
    }
}
