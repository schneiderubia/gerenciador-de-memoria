import java.util.ArrayList;
import java.util.List;

public class GerenciadorMemoria {

    private Bloco inicio;
    private final int tamanhoTotal;

    public GerenciadorMemoria() {
        this(4096);
    }

    /**
     * Cria um Gerenciador de Memória inicializando sua lista de blocos livres com um único nó
     * contendo o tamanho total da memória.
     *
     * @param tamanho o tamanho total da memória
     */
    public GerenciadorMemoria(int tamanho) {
        this.tamanhoTotal = tamanho;
        this.liberar(0, tamanhoTotal);
    }

    /**
     * Usado apenas para criar uma lista de teste.
     * Este método permite testar as devoluções de memória.
     *
     * @param blocos
     */
    public void construir(Bloco... blocos) {
        this.inicio = blocos[0];
        Bloco ultimo = inicio;
        for (Bloco bloco : blocos) {
            ultimo.setProximo(bloco);
            ultimo = bloco;
        }
    }

    /**
     * Este método é chamado por uma aplicação para alocar n bytes de memória.
     *
     * @param tamanho o tamanho do bloco de memória a alocar
     * @return o endereço de memória onde começa o bloco alocado, ou o valor -1 caso não seja possível alocar a
     * quantidade de memória solicitada.
     */
    public int alocar(int tamanho) {
        Bloco ant = null;
        Bloco atual = inicio;
        while (atual != null) {
            if (atual.getTamanho() > tamanho) {
                atual.setTamanho(atual.getTamanho() - tamanho);
                return atual.getEndereco() + atual.getTamanho();
            } else if (atual.getTamanho() == tamanho) {
                int endRemovido = atual.getEndereco();
                if (ant == null) {
                    inicio = atual.getProximo();
                } else {
                    ant.setProximo(atual.getProximo());
                }
                return endRemovido;
            }
            ant = atual;
            atual = atual.getProximo();
        }
        return -1;
    }

    /**
     * Este método é chamado quando uma aplicação libera (devolve) um espaço de memória alocado anteriormente,
     *
     * @param endereco o endereço inicial de memória liberado
     * @param tamanho  o tamanho do bloco devolvido
     */
    public void liberar(int endereco, int tamanho) {
        Bloco ant = null;
        Bloco atual = inicio;
        while (atual != null) {
            if (atual.getEndereco() >= endereco) {
                break;
            }
            ant = atual;
            atual = atual.getProximo();
        }
        if (fronteiraProx(atual, endereco, tamanho) && fronteiraAnt(ant, endereco)) {
            ant.setProximo(atual.getProximo());
            ant.setTamanho(ant.getTamanho() + atual.getTamanho() + tamanho);
        } else if (fronteiraProx(atual, endereco, tamanho)) {
            atual.setEndereco(endereco);
            atual.setTamanho(atual.getTamanho() + tamanho);
        } else if (fronteiraAnt(ant, endereco)) {
            ant.setTamanho(ant.getTamanho() + tamanho);
        } else {
            Bloco novo = new Bloco(endereco, tamanho);
            if (ant == null) {
                novo.setProximo(inicio);
                inicio = novo;
            } else {
                novo.setProximo(ant.getProximo());
                ant.setProximo(novo);
            }
        }
    }

    private boolean fronteiraAnt(Bloco ant, int endereco) {
        // return ant != null && ant.getEndereco() + ant.getTamanho() == endereco;
        if (ant == null) {
            return false;
        } else {
            int somaBloco = ant.getEndereco() + ant.getTamanho();
            if (somaBloco == endereco) {
                return true;
            } else {
                return false;
            }
        }
    }

    private boolean fronteiraProx(Bloco prox, int endereco, int tamanho) {
//        return prox != null && endereco + tamanho == prox.getEndereco();
        if (prox == null) {
            return false;
        } else if (prox.getEndereco() == endereco + tamanho) {
            return true;
        }
        return false;
    }

    private Bloco primeiraEscolha(int tamanho) {
        Bloco ant = null, atual = inicio;
        return null;
    }

    private Bloco proximaEscolha(int tamanho) {
        return null;
    }

    private Bloco melhorEscolha(int tamanho) {
        return null;
    }

    private Bloco piorEscolha(int tamanho) {
        return null;
    }

    private Bloco rapidaEscolha(int tamanho) {
        return null;
    }

    /**
     * Retorna o nó imediatamente anterior ao endereço recebido.
     *
     * @param endereco o endereço de memória
     * @return o bloco imediatamente anterior
     */
    public Bloco percorrerAte(int endereco) {
        if (inicio == null || endereco < inicio.getEndereco()) {
            return null;
        }

        Bloco blocoAnt = inicio;
        while (blocoAnt.getProximo() != null && blocoAnt.getProximo().getEndereco() < endereco) {
            blocoAnt = blocoAnt.getProximo();
        }
        return blocoAnt;
    }

    /**
     * Retorna uma lista com todos os blocos livres disponíveis.
     *
     * @return um List<No> contendo todos os blocos disponíveis
     */
    public List<Bloco> getBlocosLivres() {
        var blocosLivres = new ArrayList<Bloco>();
        Bloco blocoAtual = inicio;
        while (blocoAtual != null) {
            blocosLivres.add(blocoAtual);
            blocoAtual = blocoAtual.getProximo();
        }
        return blocosLivres;
    }

    /**
     * Retorna uma lista com todos os blocos, mostrando os setores disponíveis/indisponíveis da memória.
     *
     * @return um List<No> contendo todos os blocos disponíveis/indisponíveis da memória
     */
    public List<Bloco> getTodosBlocos() {
        var todosBlocos = new ArrayList<Bloco>();
        var contador = 0;
        Bloco blocoAtual = inicio;
        while (blocoAtual != null) {
            if (blocoAtual.getEndereco() != 0) {
                todosBlocos.add(new Bloco(contador, blocoAtual.getEndereco() - contador, false));
            }
            todosBlocos.add(blocoAtual);
            contador = blocoAtual.getEndereco() + blocoAtual.getTamanho();
            blocoAtual = blocoAtual.getProximo();
        }
        if (contador < tamanhoTotal) {
            todosBlocos.add(new Bloco(contador, tamanhoTotal - contador, false));
        }
        return todosBlocos;
    }

    public void imprimir() {
        Bloco blocoAtual = inicio;
        while (blocoAtual != null) {
            System.out.print(blocoAtual + "->");
            blocoAtual = blocoAtual.getProximo();
        }
        System.out.println();
    }

    public int getTamanhoTotal() {
        return tamanhoTotal;
    }
}
