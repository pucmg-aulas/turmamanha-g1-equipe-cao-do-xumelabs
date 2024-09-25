package JavaParking;

public class Estacionamento {
private Vaga vaga[];
private int numeroDeVagas;
private UsoDeVaga usoDeVaga;
private static int vagasCadastradas;

static {
	vagasCadastradas = 0;
}

public Estacionamento(int numeroDeVagas) {
	this.numeroDeVagas = numeroDeVagas;
	this.vaga = new Vaga[this.numeroDeVagas];
}

private double calcularCobranca() {
	double valorCobranca;
	
	valorCobranca = (this.usoDeVaga.getTempo()/15) * 4;
	if(valorCobranca >= 50) {
		valorCobranca = 50.0;
	}
	return valorCobranca;
}

public double getValorCobranca() {
	return calcularCobranca();
}

public boolean cadastrarVaga(Vaga vaga) {
	if(vagasCadastradas < this.numeroDeVagas) {
		this.vaga[vagasCadastradas] = vaga;
		vagasCadastradas++;
		return true;
	}
	return false;
}

}
