package JavaParking;

public class UsoDeVaga {
private Veiculo veiculo;
private Vaga vaga;
private byte horaChegada;
private byte minutoChegada;
private byte horaSaida;
private byte minutoSaida;
private int tempoUsado;

public UsoDeVaga(Veiculo veiculo, Vaga vaga, byte horaChegada, byte minutoChegada, byte horaSaida, byte minutoSaida) {
	this.veiculo = veiculo;
	this.vaga = vaga;
	this.horaChegada = horaChegada;
	this.minutoChegada = minutoChegada;
	this.horaSaida = horaSaida;
	this.minutoSaida = minutoSaida;
}

public boolean ocuparVaga(){
	if(!this.vaga.getStatus()) {
		this.vaga.setStatus(true);
		return true;
	}
	return false;
}

public boolean desocuparVaga() {
	if(this.vaga.getStatus()) {
		this.vaga.setStatus(false);
		return true;
	}
	return false;
}

private int calcularTempo() {
	if(this.horaChegada == this.horaSaida) {
		this.tempoUsado = this.minutoSaida - this.minutoChegada;
	}else {
		this.tempoUsado =(this.horaSaida - this.horaChegada) * 60;
		this.tempoUsado += this.minutoSaida - this.minutoChegada;
	}
	return this.tempoUsado;
}

public int getTempo() {
	return this.calcularTempo();
}
}
