import java.util.Scanner;

public class BuyAndSellStockII {

    private static int findMaxProfitRecursiveHelper(int ind, int buy, int[] prices, int[][] dp){
        int n = prices.length;
        // base case
        if(ind == n){
            // we have exhausted the prices array
            return 0;
        }
        if(dp[ind][buy] != -1){
            return dp[ind][buy];
        }

        if(buy == 1){
            // we can buy a stock
            int pick = -prices[ind] + findMaxProfitRecursiveHelper(ind+1, 0, prices, dp);
            int notPick = 0 + findMaxProfitRecursiveHelper(ind+1, 1, prices, dp);
            dp[ind][buy] = Math.max(notPick, pick);
        }else{
            // we can't buy a stock
            // we can only sell
            int sell = prices[ind] + findMaxProfitRecursiveHelper(ind+1, 1, prices, dp);
            int notSell = 0 + findMaxProfitRecursiveHelper(ind+1, 0, prices, dp);
            dp[ind][buy] = Math.max(notSell, sell);
        }
        return dp[ind][buy];
        // TC -> O(n x 2)
        // SC -> O(n x 2) + O(n)
    }

    private static int findMaxProfitRecursive(int[] prices){
        int n = prices.length;
        int[][] dp = new int[n][2];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= 1; j++) {
                dp[i][j] = -1;
            }
        }

        return findMaxProfitRecursiveHelper(0, 1, prices, dp);
        // TC -> O(n x 2)
        // SC -> O(n x 2) + O(n)
    }

    private static int findMaxProfitTabulation(int[] prices){
        int n = prices.length;
        int[][] dp = new int[n+1][2];

        // base case
        dp[n][0] = 0;
        dp[n][1] = 0;

        for (int ind = n-1; ind >= 0; ind--) {
            for (int buy = 0; buy <= 1; buy++) {
                if(buy == 1){
                    int pick = -prices[ind] + dp[ind+1][0];
                    int notPick = 0 + dp[ind+1][1];
                    dp[ind][buy] = Math.max(notPick, pick);
                }else{
                    int sell = prices[ind] + dp[ind+1][1];
                    int notSell = 0 + dp[ind+1][0];
                    dp[ind][buy] = Math.max(notSell, sell);
                }
            }
        }

        return dp[0][1];
        // TC -> O(n x 2)
        // SC -> O(n x 2)
    }

    private static int findMaxProfitSpaceOptimized(int[] prices){
        int n = prices.length;

        int[] dp = new int[2];

        // base case
        dp[0] = 0;
        dp[1] = 0;

        for (int ind = n-1; ind >= 0; ind--) {
            int[] current = new int[2];
            for (int buy = 0; buy <= 1; buy++) {
                if(buy == 1){
                    int pick = -prices[ind] + dp[0];
                    int notPick = 0 + dp[1];
                    current[buy] = Math.max(notPick, pick);
                }else{
                    int sell = prices[ind] + dp[1];
                    int notSell = 0 + dp[0];
                    current[buy] = Math.max(notSell, sell);
                }
            }
            dp = current;
        }

        return dp[1];
        // TC -> O(n x 2)
        // SC -> O(1)
    }

    private static int findMaxProfitSpaceOptimizedWithVars(int[] prices){
        int n = prices.length;

        // base case
        int aheadBuy = 0;
        int aheadNotBuy = 0;

        for (int ind = n-1; ind >= 0; ind--) {
            int pick = -prices[ind] + aheadNotBuy;
            int notPick = 0 + aheadBuy;
            int currentBuy = Math.max(notPick, pick);
            int sell = prices[ind] + aheadBuy;
            int notSell = 0 + aheadNotBuy;
            int currentNotBuy = Math.max(notSell, sell);
            aheadBuy = currentBuy;
            aheadNotBuy = currentNotBuy;

        }
        return aheadBuy;
        // TC -> O(n)
        // SC -> O(1)
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] prices = new int[n];
        for (int i = 0; i < n; i++) {
            prices[i] = sc.nextInt();
        }
        System.out.println(findMaxProfitRecursive(prices));
        System.out.println(findMaxProfitTabulation(prices));
        System.out.println(findMaxProfitSpaceOptimized(prices));
        System.out.println(findMaxProfitSpaceOptimizedWithVars(prices));
        sc.close();
    }
}
