import java.util.Scanner;

public class PartitionArrayForMaximumSum {

    private static int findMaxSumRecursiveHelper(int ind, int k, int[] arr, int[] dp){
        int n = arr.length;
        // base case
        if(ind == n){
            return 0;
        }

        if(dp[ind] != -1){
            return dp[ind];
        }
        int len = 0;
        int maxNumber = Integer.MIN_VALUE;
        int ans = Integer.MIN_VALUE;
        for (int j = ind; j < Math.min(n, ind+k); j++) {
            len += 1;
            maxNumber = Math.max(arr[j], maxNumber);
            int sum = (len * maxNumber) + findMaxSumRecursiveHelper(j+1, k, arr, dp);
            ans = Math.max(ans, sum);
        }
        dp[ind] = ans;
        return dp[ind];
        // TC -> O(n x k)
        // SC -> O(n) + O(n)
    }

    private static int findMaxSumRecursive(int[] arr, int k){
        int n = arr.length;
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            dp[i] = -1;
        }

        return findMaxSumRecursiveHelper(0, k, arr, dp);
        // TC -> O(n x k)
        // SC -> O(n) + O(n)
    }

    private static int findMaxSumTabulation(int[] arr, int k){
        int n = arr.length;
        int[] dp = new int[n+1];

        // base case
        dp[n] = 0;

        for (int ind = n-1; ind >= 0; ind--) {
            int len = 0;
            int maxNumber = Integer.MIN_VALUE;
            int ans = Integer.MIN_VALUE;
            for (int j = ind; j < Math.min(n, ind+k); j++) {
                len += 1;
                maxNumber = Math.max(maxNumber, arr[j]);
                int sum = (len * maxNumber) + dp[j+1];
                ans = Math.max(ans, sum);
            }
            dp[ind] = ans;
        }

        return dp[0];
        // TC -> O(n x k)
        // SC -> O(n) 
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        System.out.println(findMaxSumRecursive(arr, k));
        System.out.println(findMaxSumTabulation(arr, k));
        sc.close();
    }
}
