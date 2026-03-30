import java.util.Scanner;

public class JumpGameII {

    private static int findMinJumpsBruteRecursiveHelper(int ind, int jumps, int[] arr){
        int n = arr.length;
        if(ind >= (n-1)){
            return jumps;
        }
        int mini = Integer.MAX_VALUE;
        for (int i = 1; i <= arr[ind]; i++) {
            mini = Math.min(mini, findMinJumpsBruteRecursiveHelper(ind+i, jumps+1, arr));
        }
        return mini;
        // TC -> O(n^n)
        // SC -> O(n)
    }

    private static int findMinJumpsBetterRecursiveHelper(int ind, int jumps, int[] arr, int[][] dp){
        int n = arr.length;

        if(ind >= (n-1)){
            return jumps;
        }

        if(dp[ind][jumps] != -1){
            return dp[ind][jumps];
        }
        int mini = Integer.MAX_VALUE;

        for (int i = 1; i <= arr[ind]; i++) {
            mini = Math.min(mini, findMinJumpsBetterRecursiveHelper(ind+i, jumps+1, arr, dp));
        }
        dp[ind][jumps] = mini;
        return dp[ind][jumps];
    }

    private static int findMinJumpsBetter(int[] arr){
        int n = arr.length;
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = -1;
            }
        }

        return findMinJumpsBetterRecursiveHelper(0, 0, arr, dp);
        // TC -> O(n x n)
        // SC -> O(n x n)
    }

    private static int findMinJumpsOptimal(int[] arr){
        int n = arr.length;
        int left = 0;
        int right = 0;
        int jumps = 0;
        int maxRange = 0;
        
        while (right < (n-1)) {
            for (int i = left; i <= right; i++) {
                maxRange = Math.max(maxRange, i+arr[i]);
            }
            jumps += 1;
            left = right+1;
            right = maxRange;
        }
        return jumps;
        // TC -> O(n)
        // SC -> O(1)

    }

    private static int findMinJumpsBrute(int[] arr){
        return findMinJumpsBruteRecursiveHelper(0, 0, arr);
        // TC -> O(n ^ n)
        // SC -> O(n)
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        System.out.println(findMinJumpsBrute(arr));
        System.out.println(findMinJumpsBetter(arr));
        System.out.println(findMinJumpsOptimal(arr));
        sc.close();
    }
}
