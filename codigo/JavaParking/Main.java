package JavaParking;

import java.util.Scanner;

public class Main {

    // Método para exibir o menu
    public String Menu() {
        // Melhorando o estilo da string do menu
        return """
                ===============================
                       JAVA PARKING SYSTEM
                ===============================
                1. Cadastrar Vaga
				2. Cadastrar Cliente
                3. Cadastrar Veículo
                4. Estacionar Carro
				5. Desocupar Vaga
                ===============================
                Digite a opção desejada: 
                """;
    }

    public static void main(String[] args) {
		/* 
        Main main = new Main();
        Scanner teclado = new Scanner(System.in);

        System.out.println(main.Menu());

        int i = teclado.nextInt();
        teclado.close();

        System.out.println("Você escolheu a opção: " + i);
		*/

		Veiculo v1 = new Veiculo("12321");
		Veiculo v2 = new Veiculo("36989");
		VagaIdoso v7 = new VagaIdoso("155");
		Cliente c1 = new Cliente();
		Cliente c2 = new Cliente("pedro");
		c1.cadastrarVeiculo(v1);
		c2.cadastrarVeiculo(v2);

		UsoDeVaga v9 = new UsoDeVaga(v2, v7);

		Estacionamento e1 = new Estacionamento(1);
		e1.cadastrarVaga(v7);
		e1.estacionar(v9);

    }
}
