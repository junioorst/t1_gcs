import java.util.ArrayList;

public class Empresa {
    private String name;
    private ArrayList<Departamento> departamentos = new ArrayList<>();
    private ArrayList<Usuario> todosFuncionarios = new ArrayList<>();
    private ArrayList<Pedido> todosPedidos = new ArrayList<>();

    public Empresa() {
        this.name = "TechSolutions Ltda";
    }

    public ArrayList<Usuario> getFuncionarios() {
        return todosFuncionarios;
    }

    public void cadastrarUsuario(Usuario usuario) {
        todosFuncionarios.add(usuario);
    }

    public void cadastrarDepartamento (Departamento depto) {
        departamentos.add(depto);
    }

    public Usuario getFuncionario(int opcao) {
        for (int i = 0; i < todosFuncionarios.size(); i++) {
            if (i == opcao) {
                return todosFuncionarios.get(i);
            }
        }
        return null;
    }

}
