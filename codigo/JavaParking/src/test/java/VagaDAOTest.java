import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import mvc.dao.*;
import mvc.model.*;

import java.util.List;

class VagaDAOTest {

    private VagaDAO vagaDAO; 
    private Estacionamento estacionamento;

    @BeforeEach
    void setup() {
        vagaDAO = VagaDAO.getInstance();
        estacionamento = new Estacionamento(20, "XumeLabs");
    }

    @Test
    void testPesquisarVagaPorNumero() {
        String numeroVaga = "Y01";
        Vaga vaga = vagaDAO.pesquisarVagaPorNumero(estacionamento, numeroVaga);

        assertNotNull(vaga, "A vaga não deve ser nula.");
        assertEquals("Y01", vaga.getNumeroVaga(), "O número da vaga deve ser Y01.");
        assertTrue(vaga instanceof VagaIdoso, "A vaga deve ser do tipo VagaIdoso.");
        assertFalse(vaga.isOcupada(), "A vaga não deve estar ocupada.");
    }

    @Test
    void testListaDeVagas() {
        List<Vaga> vagas = vagaDAO.listaDeVagas(estacionamento);

        assertNotNull(vagas, "A lista de vagas não deve ser nula.");
        assertEquals(20, vagas.size(), "A lista de vagas deve conter 20 vagas.");

        // Verificar se a primeira vaga é do tipo correto
        Vaga primeiraVaga = vagas.get(0);
        assertEquals("Y01", primeiraVaga.getNumeroVaga(), "O número da primeira vaga deve ser Y01.");
        assertTrue(primeiraVaga instanceof VagaIdoso, "A primeira vaga deve ser do tipo VagaIdoso.");
        assertFalse(primeiraVaga.isOcupada(), "A primeira vaga não deve estar ocupada.");

        // Verificar se uma vaga VIP está na lista
        boolean encontrouVagaVip = vagas.stream().anyMatch(v -> v instanceof VagaVip && "Y11".equals(v.getNumeroVaga()));
        assertTrue(encontrouVagaVip, "Deve haver uma vaga VIP com número Y11 na lista.");
    }
}
