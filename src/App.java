import java.util.ArrayList;
import java.util.Scanner;

public class App {
    private Scanner entrada;
    private listaUsuarios listaUsuarios;

    public App() {
        entrada = new Scanner(System.in);
        listaUsuarios = new listaUsuarios();

        Usuario u;
        u = new Funcionario(1, "Júnior Stahl", 1, new DFinanceiro());
        listaUsuarios.adicionarUsuario(u);
        u = new Admin(1, "Maria Melloni", 2);
        listaUsuarios.adicionarUsuario(u);
    }

    public void executar() {
        System.out.println("Bem-vindo a TechSolutions Ltda");
        int opcao = 0;

        listaUsuarios();
        int opcaoFuncionario;
        do {
            System.out.println("Digite o usuário que gostaria de logar:");
            opcaoFuncionario = entrada.nextInt();
            if (opcaoFuncionario > listaUsuarios.getUsuarios().size() - 1 || opcaoFuncionario < 0) {
                System.out.println("Usuário inválido. Redigite.");
            }
        } while (opcaoFuncionario > listaUsuarios.getUsuarios().size() - 1 || opcaoFuncionario < 0);

        Usuario usuario = listaUsuarios.getUsuario(opcaoFuncionario);
        if (usuario.getTipo() == 2) {
            do {
                menuAdmin();
                System.out.print("Digite a opção desejada: ");
                opcao = entrada.nextInt();
                switch (opcao) {
                    case 0:
                        break;
                    default:
                        System.out.println("Opcão inválida. Redigite.");
                }
            }
            while (opcao != 0);
        } else if (usuario.getTipo() == 1) {
            do {
                operacoes();
                System.out.print("Digite a opção desejada: ");
                opcao = entrada.nextInt();
                switch (opcao) {
                    case 0:
                        break;
                    default:
                        System.out.println("Opcão inválida. Redigite.");
                }
            }
            while (opcao != 0);
        }
    }

    public void listaUsuarios() {
        ArrayList<Usuario> usuarios = listaUsuarios.getUsuarios();
        System.out.println("\nUSUÁRIOS CADASTRADOS:");
        for (int i = 0; i < usuarios.size(); i++) {
            System.out.println("[" + i + "]: " + usuarios.get(i).getNome());
        }
        System.out.println();
    }

    public void operacoes() {
        System.out.println("[6] Registrar novo pedido de aquisição");
        System.out.println("[7] Excluir pedido");
        System.out.println("[8] Reabrir pedido");
        System.out.println("[9] Trocar usuário");
        System.out.println("[10] Sair\n");
    }

    public void menuAdmin() {
        System.out.println("\nPERMISSÕES ADMIN: ");
        System.out.println("[1] Avaliar pedido aberto (aprovar/rejeitar)");
        System.out.println("[2] Listar todos os pedidos entre duas datas");
        System.out.println("[3] Buscar pedidos por funcionário solicitante");
        System.out.println("[4] Buscar pedidos pela descrição de um item");
        System.out.println("[5] Visualizar detalhes de um pedido");

        System.out.println("\nPERMISSÕES GERAIS: ");
        operacoes();
    }
}