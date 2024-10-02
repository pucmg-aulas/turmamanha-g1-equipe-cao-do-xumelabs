package JavaParking;

import java.util.ArrayList;
import java.util.List;

public class Estacionamento {
private List<Vaga> vagas;
private int numeroDeVagas;
private List<UsoDeVaga> usoDeVagas;

// Construtor para estacionamento 

public Estacionamento(int numeroDeVagas) {
	this.numeroDeVagas = numeroDeVagas;
	this.vagas = new ArrayList<>(numeroDeVagas);
	this.usoDeVagas = new ArrayList<>();

}

// Método para o cadastro de uma nova vaga no estacionamento 

public boolean cadastrarVaga(Vaga vaga) {
	if(this.validarVaga(vaga) || this.vagas.size() < numeroDeVagas){
		this.vagas.add(vaga);
		return true;
	}else{
		return false;
	}
}

//Confere se já não estrapolou o número de vagas do estacionamento 

private boolean validarVaga(Vaga vaga){
	boolean resposta = true;
	
	for(int i=0; i< vagas.size(); i++){
		if(vaga.getNumeroVaga().equals(vagas.get(i).getNumeroVaga())){
			resposta = false;
		}
	}
	return resposta;
}

// Inícia o uso de vaga 

public void estacionar(UsoDeVaga usoDeVaga){
	this.usoDeVagas.add(usoDeVaga);
}

// Aciona o desocupar vaga e encerra o uso de vaga
// Passano o valor a ser pago pelo uso 

public double sairDaVaga(UsoDeVaga usoDeVaga){
	usoDeVaga.desocuparVaga();
	return usoDeVaga.calcularCobranca();
}

}
