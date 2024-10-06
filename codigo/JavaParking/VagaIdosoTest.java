package JavaParking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

public class VagaIdosoTest {

    @Test
    public void testeCalcularAjuste() {
        VagaIdoso vagaIdoso = new VagaIdoso("1");

        // Verifica se o ajuste de desconto está correto
        assertEquals(0.85, vagaIdoso.calcularAjuste(), "O ajuste da VagaIdoso deve ser 0.85.");
    }

    @Test
    public void testeDisponibilidadeInicial() {
        VagaIdoso vagaIdoso = new VagaIdoso("1");

        // Verifica se a vaga está ocupada inicialmente
        assertFalse(vagaIdoso.isOcupada(), "A VagaIdoso deve estar disponível inicialmente.");
    }

    @Test
    public void testeGetNumeroVaga() {
        VagaIdoso vagaIdoso = new VagaIdoso("1");

        // Verifica se o número da vaga está correto
        assertEquals("1", vagaIdoso.getNumeroVaga(), "O número da vaga deve ser '1'.");
    }
}
