package Portfolio.PortfolioAlgoritmik.backtracking.maze;

public class MazeExercise {
    static final int N = 4;

    static int[][] maze = {
            {1, 0, 1, 1},
            {1, 1, 1, 0},
            {0, 0, 1, 1},
            {1, 1, 0, 3}
    };

    static int[][] path = new int[N][N];

    public static void main(String[] args) {
        if (solveMaze(0, 0)) {
            printPath();
        } else {
            System.out.println("Ingen løsning fundet.");
        }
    }

    // TODO: Implementer denne metode
    static boolean solveMaze(int row, int col) {

            // 1. Er vi udenfor labyrinten?
            if(row > 3 || col > 3 || row < 0 || col < 0) return false;

            // Har sat målet til 3 i maze og når vi har ramt det har vi ramt målet
            // 2. Er vi på målet?
        if(maze[row][col] == 3){
                path[row][col] = 1;
                System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
                System.out.println("Juhuuu du har fundet målet");
                return true; // Tjekke om feltet er gyldigt ([row][col] == 1)
            }

        // 3. Er det en væg?
        if(maze[row][col] == 0) return false; // Tjekke om feltet er en væg

        // 4. Har vi været her før?
        if(path[row][col] == 1) return false; // Tjekke om feltet er besøgt

        // 5. Markér feltet som besøgt
        path[row][col] = 1;

        // 6. Prøv alle 4 retninger
        if(solveMaze(row + 1, col)) return true;
        if(solveMaze(row - 1, col)) return true;
        if(solveMaze(row, col + 1)) return true;
        if(solveMaze(row, col - 1)) return true;

        // 7. Ingen retning virkede - backtrack!
        path[row][col] = 0;
        return false;
    }

    static void printPath() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(path[i][j] + " ");
            }
            System.out.println();
        }
    }
}
