package entidades.pagamentos;

public class TestePagamentoDefault extends Pagamento{
    public TestePagamentoDefault(String idPagamento, Double valorPagamento, String status) {
        super(idPagamento, valorPagamento, status);
    }

    @Override
    protected void executar() {
    }

    @Override
    protected String getTipoPagamento() {
        return "";
    }
}
