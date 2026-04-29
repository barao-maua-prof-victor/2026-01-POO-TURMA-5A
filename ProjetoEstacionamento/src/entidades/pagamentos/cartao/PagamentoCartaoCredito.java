package entidades.pagamentos.cartao;

import entidades.pagamentos.Pagamento;

public class PagamentoCartaoCredito extends Pagamento {
    private int numeroCartao;
    private int cvv;

    public PagamentoCartaoCredito(String idPagamento, Double valorPagamento, String status, int numeroCartao, int cvv) {
        super(idPagamento, valorPagamento, status);
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
}
