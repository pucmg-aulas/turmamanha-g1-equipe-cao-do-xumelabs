package JavaParking;

public abstract class Vaga {
private String numeroVaga;
private boolean status;

public Vaga(String numeroVaga) {
	this.numeroVaga = numeroVaga;
	this.status = false;
}

public boolean getStatus() {
	return this.status;
}

public String getNumeroVaga() {
	return this.numeroVaga;
}

public void setStatus(boolean status) {
	this.status = status;
}

public void setNumeroVaga(String numeroVaga) {
	this.numeroVaga = numeroVaga;
}

public abstract double getAjuste();

}
