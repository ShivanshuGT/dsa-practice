import java.util.Scanner;

public class PartitionSetWithMinimumDifference {

    private static int partitionTabulation(int[] arr){
        int n = arr.length;

        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
        }

        boolean[][] dp = new boolean[n][sum+1];

        // base cases
        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }

        if(arr[0] <= sum){
            dp[0][arr[0]] = true;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= sum; j++) {
                boolean skipCurrentIndex = dp[i-1][j];
                boolean takeCurrentIndex = false;
                if(arr[i] <= j){
                    takeCurrentIndex = dp[i-1][j-arr[i]];
                }
                dp[i][j] = skipCurrentIndex || takeCurrentIndex;
            }
        }

        int result = Integer.MAX_VALUE;
        for (int i = 0; i <= (sum/2); i++) {
            int s1 = i;
            if(dp[n-1][i]){
                // is a subset with sum 'i' is possible
                int s2 = sum-s1;
                result = Math.min(result, Math.abs(s2-s1));
            }
        }

        return result;
        // TC -> O(n x sum)
        // SC -> O(n x sum)
    }

    private static int partitionSpaceOptimized(int[] arr){
        int n = arr.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
        }

        boolean[] dp = new boolean[sum+1];

        dp[0] = true;
        if(arr[0] <= sum){
            dp[arr[0]] = true;
        }

        for (int i = 1; i < n; i++) {
            boolean[] current = new boolean[sum+1];
            current[0] = true;
            for (int j = 1; j <= sum; j++) {
                boolean skipCurrentIndex = dp[j];
                boolean takeCurrentIndex = false;
                if(arr[i] <= j){
                    takeCurrentIndex = dp[j-arr[i]];
                }
                current[j] = skipCurrentIndex || takeCurrentIndex;

            }
            dp = current;
        }

        int result = Integer.MAX_VALUE;
        for (int i = 0; i <= (sum/2); i++) {
            int s1 = i;
            if(dp[i]){
                int s2 = sum - s1;
                result = Math.min(result, Math.abs(s2-s1));
            }
        }
        return result;
        // TC -> O(n x sum)
        // SC -> O(sum)
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        System.out.println(partitionTabulation(arr));
        System.out.println(partitionSpaceOptimized(arr));
        sc.close();
    }
}
