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
    private List<UsoDeVaga> usoDeVagas;
    private List<Cliente> clientes;

    // Construtor para estacionamento 
    public Estacionamento(int numeroDeVagas, String nome) {
        this.nome = nome;
        this.numeroDeVagas = numeroDeVagas;
        this.vagas = new ArrayList<>(numeroDeVagas);
        cadastrarVagas();
        this.usoDeVagas = new ArrayList<>();
        this.clientes = new ArrayList<>();
    }

    private void cadastrarVagas() {
        String numeroVaga;
        int vagasPorTipo = this.numeroDeVagas / 4;  // Número de vagas para cada tipo
        int contadorIdoso = 0, contadorPcd = 0, contadorVip = 0;

        for (int i = 0; i < this.numeroDeVagas; i++) {
            numeroVaga = String.format("A%02d", i + 1);

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


    public void estacionar(UsoDeVaga usoDeVaga) {
        this.usoDeVagas.add(usoDeVaga);
        usoDeVaga.ocuparVaga();
    }

    public double sairDaVaga(UsoDeVaga usoDeVaga) {
        usoDeVaga.desocuparVaga();
        return usoDeVaga.calcularCobranca();
    }

    public List<UsoDeVaga> ListaDeUsoDeVagas() {
        return this.usoDeVagas;
    }

    public List<Cliente> ListaDeClientes() {
        return this.clientes;
    }

    public List<Vaga> ListaDeVagas() {
        return this.vagas;
    }

    public String getNomeEstacionamento(){
        return this.nome;
    }

}
