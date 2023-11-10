public class Bloco {

    private int endereco;
    private int tamanho;
    private boolean disponivel;
    private Bloco proximo;

    public Bloco(int endereco, int tamanho) {
        this(endereco, tamanho, true);
    }

    public Bloco(int endereco, int tamanho, boolean disponivel) {
        this.endereco = endereco;
        this.tamanho = tamanho;
        this.disponivel = disponivel;
        this.proximo = null;
    }

    public void diminuir(int tamanho) {
        this.tamanho -= tamanho;
    }

    public void aumentar(int tamanho) {
        this.tamanho += tamanho;
    }

    public int getEndereco() {
        return endereco;
    }

    public void setEndereco(int endereco) {
        this.endereco = endereco;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public Bloco getProximo() {
        return proximo;
    }

    public void setProximo(Bloco proximo) {
        this.proximo = proximo;
    }

    @Override
    public String toString() {
        return "[" + endereco + "|" + tamanho + "]";
    }
}
