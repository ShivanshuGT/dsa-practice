import java.util.Scanner;

public class RodCuttingProblem {

    private static int findMaxValueRecursiveHelper(int ind, int target, int[] price, int[][] dp){
        // base case
        if(ind == 0){
            // here the rod length is 1
            return target * price[0];
        }

        if(dp[ind][target] != -1){
            return dp[ind][target];
        }

        int skipCurrent = 0 + findMaxValueRecursiveHelper(ind-1, target, price, dp);
        int pickCurrent = (int) -1e9;
        int rodLength = ind+1;
        if(rodLength <= target){
            pickCurrent = price[ind] + findMaxValueRecursiveHelper(ind, target-rodLength, price, dp);
        }
        dp[ind][target] = Math.max(skipCurrent, pickCurrent);
        return dp[ind][target];
        // TC -> O(n x (n+1))
        // SC -> O(n x (n+1)) + O(n)


    }

    private static int findMaxValueRecursive(int n, int[] price){
        int[][] dp = new int[n][n+1];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j] = -1;
            }
        }

        return findMaxValueRecursiveHelper(n-1, n, price, dp);
        // TC -> O(n x (n+1))
        // SC -> O(n x (n+1)) + O(n)
    }

    private static int findMaxValueTabulation(int n, int[] price){
        int[][] dp = new int[n][n+1];

        // base case
        for (int l = 0; l <= n; l++) {
            // for ind = 0, rod length will be 1
            dp[0][l] = l * price[0];
        }

        for (int ind = 1; ind < n; ind++) {
            for (int l = 0; l <= n; l++) {
                int skipCurrent = 0 + dp[ind-1][l];
                int pickCurrent = (int) -1e9;
                int rodLength = ind+1;
                if(rodLength <= l){
                    pickCurrent = price[ind] + dp[ind][l-rodLength];
                }
                dp[ind][l] = Math.max(pickCurrent, skipCurrent);
            }
        }

        return dp[n-1][n];
        // TC -> O(n x (n+1))
        // SC -> O(n x (n+1))
    }

    private static int findMaxValueSpaceOptimizedTwoRows(int n, int[] price){
        int[] dp = new int[n+1];

        // base case
        for (int l = 0; l <= n; l++) {
            dp[l] = l * price[0];
        }

        for (int ind = 1; ind <= n; ind++) {
            int[] current = new int[n+1];
            for (int l = 0; l <= n; l++) {
                int skipCurrent = 0 + dp[l];
                int pickCurrent = (int) -1e9;
                int rodLength = ind+1;
                if(rodLength <= l){
                    pickCurrent = price[ind] + current[l-rodLength];
                }
                current[l] = Math.max(pickCurrent, skipCurrent);
            }
            dp = current;
        }

        return dp[n];
        // TC -> O(n x (n+1))
        // SC -> O(n+1)
    }

    private static int findMaxValueSpaceOptimizedOneRow(int n, int[] price){
        int[] dp = new int[n+1];

        // base case
        for (int l = 0; l <= n; l++) {
            dp[l] = l * price[0];
        }

        for (int ind = 1; ind < n; ind++) {
            for (int l = 0; l <= n; l++) {
                int skipCurrent = 0 + dp[l];
                int pickCurrent = (int) -1e9;
                int rodLength = ind+1;
                if(rodLength <= l){
                    pickCurrent = price[ind] + dp[l-rodLength];
                }
                dp[l] = Math.max(pickCurrent, skipCurrent);
            }
        }

        return dp[n];
        // TC -> O(n x (n+1))
        // SC -> O(n+1)
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] price = new int[n];
        for (int i = 0; i < n; i++) {
            price[i] = sc.nextInt();
        }
        System.out.println(findMaxValueRecursive(n, price));
        System.out.println(findMaxValueTabulation(n, price));
        System.out.println(findMaxValueSpaceOptimizedTwoRows(n, price));
        System.out.println(findMaxValueSpaceOptimizedOneRow(n, price));
        sc.close();
    }
}
