/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javaparking;

import java.util.ArrayList;
import java.util.List;

public class Cliente {

    private String nome;
    private int identificador;
    private static int proximoid;
    private List<Veiculo> veiculos;

    static {
        proximoid = 1;
    }

    // Construtor do cliente recebendo o nome
    public Cliente(String nome) {
        this();
        this.nome = nome;
    }

    // Construtor caso o cliente deseje ser anônimo
    public Cliente() {
        this.nome = "Anonimo";
        this.identificador = proximoid;
        proximoid++;
        this.veiculos = new ArrayList<>();
    }

    public String getNome() {
        return this.nome;
    }

    public int getIdentificador() {
        return this.identificador;
    }

    // Método para adicionar um novo carro ao cliente
    public void cadastrarVeiculo(Veiculo veiculo, Estacionamento estacionamento) {
        this.veiculos.add(veiculo);
        estacionamento.registarClienteTxt();

    }

    // Método para retornar e imprimir as placas dos veículos do cliente
    public List<String> getPlacaDeVeiculos() {
        List<String> placas = new ArrayList<>(); // Cria uma lista para armazenar as placas

        for (Veiculo veiculo : veiculos) {
            placas.add(veiculo.getPlaca()); // Adiciona cada placa à lista
        }

        return placas; // Retorna a lista de placas
    }

    public Veiculo acharCarro(String placa) {
        for (Veiculo veiculo : veiculos) {
            if (veiculo.getPlaca().equals(placa)) {
                return veiculo; // Retorna o veículo se a placa for encontrada
            }
        }
        return null; // Retorna null se o veículo não for encontrado
    }
}