import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Scanner;

public class App {
    private Scanner entrada = new Scanner(System.in);  // Atributo para entrada padrao (teclado)
    private PrintStream saidaPadrao = System.out;   // Guarda a saida padrao - tela (console)
    private final String nomeArquivoEntrada = "entrada.txt";  // Nome do arquivo de entrada de dados
    private final String nomeArquivoSaida = "saida.txt";  // Nome do arquivo de saida de dados
    private Scanner entrada; // entrada de dados

}


 //Construtor da aplicacao
public App() {
    redirecionaEntrada();    // Redireciona Entrada para arquivos
    redirecionaSaida();    // Redireciona Saida para arquivos
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

// Redireciona Entrada de dados para arquivos em vez de teclado
// Chame este metodo para redirecionar a leitura de dados para arquivos
private void redirecionaEntrada() {
    try {
        BufferedReader streamEntrada = new BufferedReader(new FileReader(nomeArquivoEntrada));
        entrada = new Scanner(streamEntrada);   // Usa como entrada um arquivo
    } catch (Exception e) {
        System.out.println(e);
    }
    Locale.setDefault(Locale.ENGLISH);   // Ajusta para ponto decimal
    entrada.useLocale(Locale.ENGLISH);   // Ajusta para leitura para ponto decimal
}

// Redireciona Saida de dados para arquivos em vez da tela (terminal)
// Chame este metodo para redirecionar a escrita de dados para arquivos
private void redirecionaSaida() {
    try {
        PrintStream streamSaida = new PrintStream(new File(nomeArquivoSaida), Charset.forName("UTF-8"));
        System.setOut(streamSaida);             // Usa como saida um arquivo
    } catch (Exception e) {
        System.out.println(e);
    }
    Locale.setDefault(Locale.ENGLISH);   // Ajusta para ponto decimal
}

// Restaura Entrada padrao para o teclado
// Chame este metodo para retornar a leitura de dados para o teclado
private void restauraEntrada() {
    entrada = new Scanner(System.in);
}

// Restaura Saida padrao para a tela (terminal)
// Chame este metodo para retornar a escrita de dados para a tela
private void restauraSaida() {
    System.setOut(saidaPadrao);
}

}