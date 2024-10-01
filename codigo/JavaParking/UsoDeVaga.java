package JavaParking;

import java.time.LocalDateTime;
import java.time.Duration;

public class UsoDeVaga {
private Veiculo veiculo;
private Vaga vaga;
private LocalDateTime horaChegada;
private LocalDateTime horaSaida;
private Duration tempoUsado;
private static final int maxCobranca;
private static final int valorFracao;
private static final int tempoFracao;

static {
	maxCobranca = 50;
	valorFracao = 4;
	tempoFracao = 15;
}


public UsoDeVaga(Veiculo veiculo, Vaga vaga) {
	this.veiculo = veiculo;
	this.vaga = vaga;
	this.ocuparVaga();
}

private boolean ocuparVaga(){
	if(this.vaga.isOcupada() == false) {
		this.vaga.alterarDisponibilidade(true);
		this.horaChegada = LocalDateTime.now();
		return true;
	}else {
		return false;
	}
}

public boolean desocuparVaga() {
	if(this.vaga.isOcupada()) {
		this.vaga.alterarDisponibilidade(false);
		this.horaSaida = LocalDateTime.now();
		return true;
	}else {
		return false;
	}
}

private double calcularTempoUsado() {
	
	if(this.horaChegada.getHour() == this.horaSaida.getHour()) {
		this.tempoUsado = Duration.ofMinutes(this.horaSaida.getMinute() - this.horaChegada.getMinute());
	}else {
		this.tempoUsado =  Duration.between(this.horaChegada, this.horaSaida);
	}
	
	return this.tempoUsado.toMinutes();
}

public double calcularCobranca() {
	double valorTotal;
	
	valorTotal = (this.calcularTempoUsado()/tempoFracao) * valorFracao;
	valorTotal = valorTotal * this.vaga.calcularAjuste();
	
	if(valorTotal >= maxCobranca) {
		valorTotal = maxCobranca;
	}
	
	return valorTotal;
}

public Veiculo getVeiculo() {
	return this.veiculo;
}

public Vaga getVaga() {
	return this.vaga;
}
}
