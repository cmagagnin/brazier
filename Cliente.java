import java.util.ArrayList;
import java.util.List;

public class Cliente extends Endereco {
    private static int contadorId = 1;
    private int id;
    private String nome;
    private String telefone;
    private String cpf;
    private List<Pedido> historicoPedidos;

    public Cliente(String nome, String telefone, String cpf, String rua, int numero, String bairro, String complemento) {
        super(rua, numero, bairro, complemento);
        this.id = contadorId++;
        setNome(nome);
        setTelefone(telefone);
        setCpf(cpf);
        this.historicoPedidos = new ArrayList<>();
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio.");
        }
        this.nome = nome;
    }

    public void setTelefone(String telefone) {
        if (telefone == null || telefone.trim().isEmpty()) {
            throw new IllegalArgumentException("Telefone não pode ser vazio.");
        }
        this.telefone = telefone;
    }

    public void setCpf(String cpf) {
        if (!validarCPF(cpf)) {
            throw new IllegalArgumentException("CPF inválido.");
        }
        this.cpf = cpf;
    }

    // Método para validar CPF
    private boolean validarCPF(String cpf) {
        cpf = cpf.replaceAll("\\D", "");
        if (cpf.length() != 11) return false;
        // Implementação simplificada. Para validação completa, implementar o algoritmo de validação de CPF.
        return true;
    }

    // Método para adicionar pedido ao histórico
    public void adicionarPedidoAoHistorico(Pedido pedido) {
        historicoPedidos.add(pedido);
    }

    // Getters
    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getTelefone() { return telefone; }
    public String getCpf() { return cpf; }
    public List<Pedido> getHistoricoPedidos() { return historicoPedidos; }
}
