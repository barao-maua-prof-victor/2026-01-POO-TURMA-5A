import java.util.ArrayList;

public class Main2 {

    public static void main(String[] args){
        Pedido2 meuPedido2 = new Pedido2();
        ItemDoPedido cocaZeroLata = new ItemDoPedido(2, "Coca Zero Lata", 4.99);
        ItemDoPedido xTudo = new ItemDoPedido(1, "X-Tudo", 19.99);
        ItemDoPedido batataFrita = new ItemDoPedido(3, "Batata Frita", 9.99);
        ItemDoPedido anelDeCebola = new ItemDoPedido(4, "Anel de Cebola", 24.99);

        meuPedido2.adicionarNovoItem(cocaZeroLata);
        meuPedido2.adicionarNovoItem(xTudo);
        meuPedido2.adicionarNovoItem(batataFrita);
        meuPedido2.adicionarNovoItem(anelDeCebola);

        System.out.println(meuPedido2);


        Pedido2 pedidoDoVictor = new Pedido2();
        System.out.println(pedidoDoVictor);




    }
}

class Pedido2{

    double valorTotal;
    ArrayList<ItemDoPedido> itens;
    public Pedido2() {
        this.itens = new ArrayList<>();
        this.valorTotal = 0.0;
    }

    void adicionarNovoItem(ItemDoPedido novoItem){
        System.out.println(novoItem);
        if(this.itens.size() < 3){
            this.itens.add(novoItem);
            this.valorTotal += (novoItem.quantidade * novoItem.valorUnitario);
            System.out.println("Item Adicionado com sucesso");
        }else{
            System.out.println("Pedido cheio!!!!!");
        }
    }

    @Override
    public String toString() {
        return "Pedido2{" +
                "valorTotal=" + valorTotal +
                ", itens=" + itens +
                '}';
    }
}

class ItemDoPedido{
    int quantidade;
    String nome;
    double valorUnitario;

    public ItemDoPedido(int quantidade, String nome, double valorUnitario) {
        this.quantidade = quantidade;
        this.nome = nome;
        this.valorUnitario = valorUnitario;
    }

    @Override
    public String toString() {
        return "ItemDoPedido{" +
                "quantidade=" + quantidade +
                ", nome='" + nome + '\'' +
                ", valorUnitario=" + valorUnitario +
                '}';
    }
}