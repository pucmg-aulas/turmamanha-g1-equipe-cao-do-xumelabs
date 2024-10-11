/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Veiculo {

    private String placa;

    public Veiculo(String placa) {
        this.placa = placa;
        registarNovoVeiculo();
    }

    public String getPlaca() {
        return this.placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    private void registarNovoVeiculo() {
        String nomeAquivo = "veiculo.txt";

        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(nomeAquivo, true))) {
            escritor.write("-------------------------------------");
            escritor.newLine();
            escritor.write("veículo placa " + this.placa);
            escritor.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
