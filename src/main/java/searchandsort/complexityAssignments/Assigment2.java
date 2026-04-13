package searchandsort.complexityAssignments;

import searchandsort.BigOExamples;

import java.util.ArrayList;
import java.util.LinkedList;

public class Assigment2 {
    public static void main(String[] args){

        ArrayList<Integer> integerArrayList = new ArrayList<>();
        LinkedList<Integer> integerLinkedList = new LinkedList<>();
        fillLists(integerLinkedList, integerArrayList);

        // Linked list
        System.out.println("Linked list");
        long start = System.currentTimeMillis();
        integerLinkedList.get(250000);
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

        System.out.println("Array list");

        start = System.currentTimeMillis();
        integerArrayList.get(250000);
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



        // Søgning List vs Hashset

        // List


        // Hashset


    }


    public static void fillLists(LinkedList<Integer> LinkedNumbers, ArrayList<Integer> ArrayNumbers){
        long start = System.currentTimeMillis();
        for(int i = 0; i<=500000; i++){
            LinkedNumbers.add(i);
            ArrayNumbers.add(i);
        }

        long stop = System.currentTimeMillis();
        System.out.println("Time for filling lists: " + (stop - start) + " ms\n");
    }


}
