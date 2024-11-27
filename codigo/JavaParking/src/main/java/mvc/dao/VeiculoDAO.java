package mvc.dao;

import mvc.bancoDados.BancoDados;
import mvc.model.*;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class VeiculoDAO implements Serializable {

    private static VeiculoDAO instance;

    // Construtor privado para implementação do Singleton
    private VeiculoDAO() {
    }

    // Método Singleton para garantir uma única instância
    public static VeiculoDAO getInstance() {
        if (instance == null) {
            instance = new VeiculoDAO();
        }
        return instance;
    }

    public void cadastrarVeiculo(Veiculo veiculo) {
        String sql = "INSERT INTO VEICULO (PLACA) VALUES (?)";

        PreparedStatement ps = null;

        try{
            ps = BancoDados.getConexao().prepareStatement(sql);
            ps.setString(1, veiculo.getPlaca());

            ps.executeUpdate();

        }  catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void cadastrarVeiculo(Veiculo veiculo, Cliente cliente) {
        String sql = "INSERT INTO VEICULO (PLACA, CPF) VALUES (?, ?)";

        PreparedStatement ps = null;

        try{
            ps = BancoDados.getConexao().prepareStatement(sql);
            ps.setString(1, veiculo.getPlaca());
            ps.setString(2, cliente.getCpf());

            ps.executeUpdate();

        }  catch(SQLException e){
            e.printStackTrace();
        }
    }

    public Veiculo pesquisarVeiculoPorPlaca(String placa) {
        String sql = "Select * from veiculo where placa = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        Veiculo veiculo = null;

        try{
            ps = BancoDados.getConexao().prepareStatement(sql);
            ps.setString(1, placa);

            rs = ps.executeQuery();

            if(rs.next()){
                veiculo = new Veiculo(placa);
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
    
        return veiculo;
    }

    public String obterCpfPorVeiculo(String placa) {
        String sql = "Select * from veiculo where placa = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        String resposta = null;

        try{
            ps = BancoDados.getConexao().prepareStatement(sql);
            ps.setString(1, placa);
            ps.setString(2, null);

            rs = ps.executeQuery();

            if(rs.next()){
                resposta = rs.getString("cpf");
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
    
        return resposta;
    }

    public Cliente pesquisarCliente(String cpf){
        String sql = "select c.cpf, c.nome, c.nome_estacionamento from cliente c  join veiculo v on c.cpf = v.cpf where c.cpf = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        Cliente cliente = null;

        try{
            ps = BancoDados.getConexao().prepareStatement(sql);
            ps.setString(1, cpf);

            rs = ps.executeQuery();

            if(rs.next()){
                cliente = new Cliente(rs.getString("cpf"),rs.getString("cpf"));
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

    public void associarVeiculoAoCliente(String placa, String cpf){
        String sql = "UPDATE VEICULO SET CPF = ? WHERE PLACA = ?";
        PreparedStatement ps = null;

        try{
            ps = BancoDados.getConexao().prepareStatement(sql);
            ps.setString(1, cpf);
            ps.setString(2, placa);

            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
}
