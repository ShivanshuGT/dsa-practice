public class SieveOfEratosthenes {

    private static void printAllPrimesTillN(int n){
        int[] prime = new int[n+1];

        for (int i = 0; i <= n; i++) {
            prime[i] = 1;
        }

        // pre-compute whih numbers are prime - using sieve of eratosthenes algorithm
        for (int i = 2; i*i <= n; i++) {
            if(prime[i] == 1){
                // mark all multiples of i as non-prime
                for (int j = i*i; j <=n; j+=i) {
                    prime[j] = 0;
                }
            }
        }

        for (int i = 2; i <= n; i++) {
            if(prime[i] == 1){
                System.out.print(i + " ");
            }
        }
    }
    public static void main(String[] args) {
        int n = 300000;
        printAllPrimesTillN(n);
    }
}
