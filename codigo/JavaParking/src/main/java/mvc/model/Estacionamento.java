/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Estacionamento implements Serializable {
    private static final long serialVersionUID = 2L; // Adicionando serialVersionUID

    private List<Vaga> vagas;
    private String nome;
    private int numeroDeVagas;

    // Construtor para estacionamento 
    public Estacionamento(int numeroDeVagas, String nome) {
        this.nome = nome;
        this.numeroDeVagas = numeroDeVagas;
        this.vagas = new ArrayList<>(numeroDeVagas);
        cadastrarVagas();
    }

    private void cadastrarVagas() {
        String numeroVaga;
        int vagasPorTipo = this.numeroDeVagas / 4;  // Número de vagas para cada tipo
        int contadorIdoso = 0, contadorPcd = 0, contadorVip = 0;

        for (int i = 0; i < this.numeroDeVagas; i++) {
            numeroVaga = String.format("Y%02d", i + 1);

            if (contadorIdoso < vagasPorTipo) {
                this.vagas.add(new VagaIdoso(numeroVaga));
                contadorIdoso++;
            } else if (contadorPcd < vagasPorTipo) {
                this.vagas.add(new VagaPcd(numeroVaga));
                contadorPcd++;
            } else if (contadorVip < vagasPorTipo) {
                this.vagas.add(new VagaVip(numeroVaga));
                contadorVip++;
            } else {
                this.vagas.add(new VagaDefault(numeroVaga));
            }
        }
    }

    public void setNumeroDeVagas(int numero){
        this.numeroDeVagas = numero;
    }

    public void setNomeEstacionamento(String nome){
        this.nome = nome;
    }

    public List<Vaga> ListaDeVagas() {
        return this.vagas;
    }

    public String getNomeEstacionamento(){
        return this.nome;
    }

    public int getNumeroDeVagas(){
        return this.numeroDeVagas;
    }

}