package repositorios.estacionamento;

import entidades.estacionamento.StatusTicket;
import entidades.estacionamento.Ticket;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class RepositorioTicketEmMemoria implements RepositorioTicket{

    private final List<Ticket> armazenamento = new ArrayList<>();

    @Override
    public void salvar(Ticket ticket) {
        this.armazenamento.add(ticket);
        System.out.println("[REPO] Ticket salvo com sucesso: " + ticket.getId());
    }

    @Override
    public void atualizar(Ticket ticket) {
        for (int i = 0; i < this.armazenamento.size(); i++) {
            if (this.armazenamento.get(i).getId().equals(ticket.getId())) {
                this.armazenamento.set(i, ticket);
                System.out.println("[REPO] Ticket atualizado: " + ticket.getId());
                return;
            }
        }
        System.out.println("[REPO] Ticket não encontrado para atualizar: " + ticket.getId());
    }

    @Override
    public Optional<Ticket> buscarPorId(UUID id) {
        for (Ticket ticket : this.armazenamento) {
            if (ticket.getId().equals(id)) {
                return Optional.of(ticket);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Ticket> buscarEmAndamentoPorPlaca(String placa) {
        for (Ticket ticket : this.armazenamento) {
            if (ticket.getPlacaVeiculo().equals(placa)
                    && ticket.getStatus() == StatusTicket.EM_ANDAMENTO) {
                return Optional.of(ticket);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Ticket> buscarTodos() {
        return this.armazenamento;
    }

    @Override
    public Optional<Ticket> buscarPagamentoEfetuadoPorPlaca(String placa) {
        for (Ticket ticket : this.armazenamento) {
            if (ticket.getPlacaVeiculo().equals(placa)
                    && ticket.getStatus() == StatusTicket.PAGAMENTO_EFETUADO) {
                return Optional.of(ticket);
            }
        }
        return Optional.empty();
    }


}
