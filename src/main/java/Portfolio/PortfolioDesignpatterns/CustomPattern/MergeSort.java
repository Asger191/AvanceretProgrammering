package Portfolio.PortfolioDesignpatterns.CustomPattern;

public class MergeSort implements SortStrategy {
    @Override
    public void sort(int[] ints) {
        if (ints.length > 1) {
            int mid = ints.length / 2;
            int[] left  = new int[mid];
            int[] right = new int[ints.length - mid];

            for (int i = 0; i < mid; i++)          left[i]       = ints[i];
            for (int i = mid; i < ints.length; i++) right[i - mid] = ints[i];

            sort(left);
            sort(right);
            merge(ints, left, right);
        }
    }

    private void merge(int[] numbers, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) numbers[k++] = left[i++];
            else                     numbers[k++] = right[j++];
        }
        while (i < left.length)  numbers[k++] = left[i++];
        while (j < right.length) numbers[k++] = right[j++];
    }
}
