package JavaParking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.*;

public class UsoDeVagaTest {

    @Test
    public void testeOcuparVaga() {
        Vaga vaga = new VagaDefault("1"); // Substitua pela instância adequada
        Veiculo veiculo = new Veiculo("ABC1234");
        UsoDeVaga usoDeVaga = new UsoDeVaga(veiculo, vaga);
        
        // Verifica se a vaga foi ocupada
        assertEquals(true, vaga.isOcupada(), "A vaga deve estar ocupada após ser utilizada.");
    }

    @Test
    public void testeDesocuparVaga() {
        Vaga vaga = new VagaDefault("1"); // Substitua pela instância adequada
        Veiculo veiculo = new Veiculo("ABC1234");
        UsoDeVaga usoDeVaga = new UsoDeVaga(veiculo, vaga);
        
        // Ocupar a vaga antes de desocupar
        usoDeVaga.desocuparVaga(); // Supondo que a vaga já foi ocupada anteriormente
        
        // Verifica se a vaga foi desocupada
        assertEquals(false, vaga.isOcupada(), "A vaga deve estar livre após a desocupação.");
    }
}
