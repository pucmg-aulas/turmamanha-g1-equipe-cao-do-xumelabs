package JavaParking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.junit.jupiter.api.Test;

public class VeiculoTest {

    @Test
    public void testeGetPlaca() {
        Veiculo veiculo = new Veiculo("ABC1234");
        // Verifica se a placa do veículo está correta
        assertEquals("ABC1234", veiculo.getPlaca(), "A placa do veículo deve ser 'ABC1234'.");
    }

    @Test
    public void testeRegistrarNovoVeiculo() {
        String placa = "XYZ5678";
        Veiculo veiculo = new Veiculo(placa);

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
