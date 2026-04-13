package searchandsort;

public class BigOExamples {
    public static void main(String[] args) {
        int n = 10; // Juster n for at se effekten
        /*
        System.out.println("O(1) - Konstant tid:");
        constantTime(n);

        System.out.println("\nO(log n) - Logaritmisk tid:");
        logTime(n);

        System.out.println("\nO(n) - Lineær tid:");
        linearTime(n);

        System.out.println("\nO(n^2) - Kvadratisk tid:");
        quadraticTime(n);

         */

        System.out.println("O(1) kommer her: ");
        O1(n);
        System.out.println("");

        System.out.println("O(log n) kommer her:");
        OLogN(50);
        System.out.println("");

        System.out.println("O(n) kommer her:");
        On(n);
        System.out.println("");

        System.out.println("O(n^2) kommer her:");
        OpløftetN(n);
    }

    // O(1) - Konstant tid
    public static void constantTime(int n) {
        System.out.println("Jeg printer altid én gang, uanset n.");
    }

    // O(log n) - Logaritmisk tid (Binær nedtælling)
    public static void logTime(int n) {
        for (int i = n; i > 1; i /= 2) {
            System.out.println("Jeg kører log n gange, n er nu: " + i);
        }
    }

    // O(n) - Lineær tid
    public static void linearTime(int n) {
        for (int i = 0; i < n; i++) {
            System.out.println("Itererer: " + i);
        }
    }

    // O(n^2) - Kvadratisk tid
    public static void quadraticTime(int n) {

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.println("Kvadratisk iteration: " + i + "," + j);
            }
        }

    }




    //O(1)
    public static void O1(int o){
        System.out.println("Vi printer kun dette en gang: " + o);
    }


    //O(log n)
   public static void OLogN(int p){
        int k = 0;
        for(int i = p; i>1; i=i/2){
            System.out.println("Vi printer nu: " + i);
            k++;
        }
       System.out.println("Gange vi laver log n, altså vi dividerer i to: " + k);
   }



    //O(n)
    public static void On(int o){
        for(int i = 0; i<o; i++){
            System.out.println("Vi printer i hver gang for at vise den lineære sammenhæng: " + i);
        }
    }


    //O(n^2)

    public static void OpløftetN(int n){
        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                System.out.println("Printer 0,1 for hver i iteration og indtil vi rammer 10, her kommer bid for bid: " + i + "," + j);
            }
        }
    }
}




