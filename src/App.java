import java.util.ArrayList;
import java.util.Scanner;

public class App {
    private Scanner entrada;
    private Empresa empresa;

    public App() {
        entrada = new Scanner(System.in);
        empresa = new Empresa();
    }

    public void inicializar() {
        Departamento financeiro = new Departamento(1, "Financeiro", empresa, 3500);
        empresa.cadastrarDepartamento(financeiro);
        empresa.cadastrarUsuario(new Usuario(1, "João da Silva", 1, financeiro));
        empresa.cadastrarUsuario(new Usuario(2, "Maria Oliveira", 1, financeiro));
        empresa.cadastrarUsuario(new Usuario(3, "Carlos Pereira", 1, financeiro));

        Departamento marketing = new Departamento(2, "Marketing", empresa, 1700);
        empresa.cadastrarDepartamento(marketing);
        empresa.cadastrarUsuario(new Usuario(4, "Ana Souza", 1, marketing));
        empresa.cadastrarUsuario(new Usuario(5, "Bruno Costa", 1, marketing));

        Departamento comercial = new Departamento(3, "Comercial", empresa, 6500);
        empresa.cadastrarDepartamento(comercial);
        empresa.cadastrarUsuario(new Usuario(6, "Fernanda Lima", 1, comercial));
        empresa.cadastrarUsuario(new Usuario(7, "Diego Martins", 1, comercial));
        empresa.cadastrarUsuario(new Usuario(8, "Camila Rocha", 1, comercial));

        Departamento informatica = new Departamento(4, "Informática", empresa, 11000);
        empresa.cadastrarDepartamento(informatica);
        empresa.cadastrarUsuario(new Usuario(12, "Juliana Ribeiro", 1, informatica));
        empresa.cadastrarUsuario(new Usuario(13, "Eduardo Cardoso", 1, informatica));

        Departamento recursoshumanos = new Departamento(5, "Recursos Humanos", empresa, 2000);
        empresa.cadastrarDepartamento(recursoshumanos);
        empresa.cadastrarUsuario(new Usuario(9, "Lucas Fernandes", 1, recursoshumanos));
        empresa.cadastrarUsuario(new Usuario(10, "Patrícia Gomes", 1, recursoshumanos));
        empresa.cadastrarUsuario(new Usuario(11, "Ricardo Almeida", 1, recursoshumanos));

        Departamento administrativo = new Departamento(6, "Administrativo", empresa, 5000);
        empresa.cadastrarDepartamento(administrativo);
        empresa.cadastrarUsuario(new Usuario(14, "Tatiane Moreira", 2, administrativo));
        empresa.cadastrarUsuario(new Usuario(15, "Marcelo Nunes", 2, administrativo));
    }

    public void executar() {
        inicializar();
        System.out.println("Bem-vindo a TechSolutions Ltda!");

        int opcao = 0;
        do {
            listaUsuarios();
            int opcaoFuncionario;

            do {
                System.out.println("Digite o número do usuário que gostaria de logar: ");
                opcaoFuncionario = entrada.nextInt();
                if (opcaoFuncionario > empresa.getFuncionarios().size() - 1 || opcaoFuncionario < 0) {
                    System.out.println("Usuário inválido. Redigite.");
                }
            } while (opcaoFuncionario > empresa.getFuncionarios().size() - 1 || opcaoFuncionario < 0);

            Usuario usuario = empresa.getFuncionario(opcaoFuncionario);
            if (usuario.getTipo() == 2) {
                String senhaAdmin = "teste";
                System.out.println("Olá, " + usuario.getNome() + "!");
                System.out.println("Insira a senha do administrador para prosseguir: ");
                entrada.nextLine();
                String senha = entrada.nextLine();
                if (senhaAdmin.equals(senha)) {
                    do {
                        menuAdmin();
                        System.out.print("Digite a opção desejada: ");
                        opcao = entrada.nextInt();
                        switch (opcao) {
                            case 10:
                                criarPedido(usuario);
                                break;
                            case 13:
                                break;
                            case 14:
                                break;
                            default:
                                System.out.println("Opcão inválida. Redigite.");
                        }
                    }
                    while (opcao != 14 && opcao != 13);

                } else {
                    System.out.println("Senha incorreta. Redigite.");
                }
            } else if (usuario.getTipo() == 1) {
                System.out.println("Olá, " + usuario.getNome() + "!");
                do {
                    menuFuncionario();
                    System.out.print("Digite a opção desejada: ");
                    opcao = entrada.nextInt();
                    switch (opcao) {
                        case 1:
                            criarPedido(usuario);
                            break;
                        case 4:
                            break;
                        case 5:
                            break;
                        default:
                            System.out.println("Opcão inválida. Redigite.");
                    }
                }
                while (opcao != 5 && opcao != 4);
            }
        } while (opcao == 13 || opcao == 4);
    }

    public void listaUsuarios() {
        ArrayList<Usuario> usuarios = empresa.getFuncionarios();
        System.out.println("\n==== USUÁRIOS CADASTRADOS ====");
        for (int i = 0; i < usuarios.size(); i++) {
            System.out.println("[" + i + "]: " + usuarios.get(i).getNome() + " | " + usuarios.get(i).getDepartamento().getNome());
        }
        System.out.println();
    }

    public void criarPedido(Usuario usuario) {
        System.out.println("Quantos itens deseja cadastrar no pedido: ");
        int quantidade = entrada.nextInt();

        ArrayList<Item> listaItens = new ArrayList<>();

        for (int i = 0; i < quantidade; i++) {
            System.out.println("Informe o valor do item [" + (i + 1) + "]: ");
            double valor = entrada.nextDouble();

            entrada.nextLine();

            System.out.println("Informe o nome do item [" + (i + 1) + "]: ");
            String nome = entrada.nextLine();

            listaItens.add(new Item(nome, valor));
        }

        double checkpreco = 0;

        for (Item item : listaItens) {
            checkpreco += item.getPreco();
        }

        if (checkpreco > usuario.getDepartamento().valorMaximo) {
            System.out.println("Limite excedido do departamento (" + usuario.getDepartamento().getValorMaximo() + ").");
        } else {
            System.out.println("Informe a descrição do pedido: ");
            String desc = entrada.nextLine();

            empresa.cadastrarPedido(new Pedido(usuario.getDepartamento(), usuario, listaItens, desc));
            mostrarTodosPedidos();
        }
    }

    public void mostrarTodosPedidos() {
        for (Pedido pedido : empresa.getTodosPedidos()) {
            System.out.println(pedido);
        }
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