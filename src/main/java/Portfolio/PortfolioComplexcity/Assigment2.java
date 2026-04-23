package Portfolio.PortfolioComplexcity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeSet;

public class Assigment2 {

    public static void main(String[] args){

        ArrayList<Integer> integerArrayList = new ArrayList<>();
        LinkedList<Integer> integerLinkedList = new LinkedList<>();
        HashSet<Integer> integerHashSet = new HashSet<>();
        fillLists(integerLinkedList, integerArrayList, integerHashSet);

        // Linked list
        System.out.println("Linked list");
        long start = System.currentTimeMillis();
        System.out.println(integerLinkedList.get(250000));
        long stop = System.currentTimeMillis();
        System.out.println("Time for getting index 250000: " + (stop - start) + " ms\n");

        start = System.currentTimeMillis();
        integerLinkedList.add(250000, 99);
        stop = System.currentTimeMillis();
        System.out.println("Time for adding on index 250000 with value 99:" + (stop - start) + " ms\n");

        start = System.currentTimeMillis();
        integerLinkedList.remove(250000);
        stop = System.currentTimeMillis();
        System.out.println("Time for removing index 250000:" + (stop - start) + " ms\n");

        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println();

        // Array List
        System.out.println("Array list");

        start = System.currentTimeMillis();
        System.out.println(integerArrayList.get(250000));
        stop = System.currentTimeMillis();
        System.out.println("Time for getting index 250000: " + (stop - start) + " ms\n");

        start = System.currentTimeMillis();
        integerArrayList.add(250000, 99);
        stop = System.currentTimeMillis();
        System.out.println("Time for adding on index 250000 with value 99: " + (stop - start) + " ms\n");

        start = System.currentTimeMillis();
        integerArrayList.remove(250000);
        stop = System.currentTimeMillis();
        System.out.println("Time for removing index 250000 " + (stop - start) + " ms\n");

        // Ud fra vores timestamp kan vi tydeligt se at Linked list er længere tid om det end arraylist
        // I get har Linked list kompleksiteten O(n) og Arraylist O(1)

        //Selvom både LinkedList og ArrayList implementerer List, opfører de sig forskelligt pga. deres interne struktur.
        //ArrayList gemmer elementer i et sammenhængende array, hvilket giver O(1) adgang ved get(),
        //mens LinkedList består af noder, der skal traverseres sekventielt, hvilket giver O(n).

        //Ved add og remove midt i listen har begge strukturer O(n) kompleksitet, da ArrayList skal flytte elementer,
        //mens LinkedList skal traversere listen for at finde positionen.
        //Derfor er ArrayList hurtigere ved opslag, mens forskellen er mindre ved indsættelse og sletning.

        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");

        // Søgning List vs Hashset

        // List
        start = System.currentTimeMillis();
        System.out.println(integerArrayList.contains(200000));
        stop = System.currentTimeMillis();
        System.out.println("Time searching for index 200000 " + (stop - start) + " ms\n");

        start = System.currentTimeMillis();
        System.out.println(integerArrayList.contains(600000));
        stop = System.currentTimeMillis();
        System.out.println("Time searching for index 300000 " + (stop - start) + " ms\n");

        // Hashset
        start = System.currentTimeMillis();
        System.out.println(integerHashSet.contains(200000));
        stop = System.currentTimeMillis();
        System.out.println("Time searching for index 200000 " + (stop - start) + " ms\n");

        start = System.currentTimeMillis();
        System.out.println(integerHashSet.contains(600000));
        stop = System.currentTimeMillis();
        System.out.println("Time searching for index 300000 " + (stop - start) + " ms\n");

        //Forklar forskellen. Hvad er kompleksiteten for hvert kald, og hvorfor?

        //Kompleksiteten for kaldene for ArrayList er O(n) grundet at ved contains() kaldet
        // der går den element for element igennem for at finde det vi søger

        //Kompleksiteten for kaldene for HashSet er O(1) grundet at ved contains()
        // der går den direkte til det vi leder efter
        // det er fordi den bruger hashing til at slå elementer op direkte ved hjælp af en beregnet position



        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------");
        // Comparable, equals() og hashcode()

        TreeSet<CompareClass> treeSet = new TreeSet<>();
        HashSet<CompareClass> hashSet = new HashSet<>();

        CompareClass c1 = new CompareClass(12);
        CompareClass c2 = new CompareClass(20);
        CompareClass c3 = new CompareClass(7);
        CompareClass c4 = new CompareClass(30);
        CompareClass c5 = new CompareClass(28);


        // Adding to TreeSet
        // TreeSet O(log n)
        treeSet.add(c1);
        treeSet.add(c2);
        treeSet.add(c3);
        treeSet.add(c4);
        treeSet.add(c5);


        // Adding to HashSet
        // HashSet O(1)
        hashSet.add(c1);
        hashSet.add(c2);
        hashSet.add(c3);
        hashSet.add(c4);
        hashSet.add(c5);


        //Finding
        // Treeset O(log n)
        start = System.currentTimeMillis();
        System.out.println(treeSet.contains(c5));
        stop = System.currentTimeMillis();
        System.out.println("Time searching for Object c5 - TreeSet " + (stop - start) + " ms\n");

        // HashSet O(1)
        start = System.currentTimeMillis();
        System.out.println(hashSet.contains(c5));
        stop = System.currentTimeMillis();
        System.out.println("Time searching for Object c5 - HashSet " + (stop - start) + " ms\n");

        // Remove
        // HashSet O(1)
        start = System.currentTimeMillis();
        System.out.println(hashSet.remove(c5));
        stop = System.currentTimeMillis();
        System.out.println("Time removing Object c5 - HashSet " + (stop - start) + " ms\n");

        // TreeSet O(log n)
        start = System.currentTimeMillis();
        System.out.println(treeSet.remove(c5));
        stop = System.currentTimeMillis();
        System.out.println("Time removing Object c5 - TreeSet " + (stop - start) + " ms\n");

        // HashSet O(1)
        start = System.currentTimeMillis();
        System.out.println(hashSet.contains(c5));
        stop = System.currentTimeMillis();
        System.out.println("HashSet contains: " + (stop - start) + " ns");

        // TreeSet er O(log n) fordi den skal gå igennem elementerne ved at splitte dem op mange gange
        // Modsat HashSet er kompleksiteten O(1) da den slår  direkte op ved hjælp af hashing på den beregnede position

        // Tidsmålingen viser 0 ms, da operationerne er meget hurtige.
        // Derfor kan forskellen ikke måles præcist med currentTimeMillis().



    }


    public static void fillLists(LinkedList<Integer> LinkedNumbers, ArrayList<Integer> ArrayNumbers, HashSet<Integer> HashNumbers){
        long start = System.currentTimeMillis();
        for(int i = 0; i<=500000; i++){
            LinkedNumbers.add(i);
            ArrayNumbers.add(i);
            HashNumbers.add(i);
        }
        long stop = System.currentTimeMillis();
        System.out.println("Time for filling lists: " + (stop - start) + " ms\n");
    }

}
