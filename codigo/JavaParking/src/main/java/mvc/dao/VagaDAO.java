package mvc.dao;
import mvc.model.Vaga;
import mvc.model.VagaDefault;
import mvc.model.VagaIdoso;
import mvc.model.VagaPcd;
import mvc.model.VagaVip;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class VagaDAO {

    private ArrayList<Vaga> vagas;
    private static VagaDAO instance;
    private final String filePathVagas = "vagas.txt";
    
    private VagaDAO(){
        this.vagas = new ArrayList<>();
        carregarVagas();
    }
    
    public static VagaDAO getInstance(){
        if(instance == null)
            instance = new VagaDAO();
        return instance;
    }

    public void cadastrarVaga(Vaga vaga){
        vagas.add(vaga);
        registarTodasVagas();
    }

    public void removerVaga(Vaga vaga){
        vagas.remove(vaga);
        registarTodasVagas();
    }

    public Vaga pesquisarVagaporNumero(String numeroVaga){
        for(Vaga vaga : vagas){
            if(vaga.getNumeroVaga().equals(numeroVaga)){
                return vaga;
            }
        }
        return null;
    }

    public ArrayList<Vaga> vagasCadastradas(){
        return vagas;
    }

    public double valorAjuste(Vaga vaga){
        for(Vaga v : vagas){
            if(v.getNumeroVaga().equals(vaga.getNumeroVaga())){
                return v.calcularAjuste();
            }
        }
        return 1;
    }

    private void registarTodasVagas() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePathVagas))) {
            for (Vaga vaga : vagas) {
                String tipoVaga = "";
                if (vaga instanceof VagaPcd) {
                    tipoVaga = "PCD";
                } else if (vaga instanceof VagaDefault) {
                    tipoVaga = "Default";
                } else if (vaga instanceof VagaVip) {
                    tipoVaga = "VIP";
                } else if (vaga instanceof VagaIdoso) {
                    tipoVaga = "Idoso";
                }
            writer.write(vaga.getNumeroVaga() + ";" + vaga.isOcupada() + ";" + tipoVaga);
            writer.newLine();
            }
        } catch (IOException e) {
        e.printStackTrace();
        }
    }

    private void carregarVagas() {
    File file = new File(filePathVagas);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(filePathVagas))) {
            String line;
                while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                    if (parts.length == 3) {
                        String numero = parts[0];
                        boolean disponivel = Boolean.parseBoolean(parts[1]);
                        String tipoVaga = parts[2];

                        Vaga vaga = null;

                        switch (tipoVaga) {
                            case "PCD":
                                vaga = new VagaPcd(numero);
                                vaga.alterarDisponibilidade(disponivel);
                                break;
                            case "Default":
                                vaga = new VagaDefault(numero);
                                vaga.alterarDisponibilidade(disponivel);
                                break;
                            case "VIP":
                                vaga = new VagaVip(numero);
                                vaga.alterarDisponibilidade(disponivel);
                                break;
                            case "Idoso":
                                vaga = new VagaIdoso(numero);
                                vaga.alterarDisponibilidade(disponivel);
                                break;
                    }   
                    if (vaga != null) {
                        vagas.add(vaga);
                    }
        }
                }
            } catch (IOException e) {
            e.printStackTrace();
            }
        }
    }

    
}
