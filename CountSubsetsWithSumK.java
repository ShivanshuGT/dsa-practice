import java.util.Scanner;

public class CountSubsetsWithSumK {

    private static int countSubsetsRecursiveHelper(int ind, int target, int[] arr, int[][] dp){
        // base cases
        if(target == 0){
            return 1;
        }

        if(ind == 0){
            return (arr[ind] == target) ? 1: 0;
        }

        if(dp[ind][target] != -1){
            return dp[ind][target];
        }

        int skipCurrent = countSubsetsRecursiveHelper(ind-1, target, arr, dp);
        int pickCurrent = 0;
        if(arr[ind] <= target){
            pickCurrent = countSubsetsRecursiveHelper(ind-1, target-arr[ind], arr, dp);
        }
        dp[ind][target] = skipCurrent + pickCurrent;
        return dp[ind][target];
        // TC -> O(n x target)
        // SC -> O(n x target) + O(n)
        
    }


    private static int countSubsetsRecursive(int[] arr, int target){
        int n = arr.length;
        int[][] dp = new int[n][target+1];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= target; j++) {
                dp[i][j] = -1;
            }
        }

        return countSubsetsRecursiveHelper(n-1, target, arr, dp);
        // TC -> O(n x target)
        // SC -> O(n x target) + O(n)
    }

    private static int countSubsetsTabulation(int[] arr, int target){
        int n = arr.length;
        int[][] dp = new int[n][target+1];
        
        // base cases
        for (int i = 0; i < n; i++) {
            dp[i][0] = 1;
        }

        if(arr[0] <= target){
            dp[0][arr[0]] = 1;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= target; j++) {
                int skipCurrent = dp[i-1][j];
                int pickCurrent = 0;
                if(arr[i] <= j){
                    pickCurrent = dp[i-1][j-arr[i]];
                }
                dp[i][j] = skipCurrent + pickCurrent;
            }
        }

        return dp[n-1][target];
        // TC -> O(n x target)
        // SC -> O(n x target)
    }

    private static int countSubsetsSpaceOptimized(int[] arr, int target){
        int n = arr.length;

        int[] dp = new int[target+1];

        // base cases
        dp[0] = 1;
        if(arr[0] <= target){
            dp[arr[0]] = 1;
        }

        for (int i = 1; i < n; i++) {
            int[] current = new int[target+1];
            current[0] = 1;
            for (int j = 1; j <= target; j++) {
                int skipCurrent = dp[j];
                int pickCurrent = 0;
                if(arr[i] <= j){
                    pickCurrent = dp[j-arr[i]];
                }
                current[j] = skipCurrent + pickCurrent;
            }
            dp = current;
        }
        return dp[target];
        // TC -> O(n x target)
        // SC -> O(target)
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int target = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        System.out.println(countSubsetsRecursive(arr, target));
        System.out.println(countSubsetsTabulation(arr, target));
        System.out.println(countSubsetsSpaceOptimized(arr, target));
        sc.close();
        sc.close();
    }
}
