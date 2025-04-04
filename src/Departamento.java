import java.util.ArrayList;
import java.util.List;

public class Departamento {
    String name;
    ArrayList<Usuario> funcionarios = new ArrayList<>();
    Empresa empresa;

    public Departamento(String name) {
        this.name = name;
    }

    public List<Usuario> getFuncionarios() {
        return funcionarios;
    }

    public String getName() {
        return name;
    }

}
