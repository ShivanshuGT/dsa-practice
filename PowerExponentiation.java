public class PowerExponentiation {

    private static double pow(double x, int n){
        int m = n;
        n = Math.abs(n);

        double ans = 1;

        while (n > 0) {
            if((n % 2) == 0){
                // the power is even
                x = x * x;
                n = n / 2;
            }else{
                // the power is odd
                ans = ans * x;
                n = n - 1;
            }
        }
        if(m < 0){
            return 1/ans;
        }
        return ans;
        // TC -> O(log(n))
        // SC -> O(1)
    }
    public static void main(String[] args) {
        double x = 1.2;
        int n = 21;
        System.out.println(pow(x, n));
    }
}
