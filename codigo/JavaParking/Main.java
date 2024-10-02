package JavaParking;

import java.util.Scanner;

public class Main {

	public String quantidadeDeVagas() {
		return "=============================================\n" +
			   "  Bem-vindo ao JavaParking System!\n" +
			   "=============================================\n\n" +
			   "Por favor, informe o número de vagas disponíveis\n" +
			   "para o seu estacionamento: ";
	}

	public String numeroVaga(){
		return "Digite o numero alfanumerico da Vaga: ";
	}

	public String tipoDeVaga() {
		return "=============================================\n" +
			   "  Selecione o Tipo de Vaga\n" +
			   "=============================================\n\n" +
			   "1. Vaga para Idoso\n" +
			   "2. Vaga para PCD (Pessoa com Deficiência)\n" +
			   "3. Vaga VIP\n" +
			   "4. Vaga Normal\n\n" +
			   "Digite a opção desejada: ";
	}	

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
		
        Main main = new Main();
        Scanner teclado = new Scanner(System.in);

		System.out.println(main.quantidadeDeVagas());
		int quantidadeDeVagas = teclado.nextInt();

		Estacionamento E1 = new Estacionamento(quantidadeDeVagas);
        System.out.println(main.Menu());

        int i = teclado.nextInt();
        teclado.close();

		switch (i) {
			case 1:
				System.out.println(main.numeroVaga());
				String numeroVaga = teclado.nextLine();
				System.out.println(main.tipoDeVaga());
				int tipoDeVaga = teclado.nextInt();
				
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				break;
			default:
				break;
		}

    }
}

/*Veiculo v1 = new Veiculo("12321");
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
		 */