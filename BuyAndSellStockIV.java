import java.util.Scanner;

public class BuyAndSellStockIV {

    private static int findMaxProfitRecursiveHelper(int ind, int trn, int k, int[] prices, int[][] dp){
        int n = prices.length;

        // base case
        if((ind == n) || (trn == 2*k)){
            return 0;
        }

        if(dp[ind][trn] != -1){
            return dp[ind][trn];
        }

        if((trn % 2) == 0){
            // its the case of buy
            int pick = -prices[ind] + findMaxProfitRecursiveHelper(ind+1, trn+1, k, prices, dp);
            int notPick = 0 + findMaxProfitRecursiveHelper(ind+1, trn, pick, prices, dp);
            dp[ind][trn] = Math.max(pick, notPick);
        }else{
            // its the case of sell
            int sell = prices[ind] + findMaxProfitRecursiveHelper(ind+1, trn+1, k, prices, dp);
            int notSell = 0 + findMaxProfitRecursiveHelper(ind+1, trn, k, prices, dp);
            dp[ind][trn] = Math.max(sell, notSell);
        }
        return dp[ind][trn];
        // TC -> O(n x 2 * k)
        // SC -> O(n x 2 x k) + O(n)
    }

    private static int findMaxProfitRecursive(int[] prices, int k){
        int n = prices.length;

        int[][] dp = new int[n][2*k];
        for (int ind = 0; ind < n; ind++) {
            for (int trn = 0; trn < 2*k; trn++) {
                dp[ind][trn] = -1;
            }
        }

        return findMaxProfitRecursiveHelper(0, 0, k, prices, dp);
        // TC -> O(n x 2 x k)
        // SC -> O(n x 2 x k) + O(n)
    }

    private static int findMaxProfitTabulation(int[] prices, int k){
        int n = prices.length;

        int[][] dp = new int[n+1][2*k+1];

        // base case
        // for ind == n
        for (int i = 0; i <= 2*k; i++) {
            dp[n][i] = 0;
        }

        // for trn = 2*k
        for (int i = 0; i <= n; i++) {
            dp[i][2*k] = 0;
        }

        for (int ind = n-1; ind >=0 ; ind--) {
            for (int trn = 2*k-1; trn >= 0; trn--) {
                if((trn % 2) == 0){
                    int pick = -prices[ind] + dp[ind+1][trn+1];
                    int notPick = 0 + dp[ind+1][trn];
                    dp[ind][trn] = Math.max(pick, notPick);
                }else{
                    int sell = prices[ind] + dp[ind+1][trn+1];
                    int notSell = 0 + dp[ind+1][trn];
                    dp[ind][trn] = Math.max(sell, notSell);
                }
            }
        }

        return dp[0][0];
        // TC -> O(n x 2 x k)
        // SC -> O(n x 2 x k)
    }

    private static int findMaxProfitSpaceOptimized(int[] prices, int k){
        int n = prices.length;

        int[] dp = new int[2*k+1];

        // base case
        // for ind == n
        for (int i = 0; i <= 2*k; i++) {
            dp[i] = 0;
        }

        for (int ind = n-1; ind >= 0; ind--) {
            int[] current = new int[2*k+1];
            dp[2*k] = 0;
            for (int trn = 2*k-1; trn >= 0; trn--) {
                if((trn % 2) == 0){
                    int pick = -prices[ind] + dp[trn+1];
                    int notPick = 0 + dp[trn];
                    current[trn] = Math.max(pick, notPick);
                }else{
                    int sell = prices[ind] + dp[trn+1];
                    int notSell = 0 + dp[trn];
                    current[trn] = Math.max(sell, notSell);
                }
            }
            dp = current;
        }

        return dp[0];
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
