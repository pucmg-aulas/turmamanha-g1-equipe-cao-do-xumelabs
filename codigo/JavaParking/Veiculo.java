package JavaParking;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Veiculo {
private String placa;

public Veiculo(String placa) {
	this.placa = placa;
	this.registrarVeiculoTxt();
}

public String getPlaca() {
	return this.placa;
}

public void setPlaca(String placa){
	this.placa = placa;
}

private void registrarVeiculoTxt(){
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
