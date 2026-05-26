package graphs.astar;

import Portfolio.PortfolioDesignpatterns.StrategyAStar.EuclideanHeuristic;
import Portfolio.PortfolioDesignpatterns.StrategyAStar.HeuristicStrategy;
import Portfolio.PortfolioDesignpatterns.StrategyAStar.ManhattanHeuristic;
import Portfolio.PortfolioDesignpatterns.StrategyAStar.ZeroHeuristic;

import java.util.*;

public class MazeSolver {

    // 0 = åben, 1 = mur
    static int[][] grid = {
            {0,0,0,1,0,0,0,1,0,0,0,0},
            {1,1,0,1,0,1,0,1,0,1,1,0},
            {0,0,0,0,0,1,0,0,0,0,1,0},
            {0,1,1,1,1,1,1,1,1,1,1,0},
            {0,0,0,0,0,0,0,0,1,0,0,0},
            {1,1,1,0,1,1,1,0,1,1,0,1},
            {0,0,0,0,0,0,1,0,0,0,0,0},
            {0,1,1,1,1,0,1,1,1,1,1,0},
            {0,0,0,0,1,0,0,0,0,0,1,0},
            {1,1,0,1,1,0,1,1,0,1,1,0},
            {0,0,0,0,0,0,1,0,0,0,1,0},
            {0,1,1,1,0,1,1,0,1,1,0,0},
    };

    static final int ROWS = 12, COLS = 12;

    private final HeuristicStrategy heuristicStrategy;

    public MazeSolver(HeuristicStrategy heuristicStrategy){
        this.heuristicStrategy = heuristicStrategy;
    }

    public static void main(String[] args) {

        MazeNode[][] nodes = buildGraph();
        MazeNode source      = nodes[0][0];
        MazeNode destination = nodes[11][11];

        // Kør med alle tre heuristikker
        System.out.println("=== Manhattan ===");
        new MazeSolver(new ManhattanHeuristic()).findShortestPath(source, destination);

        System.out.println("\n=== Euklidisk ===");
        new MazeSolver(new EuclideanHeuristic()).findShortestPath(source, destination);

        System.out.println("\n=== Zero (Dijkstra) ===");
        new MazeSolver(new ZeroHeuristic()).findShortestPath(source, destination);



        // Lambda-variant — ingen separat klasse nødvendig
        System.out.println("\n=== Lambda: Manhattan ===");
        HeuristicStrategy lambdaManhattan =
                (node, dest) -> Math.abs(dest.getRow() - node.getRow())
                        + Math.abs(dest.getCol() - node.getCol());
        new MazeSolver(lambdaManhattan).findShortestPath(source, destination);
    }

    public void findShortestPath(MazeNode source, MazeNode destination) {
        Map<MazeNode, MazeNode> prev = new HashMap<>();
        Map<MazeNode, Integer> dist = new HashMap<>();
        Set<MazeNode> visited = new HashSet<>();

        PriorityQueue<NodeWithDist> queue = new PriorityQueue<>();
        queue.add(new NodeWithDist(source, 0, heuristicStrategy.calculate(source, destination)));
        dist.put(source, 0);

        while (!queue.isEmpty()) {
            NodeWithDist current = queue.poll();

            if (current.node.equals(destination)) break;
            if (visited.contains(current.node)) continue;
            visited.add(current.node);

            for (MazeNode next : current.node.getNeighbors()) {
                if (visited.contains(next)) continue;

                // Alle skridt koster 1 i en labyrint
                int newDist = current.gCost + 1;

                if (newDist < dist.getOrDefault(next, Integer.MAX_VALUE)) {
                    dist.put(next, newDist);
                    prev.put(next, current.node);
                    queue.add(new NodeWithDist(next, newDist, heuristicStrategy.calculate(next, destination)));
                }
            }
        }

        // Rekonstruer stien via prev
        List<String> path = new ArrayList<>();
        MazeNode step = destination;
        while (step != null) {
            path.add(0, "(" + step.getRow() + "," + step.getCol() + ")");
            step = prev.get(step);
        }

        System.out.println("Korteste vej: " + path);
        System.out.println("Antal skridt: " + (path.size() - 1));
    }

    // Hjælpemetode til at bygge graf (udtrukket fra main for overskuelighed)
    private static MazeNode[][] buildGraph() {
        MazeNode[][] nodes = new MazeNode[ROWS][COLS];
        for (int r = 0; r < ROWS; r++)
            for (int c = 0; c < COLS; c++)
                if (grid[r][c] == 0) nodes[r][c] = new MazeNode(r, c);

        int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};
        for (int r = 0; r < ROWS; r++)
            for (int c = 0; c < COLS; c++) {
                if (nodes[r][c] == null) continue;
                for (int[] d : directions) {
                    int nr = r + d[0], nc = c + d[1];
                    if (nr >= 0 && nr < ROWS && nc >= 0 && nc < COLS && nodes[nr][nc] != null)
                        nodes[r][c].addNeighbor(nodes[nr][nc]);
                }
            }
        return nodes;
    }

    private static class NodeWithDist implements Comparable<NodeWithDist> {
        MazeNode node;
        int gCost;
        int fCost;

        public NodeWithDist(MazeNode node, int gCost, int hCost) {
            this.node = node;
            this.gCost = gCost;
            this.fCost = gCost + hCost;
        }

        @Override
        public int compareTo(NodeWithDist other) {
            return Integer.compare(this.fCost, other.fCost);
        }
    }
}