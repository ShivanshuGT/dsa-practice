import java.util.ArrayList;
import java.util.List;

public class PrintAllPrimeFactors {

    static List<Integer> getAllPrimeFactors(int n){
        List<Integer> ans = new ArrayList<>();

        for (int i = 2; i*i <= n; i++) {
            if((n % i) == 0){
                ans.add(i);
                while ((n % i) == 0) {
                    n = n / i;
                }
            }
        }

        if(n != 1){
            ans.add(n);
        }
        return ans;
        // TC -> O(sqrt(n) x logn)
        // SC -> O(1)
    }
    public static void main(String[] args) {
        int n = 1780;
        List<Integer> primeFactors = getAllPrimeFactors(n);
        for (Integer primeFactor : primeFactors) {
            System.out.print(primeFactor + " ");
        }
    }
}
