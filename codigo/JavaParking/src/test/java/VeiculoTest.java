/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import mvc.model.Veiculo;

public class VeiculoTest {

    @Test
    public void testeGetPlaca() {
        Veiculo veiculo = new Veiculo("ABC1234", 2);
        // Verifica se a placa do veículo está correta
        assertEquals("ABC1234", veiculo.getPlaca(), "A placa do veículo deve ser 'ABC1234'.");
    }

    @Test
    public void testeRegistrarNovoVeiculo() {
        String placa = "XYZ5678";
        Veiculo veiculo = new Veiculo(placa, 2);

        // Verifica se o veículo foi registrado no arquivo
        String nomeArquivo = "veiculo.txt";
        String linhaRegistrada = "veículo placa " + placa;

        // Lê o arquivo e procura a linha registrada
        boolean encontrado = false;

        try (BufferedReader leitor = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                if (linha.contains(linhaRegistrada)) {
                    encontrado = true;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertTrue(encontrado, "O veículo com a placa deve estar registrado no arquivo.");
    }
}