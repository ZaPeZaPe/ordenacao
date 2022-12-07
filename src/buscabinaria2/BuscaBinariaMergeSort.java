package buscabinaria2;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;

public class BuscaBinariaMergeSort {

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
		long usedMemory = usedMemory2-usedMemory1;
		finalTime = System.currentTimeMillis() - time;
		System.out.println("\nCode executed in " + finalTime + " ms (~" + Math.round((float) finalTime /1000) + " seconds)");
		System.out.println("Code used " + humanReadableByteCountSI(usedMemory) + " of memory");
	}

	public static void rrepeat() {
		System.out.println("Binary Search Counter with MergeSort:\n");
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
		vet = mergeSort(vet);
		buscaBinaria(vet, numeroAleatorio);

	}

	public static ArrayList<Integer> mergeSort(ArrayList<Integer> vetorDesorganizado) {
		if (vetorDesorganizado.size() == 1) {
			count++;
			return vetorDesorganizado;
		}

		ArrayList<Integer> l1 = subArray(vetorDesorganizado, 1);
		ArrayList<Integer> l2 = subArray(vetorDesorganizado, 2);

		l1 = mergeSort(l1);
		l2 = mergeSort(l2);
		count++;

		return merge(l1, l2);
	}

	public static ArrayList<Integer> merge(ArrayList<Integer> a, ArrayList<Integer> b) {
		ArrayList<Integer> c = new ArrayList<>();
		while(!a.isEmpty() && !b.isEmpty()) {
			count++;
			if (a.get(0) > b.get(0)) {
				count++;
				c.add(b.get(0));
				b.remove(0);
			} else {
				count++;
				c.add(a.get(0));
				a.remove(0);
			}
		}

		while (!a.isEmpty()) {
			count++;
			c.add(a.get(0));
            a.remove(0);
		}

		while (!b.isEmpty()) {
			count++;
			c.add(b.get(0));
			b.remove(0);
		}

		return c;
	}

	//Método que cria um subArray para o l1 e l2;
	public static ArrayList<Integer> subArray(ArrayList<Integer> vet, int code) {
		if (code == 1) {
			ArrayList<Integer> vetorSec = new ArrayList<>();
			for (int i=0; i < vet.size()/2; i++) {
				vetorSec.add(vet.get(i));
			}
			return vetorSec;
		}

		if (code == 2) {
			if (vet.size() / 2 > 0) {
				vet.subList(0, vet.size() / 2).clear();
			}
		}

        return vet;
	}

	public static void vetorConstruido(ArrayList<Integer> vetor, int quantidade) {
		for (int i = 0; i < quantidade; i++) {
			vetor.add(aleatorioNum(quantidade));
		}
	}

	public static int aleatorioNum(int qtd) {
		int i = 0;
		return (int) Math.floor(Math.random() * (qtd - i + 1) + i);
	}

	public static void buscaBinaria(ArrayList<Integer> vetor, int x) {
		int inf = 0, meio, sup = vetor.size() - 1;
		/*int count = 0;
		count++;*/
		while (inf <= sup) {
			//count++;
			meio = inf + ((sup - inf) / 2);
			/*if (x == vetor.get(meio)) {
				//count++;
				//return count;
			} else*/ if (x > vetor.get(meio)) {
				//count++;
				inf = meio + 1;
			} else {
				//count++;
				sup = meio - 1;
			}
		}
		//count++;
		//return count;
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