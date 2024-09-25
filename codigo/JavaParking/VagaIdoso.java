package JavaParking;

public class VagaIdoso extends Vaga {
	private static final double ajuste;
	
	static {
		ajuste = 0.85;
	}
	
	public VagaIdoso(String numeroVaga) {
		super(numeroVaga);
		super.setStatus(false);
	}

	public static double getAjuste() {
		return ajuste;
	}
}
