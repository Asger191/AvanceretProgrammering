package Portfolio.PortfolioAStar;

import java.util.*;

public class CitySolver {

    static int[][] grid = {
            {0,0,1,0,0,0,1,0,0,0},
            {0,1,0,0,1,0,0,0,1,0},
            {0,0,0,1,0,0,0,1,0,0},
            {1,0,0,0,0,1,0,0,0,1},
            {0,0,1,0,0,0,0,1,0,0},
            {0,1,0,0,1,0,0,0,0,0},
            {0,0,0,1,0,0,1,0,1,0},
            {1,0,0,0,0,1,0,0,0,0},
            {0,0,1,0,0,0,0,1,0,0},
            {0,0,0,0,1,0,0,0,0,0},
    };

    static final int ROWS = 10, COLS = 10;

    public static void main(String[] args) {
        CityNode[][] nodes = buildGraph();

        CityNode source = nodes[0][0]; // Nordby
        CityNode dest   = nodes[9][9]; // Sydby

        findShortestPath(source, dest);
    }

    private static CityNode[][] buildGraph() {
        // Byer placeret samme sted som i visualiseringen
        Map<String, String> cityNames = new HashMap<>();
        cityNames.put("0,0", "Nordby");
        cityNames.put("0,9", "Østby");
        cityNames.put("9,0", "Vestby");
        cityNames.put("9,9", "Sydby");
        cityNames.put("4,4", "Midtby");
        cityNames.put("2,7", "Skovby");
        cityNames.put("7,2", "Havneby");
        cityNames.put("5,8", "Bjergby");

        CityNode[][] nodes = new CityNode[ROWS][COLS];
        for (int r = 0; r < ROWS; r++)
            for (int c = 0; c < COLS; c++)
                if (grid[r][c] == 0)
                    nodes[r][c] = new CityNode(r, c, cityNames.get(r + "," + c));

        int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
        for (int r = 0; r < ROWS; r++)
            for (int c = 0; c < COLS; c++) {
                if (nodes[r][c] == null) continue;
                for (int[] d : dirs) {
                    int nr = r + d[0], nc = c + d[1];
                    if (nr >= 0 && nr < ROWS && nc >= 0 && nc < COLS && nodes[nr][nc] != null)
                        nodes[r][c].addNeighbor(nodes[nr][nc]);
                }
            }
        return nodes;
    }

    private static int heuristic(CityNode a, CityNode b) {
        return Math.abs(a.getRow() - b.getRow()) + Math.abs(a.getCol() - b.getCol());
    }

    public static void findShortestPath(CityNode source, CityNode dest) {
        Map<CityNode, CityNode> prev   = new HashMap<>();
        Map<CityNode, Integer> gCosts = new HashMap<>();
        Set<CityNode> visited = new HashSet<>();
        PriorityQueue<NodeWithCost> queue = new PriorityQueue<>();

        queue.add(new NodeWithCost(source, 0, heuristic(source, dest)));
        gCosts.put(source, 0);

        int step = 0;
        System.out.println("Søger fra " + source + " til " + dest + "\n");

        while (!queue.isEmpty()) {
            NodeWithCost current = queue.poll();
            if (visited.contains(current.node)) continue;
            visited.add(current.node);
            step++;

            int g    = current.gCost;
            int dRow = Math.abs(current.node.getRow() - dest.getRow());
            int dCol = Math.abs(current.node.getCol() - dest.getCol());
            int h    = dRow + dCol;

            System.out.printf("Skridt %2d: %-10s  g=%-3d h=%d (%d+%d)  f=%d%n",
                    step, current.node, g, h, dRow, dCol, g + h);

            if (current.node.equals(dest)) break;

            for (CityNode next : current.node.getNeighbors()) {
                if (visited.contains(next)) continue;
                int newG = current.gCost + 1;
                if (newG < gCosts.getOrDefault(next, Integer.MAX_VALUE)) {
                    gCosts.put(next, newG);
                    prev.put(next, current.node);
                    queue.add(new NodeWithCost(next, newG, heuristic(next, dest)));
                }
            }
        }

        // Rekonstruér stien
        List<CityNode> path = new ArrayList<>();
        CityNode node = dest;
        while (node != null) { path.add(0, node); node = prev.get(node); }

        System.out.println("\nKorteste vej (" + (path.size() - 1) + " skridt):");
        System.out.println(String.join(" → ", path.stream().map(Object::toString).toArray(String[]::new)));
    }

    private static class NodeWithCost implements Comparable<NodeWithCost> {
        CityNode node;
        int gCost, fCost;

        NodeWithCost(CityNode node, int gCost, int hCost) {
            this.node  = node;
            this.gCost = gCost;
            this.fCost = gCost + hCost;
        }

        @Override
        public int compareTo(NodeWithCost other) {
            return Integer.compare(this.fCost, other.fCost);
        }
    }
}