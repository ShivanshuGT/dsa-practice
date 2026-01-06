import java.util.Scanner;

public class BuyAndSellStockWithFee {

    private static int findMaxProfitRecursiveHelper(int ind, int buy, int fee, int[] prices, int[][] dp){
        int n = prices.length;

        // base case
        if(ind == n){
            return 0;
        }

        if(dp[ind][buy] != -1){
            return dp[ind][buy];
        }

        if(buy == 1){
            int pick = -prices[ind] + findMaxProfitRecursiveHelper(ind+1, 0, fee, prices, dp);
            int notPick = 0 + findMaxProfitRecursiveHelper(ind+1, 1, fee, prices, dp);
            dp[ind][buy] = Math.max(notPick, pick);
        }else{
            int sell = prices[ind] - fee + findMaxProfitRecursiveHelper(ind+1, 1, fee, prices, dp);
            int notSell = 0 + findMaxProfitRecursiveHelper(ind+1, 0, fee, prices, dp);
            dp[ind][buy] = Math.max(notSell, sell);
        }

        return dp[ind][buy];
        // TC -> O(n x 2)
        // SC -> O(n x 2) + O(n)
    }

    private static int findMaxProfitRecursive(int[] prices, int fee){
        int n = prices.length;

        int[][] dp = new int[n][2];

        for (int i = 0; i < n; i++) {
            dp[i][0] = -1;
            dp[i][1] = -1;
        }

        return findMaxProfitRecursiveHelper(0, 1, fee, prices, dp);
        // TC -> O(n x 2)
        // SC -> O(n x 2) + O(n)
    }

    private static int findMaxProfitTabulation(int[] prices, int fee){
        int n = prices.length;

        int[][] dp = new int[n+1][2];

        // base case
        // for ind == n
        dp[n][0] = 0;
        dp[n][1] = 0;

        for (int ind = n-1; ind >= 0; ind--) {
            int pick = -prices[ind] + dp[ind+1][0];
            int notPick = 0 + dp[ind+1][1];
            dp[ind][1] = Math.max(pick, notPick);
            int sell = prices[ind] - fee + dp[ind+1][1];
            int notSell = 0 + dp[ind+1][0];
            dp[ind][0] = Math.max(sell, notSell);

        }

        return dp[0][1];
        // TC -> O(n)
        // SC -> O(n x 2)
    }

    private static int findMaxProfitSpaceOptimized(int[] prices, int fee){
        int n = prices.length;

        int[] dp = new int[2];

        // base case
        // for ind == n
        dp[0] = 0;
        dp[1] = 0;

        for (int ind = n-1; ind >= 0; ind--) {
            int[] current = new int[2];
            int pick = -prices[ind] + dp[0];
            int notPick = 0 + dp[1];
            current[1] = Math.max(pick, notPick);
            int sell = prices[ind] - fee + dp[1];
            int notSell = 0 + dp[0];
            current[0] = Math.max(sell, notSell);
            dp = current;
        }

        return dp[1];
        // TC -> O(n)
        // SC -> O(1)
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int fee = sc.nextInt();
        int[] prices = new int[n];
        for (int i = 0; i < n; i++) {
            prices[i] = sc.nextInt();
        }
        System.out.println(findMaxProfitRecursive(prices, fee));
        System.out.println(findMaxProfitTabulation(prices, fee));
        System.out.println(findMaxProfitSpaceOptimized(prices, fee));
        sc.close();
    }
}
