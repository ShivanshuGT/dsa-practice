import java.util.Scanner;

public class TargetSum {

    /*
        This question boils down to the question where we have to
        count the number of subsets S1 and S2
        such that sum(S1) - sum(S2) = k
    */

    private static int findNumberOfWaysRecursiveHelper(int ind, int target, int[] arr, int[][] dp){
        // base cases
        if(ind == 0){
            if(arr[ind] == 0 && target == 0){
                return 2;
            }
            if(target == 0){
                return 1;
            }
            if(arr[0] == target){
                return 1;
            }
            return 0;
            
        }
        if(dp[ind][target] != -1){
            return dp[ind][target];
        }

        int skipCurrent = findNumberOfWaysRecursiveHelper(ind-1, target, arr, dp);
        int pickCurrent = 0;
        if(arr[ind] <= target){
            pickCurrent = findNumberOfWaysRecursiveHelper(ind-1, target-arr[ind], arr, dp);
        }

        dp[ind][target] = skipCurrent + pickCurrent;
        return dp[ind][target];
        // TC -> O(n x target)
        // SC -> O(n x target) + O(n)
    }

    private static int findNumberOfWaysRecursive(int[] arr, int k){
        int n = arr.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
        }

        if(((sum-k) < 0) || ((sum-k)%2 != 0)){
            return 0;
        }

        int target = (sum-k)/2;
        int[][] dp = new int[n][target+1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= target; j++) {
                dp[i][j] = -1;
            }
        }

        return findNumberOfWaysRecursiveHelper(n-1, target, arr, dp);
        // TC -> O(n x target)
        // SC -> O(n x target) + O(n)
        // where target = (sum-k)/2
    }

    private static int findNumberOfWaysTabulation(int[] arr, int k){
        int n = arr.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
        }

        if(((sum-k) < 0) || ((sum-k)%2 != 0)){
            return 0;
        }

        int target = (sum-k)/2;
        
        int[][] dp = new int[n][target+1];
        
        // base cases
        if(arr[0] == 0){
            dp[0][0] = 2;
        }else{
            dp[0][0] = 1;
        }
        if((arr[0] != 0) && (arr[0] <= target)){
            dp[0][arr[0]] = 1;
        }

        for (int i = 1; i < n; i++) {
            for (int t = 0; t <= target; t++) {
                int skipCurrent = dp[i-1][t];
                int pickCurrent = 0;
                if(arr[i] <= t){
                    pickCurrent = dp[i-1][t-arr[i]];
                }
                dp[i][t] = skipCurrent + pickCurrent;
            }
        }

        return dp[n-1][target];
        // TC -> O(n x target)
        // SC -> O(n x target)
    }

    private static int findNumberOfWaysSpaceOptimized(int[] arr, int k){
        int n = arr.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
        }

        if(((sum-k) < 0) || ((sum-k)%2 != 0)){
            return 0;
        }

        int target = (sum-k)/2;

        int[] dp = new int[target+1];

        // base cases
        if(arr[0] == 0){
            dp[0] = 2;
        }else{
            dp[0] = 1;
        }
        if((arr[0] != 0) && (arr[0] <= target)){
            dp[arr[0]] = 1;
        }

        for (int i = 1; i < n; i++) {
            int[] current = new int[target+1];
            for (int t = 0; t <= target; t++) {
                int skipCurrent = dp[t];
                int pickCurrent = 0;
                if(arr[i] <= t){
                    pickCurrent = dp[t-arr[i]];
                }
                current[t] = skipCurrent + pickCurrent;
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
        int k = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        System.out.println(findNumberOfWaysRecursive(arr, k));
        System.out.println(findNumberOfWaysTabulation(arr, k));
        System.out.println(findNumberOfWaysSpaceOptimized(arr, k));
        sc.close();
    }
}
