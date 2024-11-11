import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import mvc.model.*;
import mvc.dao.*;

import java.time.LocalDate;
import java.time.YearMonth;

public class UsoDeVagaDAOTest {

    private UsoDeVagaDAO usoDeVagaDAO;
    private Estacionamento estacionamento;
    private Cliente cliente;
    private Veiculo veiculo;
    private UsoDeVaga usoDeVaga1, usoDeVaga2;

    @BeforeEach
    public void setUp() {
        // Inicializa o UsoDeVagaDAO
        usoDeVagaDAO = UsoDeVagaDAO.getInstance();
        
        // Cria o estacionamento, cliente e veículos para testes
        estacionamento = new Estacionamento(10, "Estacionamento A"); 
        cliente = new Cliente("Carlos Silva", "123.456.789-00");
        veiculo = new Veiculo("ABC-1234"); 
        cliente.cadastrarVeiculo(veiculo); 
        estacionamento.addCiente(cliente);
        
        // Cria os usos de vaga para testar
        usoDeVaga1 = new UsoDeVaga(veiculo, estacionamento.ListaDeVagas().get(0));
        usoDeVaga2 = new UsoDeVaga(veiculo, estacionamento.ListaDeVagas().get(1));
        
        // Estacionar os veículos
        estacionamento.estacionar(usoDeVaga1);
        estacionamento.estacionar(usoDeVaga2);
        
        // Chama o método sair da vaga para definir a hora de saída
        estacionamento.sairDaVaga(usoDeVaga1);
        estacionamento.sairDaVaga(usoDeVaga2);
        
        // Cria os usos de vaga no DAO
        usoDeVagaDAO.criarUsoDeVaga(usoDeVaga1);
        usoDeVagaDAO.criarUsoDeVaga(usoDeVaga2);
    }

    @Test
    public void testCalcularValorTotalArrecadado() {
        // Chama o método para calcular o valor total arrecadado
        double valorTotalArrecadado = usoDeVagaDAO.calcularValorTotalArrecadado();
        
        // Verifica se o valor arrecadado é maior ou igual a zero
        assertTrue(valorTotalArrecadado >= 0, "Valor total arrecadado não pode ser negativo.");
    }

    @Test
    public void testCalcularValorArrecadadoNoMes() {
        // Define o mês de referência
        YearMonth mesReferencia = YearMonth.now();
        
        // Chama o método para calcular o valor arrecadado no mês
        double valorArrecadadoNoMes = usoDeVagaDAO.calcularValorArrecadadoNoMes(mesReferencia);
        
        // Verifica se o valor arrecadado no mês é maior ou igual a zero
        assertTrue(valorArrecadadoNoMes >= 0, "Valor arrecadado no mês não pode ser negativo.");
    }
}
