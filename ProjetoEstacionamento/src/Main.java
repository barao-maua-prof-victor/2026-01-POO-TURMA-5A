import entidades.pagamentos.cartao.PagamentoCartaoCredito;
import entidades.pagamentos.pix.PagamentoPix;

public class Main {
    public static void main(String[] args){
        PagamentoPix pagamentoPix = new PagamentoPix(
                "w43t",
                10.0,
                "AGUARDANDO_PAGAMENTO",
                "JRF4LKGJF2983RHV824HR89"
        );
        pagamentoPix.processarPagamento();

        PagamentoCartaoCredito pagamentoCartaoCredito = new PagamentoCartaoCredito(
                "f34qf3q",
                20.0,
                "AGUARDANDO",
                213,
                123
        );
        pagamentoCartaoCredito.processarPagamento();

    }
}
