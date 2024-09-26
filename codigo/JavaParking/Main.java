package JavaParking;

public class Main{
	public static void main(String args[]) {
		Veiculo v1 = new Veiculo("q1231");
		Vaga v2 = new Vaga("12das");
		UsoDeVaga v3 = new UsoDeVaga(v1, v2);
		
		System.out.println(v3.ocuparVaga());
		System.out.println(v3.desocuparVaga());
		double valor = v3.calcularCobranca();
		System.out.println(valor);
	}
}