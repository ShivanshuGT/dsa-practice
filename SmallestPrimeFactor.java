import java.util.List;

public class SmallestPrimeFactor {

    private static void printPrimeFactorization(List<Integer> queries){
        // pre-compute the smallest prime factor of each number using sieve of Eratosthenes algorithm

        int[] spf = new int[100000 + 1];

        for (int i = 1; i <= 100000; i++) {
            spf[i] = i;
        }

        for (int i = 2; i*i <= 100000; i++) {
            if(spf[i] == i){
                // the number is itself a prime number

                // mark all other multiples of this number
                for (int j = 2*i; j*j <= 100000; j+=i) {
                    if(spf[j] == j){
                        // mark them only if they are not marked previously
                        spf[j] = i;
                    }
                }
            }
        }

        // answer the queries
        for (Integer query : queries) {
            System.out.print("Prime factors of " + query + " are: " );
            while (query != 1) {
                System.out.print(spf[query] + " ");
                query = query / spf[query];
            }
            System.out.println();
        }

        // TC -> O(n x log(log(n))) + O(q x log(n))
        // SC -> O(n)
    }
    public static void main(String[] args) {
        List<Integer> queries = List.of(56, 17, 95, 12222);
        printPrimeFactorization(queries);
    }
}
