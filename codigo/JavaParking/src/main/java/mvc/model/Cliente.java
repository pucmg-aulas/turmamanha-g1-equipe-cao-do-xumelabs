package mvc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nome;
    private int identificador;
    private static int proximoid;
    private List<Veiculo> veiculos;

    static {
        proximoid = 1;
    }

    // Construtor do cliente recebendo o nome
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

    public void setIdentificador(int Id){
        this.identificador = Id;
    }

    public String getNome() {
        return this.nome;
    }

    public int getIdentificador() {
        return this.identificador;
    }

    public void cadastrarVeiculo(Veiculo veiculo) {
        this.veiculos.add(veiculo);
    }

    public List<String> getPlacaDeVeiculos() {
        List<String> placas = new ArrayList<>(); 

        for (Veiculo veiculo : veiculos) {
            placas.add(veiculo.getPlaca()); 
        }

        return placas; 
    }


}
