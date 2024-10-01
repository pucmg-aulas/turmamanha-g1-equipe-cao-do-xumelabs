package JavaParking;

public class VagaIdoso extends Vaga {
	private static final double desconto;
	
	static {
		desconto = 0.85;
	}
	
	public VagaIdoso(String numeroVaga) {
		super(numeroVaga);
		super.alterarDisponibilidade(false);
	}

	@Override
	public double calcularAjuste(){
		return desconto;
	}
}
