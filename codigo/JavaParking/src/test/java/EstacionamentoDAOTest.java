import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import mvc.model.*;
import mvc.dao.*;

import java.time.LocalDate;

public class EstacionamentoDAOTest {

    private EstacionamentoDAO estacionamentoDAO;
    private Estacionamento estacionamento;
    private Cliente cliente;
    private Veiculo veiculo;
    private UsoDeVaga usoDeVaga;

    @BeforeEach
    public void setUp() {
        // Inicializa o EstacionamentoDAO e o Estacionamento
        estacionamentoDAO = EstacionamentoDAO.getInstance();
        
        // Cria um cliente e um veículo para teste
        cliente = new Cliente("Carlos Silva", "123.456.789-00");
        veiculo = new Veiculo("ABC-1234"); // O veículo recebe apenas a placa
        cliente.cadastrarVeiculo(veiculo); // Adiciona o veículo ao cliente
        estacionamento = new Estacionamento(10, "Estacionamento A");  // Exemplo com 10 vagas
        
        // Cria um uso de vaga para teste
        usoDeVaga = new UsoDeVaga(veiculo, estacionamento.ListaDeVagas().get(0));
        estacionamento.estacionar(usoDeVaga);
    }

    @Test
    public void testPesquisarVaga() {
        // Pesquisa uma vaga no estacionamento pelo número
        Vaga vaga = estacionamentoDAO.pesquisarVaga(estacionamento, "Y01");  // Alterado para o formato correto de número de vaga
        
        // Verifica se a vaga foi encontrada
        assertNotNull(vaga, "Vaga não encontrada.");
        assertEquals("Y01", vaga.getNumeroVaga(), "Número da vaga não corresponde.");
    }

    @Test
    public void testCalcularValorTotalArrecadado() {
        // Faz o uso da vaga
        estacionamento.sairDaVaga(usoDeVaga); 
        
        double valorArrecadado = estacionamentoDAO.calcularValorTotalArrecadado(estacionamento);
        
        assertTrue(valorArrecadado >= 0, "Valor arrecadado não pode ser negativo.");
    }

    @Test
    public void testSairDaVaga() {
        UsoDeVaga usoDeVagaSaida = new UsoDeVaga(veiculo, estacionamento.ListaDeVagas().get(1));
        estacionamento.estacionar(usoDeVagaSaida);

        double valorCobrado = estacionamento.sairDaVaga(usoDeVagaSaida);

        assertTrue(valorCobrado >= 0, "O valor cobrado não pode ser negativo.");
    }
}
