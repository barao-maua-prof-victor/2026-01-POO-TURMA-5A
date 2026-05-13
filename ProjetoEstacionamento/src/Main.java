import entidades.estacionamento.Ticket;
import entidades.pagamentos.cartao.PagamentoCartaoCredito;
import entidades.pagamentos.pix.PagamentoPix;

import java.time.LocalDateTime;
import java.util.UUID;

public class Main {
    public static void main(String[] args){

        Ticket meuTicket = new Ticket(
                "ABC-1234",
                4,
                30,
                25.0
        );

        LocalDateTime dataTeste = LocalDateTime.parse("2026-05-12T22:30:00");
        Double valorPagamento = meuTicket.calcularValorTotaParaPagamento(dataTeste);

        PagamentoPix pagamentoPix = new PagamentoPix(
                valorPagamento,
                dataTeste,
                "JRF4LKGJF2983RHV824HR89"
        );
        System.out.println(pagamentoPix);
        pagamentoPix.processarPagamento();
        System.out.println(pagamentoPix);
//
//        PagamentoCartaoCredito pagamentoCartaoCredito = new PagamentoCartaoCredito(
//                "f34qf3q",
//                20.0,
//                213,
//                123
//        );
//        System.out.println(pagamentoCartaoCredito);
    }
}
