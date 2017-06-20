import java.util.Scanner;

public class Exercicio01 {
    public static void main(String[] args) {
        try (final Scanner scanner = new Scanner(System.in)) {
            System.out.print("Digite um número inteiro: ");
            int numero = Integer.parseInt(scanner.nextLine());

            System.out.println(String.format("O número digitado é %s.", numero%2==0 ? "par": "ímpar"));
        } catch(NumberFormatException e) {
            System.out.println("Ocorreu um erro: Você não digitou um número inteiro!");
        } catch (Exception e) {
            System.out.println("Ocorreu um erro: " + e.getMessage());
        }
    }
}