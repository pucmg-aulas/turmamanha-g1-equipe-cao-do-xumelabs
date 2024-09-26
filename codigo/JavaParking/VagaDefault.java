package JavaParking;

public class VagaDefault extends Vaga {

	private static final double ajuste;
	
	static {
		ajuste = 1;
	}
	
	public VagaDefault(String numeroVaga) {
		super(numeroVaga);
		super.setStatus(false);
	}

}

