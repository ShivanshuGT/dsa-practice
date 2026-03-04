import java.util.ArrayList;
import java.util.List;

public class PrintAllDivisors {

    private static List<Integer> findALlDivisors(int n){
        List<Integer> ans = new ArrayList<>();
        for (int i = 1; i*i <= n; i++) {
            if((n % i) == 0){
                ans.add(i);
                if((n / i) != i){
                    ans.add(n/i);
                }
            }
            
        }
        return ans;
        // TC -> O(sqrt(n))
        // SC -> O(1)
    }
    public static void main(String[] args) {
        int n = 36;
        List<Integer> divisors = findALlDivisors(n);
        for (Integer div : divisors) {
            System.out.print(div + " ");
        }

    }
}
