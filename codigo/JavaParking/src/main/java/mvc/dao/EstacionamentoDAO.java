package mvc.dao;

import mvc.model.Cliente;
import mvc.model.Estacionamento;
import mvc.model.UsoDeVaga;
import mvc.model.Vaga;
import mvc.model.Veiculo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EstacionamentoDAO extends AbstractDAO<Estacionamento> implements Serializable {
    private static EstacionamentoDAO instance;

    // Construtor privado para implementação do Singleton
    private EstacionamentoDAO() {
        super("C:\\Users\\paulo\\OneDrive\\Área de Trabalho\\JavaParkNovo\\turmamanha-g1-equipe-cao-do-xumelabs\\codigo\\JavaParking\\src\\main\\java\\mvc\\data\\Estacionamentos.dat");
    }

    public static EstacionamentoDAO getInstance() {
        if (instance == null) {
            instance = new EstacionamentoDAO();
        }
        return instance;
    }

    public void adicionarEstacionamento(Estacionamento estacionamento) {
        cadastrar(estacionamento);  
    }

    public void removerEstacionamento(Estacionamento estacionamento) {
        remover(estacionamento);  
    }

    public List<Estacionamento> listaDeEstacionamentos() {
        return listarTodos();  
    }

    public Vaga pesquisarVaga(Estacionamento estacionamento, String numero) {
        return estacionamento.ListaDeVagas().stream()
                .filter(vaga -> vaga.getNumeroVaga().equals(numero))
                .findFirst()
                .orElse(null);
    }
    

    public UsoDeVaga pesquisarUsoDeVagaPorPlaca(Estacionamento estacionamento, String placa) {
        return estacionamento.ListaDeUsoDeVagas().stream()
                .filter(usoDeVaga -> usoDeVaga.getVeiculo().getPlaca().equals(placa))
                .findFirst()
                .orElse(null);
    }
    
    public List<UsoDeVaga> listarUsoDeVagasPorClienteEData(Estacionamento estacionamento, String cpf, LocalDate dataInicio, LocalDate dataFim) {
        ClienteDAO clienteDAO = ClienteDAO.getInstance();

        return estacionamento.ListaDeUsoDeVagas().stream()
            .filter(uso -> {
                String placa = uso.getVeiculo().getPlaca(); 
                Cliente cliente = clienteDAO.pesquisarPorPlaca(placa);  
                return cliente != null && cliente.getCpf().equals(cpf);  
            })
            .filter(uso -> !uso.getData().isBefore(dataInicio) && !uso.getData().isAfter(dataFim)) 
            .collect(Collectors.toList()); 
    }    

    public List<Cliente> getRankingDeClientesPorMes(Estacionamento estacionamento, YearMonth mes) {
        Map<Cliente, Double> totalGastosPorCliente = new HashMap<>();
        ClienteDAO clienteDAO = ClienteDAO.getInstance();
    
        estacionamento.ListaDeUsoDeVagas().stream()
            .filter(uso -> YearMonth.from(uso.getData()).equals(mes))
            .forEach(uso -> {
                Veiculo veiculo = uso.getVeiculo();
                String placa = veiculo.getPlaca();
                
                // Usar o ClienteDAO para buscar o cliente pelo veículo
                Cliente cliente = clienteDAO.pesquisarPorPlaca(placa);
    
                if (cliente != null) {
                    totalGastosPorCliente.merge(cliente, uso.calcularCobranca(), Double::sum);
                }
            });
    
        return totalGastosPorCliente.entrySet().stream()
            .sorted((entry1, entry2) -> Double.compare(entry2.getValue(), entry1.getValue())) // Ordena pelo valor gasto
            .map(Map.Entry::getKey) // Retorna apenas os clientes
            .collect(Collectors.toList());
    }
    
        public double calcularValorTotalArrecadado(Estacionamento estacionamento) {
            return estacionamento.ListaDeUsoDeVagas().stream() // Obter a lista de usos de vaga do estacionamento
                    .mapToDouble(UsoDeVaga::calcularCobranca) // Calcular a cobrança para cada uso de vaga
                    .sum(); // Somar todos os valores
        }
        
    
        public double calcularValorArrecadadoNoMes(Estacionamento estacionamento, YearMonth mes) {
            return estacionamento.ListaDeUsoDeVagas().stream()
                    .filter(uso -> {
                        LocalDate dataUso = uso.getData();
                        YearMonth usoMes = YearMonth.from(dataUso);
                        return usoMes.equals(mes); 
                    })
                    .mapToDouble(UsoDeVaga::calcularCobranca)
                    .sum();
        }

        public double calcularValorMedioPorUso(Estacionamento estacionamento) {
            return estacionamento.ListaDeUsoDeVagas().stream() 
                    .mapToDouble(UsoDeVaga::calcularCobranca) 
                    .average() 
                    .orElse(0); // Se não houver valores, retorna 0
        }        

       public List<Vaga> listaDeVagas(Estacionamento estacionamentoUsado){
            return estacionamentoUsado.ListaDeVagas();
       }

    public void atualizarEstacionamento(Estacionamento estacionamentoAntigo, Estacionamento estacionamentoNovo) {
        atualizar(estacionamentoAntigo, estacionamentoNovo);  
    }
}
