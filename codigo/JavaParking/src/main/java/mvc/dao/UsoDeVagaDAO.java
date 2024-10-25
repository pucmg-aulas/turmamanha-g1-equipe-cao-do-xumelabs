package mvc.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import mvc.model.UsoDeVaga;
import mvc.model.Vaga;
import mvc.model.Veiculo;

public class UsoDeVagaDAO {
    private ArrayList<UsoDeVaga> usoDeVagas;
    private static UsoDeVagaDAO instance;
    
    private UsoDeVagaDAO(){
        this.usoDeVagas = new ArrayList<>();
        carregarUsosDeVagaTxt();
    }
    
    public static UsoDeVagaDAO getInstance(){
        if(instance == null)
            instance = new UsoDeVagaDAO();
        return instance;
    }

    public void criarUsoDeVaga(UsoDeVaga usoDeVaga){
        usoDeVagas.add(usoDeVaga);
        registrarTodosUsosDeVagaTxt();
    }

    private void registrarTodosUsosDeVagaTxt() {
    String filePath = "usosDeVaga.txt";
    
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
        for (UsoDeVaga usoDeVaga : usoDeVagas) {
            String linha = usoDeVaga.getVeiculo().getPlaca() + ";" + usoDeVaga.getVaga().getNumeroVaga() + ";" + usoDeVaga.getHoraChegada() + ";"
                          + usoDeVaga.getHoraSaida() + ";" + usoDeVaga.getData() + ";" + usoDeVaga.calcularCobranca();
            writer.write(linha);
            writer.newLine();
        }
    } catch (IOException e) {
        System.err.println("Erro ao registrar usos de vaga: " + e.getMessage());
    }
    }

    public void carregarUsosDeVagaTxt() {
    String filePath = "usosDeVaga.txt";
    
    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
        String linha;
        while ((linha = reader.readLine()) != null) {
            String[] partes = linha.split(";");
            
            if (partes.length == 6) {
                String placa = partes[0].trim();
                String numeroVaga = partes[1].trim();
                String horaChegadaStr = partes[2].trim();
                String horaSaidaStr = partes[3].trim();
                String dataStr = partes[4].trim();
                double valorAPagar = Double.parseDouble(partes[5].trim());

                // Buscar o Veiculo pela placa
                Veiculo veiculo = VeiculoDAO.getInstance().pesquisarVeiculoPlaca(placa);
                
                // Buscar a Vaga pelo número
                Vaga vaga = VagaDAO.getInstance().pesquisarVagaporNumero(numeroVaga);

                if (veiculo != null && vaga != null) {
                    UsoDeVaga uso = new UsoDeVaga(veiculo, vaga);

                    // Definir hora de chegada e saída
                    LocalDateTime horaChegada = LocalDateTime.parse(horaChegadaStr, DateTimeFormatter.ofPattern("HH:mm"));
                    LocalDateTime horaSaida = LocalDateTime.parse(horaSaidaStr, DateTimeFormatter.ofPattern("HH:mm"));
                    LocalDate data = LocalDate.parse(dataStr);

                    uso.setHoraChegada(horaChegada);
                    uso.setHoraSaida(horaSaida);
                    uso.setData(data);
                    uso.setValorAPagar(valorAPagar);

                    usoDeVagas.add(uso);
                }
            }
        }
    } catch (IOException e) {
        System.err.println("Erro ao carregar usos de vaga: " + e.getMessage());
    }
}



}
