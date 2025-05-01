import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
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
                            case 0:
                                avaliarPedido();
                                break;
                            case 1:
                                concluirPedido();
                                break;
                            case 2:
                                visualizarPorData();
                                break;
                            case 10:
                                criarPedido(usuario);
                                break;
                            case 11:
                                excluirPedido(usuario);
                                break;
                            case 12:

                            case 13:
                                break;
                            case 14:
                                mostrarEstatisticasPedidos();
                                break;
                            default:
                                System.out.println("Opcão inválida. Redigite.");
                        }
                    }
                    while (opcao != 12 && opcao != 13);

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
                        case 2:
                            excluirPedido(usuario);
                            break;
                        case 3:
                            break;
                        case 4:
                            break;
                        default:
                            System.out.println("Opcão inválida. Redigite.");
                    }
                }
                while (opcao != 4 && opcao != 3);
            }
        } while (opcao == 12 || opcao == 3);
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
            System.out.println("-=".repeat(15));
        }
    }

    /*Método excluirPedido:
    * recebe o usuário corrente que está usando o sistema
    * mostra todos os pedidos que:
    * 1 - Estivem com status "EM_ANALISE"
    * 2 - Tiverem sido feitos pelo usuário corrente
    * Solicita o ID do pedido que deseja excluir
    * Chama o método verificarPedido que busca um pedido com aquele ID
    * Se existir esse pedido, ele estiver em aberto e tiver sido feito
    * pelo usuário, então retorna o pedido e o pedido é removido.
    */
    public void excluirPedido(Usuario usuario) {
        for (Pedido pedido : empresa.getTodosPedidos()) {
            if (pedido.getFunc().equals(usuario) && pedido.getStatus().equals(Pedido.Status.EM_ANALISE)) {
                System.out.println(pedido);
                System.out.println("-=".repeat(20));
            }
        }
        System.out.println("Informe o id do pedido que deseja excluir: ");
        int id = entrada.nextInt();

        Pedido checkedPedido = verificarPedido(usuario, id);
        if(checkedPedido != null){
            empresa.getTodosPedidos().remove(checkedPedido);
            System.out.println("Pedido removido com sucesso");
        } else {
            System.out.println("O pedido deve: " +
                    "\n1 - Ter sido feito pelo seu usuário;" +
                    "\n2 - Ainda estar em aberto/em análise." +
                    "\nTente novamente.");
        }
    }

    private Pedido verificarPedido(Usuario usuario, int id){
        Pedido checkedPedido = buscarPedidoPorId(id);
        if(checkedPedido != null && checkedPedido.getFunc().equals(usuario)
                && checkedPedido.getStatus().equals(Pedido.Status.EM_ANALISE)) {
            return checkedPedido;
        }
        return null;
    }

    /*Método avaliarPedido:
    */
    public void avaliarPedido() {
        for (Pedido pedido : empresa.getTodosPedidos()) {
            if (pedido.getStatus().equals(Pedido.Status.EM_ANALISE)) {
                System.out.println(pedido);
                System.out.println("-=".repeat(20));
            }
        }

        System.out.println("Informe o ID do pedido que deseja avaliar");
        Pedido pedidoAvaliado = buscarPedidoPorId(entrada.nextInt());

        if(pedidoAvaliado != null) {
            System.out.println("Avaliar Pedido: " +
                        "\nAperte 1 para APROVAR; " + 
                        "\nAperte 2 para REJEITAR.");
            int escolha = entrada.nextInt();

            System.out.println("Confirme a avaliação repetindo (1 para APROVAR, 2 para REJEITAR)");
            int confirmacao = entrada.nextInt();

            if(escolha == confirmacao) {
                switch (escolha){
                    case 1:
                        pedidoAvaliado.setStatus(Pedido.Status.APROVADO);
                        break;
                    case 2:
                        pedidoAvaliado.setStatus(Pedido.Status.REJEITADO);
                        break;
                    default:
                        System.out.println("Escolha inválida");
                        break;
                }
            } else {
                System.out.println("Os comandos não batem! Tente novamente.");
            }
        } else {
            System.out.println("Pedido não encontrado");
        }
    }

    public void concluirPedido() {
        for (Pedido pedido : empresa.getTodosPedidos()) {
            if (pedido.getStatus().equals(Pedido.Status.APROVADO) || pedido.getStatus().equals(Pedido.Status.REJEITADO)) {
                System.out.println(pedido);
                System.out.println("-=".repeat(20));
            }
        }

        System.out.println("Informe o ID do pedido que deseja concluir");
        Pedido pedidoConcluido = buscarPedidoPorId(entrada.nextInt());

        if(pedidoConcluido != null) {
            System.out.println("Concluir Pedido: " +
                    "\nAperte 1 para CONCLUIR.");
            int escolha = entrada.nextInt();

            System.out.println("Confirme a conclusão repetindo 1 para CONCLUIR ou 2 para cancelar");
            int confirmacao = entrada.nextInt();

            if(escolha == confirmacao) {
                pedidoConcluido.setStatus(Pedido.Status.CONCLUIDO);
            } else {
                System.out.println("Comando inválido! Tente novamente.");
            }
        } else {
            System.out.println("Pedido não encontrado");
        }
    }

    public void visualizarPorData() {
        List<Pedido> pedidos = empresa.getTodosPedidos();

        System.out.println("Informe o intervalo de datas no formato dd/mm/aaaa\n");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate data1 = null;

        while (data1 == null) {
            try {
                System.out.print("\nPrimeira data: ");
                String input1 = entrada.nextLine().trim();

                if (input1.isEmpty()) {
                    throw new DateTimeParseException("Data vazia", input1, 0);
                }
                data1 = LocalDate.parse(input1, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Data inválida! Formato correto: dd/mm/aaaa");
            }
        }

        LocalDate data2 = null;
        while (data2 == null) {
            try {
                System.out.print("\nSegunda data: ");

                String input2 = entrada.nextLine().trim();
                if (input2.isEmpty()) {
                    throw new DateTimeParseException("Data vazia", input2, 0);
                }
                data2 = LocalDate.parse(input2, formatter);

                if (data2.isBefore(data1)) {
                    System.out.println("A segunda data deve ser posterior ou igual à primeira!");
                    data2 = null;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Data inválida! Formato correto: dd/mm/aaaa");
            }
        }

        if (pedidos.isEmpty()){
            System.out.println("Não há pedidos cadastrados nesse período");
        } else {
            for (Pedido pedido : pedidos) {
                boolean maiorOuIgual = pedido.getData().isAfter(data1) || pedido.getData().isEqual(data1);
                boolean menorOuIgual = pedido.getData().isBefore(data2) || pedido.getData().isEqual(data2);
                if (maiorOuIgual && menorOuIgual) {
                    System.out.println(pedido);
                    System.out.println("-".repeat(10) + "\n");
                }
            }
        }
    }

    private Pedido buscarPedidoPorId(int id) {
        for (Pedido pedido : empresa.getTodosPedidos()) {
            if (pedido.getId() == id) {
                return pedido;
            }
        }
        return null;
    }


    public void mostrarEstatisticasPedidos() {
        ArrayList<Pedido> pedidos = empresa.getTodosPedidos();
        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido cadastrado.");
            return;
        }

        int totalPedidos = pedidos.size();
        int emAnalise = 0, aprovados = 0, rejeitados = 0, concluidos = 0;

        for (Pedido pedido : pedidos) {
            switch (pedido.getStatus()) {
                case EM_ANALISE:
                    emAnalise++;
                    break;
                case APROVADO:
                    aprovados++;
                    break;
                case REJEITADO:
                    rejeitados++;
                    break;
                case CONCLUIDO:
                    concluidos++;
                    break;
            }
        }

        System.out.println("\n==== ESTATÍSTICAS DE PEDIDOS ====");
        System.out.println("Total de pedidos: " + totalPedidos);
        System.out.println("Em análise: " + emAnalise + " (" + (emAnalise * 100 / totalPedidos) +
                "%");
        System.out.println("Aprovados: " + aprovados + " (" + (aprovados * 100 / totalPedidos) +
                "%");
        System.out.println("Rejeitados: " + rejeitados + " (" + (rejeitados * 100 / totalPedidos) +
                "%");
        System.out.println("Concluídos: " + concluidos + " (" + (concluidos * 100 / totalPedidos) +
                "%");
    }


    public void avaliarPedido() {
        for (Pedido pedido : empresa.getTodosPedidos()) {
            if (pedido.getStatus().equals(Pedido.Status.EM_ANALISE)) {
                System.out.println(pedido);
                System.out.println("-=".repeat(20));
            }
        }

        System.out.println("Informe o ID do pedido que deseja avaliar");
        Pedido pedidoAvaliado = buscarPedidoPorId(entrada.nextInt());

        if (pedidoAvaliado != null) {
            if (!pedidoAvaliado.getStatus().equals(Pedido.Status.EM_ANALISE)) {
                System.out.println("Esse pedido já foi avaliado e não pode ser reaberto!");
                return;
            }

            System.out.println("Avaliar Pedido: " +
                    "\nAperte 1 para APROVAR; " +
                    "\nAperte 2 para REJEITAR.");
            int escolha = entrada.nextInt();

            System.out.println("Confirme a avaliação repetindo (1 para APROVAR, 2 para REJEITAR)");
            int confirmacao = entrada.nextInt();

            if (escolha == confirmacao) {
                switch (escolha) {
                    case 1:
                        pedidoAvaliado.setStatus(Pedido.Status.APROVADO);
                        break;
                    case 2:
                        pedidoAvaliado.setStatus(Pedido.Status.REJEITADO);
                        break;
                    default:
                        System.out.println("Escolha inválida");
                        break;
                }
            } else {
                System.out.println("Os comandos não batem! Tente novamente.");
            }
        } else {
            System.out.println("Pedido não encontrado.");
        }
    }

    public void menuFuncionario() {
        System.out.println("\n==== PERMISSÕES GERAIS ====");
        System.out.println("[1] Registrar novo pedido de aquisição");
        System.out.println("[2] Excluir pedido");
        System.out.println("[3] Trocar usuário");
        System.out.println("[4] Sair\n");
    }

    public void menuAdmin() {
        System.out.println("\n==== PERMISSÕES ADMIN ====");
        System.out.println("[0] Avaliar pedido aberto (aprovar/rejeitar)");
        System.out.println("[1] Concluir pedido avaliado");
        System.out.println("[2] Listar todos os pedidos entre duas datas");
        System.out.println("[3] Buscar pedidos por funcionário solicitante");
        System.out.println("[4] Buscar pedidos pela descrição de um item");
        System.out.println("[5] Visualizar detalhes de um pedido");
        System.out.println("[6] Número total de pedidos (aprovados/reprovados)");
        System.out.println("[7] Número de pedidos dos últimos 30 dias e valor médio");
        System.out.println("[8] Valor total de cada categoria nos últimos 30 dias");
        System.out.println("[9] Pedido de maior valor ainda aberto");
        System.out.println("[14] Estatísticas de pedidos (totais e percentuais)");

        System.out.println("\n==== PERMISSÕES GERAIS ====");
        System.out.println("[10] Registrar novo pedido de aquisição");
        System.out.println("[11] Excluir pedido");
        System.out.println("[12] Trocar usuário");
        System.out.println("[13] Sair\n");
    }
}
