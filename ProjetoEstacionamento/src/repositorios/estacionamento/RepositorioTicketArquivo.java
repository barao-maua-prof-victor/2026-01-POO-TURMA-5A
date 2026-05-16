package repositorios.estacionamento;

import entidades.estacionamento.Ticket;

import java.util.Optional;
import java.util.UUID;

public class RepositorioTicketArquivo implements RepositorioTicket{
    @Override
    public void salvar(Ticket ticket) {

    }

    @Override
    public void atualizar(Ticket ticket) {

    }

    @Override
    public Optional<Ticket> buscarPorId(UUID id) {
        return Optional.empty();
    }

    @Override
    public Optional<Ticket> buscarEmAndamentoPorPlaca(String placa) {
        return Optional.empty();
    }
}
