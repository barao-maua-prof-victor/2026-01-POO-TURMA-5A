package excecoes;

public class OrdemCronologicaInvalidaExcecao extends RuntimeException {
    public OrdemCronologicaInvalidaExcecao(
            String dataPassadoNome,
            String dataPassadoValor,
            String dataFuturoNome,
            String dataFuturoValor) {
        super("Data %s (%s) não pode ser antes da data %s (%s)".formatted(
                        dataFuturoNome, dataFuturoValor, dataPassadoNome, dataPassadoValor
                )
        );
    }
}
