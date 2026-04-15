package searchandsort.complexityAssignments;

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

    // equals bruges bl.a. af HashSet
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        CompareClass other = (CompareClass) obj;
        return this.value == other.value;
    }

    // hashCode bruges af HashSet
    @Override
    public int hashCode() {
        return Integer.hashCode(value);
    }
}