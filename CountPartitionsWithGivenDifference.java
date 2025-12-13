import java.util.Scanner;

public class CountPartitionsWithGivenDifference {

    /*
    We have to divide arr into two partitions with sum s1 and s2 such that
    s1 >= s2

    k = s1 - s2
    and we have sum = s1+s2, so s1 = sum-s2
    k = sum -s2 -s2
    k = sum - 2s2
    s2 = (sum-k)/2
    so, effectively we have to find the number of subsets that have the sum = (sum-k)/2
    
    */

    private static int findNumberOfSubsetsRecursiveHelper(int ind, int target, int[] arr, int[][] dp){
        // base cases
        if(ind == 0){
            if(arr[0] == 0 && target == 0){
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

        int skipCurrent = findNumberOfSubsetsRecursiveHelper(ind-1, target, arr, dp);
        int pickCurrent = 0;
        if(arr[ind] <= target){
            pickCurrent = findNumberOfSubsetsRecursiveHelper(ind-1, target-arr[ind], arr, dp);
        }

        dp[ind][target] = skipCurrent + pickCurrent;
        return dp[ind][target];
        // TC -> O(n x target)
        // SC -> O(n x target) + O(n)
    }
    private static int findNumberOfSubsetsRecursive(int[] arr, int k){
        int n = arr.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
        }

        if(((sum-k) < 0) || (((sum-k)%2) != 0)){
            // if (sum-k) is negative or (sum-k)/2 is a fraction
            return 0;
        }

        int target = (sum-k)/2;
        int[][] dp = new int[n][target+1];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= target; j++) {
                dp[i][j] = -1;
            }
        }
        return findNumberOfSubsetsRecursiveHelper(n-1, target, arr, dp);

        // TC -> O(n x target)
        // SC -> O(n x target) + O(n)
        // where target = (sum-k)/2

    }

    private static int findNumberOfSubsetsTabulation(int[] arr, int k){
        int n = arr.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
        }

        if(((sum-k) < 0) || (((sum-k)%2) != 0)){
            // if (sum-k) is negative or (sum-k)/2 is a fraction
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
            for (int j = 0; j <= target; j++) {
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

    private static int findNumberOfSubsetsSpaceOptimized(int[] arr, int k){
        int n = arr.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
        }

        if(((sum-k) < 0) || (((sum-k)%2) != 0)){
            // if (sum-k) is negative or (sum-k)/2 is a fraction
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
            for (int j = 0; j <= target; j++) {
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
        int k = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        System.out.println(findNumberOfSubsetsRecursive(arr, k));
        System.out.println(findNumberOfSubsetsTabulation(arr, k));
        System.out.println(findNumberOfSubsetsSpaceOptimized(arr, k));
        sc.close();
    }
}
