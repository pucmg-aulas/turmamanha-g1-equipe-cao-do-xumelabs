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
        carregarClientesTxt(); // Carrega os clientes do arquivo ao instanciar
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
        String filePath = "clientes.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length == 2) {
                    String nomeCliente = partes[1].trim();
                    if (nomeCliente.equalsIgnoreCase(nome)) {
                        Cliente cliente = new Cliente(nomeCliente);
                        cliente.setIdentificador(Integer.parseInt(partes[0].trim())); 
                        return cliente; 
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao pesquisar cliente: " + e.getMessage());
        }
        return null;
    }
    

    public Cliente pesquisarClienteId(int id) {
        String filePath = "clientes.txt";
    
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length == 2) {
                    int clienteId = Integer.parseInt(partes[0].trim());
                    String nomeCliente = partes[1].trim();
                    if (clienteId == id) {
                        Cliente cliente = new Cliente(nomeCliente);
                        cliente.setIdentificador(clienteId); 
                        return cliente; 
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao pesquisar cliente: " + e.getMessage());
        }
        return null; 
    }    

    public ArrayList<Cliente> listaDeClientes() {
        ArrayList<Cliente> clientes = new ArrayList<>(); 
    
        String filePath = "clientes.txt";
    
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length == 2) {
                    int clienteId = Integer.parseInt(partes[0].trim());
                    String nomeCliente = partes[1].trim();
                    Cliente cliente = new Cliente(nomeCliente);
                    cliente.setIdentificador(clienteId); 
                    clientes.add(cliente);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar lista de clientes: " + e.getMessage());
        }
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
