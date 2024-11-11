package mvc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nome;
    private String cpf;
    private int identificador;
    private static int proximoid;
    private List<Veiculo> veiculos;

    static {
        proximoid = 1;
    }

    // Construtor do cliente recebendo o nome
    public Cliente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
        this.identificador = proximoid;
        proximoid++;
        this.veiculos = new ArrayList<>();
    }

    // Construtor caso o cliente deseje ser anônimo, excluido!

    public void setIdentificador(int Id){
        this.identificador = Id;
    }

    public String getNome() {
        return this.nome;
    }

    public String getCpf() {
        return cpf;
    }

    public int getIdentificador() {
        return this.identificador;
    }

    public void cadastrarVeiculo(Veiculo veiculo) {
        this.veiculos.add(veiculo);
    }

    public List<Veiculo> getVeiculos() {
        return this.veiculos; 
    }


}
