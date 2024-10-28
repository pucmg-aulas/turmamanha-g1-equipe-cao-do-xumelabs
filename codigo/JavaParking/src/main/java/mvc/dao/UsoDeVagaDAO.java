package mvc.dao;

import mvc.model.UsoDeVaga;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UsoDeVagaDAO extends AbstractDAO<UsoDeVaga> implements Serializable {
    private static UsoDeVagaDAO instance;

    // Construtor privado para implementação do Singleton
    private UsoDeVagaDAO() {
        super("./src/main/java/mvc/data/UsoDeVaga.dat");
    }

    // Método Singleton para garantir uma única instância
    public static UsoDeVagaDAO getInstance() {
        if (instance == null) {
            instance = new UsoDeVagaDAO();
        }
        return instance;
    }

    // Método para criar um novo uso de vaga
    public void criarUsoDeVaga(UsoDeVaga usoDeVaga) {
        cadastrar(usoDeVaga);  // Chama o método da classe pai AbstractDAO
    }

    // Método para listar todos os usos de vaga
    public List<UsoDeVaga> listarUsosDeVaga() {
        return listarTodos();  // Chama o método da classe pai AbstractDAO
    }

    // Método para calcular o valor total arrecadado do estacionamento
    public double calcularValorTotalArrecadado() {
        return listarTodos().stream()
                .mapToDouble(UsoDeVaga::calcularCobranca)
                .sum();
    }

    // Método para calcular o valor arrecadado em determinado mês
    public double calcularValorArrecadadoNoMes(YearMonth mes) {
        return listarTodos().stream()
                .filter(uso -> YearMonth.from(uso.getData()).equals(mes))
                .mapToDouble(UsoDeVaga::calcularCobranca)
                .sum();
    }

    // Método para calcular o valor médio de cada utilização do estacionamento
    public double calcularValorMedioPorUso() {
        return listarTodos().stream()
                .mapToDouble(UsoDeVaga::calcularCobranca)
                .average()
                .orElse(0);
    }

    // Método para listar todos os usos de vaga em um determinado período
    public List<UsoDeVaga> ListaDeUsoDeVagasPorData(LocalDate dataInicio, LocalDate dataFim) {
        return listarTodos().stream()
                .filter(uso -> !uso.getData().isBefore(dataInicio) && !uso.getData().isAfter(dataFim))
                .collect(Collectors.toList());
    }

    // Método para atualizar um uso de vaga
    public void atualizarUsoDeVaga(UsoDeVaga usoAntigo, UsoDeVaga usoNovo) {
        atualizar(usoAntigo, usoNovo);  // Chama o método da classe pai AbstractDAO
    }

    public void removerUsoDeVaga(UsoDeVaga usoDeVaga) {
        remover(usoDeVaga);  // Chama o método da classe pai AbstractDAO
    }
}
