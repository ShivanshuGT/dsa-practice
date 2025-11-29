import java.util.Arrays;
import java.util.Scanner;

public class HouseRobber2 {

    private static int findMaxSumRecursiveHelper(int ind, int[] arr, int[] dp){
        if(ind == 0){
            return arr[0];
        }

        if(ind == 1){
            return Math.max(arr[0], arr[1]);
        }

        if(dp[ind] != -1){
            return dp[ind];
        }

        int pickCurrentIndex = arr[ind] + findMaxSumRecursiveHelper(ind-2, arr, dp);
        int skipCurrentIndex = 0 + findMaxSumRecursiveHelper(ind-1, arr, dp);
        dp[ind] = Math.max(pickCurrentIndex, skipCurrentIndex);
        return dp[ind];
        // TC -> O(n)
        // SC -> O(n)
    }

    private static int findMaxSumRecursive(int n, int[] arr){
        if( n == 1){
            return arr[0];
        }

        if( n == 2){
            return Math.max(arr[0], arr[1]);
        }
        int[] temp1 = Arrays.copyOfRange(arr, 0, n);
        int[] dp1 = new int[n-1];
        int[] temp2 = Arrays.copyOfRange(arr, 1, n+1);
        int[] dp2 = new int[n-1];

        for (int i = 0; i < n-1; i++) {
            dp1[i] = -1;
            dp2[i] = -1;
        }

        return Math.max(findMaxSumRecursiveHelper(n-2, temp1, dp1), findMaxSumRecursiveHelper(n-2, temp2, dp2));
        // TC -> O(n)
        // SC -> O(n)
    }

    private static int findMaxSumTabulationHelper(int n, int[] arr){
        if( n == 1){
            return arr[0];
        }

        if( n == 2){
            return Math.max(arr[0], arr[1]);
        }

        int[] dp = new int[n];
        dp[0] = arr[0];
        dp[1] = Math.max(arr[0], arr[1]);
        for (int i = 2; i < n; i++) {
            int pickCurrentIndex = arr[i] + dp[i-2];
            int skipCurrentIndex = 0 + dp[i-1];
            dp[i] = Math.max(pickCurrentIndex, skipCurrentIndex);
        }
        return dp[n-1];
        // TC -> O(n)
        // SC -> O(n)
    }
    private static int findMaxSumTabulation(int n, int[] arr){
        if( n == 1){
            return arr[0];
        }

        if( n == 2){
            return Math.max(arr[0], arr[1]);
        }
        int[] temp1 = Arrays.copyOfRange(arr, 0, n);
        int[] temp2 = Arrays.copyOfRange(arr, 1, n+1);
        return Math.max(findMaxSumTabulationHelper(n-1, temp1), findMaxSumTabulationHelper(n-1, temp2));
        // TC -> O(n)
        // SC -> O(n)
    }

    private static int findMaxSumSpaceOptimizedHelper(int n, int[] arr){
        if( n == 1){
            return arr[0];
        }

        if( n == 2){
            return Math.max(arr[0], arr[1]);
        }

        int prev2 = arr[0];
        int prev = Math.max(arr[0], arr[1]);

        for (int i = 2; i < n; i++) {
            int pickCurrentIndex = arr[i] + prev2;
            int skipCurrentIndex = 0 + prev;
            int val = Math.max(pickCurrentIndex, skipCurrentIndex);
            prev2 = prev;
            prev = val;
        }
        return prev;
        // TC -> O(n)
        // SC -> O(1)
    }

    private static int findMaxSumSpaceOptimized(int n, int[] arr){
        if( n == 1){
            return arr[0];
        }

        if( n == 2){
            return Math.max(arr[0], arr[1]);
        }
        int[] temp1 = Arrays.copyOfRange(arr, 0, n);
        int[] temp2 = Arrays.copyOfRange(arr, 1, n+1);
        return Math.max(findMaxSumSpaceOptimizedHelper(n-1, temp1), findMaxSumSpaceOptimizedHelper(n-1, temp2));
        // TC -> O(n)
        // SC -> O(n)

    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        System.out.println(findMaxSumRecursive(n, arr));
        System.out.println(findMaxSumTabulation(n, arr));
        System.out.println(findMaxSumSpaceOptimized(n, arr));
        sc.close();
    }
}
