import java.util.ArrayList;

public class listaUsuarios {
    private ArrayList<Usuario> usuarios;

    public listaUsuarios() {
        this.usuarios = new ArrayList<>();
    }

    public boolean adicionarUsuario(Usuario u) {
        return usuarios.add(u);
    }

    public Usuario getUsuario(int opcao) {
        for (int i = 0; i < usuarios.size(); i++) {
            if (opcao == i) {
                return usuarios.get(i);
            }
        }
        return null;
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }
}
