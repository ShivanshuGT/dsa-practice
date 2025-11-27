import java.util.Scanner;

public class MaximumSumOfNonAdjacentElements {

    private static int findMaximumSumRecursive(int ind, int[] arr, int[] dp){
        if(ind == 0){
            return arr[0];
        }

        if( ind < 0){
            return 0;
        }

        if(dp[ind] != -1){
            return dp[ind];
        }

        int takeCurrentIndex = arr[ind] + findMaximumSumRecursive(ind-2, arr, dp); // taking the current index element in the subsequence
        int skipCurrentIndex = 0 + findMaximumSumRecursive(ind-1, arr, dp); // not taking the current index element in the subsequence
        dp[ind] = Math.max(takeCurrentIndex, skipCurrentIndex);
        return dp[ind];
        // TC -> O(n)
        // SC -> O(n)
    }

    private static int findMaximumSumTabulation(int n, int[] arr){
        int[] dp = new int[n];
        if( n == 1){
            return arr[0];
        }
        if(n == 2){
            return Math.max(arr[0], arr[1]);
        }
        dp[0] = arr[0];
        dp[1] = Math.max(arr[0], arr[1]);

        for (int i = 2; i < n; i++) {
            int takeCurrentIndex = arr[i] + dp[i-2];
            int skipCurrentIndex = 0 + dp[i-1];
            dp[i] = Math.max(takeCurrentIndex, skipCurrentIndex);
        }
        return dp[n-1];
        // TC -> O(n)
        // SC -> O(n)
    }

    private static int findMaximumSumSpaceOptimized(int n, int[] arr){
        if(n == 1){
            return arr[0];
        }

        if(n == 2){
            return Math.max(arr[0], arr[1]);
        }
        int prev2 = arr[0]; // it corresponds to dp[0]
        int prev = Math.max(arr[0], arr[1]); // it corresponds to dp[1]
        for (int i = 2; i < n; i++) {
            int takeCurrentIndex = arr[i] + prev2;
            int skipCurrentIndex = 0 + prev;
            int val = Math.max(takeCurrentIndex, skipCurrentIndex);
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
        int[] arr = new int[n];
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
            dp[i] = -1;
        }
        System.out.println(findMaximumSumRecursive(n-1, arr, dp));
        System.out.println(findMaximumSumTabulation(n, arr));
        System.out.println(findMaximumSumSpaceOptimized(n, arr));
        sc.close();
    }
}
