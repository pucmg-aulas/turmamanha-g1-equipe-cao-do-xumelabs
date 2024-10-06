/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import com.mycompany.javaparking.Cliente;
import com.mycompany.javaparking.Estacionamento;
import com.mycompany.javaparking.Vaga;
import com.mycompany.javaparking.VagaDefault;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EstacionamentoTest {

    @Test
    public void testCadastrarVaga() {
        // Criando um estacionamento com 5 vagas
        Estacionamento estacionamento = new Estacionamento(5);

        // Instanciando uma vaga do tipo VagaDefault
        Vaga vagaDefault = new VagaDefault("A1");

        // Verificando se a vaga foi cadastrada com sucesso
        boolean resultado = estacionamento.cadastrarVaga(vagaDefault);
        assertTrue(resultado); // Remova `Assert.` se estiver usando a importação estática
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
        assertEquals(3, estacionamento.ListaDeClientes().get(0).getIdentificador());
    }
}
