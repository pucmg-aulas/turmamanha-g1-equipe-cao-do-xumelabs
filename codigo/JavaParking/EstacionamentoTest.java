package JavaParking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.*;

public class EstacionamentoTest {
    @Test
    public void testCadastrarVaga() {
        // Criando um estacionamento com 5 vagas
        Estacionamento estacionamento = new Estacionamento(5);

        // Instanciando uma vaga do tipo VagaDefault
        Vaga vagaDefault = new VagaDefault("A1");

        // Verificando se a vaga foi cadastrada com sucesso
        boolean resultado = estacionamento.cadastrarVaga(vagaDefault);
        Assert.assertTrue(resultado);
    }

    @Test
    public void testeCadastrarCliente() {
        // Cria um estacionamento com 10 vagas
        Estacionamento estacionamento = new Estacionamento(10);
        // Cria um cliente
        Cliente cliente = new Cliente("João");
        //Cliente cliente1 = new Cliente("Pedro");
        
        // Cadastrar o cliente no estacionamento
        estacionamento.cadastrarCliente(cliente);
        //estacionamento.cadastrarCliente(cliente1);
        
        // Verificar se o cliente foi adicionado na lista
        assertEquals(1, estacionamento.ListaDeClientes().size());
        assertEquals("João", estacionamento.ListaDeClientes().get(0).getNome());
        assertEquals(1, estacionamento.ListaDeClientes().get(0).getIdentificador());
    }
}