package Portfolio.PortfolioDesignpatterns.CustomPattern;

public class QuickSort implements SortStrategy {
    @Override
    public void sort(int[] numbers) {
        quicksort(numbers, 0, numbers.length - 1);
    }

    private void quicksort(int[] numbers, int low, int high) {
        if (low < high) {
            int pivot = partition(numbers, low, high);
            quicksort(numbers, low, pivot - 1);
            quicksort(numbers, pivot + 1, high);
        }
    }

    private int partition(int[] numbers, int low, int high) {
        int pivotValue = numbers[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (numbers[j] <= pivotValue) {
                i++;
                int temp = numbers[i]; numbers[i] = numbers[j]; numbers[j] = temp;
            }
        }
        int temp = numbers[i + 1]; numbers[i + 1] = numbers[high]; numbers[high] = temp;
        return i + 1;
    }
}