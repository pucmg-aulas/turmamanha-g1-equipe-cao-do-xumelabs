package mvc.dao;

import java.io.*;
import java.util.ArrayList;
import mvc.model.Veiculo;

public class VeiculoDAO {
    private ArrayList<Veiculo> veiculos;
    private static VeiculoDAO instance;
    private final String filePath = "veiculos.txt"; // Caminho do arquivo onde os veículos serão salvos

    private VeiculoDAO() {
        this.veiculos = new ArrayList<>();
        carregarVeiculos(); // Carregar veículos do arquivo ao iniciar
    }

    public static VeiculoDAO getInstance() {
        if (instance == null) {
            instance = new VeiculoDAO();
        }
        return instance;
    }

    public void cadastrarVeiculo(Veiculo veiculo) {
        veiculos.add(veiculo);
        salvarVeiculos(); // Salvar veículos após cadastro
    }

    public void removerVeiculo(Veiculo veiculo) {
        veiculos.remove(veiculo);
        salvarVeiculos(); // Salvar veículos após remoção
    }

    public Veiculo pesquisarVeiculo(String placa) {
        for (Veiculo veiculo : veiculos) {
            if (veiculo.getPlaca().equals(placa)) {
                return veiculo;
            }
        }
        return null;
    }

    private void salvarVeiculos() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Veiculo veiculo : veiculos) {
                writer.write(veiculo.getPlaca() + ";" + veiculo.getIdCliente());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void carregarVeiculos() {
        File file = new File(filePath);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(";");
                    if (parts.length == 2) {
                        String placa = parts[0];
                        int idCliente = Integer.parseInt(parts[1]);
                        veiculos.add(new Veiculo(placa, idCliente));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
