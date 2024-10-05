package JavaParking;

import org.junit.*;

public class ClienteTest {
    //Testa se o Cliente está sendo referido como anônimo
    @Test
    public void testAnonimo(){
        Cliente c1 = new Cliente();
        Assert.assertEquals("Anonimo", c1.getNome());
    }
    //Testa se um veículo é cadastrado corretamente
    
    @Test
    public void testCadastrarVeiculo() {
        Cliente c1 = new Cliente("João");
        Veiculo v1 = new Veiculo("ABC123");
        Estacionamento estacionamento = new Estacionamento(1);

        c1.cadastrarVeiculo(v1, estacionamento);

        Assert.assertEquals(1, c1.getPlacaDeVeiculos().size()); //Verifica se há um veiculo
        Assert.assertEquals("ABC123", c1.getPlacaDeVeiculos().get(0)); //Verifica se a placa é a mesma
    }

}