package mvc.dao;

import mvc.model.Cliente;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ClienteDAO {
    private ArrayList<Cliente> clientes;
    private static ClienteDAO instance;

    private ClienteDAO() {
        this.clientes = new ArrayList<>();
        carregarClientesTxt(); 
    }

    public static ClienteDAO getInstance() {
        if (instance == null)
            instance = new ClienteDAO();
        return instance;
    }

    public void cadastrarCliente(Cliente cliente) {
        clientes.add(cliente);
        registrarTodosClientesTxt();
    }

    public void removerCliente(Cliente cliente) {
        clientes.remove(cliente);
        registrarTodosClientesTxt();
    }

    public Cliente pesquisarClienteNome(String nome) {
        for(Cliente cliente: clientes){
            if(cliente.getNome().equals(nome)){
                return cliente;
            }
        }
        return null;
    }
    

    public Cliente pesquisarClienteId(int id) {
        for(Cliente cliente: clientes){
            if(cliente.getIdentificador() == id){
                return cliente;
            }
        }
        return null;
    }    

    public ArrayList<Cliente> listaDeClientes() {
        return clientes; 
    }
    

    public void atualizarCliente(Cliente clienteAntigo, Cliente clienteNovo) {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getIdentificador() == clienteAntigo.getIdentificador()) {
                clientes.set(i, clienteNovo);
                break;
            }
        }
        registrarTodosClientesTxt();
    }
    
    private void registrarTodosClientesTxt() {
        String filePath = "clientes.txt";
    
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
            for (Cliente cliente : clientes) {
                String linha = cliente.getIdentificador() + ";" + cliente.getNome();
                writer.write(linha);
                writer.newLine();
            }
            System.out.println("Clientes atualizados com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao registrar clientes: " + e.getMessage());
        }
    }

    public void carregarClientesTxt() {
        String filePath = "clientes.txt";
    
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length == 2) {
                    String nome = partes[1].trim();
                    Cliente cliente = new Cliente(nome); // ID é gerado automaticamente
                    clientes.add(cliente);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar clientes: " + e.getMessage());
        }
    }
    
}
