package entidades.pagamentos.pix;

import entidades.pagamentos.Pagamento;

import java.time.LocalDateTime;

public class PagamentoPix extends Pagamento {
    private String codigoPix;

    public PagamentoPix(Double valorPagamento, LocalDateTime dataHoraPagamento, String codigoPix) {
        super(valorPagamento, dataHoraPagamento);
        this.codigoPix = codigoPix;
    }

    @Override
    protected void executar() {
        System.out.println("[PAGAMENTO " + this.getTipoPagamento() + "]" +
                "Efetuado pagamento de R$: " + this.getValorPagamento());
    }

    @Override
    protected String getTipoPagamento() {
        return "PIX";
    }

    private String getCodigoPix() {
        return codigoPix;
    }
}
