package JavaParking;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String nome;
    private int identificador;
    private static int proximoid;
    private List<Veiculo> veiculos;

    static {
        proximoid = 1;
    }

    // Construtuor do cliente recebendo o nome 

    public Cliente(String nome) {
        this();
        this.nome = nome;
    }

    // Construtor caso o cliente deseje ser anônimo

    public Cliente() {
        this.nome = "Anonimo";
        this.identificador = proximoid;
        proximoid++;
        this.veiculos = new ArrayList<>();
    }

    public String getNome() {
        return this.nome;
    }

    public int getIdentificador() {
        return this.identificador;
    }

    // Método para adicionar um novo carro ao cliente 

    public void cadastrarVeiculo(Veiculo veiculo) {
        this.veiculos.add(veiculo);
        registarNovoCliente();
    }

    // Método para retornar o array list de veículos do cliente 

    public List<Veiculo> getVeiculos() {
        return veiculos;
    }

    private void registarNovoCliente(){
        String nomeAquivo = "Clientes.txt";
    
        try(BufferedWriter escritor = new BufferedWriter(new FileWriter(nomeAquivo,true))){
            escritor.write("-------------------------------------");
            escritor.newLine();
            escritor.write("Cliente nome " + this.nome +"\nIdentificador " + this.identificador + " ");
            for(int i=0; i< veiculos.size(); i++){
                escritor.newLine();
                escritor.write("veículo placa " + this.veiculos.get(i).getPlaca());
            }
            escritor.newLine();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
