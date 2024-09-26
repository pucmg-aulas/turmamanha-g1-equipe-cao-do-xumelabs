package JavaParking;

import java.util.ArrayList;
import java.util.List;

public class Estacionamento {
private List<Vaga> vagas;
private int numeroDeVagas;
private UsoDeVaga usoDeVaga;

public Estacionamento(int numeroDeVagas) {
	this.numeroDeVagas = numeroDeVagas;
	this.vagas = new ArrayList<>();
}
public void cadastrarVaga(Vaga vaga) {
	this.vagas.add(vaga);
}

}
