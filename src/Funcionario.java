public class Funcionario extends Usuario {
    Departamento departamento;

    public Funcionario(int id, String nome, int tipo, Departamento departamento) {
        super(id, nome, tipo);
        this.departamento = departamento;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }
}
