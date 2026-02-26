import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite sua idade: ");
        int idadeDigitada = Integer.parseInt(scanner.nextLine());

        System.out.print("Digite seu nome: ");
        String nome = scanner.nextLine();

        System.out.println("Digite sua altura: ");
        double alturaDigitada = Double.parseDouble(scanner.nextLine());

        System.out.println("\n--- Resultado ---");
        System.out.println("Idade: " + idadeDigitada);
        System.out.println("Nome: " + nome);
        System.out.println("Altura: " + alturaDigitada);
        scanner.close();
    }
}


