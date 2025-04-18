package concurrency.executorsolution;

import searchandsort.SortExamples;
import searchandsort.entities.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

class SortTask implements Callable<List<Student>> {
    private final List<Student> students;

    public SortTask(List<Student> students) {
        // Arbejd med kopi så vi ikke muterer original
        this.students = new ArrayList<>(students);
    }

    @Override
    public List<Student> call() throws Exception {
        SortExamples.quickSort(students, 0, students.size() - 1);
        return students;
    }
}