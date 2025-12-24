import java.util.Scanner;

public class LongestPalindromicSubsequence {

    private static int findLPSRecursiveHelper(int ind1, int ind2, String s1, String s2, int[][] dp){

        // base case
        if(ind1 <= 0 || ind2 <= 0){
            return 0;
        }

        if(dp[ind1][ind2] != -1){
            return dp[ind1][ind2];
        }

        if(s1.charAt(ind1-1) == s2.charAt(ind2-1)){
            dp[ind1][ind2] = 1 + findLPSRecursiveHelper(ind1-1, ind2-1, s1, s2, dp);
        }else{
            dp[ind1][ind2] = 0 + Math.max(findLPSRecursiveHelper(ind1, ind2-1, s1, s2, dp), findLPSRecursiveHelper(ind1-1, ind2, s1, s2, dp));
        }
        return dp[ind1][ind2];
        // TC -> O(n x n)
        // SC -> O(n x n) + O(n + n)
    }

    private static int findLPSRecursive(String s1){
        String s2 = new StringBuilder(s1).reverse().toString();

        int n = s1.length();
        int[][] dp = new int[n+1][n+1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j] = -1;
            }
        }

        return findLPSRecursiveHelper(n, n, s1, s2, dp);
        // TC -> O(n x n)
        // SC -> O(n x n) + O(n + n)
    }

    private static int findLPSTabulation(String s1){
        String s2 = new StringBuilder(s1).reverse().toString();
        int n = s1.length();

        int[][] dp = new int[n+1][n+1];

        // base case
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0;
            dp[0][i] = 0;
        }

        for (int ind1 = 1; ind1 <= n; ind1++) {
            for (int ind2 = 1; ind2 <= n; ind2++) {
                if(s1.charAt(ind1-1) == s2.charAt(ind2-1)){
                    dp[ind1][ind2] = 1 + dp[ind1-1][ind2-1];
                }else{
                    dp[ind1][ind2] = 0 + Math.max(dp[ind1][ind2-1], dp[ind1-1][ind2]);
                }
            }
        }

        return dp[n][n];
        // TC -> O(n x n)
        // SC -> O(n x n)
    }

    private static int findLPSSpaceOptimized(String s1){
        String s2 = new StringBuilder(s1).reverse().toString();
        int n = s1.length();

        int[] dp = new int[n+1];

        // base case
        for (int i = 0; i <= n; i++) {
            dp[i] = 0;
        }

        for (int ind1 = 1; ind1 <= n; ind1++) {
            int[] current = new int[n+1];
            current[0] = 0;
            for (int ind2 = 1; ind2 <= n; ind2++) {
                if(s1.charAt(ind1-1) == s2.charAt(ind2-1)){
                    current[ind2] = 1 + dp[ind2-1];
                }else{
                    current[ind2] = 0 + Math.max(dp[ind2], current[ind2-1]);
                }
            }
            dp = current;
        }

        return dp[n];
        // TC -> O(n x n)
        // SC -> O(n)
    }

    private static String printLPSTabulation(String s1){
        String s2 = new StringBuilder(s1).reverse().toString();
        int n = s1.length();

        int[][] dp = new int[n+1][n+1];

        // base case
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0;
            dp[0][i] = 0;
        }

        for (int ind1 = 1; ind1 <= n; ind1++) {
            for (int ind2 = 1; ind2 <= n; ind2++) {
                if(s1.charAt(ind1-1) == s2.charAt(ind2-1)){
                    dp[ind1][ind2] = 1 + dp[ind1-1][ind2-1];
                }else{
                    dp[ind1][ind2] = 0 + Math.max(dp[ind1-1][ind2], dp[ind1][ind2-1]);
                }
            }
        }

        int lpsLength = dp[n][n];
        char[] lps = new char[lpsLength];
        int x = lpsLength-1;

        int i = n;
        int j = n;

        while ((i > 0) && (j > 0)) {
            if(s1.charAt(i-1) == s2.charAt(j-1)){
                lps[x] = s1.charAt(i-1);
                x -= 1;
                i -= 1;
                j -= 1;
            }else{
                if(dp[i-1][j] > dp[i][j-1]){
                    i -= 1;
                }else{
                    j -= 1;
                }
            }
        }

        return new String(lps);
        // TC -> O(n x n) + O(n + n)
        // SC -> O(n x n) + O(len(lps))
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(findLPSRecursive(s));
        System.out.println(findLPSTabulation(s));
        System.out.println(findLPSSpaceOptimized(s));
        System.out.println(printLPSTabulation(s));
        sc.close();
    }
}
