package org.example.first;

import java.util.Random;

public class First {
    public static void main(String[] args) {

        //int[] data = {5, 2, 4, 6, 1, 3, 8, 1, 0, 9, 3, 3, 3, 0, 56, 2, -1};
        int[] data = generateIntArray(500000);
        //System.out.println(Arrays.toString(data));
        //System.out.println(Arrays.toString(insertionSort(data)));
        //insertionSort(data); 16
        //System.out.println(Arrays.toString(myInsertionSort(data)));
        //myInsertionSort(data); 35

    }

    public static int[] insertionSort(int[] toSort) {
        for (int j=1; j < toSort.length; j++) {
            int key = toSort[j];
            int i = j - 1;
            while (i >= 0 && toSort[i] > key) {
                toSort[i+1] = toSort[i];
                i = i - 1;
            }
            toSort[i+1] = key;
        }
        return toSort;
    }

    public static int[] myInsertionSort(int[] toSort){
        for (int y = 1; y < toSort.length; y++) {
            for (int i = y - 1; i >= 0; i--) {
                if (toSort[i+1] < toSort[i]) {
                    int buffer = toSort[i+1];
                    toSort[i+1] = toSort[i];
                    toSort[i] = buffer;
                } else {
                    break;
                }
            }
        }
        return toSort;
    }

    private static int[] generateIntArray(int size){
        Random rd = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rd.nextInt();
        }
        return arr;
    }
}