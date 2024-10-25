/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import mvc.model.UsoDeVaga;
import mvc.model.Vaga;
import mvc.model.VagaDefault;
import mvc.model.Veiculo;

/**
 *
 * @author Pedro Henrique
 */
public class UsoDeVagaTest {

    @Test
    public void testeOcuparVaga() {
        Vaga vaga = new VagaDefault("1"); // Substitua pela instância adequada
        Veiculo veiculo = new Veiculo("ABC1234", 2);
        UsoDeVaga usoDeVaga = new UsoDeVaga(veiculo, vaga);
        // Verifica se a vaga foi ocupada
        assertEquals(true, vaga.isOcupada(), "A vaga deve estar ocupada após ser utilizada.");
    }

    @Test
    public void testeDesocuparVaga() {
        Vaga vaga = new VagaDefault("1"); // Substitua pela instância adequada
        Veiculo veiculo = new Veiculo("ABC1234", 2);
        UsoDeVaga usoDeVaga = new UsoDeVaga(veiculo, vaga);
        
        // Ocupar a vaga antes de desocupar
        usoDeVaga.desocuparVaga(); // Supondo que a vaga já foi ocupada anteriormente
        
        // Verifica se a vaga foi desocupada
        assertEquals(false, vaga.isOcupada(), "A vaga deve estar livre após a desocupação.");
    }
}
