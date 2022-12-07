package buscabinaria2;

public class BuscaBinaria {

    private static final int QTD=10;
    private static int contador, somaContador, count=0;
    private static final int[][] vetorContador = new int[5][QTD];
    private static long time, finalTime;

    public static void main(String[] args) {
        long time = System.currentTimeMillis();
        rrepeat();
        long finalTime = System.currentTimeMillis() - time;
        System.out.println("\nCode executed in " + finalTime + " ms (~" + Math.round((float) finalTime/1000) + " seconds)");
    }

    public static void rrepeat() {
        System.out.println("Binary Search Counter:\n");
        execute(10);
        execute(100);
        execute(500);
        execute(1000);
        System.out.println("\nOrdenação:");
        for (int[] ints : vetorContador) {
            for (int anInt : ints) {
                System.out.print("\t" + anInt);
            }
            System.out.print("\n");
        }
    }

    public static void execute(int qtd) {
        time = System.currentTimeMillis();
        for (int i = 0; i < QTD; i++) {
            repeat(qtd, i);
        }
        finalTime = System.currentTimeMillis() - time;
        System.out.print("\t| " + finalTime + " ms (~" + Math.round((float) finalTime /1000) + " seconds)");
        System.out.print("\n");
    }
    public static void repeat(int qtd, int index) {
        int soma=0, resultado;
        for(int i=0; i < qtd; i++) {
            loop(1000);
            soma+=count;
            count=0;
            somaContador+=contador;
        }
        resultado = soma/qtd;
        switch (qtd) {
            case 1 -> vetorContador[0][index] = somaContador / qtd;
            case 10 -> vetorContador[1][index] = somaContador / qtd;
            case 100 -> vetorContador[2][index] = somaContador / qtd;
            case 500 -> vetorContador[3][index] = somaContador / qtd;
            case 1000 -> vetorContador[4][index] = somaContador / qtd;
            default -> {
            }
        }
        somaContador=0;
        System.out.print("\t" + resultado);
    }

    public static void loop(int qtd) {

        int[] vet = new int[qtd];
        int numeroAleatorio = aleatorioNum(qtd);
        vetorConstruido(qtd, vet);
        vetorOrganizado(vet);
        buscaBinaria(vet, numeroAleatorio);
    }

    public static void vetorConstruido(int quantidade, int[] vetor) {
        int i = 0;
        while (i < vetor.length) {
            vetor[i] = aleatorioNum(quantidade);
            i++;
        }
    }

    public static void vetorOrganizado(int[] vetorDesorganizado) {
        int salvarValor, k, count=0;
        for(int i=0; i<vetorDesorganizado.length; i++) {
            count++;
            salvarValor = vetorDesorganizado[i];
            k = i;
            while (k > 0 && salvarValor < vetorDesorganizado[k-1]) {
                count++;
                vetorDesorganizado[k] = vetorDesorganizado[k-1];
                k-= 1;
            }
            vetorDesorganizado[k] = salvarValor;
        }
        contador = count;
    }

    public static int aleatorioNum(int qtd) {
        int i = 0;
        return (int)Math.floor(Math.random()*(qtd-i+1)+i);
    }

    public static void buscaBinaria(int[] vetor, int x) {
        int inf=0, sup=vetor.length-1, meio; count++;
        while (inf <= sup) {
            count++;
            meio = inf + ((sup-inf)/2);
            if (x == vetor[meio]) {
                count++;
                // return meio;
                break;
            } else if (x > vetor[meio]) {
                count++;
                inf = meio + 1;
            } else {
                count++;
                sup = meio - 1;
            }
        }
        count++;
    }
}