package repositorios.estacionamento;

import entidades.estacionamento.StatusTicket;
import entidades.estacionamento.Ticket;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class RepositorioTicketCsv extends RepositorioCsvBase<Ticket>implements RepositorioTicket {

    public RepositorioTicketCsv(String caminhoArquivo) {
        super(caminhoArquivo);
    }

    @Override
    protected String cabecalho() {
        return "id;placa;intervalo;margem;valorUnitario;"
                + "dataHoraEntrada;dataHoraSaida;dataHoraPermitidaSaida;dataHoraPagamento;"
                + "tempoPermanencia;totalIntervalosDeCobranca;valorTotal;status;pagamentoId";
    }

    @Override
    protected String serializar(Ticket ticket) {
        return String.join(DELIMITADOR,
                ticket.getId().toString(), // 0
                ticket.getPlacaVeiculo(), // 1
                ticket.getIntervaloDeCobranca().toString(), // 2
                ticket.getMargemTempoParaSaidaEmMinutos().toString(), // 3
                ticket.getValorUnitarioIntervaloDeCobranca().toString(), // 4
                ticket.getDataHoraEntrada().toString(), // 5
                ticket.getDataHoraSaida().isEmpty() ? "" : ticket.getDataHoraSaida().get().toString(), // 6
                ticket.getDataHoraPermitidaSaida().isEmpty() ? "" : ticket.getDataHoraPermitidaSaida().get().toString(), // 7
                ticket.getDataHoraPagamento().isEmpty() ? "" : ticket.getDataHoraPagamento().get().toString(), // 8
                ticket.getTempoDePermanencia() != null ? ticket.getTempoDePermanencia().toString() : "", // 9
                ticket.getTotalIntervaloDeCobranca() != null ? ticket.getTotalIntervaloDeCobranca().toString() : "", // 10
                ticket.getValorTotal() != null ? ticket.getValorTotal().toString() : "", // 11
                ticket.getPagamento()!=null ? ticket.getPagamento().getIdPagamento().toString() : "", // 12
                ticket.getStatus().name() // 13
        );
    }

    @Override
    protected Ticket desserializar(String[] campos) {
        System.out.println(campos[6]);
        return Ticket.reconstituir(
                UUID.fromString(campos[0]),
                campos[1],
                Integer.parseInt(campos[2]),
                Integer.parseInt(campos[3]),
                Double.parseDouble(campos[4]),
                LocalDateTime.parse(campos[5]),
                campos[6].isEmpty() ? null : LocalDateTime.parse(campos[6]),
                campos[7].isEmpty() ? null : LocalDateTime.parse(campos[7]),
                campos[8].isEmpty() ? null : LocalDateTime.parse(campos[8]),
                campos[9].isEmpty() ? null : Integer.parseInt(campos[9]),
                campos[10].isEmpty() ? null : Integer.parseInt(campos[10]),
                campos[11].isEmpty() ? null : Double.parseDouble(campos[11]),
                StatusTicket.valueOf(campos[13])
        );
    }

    @Override
    public void salvar(Ticket ticket) {
        this.salvarNoArquivo(ticket);
    }

    @Override
    public void atualizar(Ticket ticket) {
        List<Ticket> atualizados = lerTodos().stream()
                .map(t -> t.getId().equals(ticket.getId()) ? ticket : t)
                .collect(Collectors.toList());
        reescrever(atualizados);
    }

    @Override
    public Optional<Ticket> buscarPorId(UUID id) {
        return lerTodos().stream().filter(t -> t.getId().equals(id)).findFirst();
    }

    @Override
    public Optional<Ticket> buscarEmAndamentoPorPlaca(String placa) {
        return lerTodos().stream()
                .filter(t -> t.getPlacaVeiculo().equals(placa))
                .filter(t -> t.getStatus() == StatusTicket.EM_ANDAMENTO)
                .findFirst();
    }

    @Override
    public Optional<Ticket> buscarPagamentoEfetuadoPorPlaca(String placa) {
        return lerTodos().stream()
                .filter(t -> t.getPlacaVeiculo().equals(placa))
                .filter(t -> t.getStatus() == StatusTicket.PAGAMENTO_EFETUADO)
                .findFirst();
    }

    @Override
    public List<Ticket> buscarTodos() {
        return this.lerTodos();
    }
}
