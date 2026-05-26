package Portfolio.PortfolioAStar;

import java.util.ArrayList;
import java.util.List;

public class CityNode {
    private int row;
    private int col;
    private String name; // null hvis noden ikke er en by
    private List<CityNode> neighbors;

    public CityNode(int row, int col, String name) {
        this.row = row;
        this.col = col;
        this.name = name;
        this.neighbors = new ArrayList<>();
    }

    public int getRow() { return row; }
    public int getCol() { return col; }
    public String getName() { return name; }
    public boolean isCity() { return name != null; }
    public List<CityNode> getNeighbors() { return neighbors; }

    public void addNeighbor(CityNode neighbor) {
        neighbors.add(neighbor);
    }

    @Override
    public String toString() {
        return name != null ? name : "(" + row + "," + col + ")";
    }
}
