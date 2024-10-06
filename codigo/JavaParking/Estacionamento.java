package JavaParking;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Estacionamento {
private List<Vaga> vagas;
private int numeroDeVagas;
private List<UsoDeVaga> usoDeVagas;
private List<Cliente> clientes;

// Construtor para estacionamento 

public Estacionamento(int numeroDeVagas) {
	this.numeroDeVagas = numeroDeVagas;
	this.vagas = new ArrayList<>(numeroDeVagas);
	this.usoDeVagas = new ArrayList<>();
	this.clientes = new ArrayList<>();

}

// Método para o cadastro de uma nova vaga no estacionamento 

public boolean cadastrarVaga(Vaga vaga) {
	if(this.validarVaga(vaga) && this.vagas.size() < numeroDeVagas){
		this.vagas.add(vaga);
		return true;
	}else{
		return false;
	}
}

//Confere se o nome da vaga nova já existe, caso exista ela nao pode ser cadastrada novamente

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
// Passando o valor a ser pago pelo uso 

public double sairDaVaga(UsoDeVaga usoDeVaga){
	usoDeVaga.desocuparVaga();
	registarNovoUsodeVaga();
	return usoDeVaga.calcularCobranca();
}

public void cadastrarCliente(Cliente cliente){
	this.clientes.add(cliente);
	registarNovoCliente();
}

public List<UsoDeVaga> ListaDeUsoDeVagas(){
	return this.usoDeVagas;
}

public List<Cliente> ListaDeClientes(){
	return this.clientes;
}

public List<Vaga> ListaDeVagas(){
	return this.vagas;
}

public void registarNovoCliente(){
	String nomeAquivo = "Clientes.txt";

	try(BufferedWriter escritor = new BufferedWriter(new FileWriter(nomeAquivo,false))){
		for(Cliente cliente : clientes){
		escritor.write("-------------------------------------");
		escritor.newLine();
		escritor.write("Cliente nome " + cliente.getNome() +"\nIdentificador " + cliente.getIdentificador() + " ");
		escritor.newLine();
		for(String placa : cliente.getPlacaDeVeiculos()){
		escritor.write("veículo placa " + placa);
		escritor.newLine();
		}}
		escritor.newLine();
	} catch (IOException e){
		e.printStackTrace();
	}

}

public void registarNovoUsodeVaga(){
	String nomeAquivo = "UsoDeVaga.txt";

	try(BufferedWriter escritor = new BufferedWriter(new FileWriter(nomeAquivo,false))){
		for(UsoDeVaga usoDeVaga : usoDeVagas){
		escritor.write("-------------------------------------");
		escritor.newLine();
		escritor.write("Veiculo " + usoDeVaga.getVeiculo().getPlaca() +"\nVaga " + usoDeVaga.getVaga().getNumeroVaga() + " ");
		escritor.newLine();
		escritor.write("Tempo estacionado " + usoDeVaga.calcularTempoUsado());
		escritor.newLine();
		escritor.write("Valor cobrado " + usoDeVaga.calcularCobranca());
		escritor.newLine();
		}
		escritor.newLine();
	} catch (IOException e){
		e.printStackTrace();
	}

}

 public void registrarNovaVagaVIp(Vaga vaga){
	String nomeAquivo = "Vagas.txt";

	try(BufferedWriter escritor = new BufferedWriter(new FileWriter(nomeAquivo,true))){
		escritor.write("-------------------------------------");
		escritor.newLine();
		escritor.write("Numero da Vaga " + vaga.getNumeroVaga() +"\nTipo da vaga Vip");
		escritor.newLine();
	} catch (IOException e){
		e.printStackTrace();
	}
}

public void registrarNovaVagaIdoso(Vaga vaga){
	String nomeAquivo = "Vagas.txt";

	try(BufferedWriter escritor = new BufferedWriter(new FileWriter(nomeAquivo,true))){
		escritor.write("-------------------------------------");
		escritor.newLine();
		escritor.write("Numero da Vaga " + vaga.getNumeroVaga() +"\nTipo da vaga Idoso");
		escritor.newLine();
	} catch (IOException e){
		e.printStackTrace();
	}
}

public void registrarNovaVagaPcd(Vaga vaga){
	String nomeAquivo = "Vagas.txt";

	try(BufferedWriter escritor = new BufferedWriter(new FileWriter(nomeAquivo,true))){
		escritor.write("-------------------------------------");
		escritor.newLine();
		escritor.write("Numero da Vaga " + vaga.getNumeroVaga() +"\nTipo da vaga Pcd");
		escritor.newLine();
		
	} catch (IOException e){
		e.printStackTrace();
	}
}

public void registrarNovaVagaDefault(Vaga vaga){
	String nomeAquivo = "Vagas.txt";

	try(BufferedWriter escritor = new BufferedWriter(new FileWriter(nomeAquivo,true))){
		escritor.write("-------------------------------------");
		escritor.newLine();
		escritor.write("Numero da Vaga " + vaga.getNumeroVaga() +"\nTipo da vaga Default");
		escritor.newLine();
	} catch (IOException e){
		e.printStackTrace();
	}
}

}

