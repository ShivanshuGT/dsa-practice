import java.util.Scanner;

public class MatrixChainMultiplication {

    private static int findMinOperationsRecursiveHelper(int i, int j, int[] arr, int[][] dp){

        // base case
        if(i == j){
            // this means there is only one matrix
            return 0; // since there is no other matrix left for multiplication
        }

        if(dp[i][j] != -1){
            return dp[i][j];
        }

        int ans = Integer.MAX_VALUE;

        for (int k = i; k < j; k++) {
            int steps = (arr[i-1] * arr[k] * arr[j]) + findMinOperationsRecursiveHelper(i, k, arr, dp)
            + findMinOperationsRecursiveHelper(k+1, j, arr, dp);

            if(steps < ans){
                ans = steps;
            }
        }

        dp[i][j] = ans;
        return dp[i][j];

        // TC -> O(n x n x n)
        // SC -> O(n x n) + O(n)
    }

    private static int findMinOperationsRecursive(int[] arr){
        int n = arr.length;
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = -1;
            }
        }

        return findMinOperationsRecursiveHelper(1, n-1, arr, dp);
        // TC -> O(n x n x n)
        // SC -> O(n x n) + O(n)
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        System.out.println(findMinOperationsRecursive(arr));
        sc.close();
    }
}
