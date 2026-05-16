package entidades.estacionamento;

import entidades.pagamentos.Pagamento;
import utils.DateTimeUtils;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public class Ticket {
    private final UUID id;
    private final String plavaVeiculo;
    private final Integer intervaloDeCobranca;
    private final Integer margemTempoParaSaidaEmMinutos;
    private final Double valorUnitarioIntervaloDeCobranca;
    private final LocalDateTime dataHoraEntrada;
    private LocalDateTime dataHoraSaida;
    private LocalDateTime dataHoraPermitidaSaida;
    private LocalDateTime dataHoraPagamento;
    private Integer tempoDePermanencia;
    private Integer totalIntervaloDeCobranca;
    private Double valorTotal;
    private StatusTicket status;
    private Pagamento pagamento;

    public Ticket(
            String plavaVeiculo,
            Integer intervaloDeCobranca,
            Integer margemTempoParaSaidaEmMinutos,
            Double valorUnitarioIntervaloDeCobranca) {
        this.id = UUID.randomUUID();
        this.plavaVeiculo = plavaVeiculo;
        this.intervaloDeCobranca = intervaloDeCobranca;
        this.margemTempoParaSaidaEmMinutos = margemTempoParaSaidaEmMinutos;
        this.valorUnitarioIntervaloDeCobranca = valorUnitarioIntervaloDeCobranca;
        this.dataHoraEntrada = LocalDateTime.now();
        this.dataHoraSaida = null;
        this.dataHoraPermitidaSaida = null;
        this.dataHoraPagamento = null;
        this.tempoDePermanencia = null;
        this.totalIntervaloDeCobranca = null;
        this.valorTotal = null;
        this.status = StatusTicket.EM_ANDAMENTO;
        this.pagamento = null;
    }

    // Getters
    // Data Hora Entrada
    public LocalDateTime getDataHoraEntrada() {
        return dataHoraEntrada;
    }
    public String getDataHoraEntradaFormatada(){
        return DateTimeUtils.formatarDataHoraPadrao(this.getDataHoraEntrada());
    }
    // Data Hora Saída
    public Optional<LocalDateTime> getDataHoraSaida() {
        return Optional.ofNullable(this.dataHoraSaida);
    }

    public String getDataHoraSaidaFormatada(){
        return this.getDataHoraSaida()
                .map(DateTimeUtils::formatarDataHoraPadrao)
                .orElse("VAZIO");
    }

    // Data Hora Permitida Saída
    public Optional<LocalDateTime> getDataHoraPermitidaSaida() {
        return Optional.ofNullable(this.dataHoraPermitidaSaida);
    }

    public String getDataHoraPermitidaSaidaFormatada(){
        return this.getDataHoraPermitidaSaida()
                .map(DateTimeUtils::formatarDataHoraPadrao)
                .orElse("VAZIO");
    }

    // Data Hora Pagamento
    public Optional<LocalDateTime> getDataHoraPagamento() {
        return Optional.ofNullable(this.dataHoraPagamento);
    }

    public String getDataHoraPagamentoFormatada(){
        return this.getDataHoraPagamento()
                .map(DateTimeUtils::formatarDataHoraPadrao)
                .orElse("VAZIO");
    }

    // Regras de Negócio
    private int calcularTempoDePermanencia(LocalDateTime agora){
        return (int) DateTimeUtils.calcularMinutosEntreDatas(
                this.getDataHoraEntrada(),
                agora
        );
    }

    private int calcularIntervalosDeCobranca(int tempoPermanenciaMinutos){
        double tempoPermanenciaHoras = tempoPermanenciaMinutos / 60.0;
        return (int) Math.ceil(
                tempoPermanenciaHoras / this.intervaloDeCobranca
        );
    }

    private Double calcularValorTotal(int totalIntervaloCobrancas){
        return totalIntervaloCobrancas * this.valorUnitarioIntervaloDeCobranca;
    }

    public Double calcularValorTotaParaPagamento(LocalDateTime dataHoraPagamento){
        System.out.println("Data Hora Entrada: " + this.getDataHoraEntradaFormatada());
        System.out.println(
                "Data Hora Cálculo: " + DateTimeUtils.formatarDataHoraPadrao(dataHoraPagamento)
        );
        int totalTempoPermanenciaMinutos = this.calcularTempoDePermanencia(dataHoraPagamento);
        System.out.println("Tempo de Permanência(MINUTOS): " + totalTempoPermanenciaMinutos);
        int totalIntervalosDeCobranca = this.calcularIntervalosDeCobranca(
                totalTempoPermanenciaMinutos
        );
        System.out.println("Total Intervalos: " + totalIntervalosDeCobranca);
        double valorTotal = this.calcularValorTotal(totalIntervalosDeCobranca);
        System.out.println("Valor total R$: " + valorTotal);
        return valorTotal;
    }

    private void calcularDataHoraPermitidaSaida(){
        this.getDataHoraPagamento().ifPresent(dataHoraPagamento ->
            this.dataHoraPermitidaSaida = DateTimeUtils.adicionarTempoEmMinutos(
                    dataHoraPagamento,
                    this.margemTempoParaSaidaEmMinutos
            )
        );
    }

    public void registrarPagamento(Pagamento novoPagamento){
        this.dataHoraPagamento = novoPagamento.getDataHoraPagamento();
        this.pagamento = novoPagamento;
        this.calcularDataHoraPermitidaSaida();
        this.tempoDePermanencia = this.calcularTempoDePermanencia(dataHoraPagamento);
        this.totalIntervaloDeCobranca = this.calcularIntervalosDeCobranca(
                this.tempoDePermanencia
        );
        this.valorTotal = this.calcularValorTotal(this.totalIntervaloDeCobranca);
        this.status = StatusTicket.PAGAMENTO_EFETUADO;
    }

    public boolean temPermissaoParaSair(){
        return this.getDataHoraPermitidaSaida()
                .map(dataHoraPermitidaSaida ->
                        LocalDateTime.now().isBefore(dataHoraPermitidaSaida)
                        && this.status == StatusTicket.PAGAMENTO_EFETUADO
                ).orElse(false);
    }

    public void registrarSaida(){
        this.dataHoraSaida = LocalDateTime.now();
        this.status = StatusTicket.FINALIZADO;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", plavaVeiculo='" + plavaVeiculo + '\'' +
                ", intervaloDeCobranca=" + intervaloDeCobranca +
                ", margemTempoParaSaidaEmMinutos=" + margemTempoParaSaidaEmMinutos +
                ", valorUnitarioIntervaloDeCobranca=" + valorUnitarioIntervaloDeCobranca +
                ", dataHoraEntrada=" + this.getDataHoraEntradaFormatada() +
                ", dataHoraSaida=" + this.getDataHoraSaidaFormatada() +
                ", dataHoraPermitidaSaida=" + this.getDataHoraPermitidaSaidaFormatada() +
                ", dataHoraPagamento=" + this.getDataHoraPagamentoFormatada() +
                ", tempoDePermanencia=" + tempoDePermanencia +
                ", totalIntervaloDeCobranca=" + totalIntervaloDeCobranca +
                ", valorTotal=" + valorTotal +
                ", status=" + status +
                ", pagamento=" + pagamento +
                '}';
    }
}
