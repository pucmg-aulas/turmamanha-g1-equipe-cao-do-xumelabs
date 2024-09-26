package JavaParking;

public class Main{
	public static void main(String args[]) {
		Veiculo v1 = new Veiculo("q1231");
		Vaga v5 = new Vaga("1232");

		System.out.println(v5.getStatus());
		
		UsoDeVaga v3 = new UsoDeVaga(v1, v5);
	
		System.out.println(v3.desocuparVaga());

		double valor = v3.calcularCobranca();
		System.out.println(valor);
	}
}