/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javaparking;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        Estacionamento e1 = new Estacionamento(10);
        int opcao = 0;

        do {
            System.out.println(Menu());
            opcao = teclado.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("Digite o numero alfanumerico da Vaga: ");
                    if (teclado.hasNextLine()) {
                        teclado.nextLine();  // Limpa a quebra de linha pendente
                    }
                    String numeroVaga = teclado.nextLine();  // Agora lê corretamente a string
                    cadastrarVaga(teclado, e1, numeroVaga);
                    break;

                case 2:
                    cadastrarCliente(teclado, e1);
                    break;

                case 3:
                    cadastarVeiculo(teclado, e1);
                    break;

                case 4:
                    estacionarCarro(teclado, e1);
                    break;

                case 5:
                    desocuparVaga(teclado, e1);
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

    private static void cadastrarVaga(Scanner teclado, Estacionamento estacionamento, String numeroVaga) {

        System.out.println(MenuDeTipoDeVaga());
        int tipoDeVaga = teclado.nextInt();  // lê o número do tipo de vaga
        teclado.nextLine();  // Consome a quebra de linha pendente do nextInt()

        switch (tipoDeVaga) {
            case 1:
                VagaIdoso vagaIdoso = new VagaIdoso(numeroVaga);
                if (estacionamento.cadastrarVaga(vagaIdoso)) {
                    System.out.println("Cadastrada com sucesso\n");
                    estacionamento.registrarNovaVagaIdoso(vagaIdoso);
                } else {
                    System.out.println("Nome de Vaga ja existente\n");
                }
                break;

            case 2:
                VagaPcd vagaPcd = new VagaPcd(numeroVaga);
                if (estacionamento.cadastrarVaga(vagaPcd)) {
                    System.out.println("Cadastrada com sucesso\n");
                    estacionamento.registrarNovaVagaPcd(vagaPcd);
                } else {
                    System.out.println("Nome de Vaga ja existente\n");
                }
                break;

            case 3:
                VagaVip vagaVip = new VagaVip(numeroVaga);
                if (estacionamento.cadastrarVaga(vagaVip)) {
                    System.out.println("Cadastrada com sucesso\n");
                    estacionamento.registrarNovaVagaVIp(vagaVip);
                } else {
                    System.out.println("Nome de Vaga ja existente\n");
                }
                break;

            case 4:
                VagaDefault vagaDefault = new VagaDefault(numeroVaga);
                if (estacionamento.cadastrarVaga(vagaDefault)) {
                    System.out.println("Cadastrada com sucesso\n");
                    estacionamento.registrarNovaVagaDefault(vagaDefault);
                } else {
                    System.out.println("Nome de Vaga ja existente\n");
                }
                break;

            default:
                System.out.println("Opcao inválida");
                break;
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

        System.out.println("Digite a placa do carro: ");
        if (teclado.hasNext()) {
            teclado.nextLine();
        }
        String placa = teclado.nextLine();

        //percorre o array de clientes
        for (Cliente cliente : estacionamento.ListaDeClientes()) {
            //verifica se a placa digitada existe
            if (cliente.acharCarro(placa).getPlaca().equals(placa)) {
                carroUsado.setPlaca(placa);
            }
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
                + "Digite a opção desejada: ";

    }
}