import java.util.Scanner;

public class SubsetSumEqualsToK {

    private static boolean isThereASubsetRecursiveHelper(int[] arr, int[][] dp, int ind, int target){
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
 
        boolean skipCurrentIndex = isThereASubsetRecursiveHelper(arr, dp, ind-1, target);
        boolean takeCurrentIndex = false;
        if(arr[ind] <= target){
            // take current index only if target is greater than or equal to current index value
            takeCurrentIndex = isThereASubsetRecursiveHelper(arr, dp, ind-1, target-arr[ind]);
        }
        dp[ind][target] = (skipCurrentIndex || takeCurrentIndex) ? 1 : 0;
        return dp[ind][target] == 1;

        // TC -> O(n x target)
        // SC -> O(n x target) + O(n)
    }

    private static boolean isThereASubsetRecursive(int[] arr, int target){
        int n = arr.length;

        int[][] dp = new int[n][target+1];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= target; j++) {
                dp[i][j] = -1;
            }
        }

        return isThereASubsetRecursiveHelper(arr, dp, n-1, target);
        // TC -> O(n x target)
        // SC -> O(n x target) + O(n)
    }

    private static boolean isThereASubsetTabulation(int[] arr, int target){
        int n = arr.length;
        boolean[][] dp = new boolean[n][target+1];

        // bases cases
        for (int i = 0; i < n; i++) {
            // when target is zero
            // it is always true
            dp[i][0] = true;
        }

        if(arr[0] <= target){
            // safe check
            dp[0][arr[0]] = true;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= target ; j++) {
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

    private static boolean isThereASubsetSpaceOptimzied(int[] arr, int target){
        int n = arr.length;
        boolean[] dp = new boolean[target+1];

        // base cases
        dp[0] = true;
        if(arr[0] <= target){
            // safe check
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
        int k = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        System.out.println(isThereASubsetRecursive(arr, k));
        System.out.println(isThereASubsetTabulation(arr, k));
        System.out.println(isThereASubsetSpaceOptimzied(arr, k));
        sc.close();
    }
}
