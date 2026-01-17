import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MinimumCostToCutStick {

    private static int findMinCostRecursiveHelper(int i, int j, List<Integer> cuts, int[][] dp){
        // base case
        if(i > j){
            // it means there is no partition left now
            return 0;
        }

        if(dp[i][j] != -1){
            return dp[i][j];
        }

        int minm = Integer.MAX_VALUE;

        for (int k = i; k <= j; k++) {
            int cost = (cuts.get(j+1) - cuts.get(i-1)) + findMinCostRecursiveHelper(i, k-1, cuts, dp)
            + findMinCostRecursiveHelper(k+1, j, cuts, dp);

            if(minm > cost){
                minm = cost;
            }
        }

        dp[i][j] = minm;
        return dp[i][j];

        // TC -> O(c x c x c)
        // SC -> O(c x c ) + O(c)
    }
    private static int findMinCostRecursive(int n , List<Integer> cuts){
        int c = cuts.size();

        int[][] dp = new int[c+1][c+1];
        for (int i = 0; i <= c; i++) {
            for (int j = 0; j <= c; j++) {
                dp[i][j] = -1;
            }
        }

        cuts.add(0, 0);
        cuts.add(n);

        return findMinCostRecursiveHelper(1, c, cuts, dp);
        // TC -> O(c x c x c)
        // SC -> O(c x c ) + O(c)
    }

    private static int findMinCostTabulation(int n, List<Integer> cuts){
        int c = cuts.size();
        cuts.add(0, 0);
        cuts.add(n);

        int[][] dp = new int[c+2][c+2];

        // base case
        for (int i = 0; i <= c+1; i++) {
            for (int j = 0; j <= c+1; j++) {
                if(i > j){
                    dp[i][j] = 0;
                }
            }
        }

        for (int i = c; i >= 1; i--) {
            for (int j = i; j <= c; j++) {
                int minm = Integer.MAX_VALUE;
                for (int k = i; k <= j; k++) {
                    int cost = (cuts.get(j+1) - cuts.get(i-1)) + dp[i][k-1] + dp[k+1][j];
                    if(minm > cost){
                        minm = cost;
                    }
                }

                dp[i][j] = minm;
            }
        }

        return dp[1][c];

        // TC -> O(c x c x c)
        // SC -> O(c x c)
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int c = sc.nextInt();
        List<Integer> cuts = new ArrayList<>();
        for (int i = 0; i < c; i++) {
            cuts.add(sc.nextInt());
        }
        System.out.println(findMinCostRecursive(n, cuts));
        // System.out.println(findMinCostTabulation(n, cuts));
        sc.close();
    }
}
