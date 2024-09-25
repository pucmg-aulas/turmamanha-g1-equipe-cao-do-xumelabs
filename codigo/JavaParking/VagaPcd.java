package JavaParking;

public class VagaPcd extends Vaga{
	
	private static final double ajuste;
	
	static {
		ajuste = 0.87;
	}
	
	public VagaPcd(String numeroVaga) {
		super(numeroVaga);
		super.setStatus(false);
	}

	public static double getAjuste() {
		return ajuste;
	}
}
