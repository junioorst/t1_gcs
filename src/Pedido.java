import java.time.LocalDate;
import java.util.ArrayList;

public class Pedido {

    enum Status {
        EM_ANALISE, APROVADO, REJEITADO
    }

    private Status status;
    private static int LAST_ID = 1000;
    private int id;
    private ArrayList<Item> listaItens;
    private LocalDate data;
    private double valor;
    private Departamento depto;
    private Usuario func;
    private String descricao;

    public Pedido(Departamento depto, Usuario func, ArrayList<Item> listaItens, String descricao) {
        this.listaItens = listaItens;
        this.id = ++LAST_ID;
        this.data = LocalDate.now();
        this.valor = getPrecoTotal();
        this.depto = depto;
        this.func = func;
        this.status = Status.EM_ANALISE;
        this.descricao = descricao;
    }

    private double getPrecoTotal() {
        double total = 0;
        for (Item item : listaItens) {
            total += item.getPreco();
        }
        return total;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "status=" + status +
                ", id=" + id +
                ", listaItens=" + listaItens +
                ", data=" + data +
                ", valor=" + valor +
                ", depto=" + depto +
                ", func=" + func +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
