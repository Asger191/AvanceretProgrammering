package Portfolio.PortfolioDesignpatterns.CustomPattern;

// Context-klassen: holder en reference til strategien og
// delegerer sorteringen til den uden at kende til hvilken implementationen.
public class Sorter {
    private SortStrategy strategy;

    public Sorter(SortStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(SortStrategy strategy) {
        this.strategy = strategy;
    }

    public void sort(int[] array) {
        long start = System.currentTimeMillis();
        strategy.sort(array);
        long stop = System.currentTimeMillis();
        System.out.println(strategy.getClass().getSimpleName()
                + ": " + (stop - start) + " ms");
        for (int n : array) System.out.println(n);
    }
}