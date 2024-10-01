package JavaParking;

public class VagaPcd extends Vaga{
	
	private static final double desconto;
	
	static {
		desconto = 0.87;
	}
	
	public VagaPcd(String numeroVaga) {
		super(numeroVaga);
		super.alterarDisponibilidade(false);
	}

	@Override
	public double calcularAjuste(){
		return desconto;
	}
}
