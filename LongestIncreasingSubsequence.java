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
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        System.out.println(findLISRecursive(arr));
        sc.close();
    }
}
