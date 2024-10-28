    /*
    * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
    * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
    */
    package mvc.model;

    import java.time.Duration;
    import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

    public class UsoDeVaga implements Serializable {

        private Veiculo veiculo;
        private Vaga vaga;
        private LocalDateTime horaChegada;
        private LocalDate data;
        private LocalDateTime horaSaida;
        private Duration tempoUsado;
        private double valorAPagar;
        private static final int maxCobranca;
        private static final int valorFracao;
        private static final int tempoFracao;

        private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        static {
            maxCobranca = 50;
            valorFracao = 4;
            tempoFracao = 15;
        }

    // Construtor para criar o uso de vaga 
        public UsoDeVaga(Veiculo veiculo, Vaga vaga) {
            this.veiculo = veiculo;
            this.vaga = vaga;
        }

        public boolean ocuparVaga() {
            if (this.vaga.isOcupada() == false) {
                this.vaga.alterarDisponibilidade(true);
                this.horaChegada = LocalDateTime.now();
                this.data = LocalDate.now();
                return true;
            } else {
                return false;
            }
        }

        public boolean desocuparVaga() {
            if (this.vaga.isOcupada()) {
                this.vaga.alterarDisponibilidade(false);
                this.horaSaida = LocalDateTime.now();
                return true;
            } else {
                return false;
            }
        }

        public double calcularTempoUsado() {

            if (this.horaChegada.getHour() == this.horaSaida.getHour()) {
                this.tempoUsado = Duration.ofMinutes(this.horaSaida.getMinute() - this.horaChegada.getMinute());
            } else {
                this.tempoUsado = Duration.between(this.horaChegada, this.horaSaida);
            }

            return this.tempoUsado.toMinutes();
        }

        public double calcularCobranca() {
            valorAPagar = (this.calcularTempoUsado() / tempoFracao) * valorFracao;
            valorAPagar = valorAPagar * this.vaga.calcularAjuste();

            if (valorAPagar >= maxCobranca) {
                valorAPagar = maxCobranca;
            }

            return valorAPagar;
        }

        public void setHoraChegada(LocalDateTime horaChegada){
            this.horaChegada = horaChegada;
        }

        public void setHoraSaida(LocalDateTime horaSaida){
            this.horaSaida = horaSaida;
        }

        public void setData(LocalDate data){
            this.data = data;
        }

        public void setValorAPagar(double valor){
            this.valorAPagar= valor;
        }

        public Veiculo getVeiculo() {
            return this.veiculo;
        }

        public Vaga getVaga() {
            return this.vaga;
        }

        public String getHoraChegada(){
            return this.horaChegada.format(timeFormatter);
        }
        
        public String getHoraSaida(){
            return this.horaSaida.format(timeFormatter);
        }

        public LocalDate getData(){
            return this.data;
        }
    }
