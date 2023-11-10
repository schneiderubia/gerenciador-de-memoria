public class Main {

    /*
     Seu programa deve permitir:
     1 - Alocar memória
     2 - Devolver memória
     3 - Exibir os blocos livres
     4 - Exibir todos os blocos
     0 - Sair
     */

    public static void main(String[] args) {
        var memoria = new GerenciadorMemoria();
        memoria.imprimir();

        memoria.construir(
                new Bloco(500, 200),
                new Bloco(2000, 500),
                new Bloco(3100, 900));
        memoria.imprimir();

        memoria.liberar(0, 500);
        memoria.imprimir();
        memoria.liberar(700, 100);
        memoria.imprimir();
        memoria.liberar(1800, 200);
        memoria.imprimir();
        memoria.liberar(800, 1000);
        memoria.imprimir();
        memoria.liberar(4001, 49);
        memoria.imprimir();
        memoria.liberar(4050, 46);
        memoria.imprimir();

//        memoria.getBlocosLivres();
//        memoria.alocar(1000);
//        memoria.getBlocosLivres();
//        memoria.liberar(3500, 200);
//        memoria.getBlocosLivres();
    }
}
