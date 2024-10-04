package JavaParking;

import java.util.Scanner;

public class Main {	

    public static void main(String[] args) {
		
		Estacionamento e = new Estacionamento(43);
        Cliente c = new Cliente();
		Veiculo v = new Veiculo("1234");
		c.cadastrarVeiculo(v,e);
        e.cadastrarCliente(c);
		Cliente c1 = new Cliente("Diogo");
		Veiculo v2 = new Veiculo("4123");
		c1.cadastrarVeiculo(v2,e);
		e.cadastrarCliente(c1);
		Veiculo v3 = new Veiculo("8123");
		c.cadastrarVeiculo(v3,e);

		Scanner teclado = new Scanner(System.in);
		Estacionamento e1 = new Estacionamento(10);
		int opcao= 0 ;
		
		do{
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
					
					break;

				case 4:
					estacionarCarro(teclado, e1);
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

	private static void cadastrarVaga(Scanner teclado, Estacionamento estacionamento, String numeroVaga){

    System.out.println(MenuDeTipoDeVaga());
    int tipoDeVaga = teclado.nextInt();  // lê o número do tipo de vaga
    teclado.nextLine();  // Consome a quebra de linha pendente do nextInt()

    switch (tipoDeVaga) {
        case 1:
            VagaIdoso vagaIdoso = new VagaIdoso(numeroVaga);
            if(estacionamento.cadastrarVaga(vagaIdoso)){
                System.out.println("Cadastrada com sucesso\n");
				//estacionamento.registrarNovaVagaIdoso();
            } else {
                System.out.println("Nome de Vaga ja existente\n");
            }
            break;

        case 2:
            VagaPcd vagaPcd = new VagaPcd(numeroVaga);
            if(estacionamento.cadastrarVaga(vagaPcd)){
                System.out.println("Cadastrada com sucesso\n");
				//estacionamento.registrarNovaVagaPcd();
            } else {
                System.out.println("Nome de Vaga ja existente\n");
            }
            break;

        case 3:
            VagaVip vagaVip = new VagaVip(numeroVaga);
            if(estacionamento.cadastrarVaga(vagaVip)){
                System.out.println("Cadastrada com sucesso\n");
				//estacionamento.registrarNovaVagaVIp();
            } else {
                System.out.println("Nome de Vaga ja existente\n");
            }
            break;

        case 4:
            VagaDefault vagaDefault = new VagaDefault(numeroVaga);
            if(estacionamento.cadastrarVaga(vagaDefault)){
                System.out.println("Cadastrada com sucesso\n");
				//estacionamento.registrarNovaVagaDefault();
            } else {
                System.out.println("Nome de Vaga ja existente\n");
            }
            break;

        default:
            System.out.println("Opcao inválida");
            break;
    }
}

	private static void cadastrarCliente(Scanner teclado, Estacionamento estacionamento){
	System.out.println("======= Cadastrar Cliente =======\n");
	System.out.println("Você deseja salvar o seu nome?");
	System.out.println("1. Sim");
	System.out.println("2. Não");
	System.out.print("\nEscolha uma opção: ");
	int i = teclado.nextInt();

	if(i == 1){
		if (teclado.hasNextLine()) {
			teclado.nextLine();  // Limpa a quebra de linha pendente
		}
		String nome = teclado.nextLine();
		Cliente cliente = new Cliente(nome);
		estacionamento.cadastrarCliente(cliente);
		System.out.println("Cliente cadastrado com Sucesso");
	}else{
		Cliente cliente = new Cliente();
		estacionamento.cadastrarCliente(cliente);
		System.out.println("Cliente cadastrado com Sucesso");
	}
	
	}

	private static void estacionarCarro(Scanner teclado, Estacionamento estacionamento){
		System.out.println("Digite a placa do carro: ");
		if(teclado.hasNext()){
			teclado.nextLine();
		}
		String placa = teclado.nextLine();
		if(estacionamento.possuiPlaca(placa)){
			UsoDeVaga usoDeVaga = new UsoDeVaga(null, null);
		}else{
			System.out.println("Carro nao encontrado");
		}

		
		estacionamento.estacionar(null);
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