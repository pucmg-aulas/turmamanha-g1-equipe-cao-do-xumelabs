    /*
    * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
    * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
    */
    package mvc.model;

    import java.io.Serializable;
import java.time.Duration;
    import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

    public class UsoDeVaga implements Serializable {

        private static final long serialVersionUID = 1L;

        private Veiculo veiculo;
        private Vaga vaga;
        private LocalDateTime horaChegada;
        private LocalDate data;
        private LocalDateTime horaSaida;
        private Duration tempoUsado;
        private double valorAPagar;
        private boolean status;
        private static final int MAX_COBRANCA;
        private static final int VALOR_FRACAO;
        private static final int TEMPO_FRACAO;

        static {
            MAX_COBRANCA = 50;
            VALOR_FRACAO = 4;
            TEMPO_FRACAO = 15;
        }

    // Construtor para criar o uso de vaga 
        public UsoDeVaga(Veiculo veiculo, Vaga vaga) {
            this.veiculo = veiculo;
            this.vaga = vaga;
            this.horaSaida = null;
            this.status = false;
            this.tempoUsado = null;
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
                this.status = true;
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
            valorAPagar = (this.calcularTempoUsado() / TEMPO_FRACAO) * VALOR_FRACAO;
            valorAPagar = valorAPagar * this.vaga.calcularAjuste();

            if (valorAPagar >= MAX_COBRANCA) {
                valorAPagar = MAX_COBRANCA;
            }

            return valorAPagar;
        }

        public Veiculo getVeiculo() {
            return this.veiculo;
        }

        public Vaga getVaga() {
            return this.vaga;
        }

        public LocalDate getData(){
            return this.data;
        }

        public LocalDateTime getHoraEntrada(){
            return this.horaChegada;
        }

        public LocalDateTime getHoraSaida(){
            return this.horaSaida;
        }

        public double getValorAPagar(){
            return this.valorAPagar;
        }

        public boolean getStatus(){
            return this.status;
        }

        public void setHoraChegada(LocalDateTime horaChegada) {
            this.horaChegada = horaChegada;
        }

        public void setData(LocalDate data) {
            this.data = data;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }

        public void setHoraSaida(LocalDateTime horaSaida) {
            this.horaSaida = horaSaida;
        }

        public void setTempoUsado(Duration tempoUsado) {
            this.tempoUsado = tempoUsado;
        }

        public void setValorAPagar(double valorAPagar) {
            this.valorAPagar = valorAPagar;
        }

    }