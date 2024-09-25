package JavaParking;

public class Main{
	public static void main(String args[]) {
		Veiculo v1 = new Veiculo("q1231");
		Vaga v2 = new Vaga("12das");
		UsoDeVaga v3 = new UsoDeVaga(v1, v2, 6, 6, 20, 30);
		
		Veiculo v5 = new Veiculo("q1231");
		UsoDeVaga v4 = new UsoDeVaga(v5, v2, 6, 6, 20, 30);
		
		System.out.println(v3.ocuparVaga());
		System.out.println(v3.desocuparVaga());
		System.out.println(v4.ocuparVaga());
		
		
	}
}