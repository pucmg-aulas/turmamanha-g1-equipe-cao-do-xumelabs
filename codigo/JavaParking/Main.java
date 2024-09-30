package JavaParking;

public class Main{
	public static void main(String args[]) {
		Veiculo v1 = new Veiculo("q1231");
		Vaga v5 = new VagaIdoso("1515");
		
		VagaIdoso v2 = new VagaIdoso("1515");
		VagaVip v3 = new VagaVip("1515");

		UsoDeVaga v9 = new UsoDeVaga(v1, v3);

		v9.desocuparVaga();

		System.out.println(v9.calcularCobranca());
	}
}
