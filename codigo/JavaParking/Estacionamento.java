package JavaParking;

public class Estacionamento {
private Vaga vaga[];
private int numeroDeVagas;
private UsoDeVaga usoDeVaga;
private double valorCobranca;
private static int vagasCadastradas;

static {
	vagasCadastradas = 0;
}

public Estacionamento(int numeroDeVagas) {
	this.numeroDeVagas = numeroDeVagas;
	this.vaga = new Vaga[this.numeroDeVagas];
}

private double calcularCobranca() {
	this.valorCobranca = (this.usoDeVaga.getTempo()/15) * 4;
	if(this.valorCobranca >= 50) {
		this.valorCobranca = 50.0;
	}
	return this.valorCobranca;
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
