import java.util.Scanner;

public class UnboundedKnapsack {

    private static int findMaxValueRecursiveHelper(int ind, int w, int[] weight, int[] value, int[][] dp){


        // base case
        if(ind == 0){
            return (w/weight[0]) * value[0];
        }

        if(dp[ind][w] != -1){
            return dp[ind][w];
        }

        int skipCurrent = 0 + findMaxValueRecursiveHelper(ind-1, w, weight, value, dp);
        int pickCurrent = (int) -1e9;
        if(weight[ind] <= w){
            pickCurrent = value[ind] + findMaxValueRecursiveHelper(ind, w-weight[ind], weight, value, dp);
        }

        dp[ind][w] = Math.max(skipCurrent, pickCurrent);
        return dp[ind][w];
        // TC -> O(n x bagWeight)
        // SC -> O(n x bagWeight) + O(bagWeight)
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
        // SC -> O(n x bagWeight) + O(bagWeight)
    }

    private static int findMaxValueTabulation(int[] weight, int[] value, int bagWeight){
        int n = weight.length;
        int[][] dp = new int[n][bagWeight+1];

        // base cases
        for (int w = 0; w <= bagWeight; w++) {
            dp[0][w] = (w/weight[0]) * value[0];
        }

        for (int i = 1; i < n; i++) {
            for (int w = 0; w <= bagWeight; w++) {
                int skipCurrent = 0 + dp[i-1][w];
                int pickCurrent = (int) -1e9;
                if(weight[i] <= w){
                    pickCurrent = value[i] + dp[i][w-weight[i]];
                }
                dp[i][w] = Math.max(skipCurrent, pickCurrent);
            }
        }

        return dp[n-1][bagWeight];
        // TC -> O(n x bagWeight)
        // SC -> O(n x bagWeight)
    }

    private static int findMaxValueSpaceOptimizedTwoRows(int[] weight, int[] value, int bagWeight){
        int n = weight.length;

        int[] dp = new int[bagWeight+1];

        // base case
        for (int w = 0; w <= bagWeight; w++) {
            dp[w] = (w/weight[0]) * value[0];
        }

        for (int i = 1; i < n; i++) {
            int[] current = new int[bagWeight+1];
            for (int w = 0; w <= bagWeight; w++) {
                int skipCurrent = 0 + dp[w];
                int pickCurrent = (int) -1e9;
                if(weight[i] <= w){
                    pickCurrent = value[i] + current[w-weight[i]];
                }
                current[w] = Math.max(skipCurrent, pickCurrent);
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

        // base case
        for (int w = 0; w <= bagWeight; w++) {
            dp[w] = (w/weight[0]) * value[0];
        }

        for (int i = 1; i < n; i++) {
            for (int w = 0; w <= bagWeight; w++) {
                int skipCurrent = 0 + dp[w];
                int pickCurrent = (int) -1e9;
                if(weight[i] <= w){
                    pickCurrent = value[i] + dp[w-weight[i]];
                }
                dp[w] = Math.max(skipCurrent, pickCurrent);
            }
        }

        return dp[bagWeight];
        // TC -> O(n x bagWeight)
        // SC -> O(bagWeight)
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] weight = new int[n];
        int[] value = new int[n];
        int bagWeight = sc.nextInt();
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
