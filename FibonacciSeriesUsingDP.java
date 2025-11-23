import java.util.Scanner;

public class FibonacciSeriesUsingDP {

    private static int fibonacciRecursive(int n, int[] dp){
        // TOP-DOWN approach
        if (n <= 1){
            return n;
        }
        if(dp[n] != -1){
            return dp[n];
        }
        dp[n] = fibonacciRecursive(n-1, dp) + fibonacciRecursive(n-2, dp);
        return dp[n];
        // TC -> O(n)
        // SC -> O(n)
    }

    private static int fibonacciTabulation(int n){
        if(n <= 1){
            return n;
        }
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
        // TC -> O(n)
        // SC -> O(n)

    }

    private static int fibonacciSpaceOptimized(int n){
        if(n <= 1){
            return n;
        }

        int prev = 1;
        int prev2 = 0;

        for (int i = 2; i <= n; i++) {
            int val = prev + prev2;
            prev2 = prev;
            prev = val;
        }
        return prev;
        // TC -> O(n)
        // SC -> O(1)
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        // int[] dp = new int[n+1];
        // for (int i = 0; i <= n; i++) {
        //     dp[i] = -1;
        // }
        // System.out.println(fibonacciRecursive(n, dp));
        // System.out.println(fibonacciTabulation(n));
        System.out.println(fibonacciSpaceOptimized(n));
        sc.close();
    }
}
