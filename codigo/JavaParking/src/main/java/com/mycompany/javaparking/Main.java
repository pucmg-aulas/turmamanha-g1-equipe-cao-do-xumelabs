/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javaparking;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        ArrayList<Estacionamento> estacionamento = new ArrayList<>();
        int opcao = 0, op = 0;

        System.out.println("====================================");
        System.out.println(" Bem-vindo ao Sistema de Estacionamento ");
        System.out.println("====================================\n");

        System.out.print("Digite a quantidade de vagas para cada estacionamento: ");
        int quantidadeDeVagas = teclado.nextInt();

        do {
            menuInicial();
            op = teclado.nextInt();

            if (op == 1) {
                Estacionamento e = new Estacionamento(quantidadeDeVagas);
                estacionamento.add(e);
                System.out.println("\n====================================");
                System.out.println(" Estacionamento cadastrado com sucesso! ");
                System.out.println("====================================\n");
            } else if (op != 2) {
                System.out.println("\n====================================");
                System.out.println(" Opção inválida, tente novamente.");
                System.out.println("====================================\n");
            }

        } while (op != 2);

        System.out.println("\n====================================");
        System.out.println(" Escolha o estacionamento para usar ");
        System.out.println("====================================\n");

        for (int i = 0; i < estacionamento.size(); i++) {
            System.out.println((i + 1) + " - Estacionamento " + (i + 1));
        }

        System.out.print("\nDigite o número do estacionamento que deseja usar: ");
        int estacionamentoDesejado = teclado.nextInt();

        System.out.println("\n====================================");
        System.out.println(" Você escolheu o Estacionamento " + estacionamentoDesejado);
        System.out.println("====================================\n");

        do {
            System.out.println(Menu());
            opcao = teclado.nextInt();

            switch (opcao) {
                case 1:
                    cadastrarVaga(teclado, estacionamento.get(estacionamentoDesejado - 1));
                    break;

                case 2:
                    cadastrarCliente(teclado, estacionamento.get(estacionamentoDesejado - 1));
                    break;

                case 3:
                    cadastarVeiculo(teclado, estacionamento.get(estacionamentoDesejado - 1));
                    break;

                case 4:
                    estacionarCarro(teclado, estacionamento.get(estacionamentoDesejado - 1));
                    break;

                case 5:
                    desocuparVaga(teclado, estacionamento.get(estacionamentoDesejado - 1));
                    break;

                case 6:
                    System.out.println("Sistema encerrado com sucesso.");
                    System.exit(0);
                    break;

                default:
                    break;
            }
        } while (opcao != 6);

    }

    // Método para exibir o menu
    private static String Menu() {
        return """
				===============================
					JAVA PARKING SYSTEM
				===============================
				1. Cadastrar Vaga
				2. Cadastrar Cliente
				3. Cadastrar Veículo
				4. Estacionar Carro
				5. Desocupar Vaga
				6. Sair
				===============================
				Digite a opção desejada: """;
    }

    private static void cadastrarVaga(Scanner teclado, Estacionamento estacionamento) {
        for(Vaga v : estacionamento.ListaDeVagas()){
            if(v instanceof VagaIdoso){
                System.out.println(v.getNumeroVaga() + " Vaga Idoso");
            }else if(v instanceof VagaPcd){
                System.out.println(v.getNumeroVaga() + " Vaga Pcd");
            }else if(v instanceof VagaVip){
                System.out.println(v.getNumeroVaga() + " Vaga Vip");
            }else{
                System.out.println(v.getNumeroVaga() + " Vaga default");
            }
        }        
    }

    private static void cadastrarCliente(Scanner teclado, Estacionamento estacionamento) {
        System.out.println("======= Cadastrar Cliente =======\n");
        System.out.println("Você deseja salvar o seu nome?");
        System.out.println("1. Sim");
        System.out.println("2. Não");
        System.out.print("\nEscolha uma opção: ");
        int i = teclado.nextInt();

        if (i == 1) {
            if (teclado.hasNextLine()) {
                teclado.nextLine();  // Limpa a quebra de linha pendente
        }
            System.out.println("Digite o nome: ");
            String nome = teclado.nextLine();
            Cliente cliente = new Cliente(nome);
            estacionamento.cadastrarCliente(cliente);
            System.out.println("Cliente cadastrado com Sucesso");
        } else {
            Cliente cliente = new Cliente();
            estacionamento.cadastrarCliente(cliente);
            System.out.println("Cliente cadastrado com Sucesso");
        }

    }

    private static void cadastarVeiculo(Scanner teclado, Estacionamento estacionamento) {
        System.out.println("======= Cadastrar Veículo =======\n");
        System.out.println("Informe o identificador do Dono");
        if (teclado.hasNextLine()) {
            teclado.nextLine();  // Limpa a quebra de linha pendente
        }
        int identificador = teclado.nextInt();
        teclado.nextLine();
        boolean clienteEncontrado = false;
        for (Cliente cliente : estacionamento.ListaDeClientes()) {
            if (identificador == cliente.getIdentificador()) {
                clienteEncontrado = true;
                System.out.println("Informe a placa do veículo");
                String placa = teclado.nextLine();
                Veiculo veiculo = new Veiculo(placa);
                cliente.cadastrarVeiculo(veiculo, estacionamento);
                System.out.println("Veículo cadastrado com sucesso");
            }
        }
        if (!clienteEncontrado) {
            {
                System.out.println("Cliente não encontrado");
            }
        }
    }

    private static void estacionarCarro(Scanner teclado, Estacionamento estacionamento) {
        Veiculo carroUsado = new Veiculo("123");
        String numeroVagaOcupada;
        int id;
        Cliente clienteEstacionado = new Cliente();
        boolean achou = false;

        System.out.println("Digite o ID do cliente: ");
        id = teclado.nextInt();

        System.out.println("Digite a placa do carro: ");
        if (teclado.hasNext()) {
            teclado.nextLine();
        }
        String placa = teclado.nextLine();

        //percorre o array de clientes
        for (Cliente cliente : estacionamento.ListaDeClientes()) {
            if(cliente.getIdentificador() == id){
                clienteEstacionado = cliente;
                achou = true;
            }
        }

        if(!achou){
            System.out.println("Nenhum cliente encontrado");
            System.exit(0);
        }
            //verifica se a placa digitada existe
        if (clienteEstacionado.acharCarro(placa).getPlaca().equals(placa)) {
            carroUsado.setPlaca(placa);
        }

        System.out.println("Digite a vaga ocupada: ");
        numeroVagaOcupada = teclado.nextLine();
        for (Vaga vaga : estacionamento.ListaDeVagas()) {
            if (vaga.getNumeroVaga().equals(numeroVagaOcupada)) {
                UsoDeVaga usoDeVaga = new UsoDeVaga(carroUsado, vaga);
                System.out.println("Carro estacionado com sucesso");
                estacionamento.estacionar(usoDeVaga);
            }
        }

    }

    private static void desocuparVaga(Scanner teclado, Estacionamento estacionamento) {
        System.out.println("Digite a placa do carro: ");
        if (teclado.hasNext()) {
            teclado.nextLine();
        }
        String placa = teclado.nextLine();
        boolean usoDeVagaEncontrado = false;

        for (UsoDeVaga usoDeVaga : estacionamento.ListaDeUsoDeVagas()) {
            if (usoDeVaga.getVeiculo().getPlaca().equals(placa)) {
                estacionamento.sairDaVaga(usoDeVaga);
                System.out.println("Valor Final: " + usoDeVaga.calcularCobranca());
                System.out.println("Vaga desocupada");
                usoDeVagaEncontrado = true;
                break;
            }
        }
        if (!usoDeVagaEncontrado) {
            System.out.println("Uso de vaga não encontrado");
        }

    }

    private static String MenuDeTipoDeVaga() {
        return "=============================================\n"
                + "  Selecione o Tipo de Vaga\n"
                + "=============================================\n\n"
                + "1. Vaga para Idoso\n"
                + "2. Vaga para PCD (Pessoa com Deficiência)\n"
                + "3. Vaga VIP\n"
                + "4. Vaga Normal\n\n"
                + "Digite a opção desejada:dshkjfhsd"
                + "fsd"
                + "fs"
                + "fsdfs  ";
    }

    private static void menuInicial(){
        System.out.println("====================================");
        System.out.println(" Deseja cadastrar um estacionamento?");
        System.out.println("====================================");
        System.out.println(" 1 - Sim");
        System.out.println(" 2 - Não");
        System.out.print("Digite a opção desejada: ");
    }
}