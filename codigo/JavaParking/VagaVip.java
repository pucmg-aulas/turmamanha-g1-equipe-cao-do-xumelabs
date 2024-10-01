package JavaParking;

public class VagaVip extends Vaga {

	private static final double taxa;
	
	static {
		taxa = 1.2;
	}
	
	public VagaVip(String numeroVaga) {
		super(numeroVaga);
		super.alterarDisponibilidade(false);
	}

	@Override
	public double calcularAjuste(){
		return taxa;
	}
}
