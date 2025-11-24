import java.util.Scanner;

public class ClimbingStairs{

    private static int findWaysToClimbStairsRecursive(int n, int[] dp){
        // top-down approach - we move from the required case to the base case
        if(n == 0){
            // there is only one way to climb a zero distance 
            // and that is just to stay there
            return 1;
        }

        if(n == 1){
            // there is only one way to climb a distance of 1 unit
            // that is to jump one step
            return 1;
        }

        dp[n] = findWaysToClimbStairsRecursive(n-1, dp) // in case we jump one step
         + findWaysToClimbStairsRecursive(n-2, dp); // in case we jump two steps
        return dp[n];
         // TC -> O(n)
         // SC -> O(n)

        
    }

    private static int findWaysToClimbStairsTabulation(int n){
        // bottom-up approach - we move from base case to the required case

        if(n <= 1){
            return 1;
        }

        int[] dp = new int[n+1];
        dp[0] = 1; // there is only one way to climb a zero distance
        dp[1] = 1; // there is only one way to climb a distance of 1 unit

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }

        return dp[n];
        // TC -> O(n)
        // SC -> O(n)
    }

    private static int findWaysToClimbStairsSpaceOptimized(int n){
        if(n <= 1){
            return 1;
        }
        int prev2 = 1;
        int prev = 1;

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
        Scanner sc  = new Scanner(System.in);
        int n = sc.nextInt();
        int[] dp = new int[n+1];
        System.out.println(findWaysToClimbStairsRecursive(n, dp));
        System.out.println(findWaysToClimbStairsTabulation(n));
        System.out.println(findWaysToClimbStairsSpaceOptimized(n));
        sc.close(); 
    }
}