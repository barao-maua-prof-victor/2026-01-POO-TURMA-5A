package entidades.pagamentos;

public abstract class Pagamento {
    private String idPagamento;
    private Double valorPagamento;
    private String status;

    public Pagamento(String idPagamento, Double valorPagamento, String status) {
        this.idPagamento = idPagamento;
        this.valorPagamento = valorPagamento;
        this.status = status;
    }
    // Public
    public void processarPagamento(){
        this.registrarLogInicioProcessamento();
        this.validarValor();
        this.executar();
        this.registrarLogFimProcessamento();
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
    private String getIdPagamento(){
        return this.idPagamento;
    }
}
