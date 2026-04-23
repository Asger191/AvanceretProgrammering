# Portfolio: Sortering

## 1. Leg med sortering
- Kig på klassen `SortExamples`, som viser eksempler på fire sorteringsalgoritmer.
- Se om du kan forstå hvordan de forskellige algoritmer fungerer.
- Kør metoden `testSort()` fra `main` i klassen `searchandsort.Main`og se tidsstempler for de fire algoritmer.
- Prøv at udkommentere ` Collections.shuffle(original)` og se hvad der sker med `quickSort()`.
  Hvorfor sker dette? (Hint: worst case scenario).
- Prøv at implementere metoderne `bubbleSort()`, `quickSort()` og `mergeSort` så de tager et `int[] array` som parameter.
  Du kan få brug for denne kodestump

```java 
private static void swap(int[] arr, int i, int j) {
int temp = arr[i];
arr[i] = arr[j];
arr[j] = temp;
}``