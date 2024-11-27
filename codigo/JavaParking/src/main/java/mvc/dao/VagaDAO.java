package mvc.dao;

import mvc.bancoDados.BancoDados;
import mvc.model.*;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VagaDAO implements Serializable {
    private static VagaDAO instance;

    // Construtor privado para implementação do Singleton
    private VagaDAO() {
    }

    public static VagaDAO getInstance() {
        if (instance == null) {
            instance = new VagaDAO();
        }
        return instance;
    }

    
    public void cadastrarVagas(Estacionamento e, List<Vaga> vagas) {
        String sql = "INSERT INTO VAGA (NUMEROVAGA, ocupada, TIPO_VAGA, TAXA, NOME_ESTACIONAMENTO) VALUES (?, ?, ?, ?, ?)";
        String tipoVaga;
        PreparedStatement ps = null;

        for (Vaga vaga : vagas) {
            try {
                // Determinar o tipo de vaga
                if (vaga instanceof VagaDefault) {
                    tipoVaga = "Default";
                } else if (vaga instanceof VagaVip) {
                    tipoVaga = "VIP";
                } else if (vaga instanceof VagaPcd) {
                    tipoVaga = "PCD";
                } else {
                    tipoVaga = "Idoso";
                }

                ps = BancoDados.getConexao().prepareStatement(sql);
                ps.setString(1, vaga.getNumeroVaga());
                ps.setBoolean(2, vaga.isOcupada());
                ps.setString(3, tipoVaga);
                ps.setDouble(4, vaga.calcularAjuste());
                ps.setString(5, e.getNomeEstacionamento()); // Usando o nome diretamente

                ps.executeUpdate();

            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    if (ps != null)
                        ps.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    
    public Vaga pesquisarVagaPorNumero(Estacionamento estacionamento, String numeroVaga) {
        Vaga vaga = null;
        String sql = "SELECT * FROM vaga WHERE numeroVaga = ? AND nome_estacionamento = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
    
        try {
            ps = BancoDados.getConexao().prepareStatement(sql);
            ps.setString(1, numeroVaga);
            ps.setString(2, estacionamento.getNomeEstacionamento());
    
            rs = ps.executeQuery();
    
            if (rs.next()) {
                // Recuperar tipo de vaga
                String tipoVaga = rs.getString("tipo_vaga");
    
                // Instanciar vaga com base no tipo
                if (tipoVaga.equals("Default")) {
                    vaga = new VagaDefault(numeroVaga);
                } else if (tipoVaga.equals("VIP")) {
                    vaga = new VagaVip(numeroVaga);
                } else if (tipoVaga.equals("Idoso")) {
                    vaga = new VagaIdoso(numeroVaga);
                } else {
                    vaga = new VagaPcd(numeroVaga);
                }
    
                boolean ocupada = rs.getBoolean("ocupada");
                vaga.alterarDisponibilidade(ocupada);
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
    
        return vaga;
    }
        
    public List<Vaga> listaDeVagas(Estacionamento estacionamento) {
        List<Vaga> vagas = new ArrayList<>();
        String sql = "SELECT * FROM vaga WHERE nome_estacionamento = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
    
        try {
            ps = BancoDados.getConexao().prepareStatement(sql);
            ps.setString(1, estacionamento.getNomeEstacionamento());
            rs = ps.executeQuery();
    
            while (rs.next()) {
                String numeroVaga = rs.getString("numeroVaga");
                String tipoVaga = rs.getString("tipo_vaga");
                boolean ocupada = rs.getBoolean("ocupada"); // Lê a disponibilidade da vaga
    
                Vaga vaga = null;
    
                if ("Default".equals(tipoVaga)) {
                    vaga = new VagaDefault(numeroVaga);
                } else if ("VIP".equals(tipoVaga)) {
                    vaga = new VagaVip(numeroVaga);
                } else if ("PCD".equals(tipoVaga)) {
                    vaga = new VagaPcd(numeroVaga);
                } else if ("Idoso".equals(tipoVaga)) {
                    vaga = new VagaIdoso(numeroVaga);
                }
    
                if (vaga != null) {
                    vaga.alterarDisponibilidade(ocupada);
                    vagas.add(vaga);
                }
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
    
        return vagas;
    }    

    public void atualizar(Vaga vagaAntiga, Vaga vagaNova, Estacionamento estacionamento) {
        String sql = "UPDATE vaga SET numeroVaga = ?, tipo_vaga = ?, ocupada = ?, nome_estacionamento = ? WHERE numeroVaga = ? AND nome_estacionamento = ?";
        PreparedStatement ps = null;
        String tipoVaga;
    
        try {
            ps = BancoDados.getConexao().prepareStatement(sql);

            if (vagaNova instanceof VagaDefault) {
                tipoVaga = "Default";
            } else if (vagaNova instanceof VagaVip) {
                tipoVaga = "VIP";
            } else if (vagaNova instanceof VagaPcd) {
                tipoVaga = "PCD";
            } else {
                tipoVaga = "Idoso";
            }
            
            ps.setString(1, vagaNova.getNumeroVaga());
            ps.setString(2, tipoVaga); 
            ps.setBoolean(3, vagaNova.isOcupada());
            ps.setString(4, estacionamento.getNomeEstacionamento());
    
            // Define os critérios de identificação da vaga antiga
            ps.setString(5, vagaAntiga.getNumeroVaga());
            ps.setString(6, estacionamento.getNomeEstacionamento());
    
            // Executa a atualização
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
}
