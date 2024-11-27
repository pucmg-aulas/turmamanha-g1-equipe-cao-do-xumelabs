package mvc.bancoDados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BancoDados {
    private static final String url = "jdbc:mysql://localhost:3306/javaparking";
    private static final String user = "root";
    private static final String senha = "root";
    private static BancoDados instancia = null;
    private static Connection conn = null;

    // Construtor privado para garantir Singleton
    private BancoDados() {
    }

    // Método para garantir uma única instância
    public static BancoDados getInstancia() {
        if (instancia == null) {
            instancia = new BancoDados();
            conectar();
        }
        return instancia;
    }

    // Método para conectar ao banco de dados
    private static void conectar() {
        try {
            conn = DriverManager.getConnection(url, user, senha);
        } catch (SQLException e) {
            Logger.getLogger(BancoDados.class.getName()).log(Level.SEVERE, "Falha ao conectar ao banco de dados!", e);
        }
    }

    // Método para obter a conexão
    public static Connection getConexao() {
        if (conn == null) {
            // Garante que a conexão será inicializada
            BancoDados.getInstancia();
        }
        return conn;
    }

    // Método público para desconectar do SGBD após o uso
    public void desconectar() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(BancoDados.class.getName()).log(Level.SEVERE, "Erro ao desconectar do banco de dados!", ex);
        }
    }
}
