import java.time.LocalDate;
import java.util.ArrayList;

public class Pedido {
    ArrayList<Item> itens;

    enum Status {
        EM_ANALISE, APROVADO, REJEITADO
    }

    private Status status;
    private int id;
    private ArrayList<Item> listaItens;
    private LocalDate data;
    private double valor;
    private Departamento depto;
    private Usuario func;
    private String descricao;

    public Pedido(Departamento depto, Usuario func, ArrayList<Item> itens, String descricao) {
        this.itens = itens;
        this.data = LocalDate.now();
        this.valor = getPrecoTotal();
        this.depto = depto;
        this.func = func;
        this.status = Status.EM_ANALISE;
        this.descricao = descricao;
    }

    private double getPrecoTotal() {
        double total = 0;
        for (Item item : itens) {
            total += item.getPreco();
        }
        return total;
    }


}
