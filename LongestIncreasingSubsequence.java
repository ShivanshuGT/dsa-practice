import java.util.Scanner;

public class LongestIncreasingSubsequence {

    private static int findLISRecursiveHelper(int ind, int prevInd, int[] arr, int [][] dp){
        int n = arr.length;
        // base case
        if(ind == n){
            return 0;
        }

        if(dp[ind][prevInd+1] != -1){ // since we have right shifted for prevInd by 1
            return dp[ind][prevInd+1];
        }

        // skip the current index
        int notPick = 0 + findLISRecursiveHelper(ind+1, prevInd, arr, dp);
        // pick the current index
        int pick = 0;
        if((prevInd == -1) || (arr[ind] > arr[prevInd])){
            pick = 1 + findLISRecursiveHelper(ind+1, ind, arr, dp);
        }
        dp[ind][prevInd+1] = Math.max(notPick, pick);
        return dp[ind][prevInd+1];
        // TC -> O(n x n)
        // SC -> O(n x n) + O(n)
    }

    private static int findLISRecursive(int [] arr){
        int n = arr.length;
        int[][] dp = new int[n][n+1]; 

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j] = -1;
            }
        }

        return findLISRecursiveHelper(0, -1, arr, dp);
        // TC -> O(n x n)
        // SC -> O(n x n) + O(n)
    }

    private static int findLISTabulation(int[] arr){
        int n = arr.length;

        int[][] dp = new int[n+1][n+1];

        // base case
        // for ind == n
        for (int prevInd = 0; prevInd <= n; prevInd++) {
            dp[n][prevInd] = 0;
        }

        for (int ind = n-1; ind >= 0; ind--) {
            for (int prevInd = ind-1; prevInd >= -1; prevInd--) {
                int skip = 0 + dp[ind+1][prevInd+1];
                int pick = 0;
                if((prevInd == -1) || (arr[ind] > arr[prevInd])){
                    pick = 1 + dp[ind+1][ind+1];
                }
                dp[ind][prevInd+1] = Math.max(skip, pick);
            }
        }

        return dp[0][0];
        // TC -> O(n x n)
        // SC -> O(n x n)
    }

    private static int findLISSpaceOptimized(int[] arr){
        int n = arr.length;

        int[] dp = new int[n+1];

        // base case
        // for ind == n
        for (int prevInd = 0; prevInd <= n; prevInd++) {
            dp[prevInd] = 0;
        }

        for (int ind = n-1; ind >= 0; ind--) {
            int[] current = new int[n+1];
            for (int prevInd = n-1; prevInd >= -1; prevInd--) {
                int skip = 0 + dp[prevInd+1];
                int pick = 0;
                if((prevInd == -1) || (arr[ind] > arr[prevInd])){
                    pick = 1 + dp[ind+1];
                }
                current[prevInd+1] = Math.max(skip, pick);
            }
            dp = current;
        }

        return dp[0];
        // TC -> O(n x n)
        // SC -> O(n x 2)
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        System.out.println(findLISRecursive(arr));
        System.out.println(findLISTabulation(arr));
        System.out.println(findLISSpaceOptimized(arr));
        sc.close();
    }
}
