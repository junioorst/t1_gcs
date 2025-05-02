import java.time.LocalDate;
import java.util.ArrayList;

public class Pedido {

    enum Status {
        EM_ANALISE, APROVADO, REJEITADO, CONCLUIDO
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

    public Usuario getFunc() {
        return func;
    }

    public Status getStatus() {
        return status;
    }

    public LocalDate getData() {
        return data;
    }

    public int getId() {
        return id;
    }

    public double getPrecoTotal() {
        double total = 0;
        for (Item item : listaItens) {
            total += item.getPreco();
        }
        return total;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Pedido:" +
                "\nStatus = " + status +
                "\nID = " + id +
                "\nLista de Itens = " + listaItens +
                "\nData = " + data +
                "\nValor = R$" + valor +
                "\n" + depto +
                "\n" + func +
                "\nDescrição = '" + descricao + '\'';
    }
}
