package JavaParking;

import java.util.Scanner;

public class Main {	

    public static void main(String[] args) {
		Estacionamento e = new Estacionamento(43);
        Cliente c = new Cliente();
		Veiculo v = new Veiculo("1234");
		c.cadastrarVeiculo(v);
        e.cadastrarCliente(c);
		Cliente c2 = new Cliente("diogo");
		Veiculo v2 = new Veiculo("1548");
		e.cadastrarCliente(c2);
		c2.cadastrarVeiculo(v2);
		Scanner teclado = new Scanner(System.in);
		Estacionamento e1 = new Estacionamento(10);
		int opcao= 0 ;
		
		do{
			System.out.println(Menu());
			opcao = teclado.nextInt();

			switch (opcao) {
				case 1:
					cadastrarVaga(teclado, e1);
					break;
			
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
		}while(opcao != 5);


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
				Digite a opção desejada: 
				""";
	}

	private static void cadastrarVaga(Scanner teclado, Estacionamento estacionamento){
		System.out.println("Digite o numero alfanumerico da Vaga: ");
		String numeroVaga = teclado.nextLine();

		System.out.println(MenuDeTipoDeVaga());
		int tipoDeVaga = teclado.nextInt();

		switch (tipoDeVaga) {
			case 1:
				VagaIdoso vagaIdoso = new VagaIdoso(numeroVaga);
				if(estacionamento.cadastrarVaga(vagaIdoso)){
					System.out.println("Cadastrada com sucesso\n");
				}else{
					System.out.println("Nome de Vaga ja existente\n");
				}
				break;

			case 2:
				VagaPcd vagaPcd = new VagaPcd(numeroVaga);
				if(estacionamento.cadastrarVaga(vagaPcd)){
					System.out.println("Cadastrada com sucesso\n");
				}else{
					System.out.println("Nome de Vaga ja existente\n");
				}
				break;

			case 3:
				VagaVip vagaVip = new VagaVip(numeroVaga);
				if(estacionamento.cadastrarVaga(vagaVip)){
					System.out.println("Cadastrada com sucesso\n");
				}else{
					System.out.println("Nome de Vaga ja existente\n");
				}
				break;

			case 4:
				VagaDefault vagaDefault = new VagaDefault(numeroVaga);
				if(estacionamento.cadastrarVaga(vagaDefault)){
					System.out.println("Cadastrada com sucesso\n");
				}else{
					System.out.println("Nome de Vaga ja existente\n");
				}
				break;
		
			default:
				System.out.println("Opcao inválida");
				break;
		}
	}

	private static String MenuDeTipoDeVaga() {
		return "=============================================\n" +
			   "  Selecione o Tipo de Vaga\n" +
			   "=============================================\n\n" +
			   "1. Vaga para Idoso\n" +
			   "2. Vaga para PCD (Pessoa com Deficiência)\n" +
			   "3. Vaga VIP\n" +
			   "4. Vaga Normal\n\n" +
			   "Digite a opção desejada: ";

	}
}