import java.util.Scanner;

public class ZeroOneKnapsack {

    private static int findMaxValueRecursiveHelper(int ind, int W, int[] weight, int[] value, int[][] dp){
        // base cases
        if(ind == 0){
            if(weight[0] <= W){
                // the item can fit in the bag
                return value[0];
            }else{
                return 0;
            }
        }

        if(dp[ind][W] != -1){
            return dp[ind][W];
        }

        int skipCurrent = 0 + findMaxValueRecursiveHelper(ind-1, W, weight, value, dp);
        int pickCurrent = Integer.MIN_VALUE;
        if(weight[ind] <= W){
            // the item can fit in the bag
            pickCurrent = value[ind] + findMaxValueRecursiveHelper(ind-1, W-weight[ind], weight, value, dp);
        }

        dp[ind][W] = Math.max(skipCurrent, pickCurrent);
        return dp[ind][W];
        // TC -> O(n x bagWeight)
        // SC -> O(n x bagWeight) + O(n)
    }

    private static int findMaxValueRecursive(int[] weight, int[] value, int bagWeight){
        int n = weight.length;

        int[][] dp = new int[n][bagWeight+1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= bagWeight; j++) {
                dp[i][j] = -1;
            }
        }

        return findMaxValueRecursiveHelper(n-1, bagWeight, weight, value, dp);
        // TC -> O(n x bagWeight)
        // SC -> O(n x bagWeight) + O(n)
    }

    private static int findMaxValueTabulation(int[] weight, int[] value, int bagWeight){
        int n = weight.length;

        int[][] dp = new int[n][bagWeight+1];

        // base cases
        for (int i = weight[0]; i <= bagWeight; i++) {
            dp[0][i] = value[0];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= bagWeight; j++) {
                int skipCurrent = 0 + dp[i-1][j];
                int pickCurrent = Integer.MIN_VALUE;
                if(weight[i] <= j){
                    pickCurrent = value[i] + dp[i-1][j-weight[i]];
                }
                dp[i][j] = Math.max(skipCurrent, pickCurrent);
            }
        }
        return dp[n-1][bagWeight];
        // TC -> O(n x bagWeight)
        // SC -> O(n x bagWeight)
    }

    private static int findMaxValueSpaceOptimizedTwoRows(int[] weight, int[] value, int bagWeight){
        int n = weight.length;

        int[] dp = new int[bagWeight+1];
        for (int i = weight[0]; i <= bagWeight; i++) {
            dp[i] = value[0];
        }

        for (int i = 1; i < n; i++) {
            int[] current = new int[bagWeight+1];
            for (int j = 0; j <= bagWeight; j++) {
                int skipCurrent = 0 + dp[j];
                int pickCurrent = Integer.MIN_VALUE;
                if(weight[i] <= j){
                    pickCurrent = value[i] + dp[j-weight[i]];
                }
                current[j] = Math.max(skipCurrent, pickCurrent);
            }
            dp = current;
        }
        return dp[bagWeight];
        // TC -> O(n x bagWeight)
        // SC -> O(bagWeight)
    }

    private static int findMaxValueSpaceOptimizedOneRow(int[] weight, int[] value, int bagWeight){
        int n = weight.length;

        int[] dp = new int[bagWeight+1];
        for (int i = weight[0]; i <= bagWeight; i++) {
            dp[i] = value[0];
        }

        for (int i = 1; i < n; i++) {
            for (int j = bagWeight; j >= 0; j--) {
                int skipCurrent = 0 + dp[j];
                int pickCurrent = Integer.MIN_VALUE;
                if(weight[i] <= j){
                    pickCurrent = value[i] + dp[j-weight[i]];
                }
                dp[j] = Math.max(skipCurrent, pickCurrent);
            }
        }
        return dp[bagWeight];

        // TC -> O(n x bagWeight)
        // SC -> O(bagWeight)
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int bagWeight = sc.nextInt();
        int[] weight = new int[n];
        int[] value = new int[n];
        for (int i = 0; i < n; i++) {
            weight[i] = sc.nextInt();
        }
        for (int i = 0; i < n; i++) {
            value[i] = sc.nextInt();
        }

        System.out.println(findMaxValueRecursive(weight, value, bagWeight));
        System.out.println(findMaxValueTabulation(weight, value, bagWeight));
        System.out.println(findMaxValueSpaceOptimizedTwoRows(weight, value, bagWeight));
        System.out.println(findMaxValueSpaceOptimizedOneRow(weight, value, bagWeight));
        sc.close();
    }
}
