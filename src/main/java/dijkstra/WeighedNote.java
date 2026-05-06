package dijkstra;
import java.util.HashMap;
import java.util.Map;

public class WeighedNote {
    private String name;
    // Kanterne er vægtede, så vi gemmer afstanden til naboerne i et map
    private Map<WeighedNote, Integer> neighbors;

    public WeighedNote(String name) {
        this.name = name;
        this.neighbors = new HashMap<>();
    }

    public String getName(){ return name;}

    public Map<WeighedNote, Integer> getNeighbors() {return neighbors;}

    public void addNeighbor(WeighedNote neighbor, int weight){neighbors.put(neighbor, weight);}
}
