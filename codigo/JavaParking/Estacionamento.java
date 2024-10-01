package JavaParking;

import java.util.ArrayList;
import java.util.List;

public class Estacionamento {
private List<Vaga> vagas;
private int numeroDeVagas;
private List<UsoDeVaga> usoDeVagas;

public Estacionamento(int numeroDeVagas) {
	this.numeroDeVagas = numeroDeVagas;
	this.vagas = new ArrayList<>(numeroDeVagas);
	this.usoDeVagas = new ArrayList<>();
}

public boolean cadastrarVaga(Vaga vaga) {
	if(this.validarVaga(vaga) || this.vagas.size() < numeroDeVagas){
		this.vagas.add(vaga);
		return true;
	}else{
		return false;
	}
}

private boolean validarVaga(Vaga vaga){
	boolean resposta = true;
	
	for(int i=0; i< vagas.size(); i++){
		if(vaga.getNumeroVaga().equals(vagas.get(i).getNumeroVaga())){
			resposta = false;
		}
	}
	return resposta;
}

public void estacionar(UsoDeVaga usoDeVaga){
	this.usoDeVagas.add(usoDeVaga);
}

public double sairDaVaga(UsoDeVaga usoDeVaga){
	usoDeVaga.desocuparVaga();
	return usoDeVaga.calcularCobranca();
}

public List listaDeUsoDeVagas(){
	return this.usoDeVagas;
}

}
