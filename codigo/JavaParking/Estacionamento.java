package JavaParking;

public class Estacionamento {
private Vaga vaga[];
private int numeroDeVagas;
private UsoDeVaga usoDeVaga;
private double valorCobranca;

private void calcularCobranca() {
	this.valorCobranca = (this.usoDeVaga.getTempo()/15) * 4;
	if(this.valorCobranca >= 50) {
		this.valorCobranca = 50.0;
	}
}

public double getValorCobranca() {
	return this.valorCobranca;
}


}
