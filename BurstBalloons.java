import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BurstBalloons {

    private static int findMaxPointsRecursiveHelper(int i, int j, List<Integer> arr, int[][] dp){
        // base case
        if(i > j){
            return 0;
        }

        if(dp[i][j] != -1){
            return dp[i][j];
        }

        int ans = Integer.MIN_VALUE;
        for (int ind = i; ind <= j; ind++) {
            int point = arr.get(i-1)*arr.get(ind)*arr.get(j+1) + findMaxPointsRecursiveHelper(i, ind-1, arr, dp)
            + findMaxPointsRecursiveHelper(ind+1, j, arr, dp);
            ans = Math.max(point, ans);
        }
        dp[i][j] = ans;
        return ans;
        // TC -> O(n x n x n)
        // SC -> O(n x n) + O(n)
    }

    private static int findMaxPointsRecursive(List<Integer> arr){
        int n = arr.size();
        arr.add(0, 1);
        arr.add(1);

        int[][] dp = new int[n+1][n+1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j] = -1;
            }
        }

        return findMaxPointsRecursiveHelper(1, n, arr, dp);
        // TC -> O(n x n x n)
        // SC -> O(n x n) + O(n)

    }

    private static int findMaxPointsTabulation(List<Integer> arr){
        int n = arr.size();
        arr.add(0, 1);
        arr.add(1);

        int[][] dp = new int[n+2][n+2];

        // base case
        for (int i = 0; i <= n+1; i++) {
            for (int j = 0; j <= n+1; j++) {
                if(i > j){
                    dp[i][j] = 0;
                }
            }
        }

        for (int i = n; i >= 1; i--) {
            for (int j = i; j <= n; j++) {
                int ans = Integer.MIN_VALUE;
                for (int ind = i; ind <= j; ind++) {
                    int points = arr.get(i-1)*arr.get(ind)*arr.get(j+1)
                    + dp[i][ind-1] + dp[ind+1][j];
                    ans = Math.max(ans, points);
                }
                dp[i][j] = ans;
            }
        }

        return dp[1][n];
        // TC -? O(n x n x n)
        // SC -> O(n x n)
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Integer> arr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            arr.add(sc.nextInt());
        }
        // System.out.println(findMaxPointsRecursive(arr));
        System.out.println(findMaxPointsTabulation(arr));
        sc.close();
    }
}
