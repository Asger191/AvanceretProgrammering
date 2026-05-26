package Portfolio.PortfolioDesignpatterns.CustomPattern;

import java.util.Random;

public class SortAssignment {
    public static void main(String[] args) {
        Random ran = new Random();

        int[] numbers  = randomArray(ran, 20);
        int[] numbers2 = randomArray(ran, 20);
        int[] numbers3 = randomArray(ran, 20);

        Sorter sorter = new Sorter(new BubbleSort());
        sorter.sort(numbers);

        // Strategien udskiftes dynamisk — ingen ændring i Sorter
        sorter.setStrategy(new MergeSort());
        sorter.sort(numbers2);

        sorter.setStrategy(new QuickSort());
        sorter.sort(numbers3);
    }

    private static int[] randomArray(Random ran, int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) arr[i] = ran.nextInt(100);
        return arr;
    }
}