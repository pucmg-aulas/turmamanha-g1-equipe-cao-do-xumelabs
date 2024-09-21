package JavaParking;

public class Cliente {
private String nome;
private int identificador;
private static int proximoid;
private Veiculo veiculo[];
private static int numeroDeVeiculos;

static {
	proximoid = 1;
	numeroDeVeiculos = 0;
}

public Cliente(String nome) {
	this.nome = nome;
	this.identificador = proximoid;
	this.veiculo = new Veiculo[numeroDeVeiculos];
}

public Cliente() {
	this.nome = "Anonimo";
	this.identificador = proximoid;
	this.veiculo = new Veiculo[numeroDeVeiculos];
}

public String getNome() {
	return this.nome;
}

public int getIdentificador() {
	return this.identificador;
}

public void cadastrarVeiculo(Veiculo veiculo) {
	numeroDeVeiculos++;
	this.veiculo[numeroDeVeiculos--] = veiculo;
}

}
