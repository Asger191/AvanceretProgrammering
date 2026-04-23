package Portfolio.PortfolioSort;

import java.util.Random;

public class SortAssigment {
    public static void main(String [] args){

        Random ran = new Random();
        int[] numbers = new int[100];
        int[] numbers2 = new int[100];
        int[] numbers3 = new int[100];

        for(int i = 0; i<100; i++){
            numbers[i] = ran.nextInt(1000);
            numbers2[i] = ran.nextInt(1000);
            numbers3[i] = ran.nextInt(1000);

        }


        long start = System.currentTimeMillis();
        sortByBubble(numbers);
        long stop = System.currentTimeMillis();
        System.out.println("Time for bubbling the integer array: " + (stop - start) + " ms\n");
        for(int n : numbers){
            System.out.println(n);
        }

        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        start = System.currentTimeMillis();
        sortByMerge(numbers2);
        stop = System.currentTimeMillis();
        System.out.println("Time for merging the integer array: " + (stop - start) + " ms\n");
        for(int n : numbers2){
            System.out.println(n);
        }

        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------");
        start = System.currentTimeMillis();
        sortByQuicksort(numbers3, 0, numbers3.length - 1);
        stop = System.currentTimeMillis();
        System.out.println("Time for quicksorting the integer array: " + (stop - start) + " ms\n");
        for(int n : numbers3){
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

        // Hvis der er mere end et element i ints[]
        if(ints.length > 1){

            // Finder midten af arrayet
            int mid = ints.length / 2;

            // Erklærer en venstre og en højre side
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
            mergeIntegers(ints, left, right);
        }
    }

    public static void mergeIntegers(int[] numbers ,int[] left, int[] right){

        // Instansierer i = venstre[] j = højre[] k = samlet[]
        int i = 0, j = 0, k = 0;

        while(i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                numbers[k] = left[i];
                i++;
                k++;
            } else {
                numbers[k] = right[j];
                j++;
                k++;
            }
        }


        // Kommer til sidst i processen når en af siderne er tomme
        // Hvis højre er tom så fylder den resten af left på til sidst i rækken
            while(i < left.length){
                numbers[k] = left[i];
                k++;
                i++;
            }
        // Hvis venstre er tom så fylder den resten af right på til sidst i rækken
            while(j < right.length){
                numbers[k] = right[j];
                k++;
                j++;
            }
    }


// Her sorterer jeg et rekursivt et array med quicksort algoritmen
    public static void sortByQuicksort(int[] numbers, int low, int high){

        // Basistilstand: stop når partitionen har 0 eller 1 elementer
        if(low < high){

            // Placer pivot-elementet på sin korrekte plads og få dets index
            int pivot = customPartition(numbers, low, high);

            // Sortér den venstre del (elementer mindre end pivot)
            sortByQuicksort(numbers, low, pivot - 1);

            // Sortér den højre del (elementer større end pivot)
            sortByQuicksort(numbers, pivot + 1, high);
        }
    }


    // Partitionerer arrayet og returnerer det endelige index for pivot-elementet
    public static int customPartition(int[] numbers, int low, int high){

        // Vælg det sidste element som pivot
        int pivotValue = numbers[high];

        // i holder styr på grænsen mellem elementer mindre og større end pivot
        int i = low - 1;

        // Gennemgå alle elementer i partitionen undtagen pivot
        for(int j = low; j< high; j++){

            // Hvis elementet er mindre end eller lig med pivot, skal det til venstre
            if(numbers[j] <= pivotValue){
                i++;

                // Byt numbers[i] og numbers[j] så det lille element kommer til venstre
                int temp = numbers[i];
                numbers[i] = numbers[j];
                numbers[j] = temp;
            }
        }

        // Placer pivot på sin korrekte plads ved at bytte med numbers[i + 1]
        int temp = numbers[i + 1];
        numbers[i + 1] = numbers[high];
        numbers[high] = temp;

        // Returnér index for pivot-elementet
        return i + 1;
    }


}
