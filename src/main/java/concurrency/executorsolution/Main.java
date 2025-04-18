package concurrency.executorsolution;
import searchandsort.SortExamples;
import searchandsort.entities.Student;
import searchandsort.util.Factory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) throws Exception {
        List<Student> students = new ArrayList<>();
        Factory.fillWithStudents(students, 10000000);
        Collections.shuffle(students);

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<List<Student>> future = executor.submit(new SortTask(students));

        System.out.println("Sortering startet i baggrunden...");

        // Imens sorteringen kører, gør vi noget andet
        for (int i = 0; i < 5; i++) {
            System.out.println("[Main] Arbejder videre..." + i);
            Thread.sleep(500);
        }

        // Nu henter vi resultatet fra sorteringen
        List<Student> sorted = future.get();
        System.out.println("Sortering færdig. Første 10 elever:");
        for(int i = 0; i < 10; i++){
            System.out.println(sorted.get(i));
        }

        executor.shutdown();
    }
}
