package mvc.dao;

import mvc.model.UsoDeVaga;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.stream.Collectors;

public class UsoDeVagaDAO extends AbstractDAO<UsoDeVaga> implements Serializable {
    private static UsoDeVagaDAO instance;

    // Construtor privado para implementação do Singleton
    private UsoDeVagaDAO() {
        super("C:\\Users\\paulo\\Desktop\\JavaPark\\turmamanha-g1-equipe-cao-do-xumelabs\\codigo\\JavaParking\\src\\main\\java\\mvc\\data\\UsoDeVagas.dat");
    }

    // Método Singleton para garantir uma única instância
    public static UsoDeVagaDAO getInstance() {
        if (instance == null) {
            instance = new UsoDeVagaDAO();
        }
        return instance;
    }

    public void criarUsoDeVaga(UsoDeVaga usoDeVaga) {
        cadastrar(usoDeVaga); 
    }

    public List<UsoDeVaga> listarUsosDeVaga() {
        return listarTodos();  
    }

    public double calcularValorTotalArrecadado() {
        return listarTodos().stream()
                .mapToDouble(UsoDeVaga::calcularCobranca)
                .sum();
    }

    public double calcularValorArrecadadoNoMes(YearMonth mes) {
        return listarTodos().stream()
                .filter(uso -> YearMonth.from(uso.getData()).equals(mes))
                .mapToDouble(UsoDeVaga::calcularCobranca)
                .sum();
    }

    public double calcularValorMedioPorUso() {
        return listarTodos().stream()
                .mapToDouble(UsoDeVaga::calcularCobranca)
                .average()
                .orElse(0);
    }

    public List<UsoDeVaga> ListaDeUsoDeVagasPorData(LocalDate dataInicio, LocalDate dataFim) {
        return listarTodos().stream()
                .filter(uso -> !uso.getData().isBefore(dataInicio) && !uso.getData().isAfter(dataFim))
                .collect(Collectors.toList());
    }

    public void atualizarUsoDeVaga(UsoDeVaga usoAntigo, UsoDeVaga usoNovo) {
        atualizar(usoAntigo, usoNovo); 
    }

    public void removerUsoDeVaga(UsoDeVaga usoDeVaga) {
        remover(usoDeVaga);  
    }
}
