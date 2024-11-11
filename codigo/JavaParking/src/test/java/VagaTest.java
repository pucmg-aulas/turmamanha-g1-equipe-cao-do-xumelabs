import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import mvc.model.Vaga;

// Classe concreta para teste da classe abstrata Vaga
class VagaTeste extends Vaga {
    public VagaTeste(String numeroVaga) {
        super(numeroVaga);
    }

    @Override
    public double calcularAjuste() {
        return 0; // Implementação simples para possibilitar o teste
    }
}

public class VagaTest {

    private Vaga vaga;

    @BeforeEach
    public void setUp() {
        vaga = new VagaTeste("A01");
    }

    @Test
    public void testGetNumeroVaga() {
        assertEquals("A01", vaga.getNumeroVaga());
    }

    @Test
    public void testAlterarDisponibilidade() {
        vaga.alterarDisponibilidade(true);  
        assertTrue(vaga.isOcupada());

        vaga.alterarDisponibilidade(false); 
        assertFalse(vaga.isOcupada());
    }
}
