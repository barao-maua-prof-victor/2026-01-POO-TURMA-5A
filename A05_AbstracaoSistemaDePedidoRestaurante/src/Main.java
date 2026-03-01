import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        Pedido meuPedido = new Pedido();

        meuPedido.adicionarNovoItem(2, "Coca Zero Lata", 4.99);
        meuPedido.adicionarNovoItem(1, "X-Tudo", 19.99);
        meuPedido.adicionarNovoItem(3, "Batata Frita", 9.99);

        System.out.println(meuPedido.quantidadeDosItens.get(0));
        System.out.println(meuPedido.valorUnitarioDosItens.get(1));

        System.out.println(meuPedido);
    }
}

class Pedido{
    double valorTotal;

    ArrayList<Integer> quantidadeDosItens;
    ArrayList<String> nomeDosItens;
    ArrayList<Double> valorUnitarioDosItens;

    public Pedido() {
        this.quantidadeDosItens = new ArrayList<>();
        this.nomeDosItens = new ArrayList<>();
        this.valorUnitarioDosItens = new ArrayList<>();

        this.valorTotal = 0.0;
    }

    void adicionarNovoItem(int quantidade, String nome, double valorUnitario){
        this.quantidadeDosItens.add(quantidade);
        this.nomeDosItens.add(nome);
        this.valorUnitarioDosItens.add(valorUnitario);
        this.valorTotal += (quantidade * valorUnitario);
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "valorTotal=" + valorTotal +
                ", quantidadeDosItens=" + quantidadeDosItens +
                ", nomeDosItens=" + nomeDosItens +
                ", valorUnitarioDosItens=" + valorUnitarioDosItens +
                '}';
    }
}

