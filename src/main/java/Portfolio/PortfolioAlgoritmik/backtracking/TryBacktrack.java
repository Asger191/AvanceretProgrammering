package Portfolio.PortfolioAlgoritmik.backtracking;

public class TryBacktrack {
    public static void main(String[] args) {

     /*   System.out.println("Simple loop:");
        simpleForloop();
        System.out.println("----------------------------------");
        System.out.println("Inner loop:");
        innerLoop();
        System.out.println("----------------------------------");
        System.out.println("More inner loops:");
        moreInnerLoops();
        System.out.println("----------------------------------");*/
        System.out.println("Explore:");
        explore(0, "");
        System.out.println("----------------------------------");
        System.out.println("Explore with backtrack");
        exploreWithBacktrack(0, "", 0);
    }

    static void simpleForloop(){
        for(int i = 1; i <= 2; i++){
            System.out.println(i);
        }
    }


    static void innerLoop(){
        for (int i = 1; i <= 2; i++) {
            for (int j = 1; j <= 2; j++) {
                System.out.println("" + i + j);
            }
        }
    }

    static void moreInnerLoops(){
        for (int i = 1; i < 3; i++) {
            for (int j = 1; j <= 2; j++) {
                for (int k = 1; k <= 2; k++) {
                    System.out.println("" + i + j + k);
                }
            }
        }
    }

    // Alle muligheder afsøges - vi går så dybt som muligt (DFS)
    // Når vi når enden af en sti (step == 3), printer vi path
    // Derefter går vi tilbage og prøver næste mulighed (implicit backtracking)
    static void explore(int step, String path) {
        if (step == 3) {
            System.out.println("Done: " + path);
            return;
        }
        for (int i = 1; i <= 2; i++) {
            explore(step + 1, path + i);
        }
    }

    // Vi afsøger alle muligheder, men stopper tidligt hvis sum > 5
    // Vi printer kun, hvis vi er ved enden af en vej (step == 3) OG sum == 5
    // Derefter går vi tilbage og prøver næste mulighed (backtracking)
    static void exploreWithBacktrack(int step, String path, int sum) {
        if (sum > 5) return; // afskær stier, der ikke kan give gyldig løsning

        if (step == 3) {
            if (sum == 5) {
                System.out.println("Match: " + path);
            }
            return;
        }

        for (int i = 1; i <= 2; i++) {
            exploreWithBacktrack(step + 1, path + i, sum + i);
        }
    }

}
