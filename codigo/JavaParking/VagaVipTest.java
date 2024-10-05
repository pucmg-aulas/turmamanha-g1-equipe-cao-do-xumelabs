package JavaParking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

public class VagaVipTest {

    @Test
    public void testeCalcularAjuste() {
        VagaVip vagaVip = new VagaVip("1");

        // Verifica se o ajuste de taxa está correto
        assertEquals(1.2, vagaVip.calcularAjuste(), "O ajuste da VagaVip deve ser 1.2.");
    }

    @Test
    public void testeDisponibilidadeInicial() {
        VagaVip vagaVip = new VagaVip("1");

        // Verifica se a vaga está ocupada inicialmente
        assertFalse(vagaVip.isOcupada(), "A VagaVip deve estar disponível inicialmente.");
    }

    @Test
    public void testeGetNumeroVaga() {
        VagaVip vagaVip = new VagaVip("1");

        // Verifica se o número da vaga está correto
        assertEquals("1", vagaVip.getNumeroVaga(), "O número da vaga deve ser '1'.");
    }
}
