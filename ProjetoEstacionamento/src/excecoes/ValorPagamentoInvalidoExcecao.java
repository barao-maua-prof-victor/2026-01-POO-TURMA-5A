package excecoes;

public class ValorPagamentoInvalidoExcecao extends RuntimeException {
    private final Double valorInvalido;

    public ValorPagamentoInvalidoExcecao(Double valorInvalido) {
        super("Valor de R$: " + valorInvalido + " inválido");
        this.valorInvalido = valorInvalido;
    }

    public Double getValorInvalido() {
        return valorInvalido;
    }
}
