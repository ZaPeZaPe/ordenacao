package buscabinaria2;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;

public class BuscaBinariaQuickSort {

    private static final Runtime runtime = Runtime.getRuntime();
    private static final int QTD = 10;
    private static final int ENTRADA = 1000;
    private static int count = 0;
    private static long time;
    private static long finalTime;

    public static void main(String[] args) {
        getMemoryAndTime();
    }

    public static void getMemoryAndTime() {
        time = System.currentTimeMillis();
	    long usedMemory1 = runtime.totalMemory() - runtime.freeMemory();
        rrepeat();
        long usedMemory2 = runtime.totalMemory() - runtime.freeMemory();
        long usedMemory = usedMemory2 - usedMemory1;
        finalTime = System.currentTimeMillis() - time;
        System.out.println("\nProcess finished in " + finalTime + " ms (~" + Math.round((float) finalTime /1000) + " seconds)");
        System.out.println("Code used " + humanReadableByteCountSI(usedMemory) + " of memory");
    }

    public static void rrepeat() {
        System.out.println("Binary Search Counter with QuickSort:\n");
        execute(10);
        execute(100);
        execute(500);
        execute(1000);
    }

    public static void execute(int qtd) {
        long time = System.currentTimeMillis();
        boolean OK=true;
        for (int i = 0; i < QTD; i++) {
            if(OK) {
                System.out.print(qtd+"");
                OK=false;
            }
            repeat(qtd);
        }
        long totalFinalTime = System.currentTimeMillis() - time;
        finalTime = Math.round((float) (System.currentTimeMillis() - time)/QTD);
        System.out.print("\t| " + finalTime + " ms (~" + Math.round((float) finalTime/1000) + " seconds) p/ column; (" + totalFinalTime + " ms)");
        System.out.print("\n");
    }

    public static void repeat(int qtd) {
        int soma = 0, resultado;
        for (int i = 0; i < qtd; i++) {
            loop(ENTRADA);
            soma += count;
            count = 0;
        }
        resultado = soma/qtd;
        System.out.print("\t" + resultado);
    }

    public static void loop(int qtd) {

        ArrayList<Integer> vet = new ArrayList<>();
        int numeroAleatorio = aleatorioNum(qtd);
        vetorConstruido(vet, qtd);
        quickSort(vet, 0, vet.size() - 1);
        buscaBinaria(vet, numeroAleatorio);

    }

    public static void vetorConstruido(ArrayList<Integer> vetor, int quantidade) {
        for (int i = 0; i < quantidade; i++) {
            vetor.add(aleatorioNum(quantidade));
        }
    }

    public static void quickSort(ArrayList<Integer> vetorDesorganizado, int low, int high) {
        count++;
        if (low < high) {
            count++;
            int pi = partition(vetorDesorganizado, low, high);
            quickSort(vetorDesorganizado, low, pi - 1);
            quickSort(vetorDesorganizado, pi + 1, high);
        }
    }

    public static int partition(ArrayList<Integer> vetorDesorganizado, int low, int high) {
        count++;
        int pivot = vetorDesorganizado.get(high);
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            count++;
            if (vetorDesorganizado.get(j) <= pivot) {
                count++;
                i++;
                int temp = vetorDesorganizado.get(i);
                vetorDesorganizado.set(i, vetorDesorganizado.get(j));
                vetorDesorganizado.set(j, temp);
            }
        }

        int temp = vetorDesorganizado.get(i+1);
        vetorDesorganizado.set(i+1, vetorDesorganizado.get(high));
        vetorDesorganizado.set(high, temp);

        return i + 1;
    }

    public static int aleatorioNum(int qtd) {
        int i = 0;
        return (int) Math.floor(Math.random() * (qtd - i + 1) + i);
    }

    public static void buscaBinaria(ArrayList<Integer> vetor, int x) {
        int inf = 0,sup = vetor.size() - 1, meio;
        while (inf <= sup) {
            meio = inf + ((sup - inf) / 2);
            if (x > vetor.get(meio)) {
                inf = meio + 1;
            } else {
                sup = meio - 1;
            }
        }
    }

    public static String humanReadableByteCountSI(long bytes) {
        if (-1000 < bytes && bytes < 1000) {
            return bytes + " B";
        }
        CharacterIterator ci = new StringCharacterIterator("kMGTPE");
        while (bytes <= -999_950 || bytes >= 999_950) {
            bytes /= 1000;
            ci.next();
        }
        return String.format("%.1f %cB", bytes / 1000.0, ci.current());
    }
}
