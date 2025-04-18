package concurrency.executorexercise;

import searchandsort.entities.Student;
import searchandsort.util.Factory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) throws Exception {
        List<Student> students = new ArrayList<>();
        Factory.fillWithStudents(students, 1000000);
        Collections.shuffle(students);

        // Her skal du lave en ExecutorService
        // Du skal have lavet en klasse SortTask som er callable og som kan modtage en liste og sende den sorteret tilbage
        // Endelig skal du have en Future-variable her, som modtager listen.

        System.out.println("Sortering startet i baggrunden...");

        // Imens sorteringen kører, gør vi noget andet
        for (int i = 0; i < 5; i++) {
            System.out.println("[Main] Arbejder videre..." + i);
            Thread.sleep(500);
        }

        // Nu henter vi resultatet fra sorteringen
        /*
        Her skal du lave variable kaldet sorted og tildele den resultatet fra din Future

        System.out.println("Sortering færdig. Første 10 elever:");
        for(int i = 0; i < 10; i++){
            System.out.println(sorted.get(i));
        }
        */

        // og lukker vores Executor pænt
        // executor.shutdown();
    }
}
