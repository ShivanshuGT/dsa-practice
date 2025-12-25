import java.util.Scanner;

public class MinimumInsertionsToMakeStringPalindrome {

    // minimum insertions to make a string palindrome = size of string - length of longest palindromic subsequence

    private static int findLPSRecursive(int ind1, int ind2, String s1, String s2, int[][] dp){
        // base case
        if((ind1 <= 0) || (ind2 <= 0)){
            return 0;
        }

        if(dp[ind1][ind2] != -1){
            return dp[ind1][ind2];
        }

        if(s1.charAt(ind1-1) == s2.charAt(ind2-1)){
            dp[ind1][ind2] = 1 + findLPSRecursive(ind1-1, ind2-1, s1, s2, dp);
        }else{
            dp[ind1][ind2] = 0 + Math.max(findLPSRecursive(ind1, ind2-1, s1, s2, dp), findLPSRecursive(ind1-1, ind2, s1, s2, dp));
        }
        return dp[ind1][ind2];
        // TC -> O(n x n)
        // SC -> O(n x n) + O(n + n)
    }

    private static int findMinimumInsertionsRecusrsive(String s1){
        String s2 = new StringBuilder(s1).reverse().toString();

        int n = s1.length();

        int[][] dp = new int[n+1][n+1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j] = -1;
            }
        }

        int lpsLength = findLPSRecursive(n, n, s1, s2, dp);
        return (n- lpsLength);
        // TC -> O(n x n)
        // SC -> O(n x n) + O(n + n)
    }

    private static int findMinimumInsertionsTabulation(String s1){
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
        return (n - lpsLength);
        // TC -> O(n x n)
        // SC -> O(n x n)
    }

    private static int findMinimumInsertionsSpaceOptimized(String s1){
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

        int lpsLength = dp[n];
        return (n - lpsLength);

        // TC -> O(n x n)
        // SC -> O(n)
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(findMinimumInsertionsRecusrsive(s));
        System.out.println(findMinimumInsertionsTabulation(s));
        System.out.println(findMinimumInsertionsSpaceOptimized(s));
        sc.close();
    }
}
