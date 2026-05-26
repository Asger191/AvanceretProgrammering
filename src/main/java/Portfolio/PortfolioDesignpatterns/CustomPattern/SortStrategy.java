package Portfolio.PortfolioDesignpatterns.CustomPattern;

// Strategy pattern: definerer en fælles kontrakt for alle sorteringsalgoritmer.
// Problemet: sorteringsalgoritmen var hardkodet i main — man kunne ikke
// udskifte den uden at ændre koden. Strategy løser dette ved at
// indkapsle hver algoritme i sin egen klasse med samme interface,
// så algoritmen kan vælges og udskiftes dynamisk ved runtime.
@FunctionalInterface
public interface SortStrategy {
    void sort(int[] array);
}