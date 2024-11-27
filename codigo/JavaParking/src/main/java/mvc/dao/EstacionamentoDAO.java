package mvc.dao;

import mvc.bancoDados.BancoDados;
import mvc.model.Estacionamento;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstacionamentoDAO implements Serializable {
    private static EstacionamentoDAO instance;

    // Construtor privado para implementação do Singleton
    private EstacionamentoDAO() {
    }

    public static EstacionamentoDAO getInstance() {
        if (instance == null) {
            instance = new EstacionamentoDAO();
        }
        return instance;
    }

    public void adicionarEstacionamento(Estacionamento estacionamento) {
        String sql = "INSERT INTO ESTACIONAMENTO (NOME_ESTACIONAMENTO, NUMERODEVAGAS) VALUES (?, ?)";

        PreparedStatement ps = null;

        try {
            ps = BancoDados.getConexao().prepareStatement(sql);
            ps.setString(1, estacionamento.getNomeEstacionamento());
            ps.setInt(2, estacionamento.getNumeroDeVagas());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Estacionamento> listaDeEstacionamentos() {
        List<Estacionamento> estacionamentos = new ArrayList<>();
        String sql = "SELECT * FROM ESTACIONAMENTO";
        PreparedStatement ps = null;
        ResultSet rs = null;
    
        try {
            ps = BancoDados.getConexao().prepareStatement(sql);
            rs = ps.executeQuery(); 
    
            while (rs.next()) {             
                Estacionamento estacionamento = new Estacionamento(rs.getInt("NUMERODEVAGAS"), rs.getString("NOME_ESTACIONAMENTO"));
    
                estacionamentos.add(estacionamento);
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
    
        return estacionamentos; 
    }    

    public boolean verificarEstacionamentoJaCadastrado(String nome){
        String sql = "Select * from estacionamento where nome_estacionamento = ?";
        PreparedStatement ps = null;
        ResultSet rs;
        boolean resultado = false;

        try{
            ps = BancoDados.getConexao().prepareStatement(sql);
            ps.setString(1, nome);
            rs = ps.executeQuery();

            if(rs.next()){
                resultado = true;
            }
        } catch(SQLException e){
            e.printStackTrace();
        }

        return resultado;
    }
}
