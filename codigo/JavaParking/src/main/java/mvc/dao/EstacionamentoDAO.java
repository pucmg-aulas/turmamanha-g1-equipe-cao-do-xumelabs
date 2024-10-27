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

import mvc.model.Estacionamento;
import mvc.model.UsoDeVaga;
import mvc.model.Vaga;
import mvc.model.Veiculo;

public class EstacionamentoDAO {

    private ArrayList<Estacionamento> estacionamentos;
    private static EstacionamentoDAO instance;
    
    private EstacionamentoDAO(){
        this.estacionamentos = new ArrayList<>();
    }
    
    public static EstacionamentoDAO getInstance(){
        if(instance == null)
            instance = new EstacionamentoDAO();
        return instance;
    }

    public void adicionarEstacionamento(Estacionamento estacionamento){
        estacionamentos.add(estacionamento);
    }

    public void removerEstacionamento(Estacionamento estacionamento){
        estacionamentos.remove(estacionamento);
    }

    public ArrayList<Estacionamento> listaDeEstacionamentos(){
        return estacionamentos;
    }

    private void registrarTodosEstacionamentosTxt() {
        String filePath = "estacionamentos.txt";
    
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
            for (Estacionamento estacionamento : estacionamentos) {
                StringBuilder linha = new StringBuilder();
                linha.append(estacionamento.getNumeroEstacionamento()).append(";"); 
                
                for (Vaga vaga : estacionamento.ListaDeVagas()) {
                    linha.append(vaga.getNumeroVaga()).append(",").append(vaga.isOcupada()).append(";");
                }
                
                // Armazenando informações dos usos de vagas
                for (UsoDeVaga uso : estacionamento.ListaDeUsoDeVagas()) {
                    linha.append(uso.getVeiculo().getPlaca()).append(",")
                         .append(uso.getVaga().getNumeroVaga()).append(",")
                         .append(uso.getHoraChegada()).append(",")
                         .append(uso.getHoraSaida()).append(",")
                         .append(uso.getData().toString()).append(",")
                         .append(uso.calcularCobranca()).append(";");
                }
    
                if (linha.length() > 0) {
                    linha.setLength(linha.length() - 1); 
                }
    
                writer.write(linha.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao registrar estacionamentos: " + e.getMessage());
        }
    }

    public void carregarEstacionamentosTxt() {
    String filePath = "estacionamentos.txt";

    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
        String linha;
        while ((linha = reader.readLine()) != null) {
            String[] partes = linha.split(";");

            if (partes.length > 0) {
                // O número do estacionamento é o primeiro elemento
                int numeroEstacionamento = Integer.parseInt(partes[0].trim());
                Estacionamento estacionamento = new Estacionamento(numeroEstacionamento);

                // Processar as vagas
                int index = 1;
                while (index < partes.length && partes[index].contains(",")) {
                    String[] vagaInfo = partes[index].split(",");
                    String numeroVaga = vagaInfo[0].trim();
                    boolean ocupada = Boolean.parseBoolean(vagaInfo[1].trim());

                    // Criar e adicionar a vaga ao estacionamento
                    Vaga vaga = new Vaga(numeroVaga);
                    vaga.alterarDisponibilidade(ocupada);
                    estacionamento.adicionarVaga(vaga); // Supondo que você tenha um método para adicionar vagas

                    index++;
                }

                // Processar os usos de vagas
                while (index < partes.length) {
                    String[] usoInfo = partes[index].split(",");
                    if (usoInfo.length == 6) {
                        String placa = usoInfo[0].trim();
                        String numeroVaga = usoInfo[1].trim();
                        String horaChegadaStr = usoInfo[2].trim();
                        String horaSaidaStr = usoInfo[3].trim();
                        String dataStr = usoInfo[4].trim();
                        double valorAPagar = Double.parseDouble(usoInfo[5].trim());

                        // Buscar o Veiculo pela placa
                        Veiculo veiculo = VeiculoDAO.getInstance().pesquisarVeiculoPlaca(placa);
                        
                        // Buscar a Vaga pelo número
                        Vaga vaga = VagaDAO.getInstance().pesquisarVagaporNumero(numeroVaga);

                        if (veiculo != null && vaga != null) {
                            UsoDeVaga uso = new UsoDeVaga(veiculo, vaga);
                            uso.setHoraChegada(LocalDateTime.parse(horaChegadaStr, DateTimeFormatter.ofPattern("HH:mm")));
                            uso.setHoraSaida(LocalDateTime.parse(horaSaidaStr, DateTimeFormatter.ofPattern("HH:mm")));
                            uso.setData(LocalDate.parse(dataStr));
                            uso.setValorAPagar(valorAPagar);

                            estacionamento.estacionar(uso); // Supondo que você tenha um método para adicionar usos de vaga
                        }
                    }
                    index++;
                }

                // Adicionar o estacionamento à lista de estacionamentos
                estacionamentos.add(estacionamento);
            }
        }
    } catch (IOException e) {
        System.err.println("Erro ao carregar estacionamentos: " + e.getMessage());
    }
}

        
}
