package buscabinaria2;

import java.util.ArrayList;

public class Teste {

    public static void main(String[] args) {

        ArrayList<Integer> vet = new ArrayList<>();
        int numeroAleatorio = BuscaBinariaMergeSort.aleatorioNum(10);
        BuscaBinariaMergeSort.vetorConstruido(vet, 10);
        for (int i=0; i < vet.size(); i++) {
            System.out.println(vet.get(i));
        }
        System.out.println("----");
        vet = BuscaBinariaMergeSort.mergeSort(vet);
        for (int i=0; i < vet.size(); i++) {
            System.out.println(vet.get(i));
        }

    }

}
