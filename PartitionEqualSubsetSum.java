import java.util.Scanner;

public class PartitionEqualSubsetSum {

    private static boolean isPossibleRecursiveHelper(int ind, int target, int[] arr, int[][] dp){

        // base cases
        if(target == 0){
            return true;
        }

        if(ind == 0){
            return arr[0] == target;
        }

        if(dp[ind][target] != -1){
            return dp[ind][target] == 1;
        }

        boolean skipCurrentIndex = isPossibleRecursiveHelper(ind-1, target, arr, dp);
        boolean takeCurrentIndex = false;
        if(arr[ind] <= target){
            takeCurrentIndex = isPossibleRecursiveHelper(ind-1, target-arr[ind], arr, dp);
        }

        dp[ind][target] = (skipCurrentIndex || takeCurrentIndex) ? 1: 0;
        return dp[ind][target] == 1;
        // TC -> O(n x target)
        // SC -> O(n x target) + O(n)

    }

    private static boolean isPossibleRecursive(int[] arr){
        int n = arr.length;
        int sum = 0;
        if(n == 1 && arr[0] == 0){
            return false;
        }
        for (int i = 0; i < n; i++) {
            sum += arr[i];
        }


        if((sum % 2) != 0){
            // sum is odd
            return false;
        }

        int target = sum / 2;
        int[][] dp = new int[n][target+1];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= target; j++) {
                dp[i][j] = -1;
            }
        }
        return isPossibleRecursiveHelper(n-1, target, arr, dp);
        // TC -> O(n x target)
        // SC -> O(n x target) + O(n)
    }

    private static boolean isPossibleTabulation(int[] arr){
        int n = arr.length;
        int sum = 0;
        if(n == 1 && arr[0] == 0){
            return false;
        }
        for (int i = 0; i < n; i++) {
            sum += arr[i];
        }


        if((sum % 2) != 0){
            // sum is odd
            return false;
        }

        int target = sum / 2;
        boolean[][] dp = new boolean[n][target+1];

        // base cases
        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }
        if(arr[0] <= target){
            // safe check
            dp[0][arr[0]] = true;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= target; j++) {
                boolean skipCurrentIndex = dp[i-1][j];
                boolean takeCurrentIndex = false;
                if(arr[i] <= j){
                    takeCurrentIndex = dp[i-1][j-arr[i]];
                }
                dp[i][j] = skipCurrentIndex || takeCurrentIndex;
            }
        }
        return dp[n-1][target];
        // TC -> O(n x target)
        // SC -> O(n x target)
    }

    private static boolean isPossibleSpaceOptimized(int[] arr){
        int n = arr.length;
        int sum = 0;
        if(n == 1 && arr[0] == 0){
            return false;
        }
        for (int i = 0; i < n; i++) {
            sum += arr[i];
        }

        if((sum % 2) != 0){
            // sum is odd
            return false;
        }

        int target = sum / 2;

        boolean[] dp = new boolean[target+1];
        dp[0] = true;

        if(arr[0] <= target){
            dp[arr[0]] = true;
        }

        for (int i = 1; i < n; i++) {
            boolean[] current = new boolean[target+1];
            current[0] = true;
            for (int j = 1; j <= target; j++) {
                boolean skipCurrentIndex = dp[j];
                boolean takeCurrentIndex = false;
                if(arr[i] <= j){
                    takeCurrentIndex = dp[j-arr[i]];
                }
                current[j] = skipCurrentIndex || takeCurrentIndex;
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
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        System.out.println(isPossibleRecursive(arr));
        System.out.println(isPossibleTabulation(arr));
        System.out.println(isPossibleSpaceOptimized(arr));
        sc.close();
    }
}
