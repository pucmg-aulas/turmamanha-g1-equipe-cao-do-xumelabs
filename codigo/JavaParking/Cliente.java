package JavaParking;

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

    public String getNome() {
        return this.nome;
    }

    public int getIdentificador() {
        return this.identificador;
    }

    // Método para adicionar um novo carro ao cliente
    public void cadastrarVeiculo(Veiculo veiculo) {
        this.veiculos.add(veiculo);
    }

    // Método para retornar e imprimir as placas dos veículos do cliente
    public List<Veiculo> getPlacaDeVeiculos() {
        for (Veiculo veiculo : veiculos) {
            System.out.println(veiculo.getPlaca());
        }
        return veiculos;
    }

}
