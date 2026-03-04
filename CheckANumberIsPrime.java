public class CheckANumberIsPrime {

    // a number is prime if it is divisible by 1 nad itself and 
    // the total number of divisors is exactly 2

    private static boolean checkPrime(int n){
        int counter = 0;

        for (int i = 1; i*i <= n; i++) {
            if((n % i) == 0){
                counter += 1;
                if((n / i) != i){
                    counter += 1;
                }
            }
            if(counter > 2){
                break;
            }
        }

        if(counter == 2){
            return true;
        }
        return false;
        // TC -> O(sqrt(n))
        // SC -> O(1)
    }
    
    public static void main(String[] args) {
        int n = 1;
        System.out.println(checkPrime(n));
    }
}
