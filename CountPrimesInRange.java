import java.util.ArrayList;
import java.util.List;

public class CountPrimesInRange {

    private static int[] getSieve(int n){
        int[] arr = new int[n+1];

        for (int i = 2; i <= n; i++) {
            arr[i] = 1;
        }


        for (int i = 2; i*i <= n; i++) {
            if(arr[i] == 1){
                for (int j = i*i; j <= n; j+=i) {
                    arr[j] = 0;
                }
            }
        }

        return arr;
        // TC -> O(n x log(log(n)))
        // SC -> O(n)
    }

    private static void countPrimesBetter(List<List<Integer>> queries){
        int[] sieve = getSieve(1000000);

        for (List<Integer> query : queries) {
            int counter = 0;
            int left = query.get(0);
            int right = query.get(1);

            for (int i = left; i <= right; i++) {
                if(sieve[i] == 1){
                    counter += 1;
                }
            }
            System.out.println(counter);
        }
        // TC -> O(n x log(log(n))) + O(q x (left - right + 1))
        // SC -> O(n)
    }

    private static void countPrimesOptimal(List<List<Integer>> queries){
        int[] sieve = getSieve(1000000);

        // pre-computer the prefix sum on the sieve
        int counter = 0;
        for (int i = 0; i <= 1000000; i++) {
            counter += sieve[i];
            sieve[i] = counter;
        }

        for (List<Integer> query : queries) {
            int left = query.get(0);
            int right = query.get(1);
            System.out.println(sieve[right] - sieve[left - 1]);
        }
        // TC -> O(n x log(log(n))) + O(n) + O(q)
        // SC -> O(n)
    }
    public static void main(String[] args) {
        List<List<Integer>> queries = new ArrayList<>();
        queries.add(List.of(1,19));
        queries.add(List.of(15,28));
        queries.add(List.of(45,54));

        countPrimesBetter(queries);
        countPrimesOptimal(queries);
    }
}
