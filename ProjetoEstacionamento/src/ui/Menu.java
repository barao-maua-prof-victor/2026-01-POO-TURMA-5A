package ui;

import entidades.estacionamento.Ticket;
import entidades.pagamentos.Pagamento;
import entidades.pagamentos.cartao.PagamentoCartaoCredito;
import entidades.pagamentos.pix.PagamentoPix;
import excecoes.OrdemCronologicaInvalidaExcecao;
import excecoes.PlacaComTicketEmAndamentoExcecao;
import excecoes.PlacaVeiculoNaoEncontradaExcecao;
import excecoes.ValorPagamentoInvalidoExcecao;
import repositorios.estacionamento.RepositorioTicket;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;

public class Menu {

    private final Scanner scanner = new Scanner(System.in);
    private final RepositorioTicket repositorioTicket;
    private final LocalDateTime dataHoraConsultaTeste = LocalDateTime.parse("2026-05-20T22:30:00");

    private final Integer intervaloDeCobranca = 2;
    private final Integer margemTempoParaSaidaEmMinutos = 30;
    private final Double valorUnitarioIntervaloDeCobranca = 10.0;

    public Menu(RepositorioTicket repositorioTicket) {
        this.repositorioTicket = repositorioTicket;
    }

    public void iniciar(){
        int opcao;
        do {
            this.exibirMenu();
            opcao = this.lerInteiro();
            try {
                switch (opcao) {
                    case 1 -> this.registrarNovaEntrada();
                    case 2 -> this.consultarValorAPagar();
                    case 3 -> this.realizarPagamento();
                    case 4 -> this.registrarSaida();
                    case 5 -> this.listarTickets();
                    case 0 -> System.out.println("Encerrando o sistema");
                }
            }catch (PlacaVeiculoNaoEncontradaExcecao | PlacaComTicketEmAndamentoExcecao |
                    OrdemCronologicaInvalidaExcecao | ValorPagamentoInvalidoExcecao e) {
                System.out.println(e.getMessage());
            }
        }while(opcao!=0);
    }

    private int lerInteiro() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void exibirMenu() {
        System.out.println("\n=== ESTACIONAMENTO ===");
        System.out.println("1. Nova entrada");
        System.out.println("2. Consultar valor a pagar");
        System.out.println("3. Pagar");
        System.out.println("4. Registrar saída");
        System.out.println("5. Listar");
        System.out.println("0. Sair");
        System.out.print("Opção: ");
    }

    // Helpers
    private String lerPlaca(){
        System.out.print("Placa do veículo: ");
        return scanner.nextLine().toUpperCase();
    }

    private Ticket buscarTicketEmAndamentoPorPlaca() {
        String placa = this.lerPlaca();
        Optional<Ticket> ticket = this.repositorioTicket.buscarEmAndamentoPorPlaca(placa);
        if (ticket.isEmpty()){
            throw new PlacaVeiculoNaoEncontradaExcecao(placa);
        }
        return ticket.get();
    }

    private Ticket buscarTicketComPagamentoEfetuadoPorPlaca(){
        String placa = this.lerPlaca();
        Optional<Ticket> ticket = this.repositorioTicket.buscarPagamentoEfetuadoPorPlaca(placa);
        if (ticket.isEmpty()){
            throw new PlacaVeiculoNaoEncontradaExcecao(placa);
        }
        return ticket.get();
    }

    // Métodos das opções de menu
    private void registrarNovaEntrada(){
        String placa = this.lerPlaca();

        if (this.repositorioTicket.buscarEmAndamentoPorPlaca(placa).isPresent()) {
            throw new PlacaComTicketEmAndamentoExcecao(placa);
        }

        Ticket ticket = new Ticket(
                placa,
                this.intervaloDeCobranca,
                this.margemTempoParaSaidaEmMinutos,
                this.valorUnitarioIntervaloDeCobranca
        );
        this.repositorioTicket.salvar(ticket);
        System.out.println("Entrada registrada!");
        System.out.println("ID do ticket : " + ticket.getId());
        System.out.println("Hora de entrada: " + ticket.getDataHoraEntradaFormatada());
    }

    private void consultarValorAPagar(){
            Ticket ticket = this.buscarTicketEmAndamentoPorPlaca();
            // Double valorTotal = ticketEncontrado.calcularValorTotalParaPagamento(LocalDateTime.now()); // Produção
            Double valorTotal = ticket.calcularValorTotalParaPagamento(this.dataHoraConsultaTeste);
            System.out.println("Valor Total a pagar R$: " + valorTotal);
    }

    private void realizarPagamento(){
        Ticket ticket = this.buscarTicketEmAndamentoPorPlaca();
        Double valor = ticket.calcularValorTotalParaPagamento(this.dataHoraConsultaTeste);

        Pagamento pagamento;
        // LocalDateTime dataHoraPagamento = LocalDateTime.now() // Produção
        System.out.println("\n1 - Pix\n2 - Cartão de Crédito\nOpção: ");
        switch (this.lerInteiro()) {
            case 1 -> {
                pagamento = new PagamentoPix(
                        valor,
                        this.dataHoraConsultaTeste,
                        UUID.randomUUID().toString()
                );
            }
            case 2 -> {
                System.out.print("Número do cartão: ");
                int numeroCartao = lerInteiro();
                System.out.print("CVV: ");
                int cvv = lerInteiro();
                pagamento = new PagamentoCartaoCredito(
                        valor,
                        this.dataHoraConsultaTeste,
                        numeroCartao,
                        cvv
                );
            }
            default -> {
                System.out.println("Opção inválida.");
                return;
            }
        }

        pagamento.processarPagamento();

        ticket.registrarPagamento(pagamento);
        repositorioTicket.atualizar(ticket);
    }

    private void registrarSaida(){
        Ticket ticket = this.buscarTicketComPagamentoEfetuadoPorPlaca();
        ticket.registrarSaida();
        this.repositorioTicket.atualizar(ticket);
    }

    private void listarTickets() {
        List<Ticket> tickets = this.repositorioTicket.buscarTodos();

        if (tickets.isEmpty()) {
            System.out.println("Nenhum ticket registrado.");
            return;
        }

        System.out.println("\n=== TICKETS ===");
        for (Ticket ticket : tickets) {
            System.out.printf("ID: %s | Placa: %s | Entrada: %s | Status: %s%n",
                    ticket.getId(),
                    ticket.getPlacaVeiculo(),
                    ticket.getDataHoraEntradaFormatada(),
                    ticket.getStatus()
            );
        }
    }
}
