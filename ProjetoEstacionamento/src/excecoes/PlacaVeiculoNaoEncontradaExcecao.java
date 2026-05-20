package excecoes;

public class PlacaVeiculoNaoEncontradaExcecao extends RuntimeException {
    private final String placaVeiculo;

    public PlacaVeiculoNaoEncontradaExcecao(String placaVeiculo) {
        super("Placa " + placaVeiculo + " não encontrada");
        this.placaVeiculo = placaVeiculo;
    }

    public String getPlacaVeiculo() {
        return placaVeiculo;
    }
}
