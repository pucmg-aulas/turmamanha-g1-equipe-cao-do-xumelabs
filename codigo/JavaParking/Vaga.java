package JavaParking;

public class Vaga {
private String numeroVaga;
private boolean status;
private static final double ajuste = 1;

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

public double getAjuste() {
	return ajuste;
}
}
