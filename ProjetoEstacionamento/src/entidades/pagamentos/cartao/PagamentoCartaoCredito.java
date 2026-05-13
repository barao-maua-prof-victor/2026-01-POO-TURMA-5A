package entidades.pagamentos.cartao;

import entidades.pagamentos.Pagamento;

import java.time.LocalDateTime;

public class PagamentoCartaoCredito extends Pagamento {
    private int numeroCartao;
    private int cvv;

    public PagamentoCartaoCredito(Double valorPagamento, LocalDateTime dataHoraPagamento, int numeroCartao, int cvv) {
        super(valorPagamento, dataHoraPagamento);
        this.numeroCartao = numeroCartao;
        this.cvv = cvv;
    }

    public int getNumeroCartao() {
        return numeroCartao;
    }

    public int getCvv() {
        return cvv;
    }

    @Override
    protected void executar() {
        System.out.println("[PAGAMENTO " + this.getTipoPagamento() + "]" +
                "Efetuado pagamento de R$: " + this.getValorPagamento() +
                "No cartão de número: " + this.getNumeroCartao());
    }

    @Override
    protected String getTipoPagamento() {
        return "CREDITO";
    }

    public void setNumeroCartao(int numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }
}
