package JavaParking;

public class VagaVip extends Vaga {

	private static final double ajuste;
	
	static {
		ajuste = 1.2;
	}
	
	public VagaVip(String numeroVaga) {
		super(numeroVaga);
		super.setStatus(false);
	}

}
