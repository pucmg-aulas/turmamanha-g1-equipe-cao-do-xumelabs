package JavaParking;

public class Vaga {
private String numeroVaga;
private boolean status;

public Vaga(String numeroVaga) {
	this.numeroVaga = numeroVaga;
	this.status = false;
}

public boolean getStatus() {
	return this.status;
}

public void setStatus(boolean status) {
	this.status = status;
}

public void setNumeroVaga(String numeroVaga) {
	this.numeroVaga = numeroVaga;
}
}
