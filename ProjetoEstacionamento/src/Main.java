import repositorios.estacionamento.RepositorioTicket;
import repositorios.estacionamento.RepositorioTicketEmMemoria;
import ui.Menu;

public class Main {
    public static void main(String[] args) {

        RepositorioTicket repo = new RepositorioTicketEmMemoria();
        Menu menu = new Menu(repo);
        menu.iniciar();

    }
}
