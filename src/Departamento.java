public class Departamento {
    int id;
    String nome;
    double valorMaximo;
    Empresa empresa;

    public Departamento(int id, String name, Empresa empresa, double valorMaximo) {
        this.id = id;
        this.nome = name;
        this.empresa = empresa;
        this.valorMaximo = valorMaximo;
    }

    public int getId() {
        return id;
    }

    public double getValorMaximo() {
        return valorMaximo;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "Departamento: " +
                nome + '\'';
    }
}
