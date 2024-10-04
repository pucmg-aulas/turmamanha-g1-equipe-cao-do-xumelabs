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

//Confere se o nome da vaga nova já existe, caso exista ela nao podera existir

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

public void cadastrarCliente(Cliente cliente){
	this.clientes.add(cliente);
	registarNovoCliente();

}

public List<Cliente> ListaDeClientes(){
	return this.clientes;
}

public void registarNovoCliente(){
	String nomeAquivo = "Clientes.txt";

	try(BufferedWriter escritor = new BufferedWriter(new FileWriter(nomeAquivo,false))){
		for(Cliente cliente : clientes){
		escritor.write("-------------------------------------");
		escritor.newLine();
		escritor.write("Cliente nome " + cliente.getNome() +"\nIdentificador " + cliente.getIdentificador() + " ");
		for(String placa : cliente.getPlacaDeVeiculos()){
			escritor.newLine();
			escritor.write("veículo placa " + placa);
			escritor.newLine();
		}}
		escritor.newLine();
	} catch (IOException e){
		e.printStackTrace();
	}

}

//verificar se uma placa é de algum cliente 

public boolean possuiPlaca(String placa){
	boolean achou = false;
	for(int i=0; i<clientes.size(); i++){
		for(String placas : clientes.get(i).getPlacaDeVeiculos())
		if(placas == placa){
			achou = true;
		}
	}

	return achou;
}


public void registrarNovaVagaVIp(){
	String nomeAquivo = "Vagas.txt";

	try(BufferedWriter escritor = new BufferedWriter(new FileWriter(nomeAquivo,true))){
		for(Vaga vaga : vagas){
		escritor.write("-------------------------------------");
		escritor.newLine();
		escritor.write("Numero da Vaga " + vaga.getNumeroVaga() +"\nTipo da vaga Vip");
		escritor.newLine();
		}
	} catch (IOException e){
		e.printStackTrace();
	}
}

public void registrarNovaVagaIdoso(){
	String nomeAquivo = "Vagas.txt";

	try(BufferedWriter escritor = new BufferedWriter(new FileWriter(nomeAquivo,true))){
		for(Vaga vaga : vagas){
		escritor.write("-------------------------------------");
		escritor.newLine();
		escritor.write("Numero da Vaga " + vaga.getNumeroVaga() +"\nTipo da vaga Vip");
		escritor.newLine();
		}
	} catch (IOException e){
		e.printStackTrace();
	}
}

public void registrarNovaVagaPcd(){
	String nomeAquivo = "Vagas.txt";

	try(BufferedWriter escritor = new BufferedWriter(new FileWriter(nomeAquivo,true))){
		for(Vaga vaga : vagas){
		escritor.write("-------------------------------------");
		escritor.newLine();
		escritor.write("Numero da Vaga " + vaga.getNumeroVaga() +"\nTipo da vaga Pcd");
		escritor.newLine();
		}
	} catch (IOException e){
		e.printStackTrace();
	}
}

public void registrarNovaVagaDefault(){
	String nomeAquivo = "Vagas.txt";

	try(BufferedWriter escritor = new BufferedWriter(new FileWriter(nomeAquivo,true))){
		for(Vaga vaga : vagas){
		escritor.write("-------------------------------------");
		escritor.newLine();
		escritor.write("Numero da Vaga " + vaga.getNumeroVaga() +"\nTipo da vaga Default");
		escritor.newLine();
		}
	} catch (IOException e){
		e.printStackTrace();
	}
}

}

