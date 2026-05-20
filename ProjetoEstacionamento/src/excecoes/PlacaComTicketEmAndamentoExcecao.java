package excecoes;

public class PlacaComTicketEmAndamentoExcecao extends RuntimeException {
    private String placa;

    public PlacaComTicketEmAndamentoExcecao(String placa) {
        super("Placa " + placa + " já possui ticket em andamento.");
        this.placa = placa;
    }

    public String getPlaca() {
        return placa;
    }
}
