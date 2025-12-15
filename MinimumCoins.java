import java.util.Scanner;

public class MinimumCoins {

    private static int findMinimumCoinsRecursiveHelper(int ind, int target, int[] coins, int[][] dp){
        // base cases

        if(ind == 0){
            if((target % coins[0]) == 0){
                return target/coins[0];
            }else{
                return (int) 1e9;
            }
        }

        if(dp[ind][target] != -1){
            return dp[ind][target];
        }

        int skipCurrent = 0 + findMinimumCoinsRecursiveHelper(ind-1, target, coins, dp);

        int pickCurrent = (int) 1e9;
        if(coins[ind] <= target){
            pickCurrent = 1 + findMinimumCoinsRecursiveHelper(ind, target-coins[ind], coins, dp);
        }

        dp[ind][target] = Math.min(skipCurrent, pickCurrent);
        return dp[ind][target];
        // TC -> O(n x target)
        // SC -> O(n x target) + recurison stack space
    }

    private static int findMinimumCoinsRecursive(int[] coins, int target){
        int n = coins.length;

        int[][] dp = new int[n][target+1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= target; j++) {
                dp[i][j] = -1;
            }
        }

        int result = findMinimumCoinsRecursiveHelper(n-1, target, coins, dp);
        if(result >= (int)1e9){
            return -1;
        }
        return result;
        // TC -> O(n x target)
        // SC -> O(n x target) + recursion stack space
        
    }

    private static int findMinimumCoinsTabulation(int[] coins, int target){
        int n = coins.length;
        int[][] dp = new int[n][target+1];

        // base cases
        for (int t = 0; t <= target; t++) {
            if((t % coins[0]) == 0){
                dp[0][t] = t/coins[0];
            }else{
                dp[0][t] = (int)1e9;
            }
        }

        for (int i = 1; i < n; i++) {
            for (int t = 0; t <= target; t++) {
                int skipCurrent = 0 + dp[i-1][t];
                int pickCurrent = (int)1e9;
                if(coins[i] <= t){
                    pickCurrent = 1 + dp[i][t-coins[i]];
                }
                dp[i][t] = Math.min(skipCurrent, pickCurrent);
            }
        }

        if(dp[n-1][target] >= (int)1e9){
            return -1;
        }
        return dp[n-1][target];
        // TC -> O(n x target)
        // SC -> O(n x target)
    }

    private static int findMinimumCoinsSpaceOptimized(int[] coins, int target){
        int n = coins.length;
        int[] dp = new int[target+1];

        // base cases
        for (int t = 0; t <= target; t++) {
            if((t % coins[0]) == 0){
                dp[t] = t/coins[0];
            }else{
                dp[t] = (int)1e9;
            }
        }

        for (int i = 1; i < n; i++) {
            int current[] = new int[target+1];
            for (int t = 0; t <= target; t++) {
                int skipCurrent = 0 + dp[t];
                int pickCurrent = (int)1e9;
                if(coins[i] <= t){
                    pickCurrent = 1 + current[t-coins[i]];
                }
                current[t] = Math.min(skipCurrent, pickCurrent);
            }
            dp = current;
        }

        if(dp[target] >= (int)1e9){
            return -1;
        }
        return dp[target];
        // TC -> O(n x target)
        // SC -> O(n x target)
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] coins = new int[n];
        int target = sc.nextInt();
        for (int i = 0; i < n; i++) {
            coins[i] = sc.nextInt();
        }
        System.out.println(findMinimumCoinsRecursive(coins, target));
        System.out.println(findMinimumCoinsTabulation(coins, target));
        System.out.println(findMinimumCoinsSpaceOptimized(coins, target));
        sc.close();
    }
}
