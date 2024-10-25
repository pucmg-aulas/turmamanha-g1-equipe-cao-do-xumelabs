/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
import mvc.model.Cliente;
import mvc.model.Estacionamento;
import mvc.model.Veiculo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClienteTest {
    // Testa se o Cliente está sendo referido como anônimo
    @Test
    public void testAnonimo() {
        Cliente c1 = new Cliente();
        assertEquals("Anonimo", c1.getNome());
    }

    // Testa se um veículo é cadastrado corretamente
    @Test
    public void testCadastrarVeiculo() {
        Cliente c1 = new Cliente("João");
        Veiculo v1 = new Veiculo("ABC123", 2);
        Estacionamento estacionamento = new Estacionamento(1);

        c1.cadastrarVeiculo(v1, estacionamento);

        assertEquals(1, c1.getPlacaDeVeiculos().size()); // Verifica se há um veículo
        assertEquals("ABC123", c1.getPlacaDeVeiculos().get(0)); // Verifica se a placa é a mesma
    }
}

