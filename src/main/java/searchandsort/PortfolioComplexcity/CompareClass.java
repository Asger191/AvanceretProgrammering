package searchandsort.PortfolioComplexcity;

public class CompareClass implements Comparable<CompareClass> {

    private int value;

    public CompareClass(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    // compareTo bruges af TreeSet

    @Override
    public int compareTo(CompareClass other) {
        return Integer.compare(this.value, other.value);
    }




    // equals bruges af HashSet
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CompareClass)) return false;

        CompareClass other = (CompareClass) o;
        return this.value == other.value;
    }

    // hashCode bruges af HashSet
    @Override
    public int hashCode() {
        return Integer.hashCode(value);
    }
}