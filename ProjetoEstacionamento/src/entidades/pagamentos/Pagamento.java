package entidades.pagamentos;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class Pagamento {
    private final UUID idPagamento;
    private final Double valorPagamento;
    private StatusPagamento status;
    private final LocalDateTime dataHoraPagamento;

    public Pagamento(Double valorPagamento, LocalDateTime dataHoraPagamento) {
        this.idPagamento = UUID.randomUUID();
        this.valorPagamento = valorPagamento;
        this.status = StatusPagamento.AGUARDANDO_PAGAMENTO;
        this.dataHoraPagamento = dataHoraPagamento;
    }
    // Public
    public void processarPagamento(){
        this.registrarLogInicioProcessamento();
        this.validarValor();
        this.executar();
        this.registrarLogFimProcessamento();
        this.status = StatusPagamento.PAGO;
    }

    // Protected
    protected abstract void executar();
    protected abstract String getTipoPagamento();
    protected void validarValor(){
        if (this.getValorPagamento() < 0){
            System.out.println("Valor de pagamento inválido");
        }
    }
    protected Double getValorPagamento(){
        return this.valorPagamento;
    }

    // Default
    void registrarLogInicioProcessamento(){
        System.out.println("[LOG] [INFO] Iniciado pagamento de id "
                + this.getIdPagamento());
    }
    void registrarLogFimProcessamento(){
        System.out.println("[LOG] [INFO] Finalizado pagamento de id "
                + this.getIdPagamento());
    }

    // Private
    private UUID getIdPagamento(){
        return this.idPagamento;
    }

    public StatusPagamento getStatus() {
        return status;
    }

    public LocalDateTime getDataHoraPagamento() {
        return dataHoraPagamento;
    }

    @Override
    public String toString() {
        return "Pagamento{" +
                "idPagamento=" + idPagamento +
                ", valorPagamento=" + valorPagamento +
                ", status=" + status +
                ", dataHoraPagamento=" + dataHoraPagamento +
                '}';
    }
}
