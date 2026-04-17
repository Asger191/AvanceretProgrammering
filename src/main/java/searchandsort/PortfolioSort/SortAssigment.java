package searchandsort.PortfolioSort;

import java.util.Random;

public class SortAssigment {
    public static void main(String [] args){

        Random ran = new Random();
        int[] numbers = new int[100];

        for(int i = 0; i<100; i++){
            numbers[i] = ran.nextInt(1000);
        }

        long start = System.currentTimeMillis();
        sortByBubble(numbers);
        long stop = System.currentTimeMillis();
        System.out.println("Time for bubbling the integer array: " + (stop - start) + " ms\n");
        for(int n : numbers){
            System.out.println(n);
        }

    }

    public static void sortByBubble(int[] ints){

        for (int i = 0; i < ints.length - 1; i++){
            for (int j = 0; j < ints.length - i - 1; j++){

                if (ints[j] > ints[j + 1]) {
                    swap(ints, j, j + 1);
                }
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    public static void sortByMerge(int[] ints){
        if(ints.length > 1){
            int mid = ints.length / 2;

            int[] left = new int[mid];
            int[] right = new int[ints.length - mid];

            // kopier venstre
            for (int i = 0; i < mid; i++) {
                left[i] = ints[i];
            }

            // kopier højre
            for (int i = mid; i < ints.length; i++) {
                right[i - mid] = ints[i];
            }

            // rekursivt kald
            sortByMerge(left);
            sortByMerge(right);

            // merge (skal implementeres)
        }
    }

    public static void mergeIntegers(int[] numbers ,int[] left, int[] right){
        int i = 0, j = 0, k = 0;

        while(i < left.length && j < right.length){
            numbers.set
        }

    }


}
