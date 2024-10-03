package JavaParking;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Veiculo {
private String placa;

public Veiculo(String placa) {
	this.placa = placa;
	registarNovoVeiculo();
}

public String getPlaca() {
	return this.placa;
}

private void registarNovoVeiculo(){
	String nomeAquivo = "veiculo.txt";

	try(BufferedWriter escritor = new BufferedWriter(new FileWriter(nomeAquivo,true))){
		escritor.write("-------------------------------------");
		escritor.newLine();
		escritor.write("veículo placa " + this.placa);
		escritor.newLine();
	} catch (IOException e){
		e.printStackTrace();
	}
}

}
