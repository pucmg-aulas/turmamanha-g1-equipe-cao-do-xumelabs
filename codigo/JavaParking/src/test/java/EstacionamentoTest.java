import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import mvc.model.*;

public class EstacionamentoTest {

    private Estacionamento estacionamento;
    private Cliente cliente;
    private UsoDeVaga usoDeVaga;
    private Vaga vaga;
    private Veiculo veiculo;

    @BeforeEach
    public void setUp() {
        estacionamento = new Estacionamento(4, "Estacionamento Central");
        
        cliente = new Cliente("Carlos", "123.456.789-00");
        veiculo = new Veiculo("ABC1234");
        cliente.cadastrarVeiculo(veiculo);
        
        vaga = estacionamento.ListaDeVagas().get(0); // Primeira vaga disponível
        usoDeVaga = new UsoDeVaga(veiculo, vaga);
    }

    @Test
    public void testEstacionar() {
        // Estacionando o veículo
        boolean ocupacao = usoDeVaga.ocuparVaga();
        estacionamento.estacionar(usoDeVaga);
        
        // Verificando se a vaga foi ocupada
        assertTrue(ocupacao, "A vaga deveria estar ocupada.");
        assertTrue(vaga.isOcupada(), "A vaga não foi marcada como ocupada.");
        
        // Verificando se o uso de vaga foi adicionado corretamente
        assertEquals(1, estacionamento.ListaDeUsoDeVagas().size(), "Deveria haver um uso de vaga registrado.");
    }

    @Test
    public void testSairDaVaga() {
        // Estacionando o veículo
        usoDeVaga.ocuparVaga();
        estacionamento.estacionar(usoDeVaga);
        
        // Saindo da vaga
        double valorCobrado = estacionamento.sairDaVaga(usoDeVaga);
        
        // Verificando se a vaga foi desocupada
        assertTrue(usoDeVaga.getStatus(), "O status da vaga deveria ser 'desocupada'.");
        assertFalse(vaga.isOcupada(), "A vaga não foi desocupada corretamente.");
        
        // Verificando se o valor da cobrança é maior que zero
        assertTrue(valorCobrado > 0, "O valor cobrado deve ser maior que zero.");
    }
}
