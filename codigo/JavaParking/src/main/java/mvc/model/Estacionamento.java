/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Estacionamento {

    private List<Vaga> vagas;
    private int numeroDeVagas;
    private List<UsoDeVaga> usoDeVagas;
    private List<Cliente> clientes;

// Construtor para estacionamento 
    public Estacionamento(int numeroDeVagas) {
        this.numeroDeVagas = numeroDeVagas;
        this.vagas = new ArrayList<>(numeroDeVagas);
        cadastrarVagas();
        this.usoDeVagas = new ArrayList<>();
        this.clientes = new ArrayList<>();

    }

// Método para o cadastro de uma nova vaga no estacionamento 

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
            // Adicionar o restante como VagaDefault
            this.vagas.add(new VagaDefault(numeroVaga));
        }
    }
}


// Inícia o uso de vaga 
    public void estacionar(UsoDeVaga usoDeVaga) {
        this.usoDeVagas.add(usoDeVaga);
    }

// Aciona o desocupar vaga e encerra o uso de vaga
// Passando o valor a ser pago pelo uso 
    public double sairDaVaga(UsoDeVaga usoDeVaga) {
        usoDeVaga.desocuparVaga();
        registarUsodeVagaTxt();
        return usoDeVaga.calcularCobranca();
    }

    public void cadastrarCliente(Cliente cliente) {
        this.clientes.add(cliente);
        registarClienteTxt();
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

    public void registarClienteTxt() {
        String nomeAquivo = "Clientes.txt";

        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(nomeAquivo, false))) {
            for (Cliente cliente : clientes) {
                escritor.write("-------------------------------------");
                escritor.newLine();
                escritor.write("Cliente nome " + cliente.getNome() + "\nIdentificador " + cliente.getIdentificador() + " ");
                escritor.newLine();
                for (String placa : cliente.getPlacaDeVeiculos()) {
                    escritor.write("veículo placa " + placa);
                    escritor.newLine();
                }
            }
            escritor.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void registarUsodeVagaTxt() {
        String nomeAquivo = "UsoDeVaga.txt";

        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(nomeAquivo, false))) {
            for (UsoDeVaga usoDeVaga : usoDeVagas) {
                escritor.write("-------------------------------------");
                escritor.newLine();
                escritor.write("Veiculo " + usoDeVaga.getVeiculo().getPlaca() + "\nVaga " + usoDeVaga.getVaga().getNumeroVaga() + " ");
                escritor.newLine();
                escritor.write("Tempo estacionado " + usoDeVaga.calcularTempoUsado() + " minutos");
                escritor.newLine();
                escritor.write("Valor cobrado " + usoDeVaga.calcularCobranca());
                escritor.newLine();
            }
            escritor.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void registrarVagaVIpTxt(Vaga vaga) {
        String nomeAquivo = "Vagas.txt";

        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(nomeAquivo, true))) {
            escritor.write("-------------------------------------");
            escritor.newLine();
            escritor.write("Numero da Vaga " + vaga.getNumeroVaga() + "\nTipo da vaga Vip");
            escritor.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void registrarVagaIdosoTxt(Vaga vaga) {
        String nomeAquivo = "Vagas.txt";

        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(nomeAquivo, true))) {
            escritor.write("-------------------------------------");
            escritor.newLine();
            escritor.write("Numero da Vaga " + vaga.getNumeroVaga() + "\nTipo da vaga Idoso");
            escritor.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void registrarVagaPcdTxt(Vaga vaga) {
        String nomeAquivo = "Vagas.txt";

        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(nomeAquivo, true))) {
            escritor.write("-------------------------------------");
            escritor.newLine();
            escritor.write("Numero da Vaga " + vaga.getNumeroVaga() + "\nTipo da vaga Pcd");
            escritor.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void registrarVagaDefaultTxt(Vaga vaga) {
        String nomeAquivo = "Vagas.txt";

        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(nomeAquivo, true))) {
            escritor.write("-------------------------------------");
            escritor.newLine();
            escritor.write("Numero da Vaga " + vaga.getNumeroVaga() + "\nTipo da vaga Default");
            escritor.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}