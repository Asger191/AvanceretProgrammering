package Portfolio.PortfolioAlgoritmik.lazy;

import java.util.HashMap;
import java.util.Map;

public class LazyCalculations {
    static Map<Integer, Long> memo = new HashMap<>();
    static Map<Integer, Long> myMemo = new HashMap<>();
    static int counter = 1;
    public static long lazyFactorial(int n) {

        if (memo.containsKey(n)) {
            return memo.get(n); // allerede beregnet? returnér resultatet
        }

        long result;
        if (n == 0 || n == 1) {
            result = 1;
        } else {
            result = n * lazyFactorial(n - 1);
        }

        memo.put(n, result); // gem til næste gang
        return result;
    }

    public static long lazySum(int n){

        if (myMemo.containsKey(n)) {
            return myMemo.get(n); // allerede beregnet? returnér resultatet
        }
        System.out.println("Counter: " + counter++);

        long result;
        if(n==0 || n==1){
            result = 1;
        }
        else {
           result = n + lazySum(n - 1);
        }

        myMemo.put(n, result);
        return result;
    }

    public static void main(String[] args) {
        System.out.println(lazySum(5));
        System.out.println("-------------------------------------------------------------------------------------------------------------");
        System.out.println(lazyFactorial(5));  // udregner og gemmer
        System.out.println(lazyFactorial(4));  // bliver hurtigt, fordi det allerede er gemt
        System.out.println(lazyFactorial(6));  // bruger gemt factorial(5)
    }
}

