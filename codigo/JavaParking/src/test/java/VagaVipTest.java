/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import com.mycompany.javaparking.VagaVip;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
