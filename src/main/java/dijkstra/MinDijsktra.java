package dijkstra;

import java.util.*;

public class MinDijsktra {
    
        public static void main(String[] args) {

            // Opretter alle noderne (husene)
            WeighedNote house0 = new WeighedNote("A");
            WeighedNote house1 = new WeighedNote("B");
            WeighedNote house2 = new WeighedNote("C");
            WeighedNote house3 = new WeighedNote("D");
            WeighedNote house4 = new WeighedNote("E");
            WeighedNote house5 = new WeighedNote("F");
            WeighedNote house6 = new WeighedNote("G");
            WeighedNote house7 = new WeighedNote("H");
            WeighedNote house8 = new WeighedNote("I");
            WeighedNote house9 = new WeighedNote("J");
            WeighedNote house10 = new WeighedNote("K");
            WeighedNote house11 = new WeighedNote("L");
            WeighedNote house12 = new WeighedNote("M");

            // Tilføjer veje mellem husene
            house0.addNeighbor(house1, 4);
            house0.addNeighbor(house2, 2);
            house0.addNeighbor(house3, 7);

            house1.addNeighbor(house4, 3);
            house1.addNeighbor(house5, 6);

            house2.addNeighbor(house5, 4);
            house2.addNeighbor(house6, 5);

            house3.addNeighbor(house6, 3);
            house3.addNeighbor(house7, 6);

            house4.addNeighbor(house8, 5);
            house4.addNeighbor(house5, 2);

            house5.addNeighbor(house8, 4);
            house5.addNeighbor(house9, 7);

            house6.addNeighbor(house9, 3);
            house6.addNeighbor(house10, 5);

            house7.addNeighbor(house10, 4);

            house8.addNeighbor(house11, 3);
            house8.addNeighbor(house9, 6);

            house9.addNeighbor(house11, 5);
            house9.addNeighbor(house10, 4);

            house10.addNeighbor(house12, 8);

            house11.addNeighbor(house12, 4);

            // Finder korteste vej
            findShortestPath(house0, house12);
        }

        public static void findShortestPath(WeighedNote start, WeighedNote destination) {

            // Gemmer den korteste kendte afstand til hver node
            Map<WeighedNote, Integer> distances = new HashMap<>();

            // Gemmer hvilken node vi kom fra
            Map<WeighedNote, WeighedNote> previous = new HashMap<>();

            // Holder styr på besøgte noder
            Set<WeighedNote> visited = new HashSet<>();

            // Prioritetskø der altid giver den billigste node først
            PriorityQueue<NodeDistance> queue = new PriorityQueue<>();

            // Startnoden har afstand 0
            distances.put(start, 0);

            // Tilføjer startnoden til køen
            queue.add(new NodeDistance(start, 0));

            while (!queue.isEmpty()) {

                // Henter noden med den laveste afstand
                NodeDistance current = queue.poll();

                WeighedNote currentNode = current.node;

                // Spring over hvis noden allerede er besøgt
                if (visited.contains(currentNode)) {
                    continue;
                }

                // Marker som besøgt
                visited.add(currentNode);

                // Stop hvis destinationen er fundet
                if (currentNode.equals(destination)) {
                    break;
                }

                // Undersøger alle naboer
                for (Map.Entry<WeighedNote, Integer> entry :
                        currentNode.getNeighbors().entrySet()) {

                    WeighedNote neighbor = entry.getKey();
                    int weight = entry.getValue();

                    // Spring over hvis naboen allerede er besøgt
                    if (visited.contains(neighbor)) {
                        continue;
                    }

                    // Beregner ny mulig afstand
                    int newDistance = distances.get(currentNode) + weight;

                    // Opdater kun hvis den nye vej er kortere
                    if (newDistance < distances.getOrDefault(neighbor, Integer.MAX_VALUE)) {

                        // Gem ny afstand
                        distances.put(neighbor, newDistance);

                        // Gem hvor vi kom fra
                        previous.put(neighbor, currentNode);

                        // Tilføj naboen til køen
                        queue.add(new NodeDistance(neighbor, newDistance));
                    }
                }
            }

            // Rekonstruerer den korteste vej
            List<String> path = new ArrayList<>();

            WeighedNote current = destination;

            while (current != null) {
                path.add(0, current.getName());
                current = previous.get(current);
            }

            // Printer resultatet
            System.out.println("Korteste vej:");

            for (int i = 0; i < path.size(); i++) {

                System.out.print("Hus " + path.get(i));

                if (i < path.size() - 1) {
                    System.out.print(" -> ");
                }
            }

            System.out.println();

            System.out.println("Samlet afstand: "
                    + distances.get(destination));
        }

        // Hjælpeklasse til PriorityQueue
        static class NodeDistance implements Comparable<NodeDistance> {

            WeighedNote node;
            int distance;

            public NodeDistance(WeighedNote node, int distance) {
                this.node = node;
                this.distance = distance;
            }

            @Override
            public int compareTo(NodeDistance other) {
                return Integer.compare(this.distance, other.distance);
            }
        }
    
}
