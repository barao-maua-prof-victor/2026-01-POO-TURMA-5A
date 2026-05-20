package repositorios.estacionamento;

import entidades.estacionamento.Ticket;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RepositorioTicket {

    void salvar(Ticket ticket);
    void atualizar(Ticket ticket);
    Optional<Ticket> buscarPorId(UUID id);
    Optional<Ticket> buscarEmAndamentoPorPlaca(String placa);
    Optional<Ticket> buscarPagamentoEfetuadoPorPlaca(String placa);
    List<Ticket> buscarTodos();
}
