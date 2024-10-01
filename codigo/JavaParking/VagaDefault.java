package JavaParking;

public class VagaDefault extends Vaga {

	private static final double taxa;
	
	static {
		taxa = 1;
	}
	
	public VagaDefault(String numeroVaga) {
		super(numeroVaga);
		super.alterarDisponibilidade(false);
	}


	@Override
	public double calcularAjuste(){
		return taxa;
	}

}

