package JavaParking;

import java.util.Scanner;

public class Main {

    // Método para exibir o menu
    public String Menu() {
        // Melhorando o estilo da string do menu
        return """
                ===============================
                       JAVA PARKING SYSTEM
                ===============================
                1. Cadastrar Vaga
                2. Usar Vaga
                3. Cadastrar Cliente
                4. Cadastrar Veículo
                ===============================
                Digite a opção desejada: 
                """;
    }

    public static void main(String[] args) {
        Main main = new Main();
        Scanner teclado = new Scanner(System.in);

        System.out.println(main.Menu());

        int i = teclado.nextInt();
        teclado.close();

        System.out.println("Você escolheu a opção: " + i);
    }
}
