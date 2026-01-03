import java.util.Scanner;

public class BuyAndSellStockIII {

    private static int findMaxProfitRecursiveHelper(int ind, int buy, int cap, int[] prices, int[][][] dp){
        int n = prices.length;
        // base case
        if((ind == n) || (cap == 0)){
            return 0;
        }

        if(dp[ind][buy][cap] != -1){
            return dp[ind][buy][cap];
        }

        if(buy == 1){
            int pick = -prices[ind] + findMaxProfitRecursiveHelper(ind+1, 0, cap, prices, dp);
            int notPick = 0 + findMaxProfitRecursiveHelper(ind+1, 1, cap, prices, dp);
            dp[ind][buy][cap] = Math.max(pick, notPick);
        }else{
            int sell = prices[ind] + findMaxProfitRecursiveHelper(ind+1, 1, cap-1, prices, dp);
            int notSell = 0 + findMaxProfitRecursiveHelper(ind+1, 0, cap, prices, dp);
            dp[ind][buy][cap] = Math.max(sell, notSell);
        }
        return dp[ind][buy][cap];
        // TC -> O(n x 2 x k)
        // SC -> O(n x 2 x k) + O(n)
    }

    private static int findMaxProfitRecursive(int[] prices, int k){
        int n = prices.length;

        int[][][] dp = new int[n][2][k+1]; // since we will be storing states from 0 to k

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= 1; j++) {
                for (int j2 = 0; j2 <= k; j2++) {
                    dp[i][j][j2] = -1;
                }
            }
        }

        return findMaxProfitRecursiveHelper(0, 1, k, prices, dp);
        // TC -> O(n x 2 x k)
        // SC -> O(n x 2 x k) + O(n)
    }

    private static int findMaxProfitTabulation(int[] prices, int k){
        int n = prices.length;

        int[][][] dp = new int[n+1][2][k+1];

        // base cases
        // for ind == n
        for (int buy = 0; buy <= 1; buy++) {
            for (int cap = 0; cap <= k; cap++) {
                dp[n][buy][cap] = 0;
            }
        }

        // for cap == 0
        for (int ind = 0; ind <= n; ind++) {
            for (int buy = 0; buy <= 1; buy++) {
                dp[ind][buy][0] = 0;
            }
        }

        for (int ind = n-1; ind >= 0; ind--) {
            for (int buy = 0; buy <= 1; buy++) {
                for (int cap = 1; cap <= k; cap++) {
                    if(buy == 1){
                        int pick = -prices[ind] + dp[ind+1][0][cap];
                        int notPick = 0 + dp[ind+1][1][cap];
                        dp[ind][buy][cap] = Math.max(pick, notPick);
                    }else{
                        int sell = prices[ind] + dp[ind+1][1][cap-1];
                        int notSell = 0 + dp[ind+1][0][cap];
                        dp[ind][buy][cap] = Math.max(sell, notSell);
                    }
                }
            }
        }

        return dp[0][1][k];
        // TC -> O(n x 2 x k)
        // SC -> O(n x 2 x k)
    }

    private static int findMaxProfitSpaceOptimized(int[] prices, int k){
        int n = prices.length;

        int[][] dp = new int[2][k+1];
        
        // base case
        // for ind == n
        for (int buy = 0; buy <= 1; buy++) {
            for (int cap = 0; cap <= k; cap++) {
                dp[buy][cap] = 0;
            }
        }

        for (int ind = n-1; ind >= 0; ind--) {
            int[][] current = new int[2][k+1];
            // for cap == 0
            current[0][0] = 0;
            current[1][0] = 0;
            for (int buy = 0; buy <= 1; buy++) {
                for (int cap = 1; cap <= k; cap++) {
                    if(buy == 1){
                        int pick = -prices[ind] + dp[0][cap];
                        int notPick = 0 + dp[1][cap];
                        current[buy][cap] = Math.max(pick, notPick);
                    }else{
                        int sell = prices[ind] + dp[1][cap-1];
                        int notSell = 0 + dp[0][cap];
                        current[buy][cap] = Math.max(sell, notSell);
                    }
                }
            }
            dp = current;
        }

        return dp[1][k];
        // TC -> O(n x 2 x k)
        // SC -> O(2 x k)
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] prices = new int[n];
        for (int i = 0; i < n; i++) {
            prices[i] = sc.nextInt();
        }
        System.out.println(findMaxProfitRecursive(prices, k));
        System.out.println(findMaxProfitTabulation(prices, k));
        System.out.println(findMaxProfitSpaceOptimized(prices, k));
        sc.close();
    }
}
