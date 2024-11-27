package mvc.dao;

import mvc.bancoDados.BancoDados;
import mvc.model.*;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteDAO implements Serializable {

    private static ClienteDAO instance;

    // Construtor privado para implementação do Singleton
    private ClienteDAO() {
    }

    public static ClienteDAO getInstance() {
        if (instance == null) {
            instance = new ClienteDAO();
        }
        return instance;
    }

    public void cadastrarCliente(Cliente cliente, Estacionamento estacionamento) {
        String sql = "INSERT INTO cliente (nome, cpf, nome_estacionamento) VALUES (?, ?, ?)";

        PreparedStatement ps = null;

        try {
            ps = BancoDados.getConexao().prepareStatement(sql);
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getCpf());
            ps.setString(3, estacionamento.getNomeEstacionamento());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

     public Cliente pesquisarPorCpf(String cpf, Estacionamento estacionamento) {
        Cliente cliente = null;
        String sql = "SELECT * FROM cliente WHERE cpf = ? AND nome_estacionamento = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
    
        try {
            ps = BancoDados.getConexao().prepareStatement(sql);
            ps.setString(1, cpf);
            ps.setString(2, estacionamento.getNomeEstacionamento());
    
            rs = ps.executeQuery();
    
            if (rs.next()) {
                String nome = rs.getString("nome");
                cliente = new Cliente(nome, cpf);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    
        return cliente;
    }    

}
