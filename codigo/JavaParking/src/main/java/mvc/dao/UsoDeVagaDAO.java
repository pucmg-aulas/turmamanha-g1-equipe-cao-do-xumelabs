package mvc.dao;

import mvc.bancoDados.BancoDados;
import mvc.model.*;
import java.sql.Timestamp;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsoDeVagaDAO implements Serializable {
    private static UsoDeVagaDAO instance;

    // Construtor privado para implementação do Singleton
    private UsoDeVagaDAO() {
    }

    // Método Singleton para garantir uma única instância
    public static UsoDeVagaDAO getInstance() {
        if (instance == null) {
            instance = new UsoDeVagaDAO();
        }
        return instance;
    }

    public void cadastrar(Estacionamento estacionamento, UsoDeVaga usoDeVaga) {
        String sql = "INSERT INTO usodevaga (placa, numeroVaga, horachegada, horasaida, data, tempousado, valoraPagar, status, nome_estacionamento) VALUES (?,?,?,?,?,?,?,?,?)";

        try (PreparedStatement ps = BancoDados.getConexao().prepareStatement(sql)) {
            ps.setString(1, usoDeVaga.getVeiculo().getPlaca());
            ps.setString(2, usoDeVaga.getVaga().getNumeroVaga());

            // Conversões para Timestamp
            ps.setTimestamp(3,
                    usoDeVaga.getHoraEntrada() != null ? Timestamp.valueOf(usoDeVaga.getHoraEntrada()) : null);
            ps.setTimestamp(4, usoDeVaga.getHoraSaida() != null ? Timestamp.valueOf(usoDeVaga.getHoraSaida()) : null);

            // Conversão para java.sql.Date
            ps.setDate(5, usoDeVaga.getData() != null ? java.sql.Date.valueOf(usoDeVaga.getData()) : null);

            // Tempo usado e valor a pagar
            ps.setNull(6, java.sql.Types.DOUBLE);
            ps.setDouble(7, usoDeVaga.getValorAPagar());

            // Status e nome do estacionamento
            ps.setBoolean(8, usoDeVaga.getStatus());
            ps.setString(9, estacionamento.getNomeEstacionamento());

            // Execução do comando SQL
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<UsoDeVaga> listarUsosDeVaga(Estacionamento estacionamento) {
        List<UsoDeVaga> usosDeVagas = new ArrayList<>();
        String sql = "SELECT * FROM usodevaga WHERE nome_estacionamento = ?";
        VagaDAO vagaDAO = VagaDAO.getInstance();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = BancoDados.getConexao().prepareStatement(sql);
            ps.setString(1, estacionamento.getNomeEstacionamento());
            rs = ps.executeQuery();

            while (rs.next()) {
                // Criar veículo com a placa
                Veiculo veiculo = new Veiculo(rs.getString("placa"));

                // Recuperar vaga associada
                Vaga vaga = vagaDAO.pesquisarVagaPorNumero(estacionamento, rs.getString("numeroVaga"));

                // Criar uso de vaga com os dados básicos
                UsoDeVaga usoDeVaga = new UsoDeVaga(veiculo, vaga);

                // Recuperar e definir status do uso de vaga
                boolean status = rs.getBoolean("status");
                usoDeVaga.setStatus(status);

                LocalDateTime horaChegada = rs.getTimestamp("horachegada").toLocalDateTime();
                usoDeVaga.setHoraChegada(horaChegada);

                if (rs.getTimestamp("horasaida") != null) {
                    LocalDateTime horaSaida = rs.getTimestamp("horasaida").toLocalDateTime();
                    usoDeVaga.setHoraSaida(horaSaida);
                }

                // Recuperar e definir data
                LocalDate data = rs.getDate("data").toLocalDate();
                usoDeVaga.setData(data);

                // Recuperar valor a pagar, caso exista
                if (rs.getDouble("valoraPagar") != 0) {
                    double valorAPagar = rs.getDouble("valoraPagar");
                    usoDeVaga.setValorAPagar(valorAPagar);
                }

                // Adicionar à lista
                usosDeVagas.add(usoDeVaga);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (ps != null)
                    ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return usosDeVagas;
    }

    public UsoDeVaga pesquisarUsoDeVagaNaoFinalizado(Estacionamento estacionamento, String placa) {
        // pega apenas usos de vagas ainda nao completos
        UsoDeVaga usoDeVaga = null;
        String sql = "SELECT * FROM USODEVAGA WHERE placa = ? and status = ? AND nome_estacionamento = ?";
        VagaDAO vagaDAO = VagaDAO.getInstance(); // Acessar vagas para associar ao uso
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = BancoDados.getConexao().prepareStatement(sql);
            ps.setString(1, placa);
            ps.setBoolean(2, false);
            ps.setString(3, estacionamento.getNomeEstacionamento());

            rs = ps.executeQuery();

            if (rs.next()) {
                // Recuperar veículo associado à placa
                Veiculo veiculo = new Veiculo(rs.getString("placa"));

                // Pesquisar vaga pelo número no contexto do estacionamento
                Vaga vaga = vagaDAO.pesquisarVagaPorNumero(estacionamento, rs.getString("numeroVaga"));

                // Recuperar demais informações do uso de vaga
                LocalDateTime horaChegada = rs.getTimestamp("horachegada").toLocalDateTime();
                LocalDateTime horaSaida = rs.getTimestamp("horasaida") != null
                        ? rs.getTimestamp("horasaida").toLocalDateTime()
                        : null;
                LocalDate data = rs.getDate("data").toLocalDate();
                boolean status = rs.getBoolean("status");
                double tempoUsado = rs.getDouble("tempousado");
                double valorAPagar = rs.getDouble("valoraPagar");

                // Instanciar UsoDeVaga com todos os dados recuperados
                usoDeVaga = new UsoDeVaga(veiculo, vaga);
                usoDeVaga.setHoraChegada(horaChegada);
                usoDeVaga.setHoraSaida(horaSaida);
                usoDeVaga.setData(data);
                usoDeVaga.setStatus(status);
                usoDeVaga.setValorAPagar(valorAPagar);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (ps != null)
                    ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return usoDeVaga;
    }

    public List<UsoDeVaga> listaUsoDeVagasPorClienteEData(Estacionamento estacionamento, String cpf,
            LocalDate dataInicio, LocalDate dataFim) {
        List<UsoDeVaga> usosDeVagas = new ArrayList<>();
        PreparedStatement ps = null;
        VagaDAO vagaDAO = VagaDAO.getInstance();
        String sql = """
                    SELECT uv.*
                    FROM usodevaga uv
                    JOIN veiculo v ON uv.placa = v.placa
                    JOIN cliente c ON v.cpf = c.cpf
                    WHERE uv.nome_estacionamento = ?
                      AND c.cpf = ?
                      AND uv.horachegada BETWEEN ? AND ?
                      AND uv.horasaida BETWEEN ? AND ?
                """;

        try {
            ps = BancoDados.getConexao().prepareStatement(sql);
            ps.setString(1, estacionamento.getNomeEstacionamento());
            ps.setString(2, cpf);
            ps.setObject(3, dataInicio.atStartOfDay());
            ps.setObject(4, dataFim.atTime(23, 59, 59));
            ps.setObject(5, dataInicio.atStartOfDay());
            ps.setObject(6, dataFim.atTime(23, 59, 59));

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Vaga vaga = vagaDAO.pesquisarVagaPorNumero(estacionamento, rs.getString("numeroVaga"));
                UsoDeVaga usoDeVaga = new UsoDeVaga(new Veiculo(rs.getString("placa")), vaga);
                usoDeVaga.setHoraChegada(rs.getTimestamp("horachegada").toLocalDateTime());
                usoDeVaga.setHoraSaida(rs.getTimestamp("horasaida").toLocalDateTime());
                usoDeVaga.setValorAPagar(rs.getDouble("valoraPagar"));
                usoDeVaga.setStatus(rs.getBoolean("status"));
                usoDeVaga.setData(rs.getDate("data").toLocalDate());
                usosDeVagas.add(usoDeVaga);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usosDeVagas;
    }

    public double calcularValorTotalArrecadado(Estacionamento estacionamento) {
        String sql = "select sum(valoraPagar) as total from usodevaga where nome_estacionamento = ? and status = ?";

        PreparedStatement ps = null;
        ResultSet rs = null;
        double valor = 0;

        try {
            ps = BancoDados.getConexao().prepareStatement(sql);

            ps.setString(1, estacionamento.getNomeEstacionamento());
            ps.setInt(2, 1);
            rs = ps.executeQuery();

            if (rs.next()) {
                valor = rs.getDouble("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (ps != null)
                    ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return valor;
    }

    public double calcularValorTotalArrecadadoPorMes(Estacionamento estacionamento, int ano, int mes) {
        String sql = "SELECT SUM(valoraPagar) AS total FROM usodevaga WHERE nome_estacionamento = ? AND YEAR(data) = ? AND MONTH(data) = ? and status = ?";
        double valor = 0.0;
        PreparedStatement ps = null;

        try {
            ps = BancoDados.getConexao().prepareStatement(sql);
            ps.setString(1, estacionamento.getNomeEstacionamento());
            ps.setInt(2, ano); // Filtro pelo ano
            ps.setInt(3, mes); // Filtro pelo mês
            ps.setInt(4, 1);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                valor = rs.getDouble("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return valor;
    }

    public double calcularValorMedioPorUso(Estacionamento estacionamento) {
        String sql = "select avg(valoraPagar) as media from usodevaga where nome_estacionamento = ?  and status = ?";
        double media = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = BancoDados.getConexao().prepareStatement(sql);

            ps.setString(1, estacionamento.getNomeEstacionamento());
            ps.setInt(2, 1);

            rs = ps.executeQuery();
            if (rs.next()) {
                media = rs.getDouble("media");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return media;
    }

    public List<Map<String, Object>> rankingDeClientes(Estacionamento estacionamento, int mes, int ano) {
        List<Map<String, Object>> ranking = new ArrayList<>();

        String sql = """
                    SELECT veiculo.cpf, SUM(usodevaga.valoraPagar) AS soma
                    FROM usodevaga
                    JOIN veiculo ON usodevaga.placa = veiculo.placa
                    WHERE usodevaga.status = ?
                      AND veiculo.cpf IS NOT NULL
                      AND usodevaga.nome_estacionamento = ?
                      AND YEAR(data) = ?
                      AND MONTH(data) = ?
                    GROUP BY veiculo.cpf
                    ORDER BY soma DESC
                """;

        try (PreparedStatement ps = BancoDados.getConexao().prepareStatement(sql)) {
            ps.setInt(1, 1);
            ps.setString(2, estacionamento.getNomeEstacionamento());
            ps.setInt(3, ano);
            ps.setInt(4, mes);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String cpf = rs.getString("cpf");
                    Cliente cliente = ClienteDAO.getInstance().pesquisarPorCpf(cpf, estacionamento);

                    double totalPago = rs.getDouble("soma");
                    String nome = cliente.getNome();

                    // Adiciona os dados ao ranking
                    Map<String, Object> registro = new HashMap<>();
                    registro.put("nome", nome);
                    registro.put("valorPago", totalPago);
                    ranking.add(registro);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ranking;
    }

    public List<Map<String, Object>> vagasUtilizadasPorPeriodo(Estacionamento estacionamento, String turno,
            int anoEscolhido, int mesEscolhido) {
        String sql = "SELECT v.tipo_vaga, COUNT(uv.numeroVaga) AS vagas_usadas, SUM(uv.valoraPagar) AS valor_arrecadado FROM usodevaga uv JOIN vaga v ON uv.numeroVaga = v.numeroVaga WHERE uv.nome_estacionamento = ? AND v.nome_estacionamento = ? AND uv.status = ? AND HOUR(uv.horachegada) BETWEEN ? AND ? AND HOUR(uv.horasaida) BETWEEN ? AND ? AND MONTH(uv.data) = ? AND YEAR(uv.data) = ? GROUP BY v.tipo_vaga;";

        PreparedStatement ps = null;
        List<Map<String, Object>> lista = new ArrayList<>();

        try {
            ps = BancoDados.getConexao().prepareStatement(sql);
            int inicio, fim;

            if (turno.equalsIgnoreCase("manhã")) {
                inicio = 7;
                fim = 11;
            } else if (turno.equalsIgnoreCase("tarde")) {
                inicio = 12;
                fim = 17;
            } else if (turno.equalsIgnoreCase("noite")) {
                inicio = 18;
                fim = 23;
            } else {
                throw new IllegalArgumentException("Turno inválido: " + turno);
            }

            ps.setString(1, estacionamento.getNomeEstacionamento());
            ps.setString(2, estacionamento.getNomeEstacionamento());
            ps.setInt(3, 1); // Presumivelmente, 1 indica status ativo
            ps.setInt(4, inicio);
            ps.setInt(5, fim);
            ps.setInt(6, inicio);
            ps.setInt(7, fim);
            ps.setInt(8, mesEscolhido);
            ps.setInt(9, anoEscolhido);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Map<String, Object> registro = new HashMap<>();
                    registro.put("tipoVaga", rs.getString("tipo_vaga"));
                    registro.put("quantidadeDeVagas", rs.getInt("vagas_usadas"));
                    registro.put("valorArrecadado", rs.getDouble("valor_arrecadado"));

                    // Adiciona à lista
                    lista.add(registro);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public List<Map<String, Object>> taxaDeVagasObtidasPorTipo(Estacionamento estacionamento, int anoEscolhido,  int mesEscolhido){
        String sql = "SELECT \n" + //
                        "    v.tipo_vaga, \n" + //
                        "    COUNT(uv.numeroVaga) AS vagas_usadas, \n" + //
                        "    (COUNT(uv.numeroVaga) * 100.0 / (SELECT COUNT(uv2.numeroVaga) \n" + //
                        "                                      FROM usodevaga uv2 \n" + //
                        "                                      WHERE uv2.status = ? AND uv2.nome_estacionamento = uv.nome_estacionamento)) AS porcentagem_utilizacao,\n" + //
                        "    SUM(uv.valoraPagar) AS valor_total_arrecadado\n" + //
                        "FROM \n" + //
                        "    usodevaga uv\n" + //
                        "JOIN \n" + //
                        "    vaga v ON uv.numeroVaga = v.numeroVaga\n" + //
                        "WHERE \n" + //
                        "\tuv.nome_estacionamento = ?\n" + //
                        "    AND v.nome_estacionamento = ?\n" + //
                        "    AND uv.status = ?\n" + //
                        "    AND MONTH(uv.data) = ?\n" + //
                        "    AND YEAR(uv.data) = ?\n" + //
                        "GROUP BY \n" + //
                        "    v.tipo_vaga\n" + //
                        "ORDER BY \n" + //
                        "    porcentagem_utilizacao DESC;";

        List<Map<String, Object>> lista = new ArrayList<>();
        PreparedStatement ps = null;

        try{
            ps = BancoDados.getConexao().prepareStatement(sql);

            ps.setInt(1, 1);
            ps.setString(2, estacionamento.getNomeEstacionamento());
            ps.setString(3, estacionamento.getNomeEstacionamento());
            ps.setInt(4, 1);
            ps.setInt(5, mesEscolhido);
            ps.setInt(6, anoEscolhido);

            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    Map<String, Object> registro = new HashMap<>();

                    registro.put("tipoVaga", rs.getString("tipo_vaga"));
                    registro.put("numeroDeVagasUsadas", rs.getInt("vagas_usadas"));
                    registro.put("porcentagem", rs.getDouble("porcentagem_utilizacao"));
                    registro.put("valorArrecadado", rs.getDouble("valor_total_arrecadado"));

                    lista.add(registro);
                }
            }
        } catch  (SQLException e){
            e.printStackTrace();
        }
        return lista;
    }

    public void atualizarUsoDeVaga(UsoDeVaga usoAntigo, UsoDeVaga usoNovo, Estacionamento estacionamento) {
        String sql = "UPDATE usoDeVaga SET placa = ?, numeroVaga = ?, horachegada = ?, horasaida = ?, data = ?, tempousado = ?, valoraPagar = ?, status = ?, nome_estacionamento = ? WHERE placa = ? AND numeroVaga = ? AND nome_estacionamento = ?";
        PreparedStatement ps = null;

        try {
            ps = BancoDados.getConexao().prepareStatement(sql);

            // Definindo os novos valores
            ps.setString(1, usoNovo.getVeiculo().getPlaca());
            ps.setString(2, usoNovo.getVaga().getNumeroVaga());
            ps.setTimestamp(3, Timestamp.valueOf(usoNovo.getHoraEntrada()));

            // Trata o caso em que horaSaida é nula
            if (usoNovo.getHoraSaida() != null) {
                ps.setTimestamp(4, Timestamp.valueOf(usoNovo.getHoraSaida()));
            } else {
                ps.setNull(4, java.sql.Types.TIMESTAMP);
            }

            ps.setDate(5, java.sql.Date.valueOf(usoNovo.getData()));

            // Calcula e trata tempo usado
            if (usoNovo.getHoraSaida() != null) {
                ps.setDouble(6, usoNovo.calcularTempoUsado());
            } else {
                ps.setNull(6, java.sql.Types.DOUBLE);
            }

            ps.setDouble(7, usoNovo.calcularCobranca());
            ps.setBoolean(8, usoNovo.getStatus());
            ps.setString(9, estacionamento.getNomeEstacionamento());

            // Define os critérios de identificação do registro antigo (PK composta +
            // nome_estacionamento)
            ps.setString(10, usoAntigo.getVeiculo().getPlaca());
            ps.setString(11, usoAntigo.getVaga().getNumeroVaga());
            ps.setString(12, estacionamento.getNomeEstacionamento());

            // Executa a atualização
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null)
                    ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
