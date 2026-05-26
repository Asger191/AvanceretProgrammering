package algorithms.lazy;

import java.util.HashMap;
import java.util.Map;

public class EagerCalculations {
    static Map<Integer, Long> myMemo = new HashMap<>();


static{
    myMemo.put(0, 1L); // base case: 0! = 1

    // beregn fakultet for alle tal op til 50 og læg det i map
    for(int i = 1; i<50; i++){
        myMemo.put(i, i * myMemo.get(i-1));
    }

}

   public static long eagerFactorial(int n){
    return myMemo.get(n);
   }

   public static void main(String[] args){
       System.out.println(eagerFactorial(5));
   }
}
