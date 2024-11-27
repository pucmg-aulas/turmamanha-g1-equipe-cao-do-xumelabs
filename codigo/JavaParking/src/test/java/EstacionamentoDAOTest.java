import mvc.dao.*;
import mvc.bancoDados.BancoDados;
import mvc.model.Estacionamento;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EstacionamentoDAOTest {

    private static EstacionamentoDAO estacionamentoDAO;

    @BeforeAll
    static void setup() {
        // Obter a instância do DAO
        estacionamentoDAO = EstacionamentoDAO.getInstance();
    }

    @Test
    void testListaDeEstacionamentos() {
        // Act
        List<Estacionamento> estacionamentos = estacionamentoDAO.listaDeEstacionamentos();

        // Assert
        assertNotNull(estacionamentos, "A lista de estacionamentos não deve ser nula.");
        assertEquals(3, estacionamentos.size(), "A lista de estacionamentos deve conter exatamente 3 elementos.");
        
        // Verificar os estacionamentos específicos
        assertTrue(estacionamentos.stream().anyMatch(e -> e.getNomeEstacionamento().equals("GuardaCarros") && e.getNumeroDeVagas() == 30));
        assertTrue(estacionamentos.stream().anyMatch(e -> e.getNomeEstacionamento().equals("NorteMinas") && e.getNumeroDeVagas() == 25));
        assertTrue(estacionamentos.stream().anyMatch(e -> e.getNomeEstacionamento().equals("XumeLabs") && e.getNumeroDeVagas() == 20));
    }

    @Test
    void testPesquisarEstacionamentoInexistente() {
        // Act
        List<Estacionamento> estacionamentos = estacionamentoDAO.listaDeEstacionamentos();

        // Assert
        assertFalse(estacionamentos.stream().anyMatch(e -> e.getNomeEstacionamento().equals("EstacionamentoInexistente")),
                "Não deve haver nenhum estacionamento chamado 'EstacionamentoInexistente'.");
    }
}
