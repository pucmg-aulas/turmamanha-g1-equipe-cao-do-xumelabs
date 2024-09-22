package JavaParking;

public class UsoDeVaga {
private Veiculo veiculo;
private Vaga vaga;
private int horaChegada;
private int minutoChegada;
private int horaSaida;
private int minutoSaida;
private int tempoUsado;

public UsoDeVaga(Veiculo veiculo, Vaga vaga, int horaChegada, int minutoChegada, int horaSaida, int minutoSaida) {
	this.veiculo = veiculo;
	this.vaga = vaga;
	this.horaChegada = horaChegada;
	this.minutoChegada = minutoChegada;
	this.horaSaida = horaSaida;
	this.minutoSaida = minutoSaida;
	validarHorario();
}

private void validarHorario() {
	if(this.horaChegada >= 24 || this.horaChegada < 0) {
		this.horaChegada = 0;
	}
	if(this.horaSaida >= 24 || this.horaSaida < 0) {
		this.horaSaida = 0;
	}
	if(this.minutoChegada >= 60 || this.minutoChegada < 0) {
		this.minutoChegada = 0;
	}
	if(this.minutoSaida >= 60 || this.minutoSaida < 0) {
		this.minutoSaida = 0;
	}
}


public Veiculo getVeiculo() {
	return this.veiculo;
}

public Vaga getVaga() {
	return this.vaga;
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
