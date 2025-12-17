import java.util.Scanner;

public class CoinChange2 {

    private static int findNumberOfWaysRecursiveHelper(int ind, int target, int[] arr, int[][]dp){
        // base cases
        if(ind == 0){
            return ((target % arr[ind]) == 0) ? 1 : 0;
        }

        if(dp[ind][target] != -1){
            return dp[ind][target];
        }
        int skipCurrent = findNumberOfWaysRecursiveHelper(ind-1, target, arr, dp);
        int pickCurrent = 0;
        if(arr[ind] <= target){
            pickCurrent = findNumberOfWaysRecursiveHelper(ind, target-arr[ind], arr, dp);
        }

        dp[ind][target] = skipCurrent + pickCurrent;
        return dp[ind][target];
        // TC -> O(n x target)
        // SC -> O(n x target) + O(target)
    }

    private static int findNumberOfWaysRecursive(int[] arr, int target){
        int n = arr.length;
        int[][] dp = new int[n][target+1];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= target; j++) {
                dp[i][j] = -1;
            }
        }

        return findNumberOfWaysRecursiveHelper(n-1, target, arr, dp);
        // TC -> O(n x target)
        // SC -> O(n x target) + O(target)
    }

    private static int findNumberOfWaysTabulation(int[] arr, int target){
        int n = arr.length;
        int[][] dp = new int[n][target+1];

        // base cases
        for (int t = 0; t <= target; t++) {
            dp[0][t] = ((t % arr[0]) == 0) ? 1 : 0;
        }

        for (int i = 1; i < n; i++) {
            for (int t = 0; t <= target; t++) {
                int skipCurrent = dp[i-1][t];
                int pickCurrent = 0;
                if(arr[i] <= t){
                    pickCurrent = dp[i][t-arr[i]];
                }
                dp[i][t] = skipCurrent + pickCurrent;
            }
        }

        return dp[n-1][target];
        // TC -> O(n x target)
        // SC -> O(n x target)
    }

    private static int findNumberOfWaysSpaceOptimized(int[] arr, int target){
        int n = arr.length;
        int[] dp = new int[target+1];

        // base cases
        for (int t = 0; t <= target; t++) {
            dp[t] = ((t % arr[0]) == 0) ? 1 : 0;
        }

        for (int i = 1; i < n; i++) {
            int[] current = new int[target+1];
            for (int t = 0; t <= target; t++) {
                int skipCurrent = dp[t];
                int pickCurrent = 0;
                if(arr[i] <= t){
                    pickCurrent = current[t-arr[i]];
                }
                current[t] = skipCurrent + pickCurrent;
            }
            dp = current;
        }

        return dp[target];
        // TC -> O(n x target)
        // SC -> O(n x target)
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int target = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        System.out.println(findNumberOfWaysRecursive(arr, target));
        System.out.println(findNumberOfWaysTabulation(arr, target));
        System.out.println(findNumberOfWaysSpaceOptimized(arr, target));
        sc.close();
    }
}
