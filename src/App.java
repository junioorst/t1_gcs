import java.util.ArrayList;
import java.util.Scanner;

public class App {
    private Scanner entrada;
    private Empresa empresa;

    public App() {
        entrada = new Scanner(System.in);
        this.empresa = new Empresa();
        empresa.cadastrarUsuario(new Usuario(1, "Júnior", 2));
        empresa.cadastrarUsuario(new Usuario(1, "Maria", 1));
        empresa.cadastrarUsuario(new Usuario(1, "João", 1));
    }

    public void executar() {
        System.out.println("Bem-vindo a TechSolutions Ltda");
        int opcao = 0;

        listaUsuarios();
        int opcaoFuncionario;
        do {
            System.out.println("Digite o usuário que gostaria de logar:");
            opcaoFuncionario = entrada.nextInt();
            if (opcaoFuncionario > empresa.getFuncionarios().size() - 1 || opcaoFuncionario < 0) {
                System.out.println("Usuário inválido. Redigite.");
            }
        } while (opcaoFuncionario > empresa.getFuncionarios().size() - 1 || opcaoFuncionario < 0);

        Usuario usuario = empresa.getFuncionario(opcaoFuncionario);
        if (usuario.getTipo() == 2) {
            do {
                menuAdmin();
                System.out.print("Digite a opção desejada: ");
                opcao = entrada.nextInt();
                switch (opcao) {
                    case 14:
                        break;
                    default:
                        System.out.println("Opcão inválida. Redigite.");
                }
            }
            while (opcao != 14);
        } else if (usuario.getTipo() == 1) {
            do {
                menuFuncionario();
                System.out.print("Digite a opção desejada: ");
                opcao = entrada.nextInt();
                switch (opcao) {
                    case 5:
                        break;
                    default:
                        System.out.println("Opcão inválida. Redigite.");
                }
            }
            while (opcao != 5);
        }
    }

    public void listaUsuarios() {
        ArrayList<Usuario> usuarios = empresa.getFuncionarios();
        System.out.println("\nUSUÁRIOS CADASTRADOS:");
        for (int i = 0; i < usuarios.size(); i++) {
            System.out.println("[" + i + "]: " + usuarios.get(i).getNome());
        }
        System.out.println();
    }

    public void menuFuncionario() {
        System.out.println("\n==== PERMISSÕES GERAIS ====");
        System.out.println("[1] Registrar novo pedido de aquisição");
        System.out.println("[2] Excluir pedido");
        System.out.println("[3] Reabrir pedido");
        System.out.println("[4] Trocar usuário");
        System.out.println("[5] Sair\n");
    }

    public void menuAdmin() {
        System.out.println("\n==== PERMISSÕES ADMIN ====");
        System.out.println("[1] Avaliar pedido aberto (aprovar/rejeitar)");
        System.out.println("[2] Listar todos os pedidos entre duas datas");
        System.out.println("[3] Buscar pedidos por funcionário solicitante");
        System.out.println("[4] Buscar pedidos pela descrição de um item");
        System.out.println("[5] Visualizar detalhes de um pedido");
        System.out.println("[6] Número total de pedidos (aprovados/reprovados)");
        System.out.println("[7] Número de pedidos dos últimos 30 dias e valor médio");
        System.out.println("[8] Valor total de cada categoria nos últimos 30 dias");
        System.out.println("[9] Pedido de maior valor ainda aberto");

        System.out.println("\n==== PERMISSÕES GERAIS ====");
        System.out.println("[10] Registrar novo pedido de aquisição");
        System.out.println("[11] Excluir pedido");
        System.out.println("[12] Reabrir pedido");
        System.out.println("[13] Trocar usuário");
        System.out.println("[14] Sair\n");
    }
}