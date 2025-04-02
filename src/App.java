import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class App {
    private Scanner entrada; // entrada de dados

}


 //Construtor da aplicacao
public App() {
    entrada = new Scanner(System.in);   // Usar o teclado
    entrada.useLocale(Locale.ENGLISH);  // Usar . como decimal
}

//Executa a aplicacao termina quando o usuario digitar a opcao 0
public void executar() {
    System.out.println("TechSolutions Ltda");
    int opcao = 0;
    do {
        menu();
        System.out.print("Digite a opcao desejada: ");
        opcao = entrada.nextInt();
        entrada.nextLine();
        switch (opcao) {
            case 0:
                break;
            default:
                System.out.println("Opcao invalida. Redigite.");
        }
    }
    while (opcao != 0);
}
