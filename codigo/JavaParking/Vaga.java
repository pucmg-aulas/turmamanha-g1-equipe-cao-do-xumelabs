package JavaParking;

public abstract class Vaga {
private String numeroVaga;
private boolean disponivel;

public Vaga(String numeroVaga) {
	this.numeroVaga = numeroVaga;
	this.disponivel = false;
}

public String getNumeroVaga(){
	return this.numeroVaga;
}

public boolean isOcupada() {
	return this.disponivel;
}

public void alterarDisponibilidade(boolean disponivel) {
	this.disponivel = disponivel;
}

public abstract double calcularAjuste();

}
