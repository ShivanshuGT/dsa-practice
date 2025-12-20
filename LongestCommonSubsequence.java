import java.util.Scanner;

public class LongestCommonSubsequence {

    private static int findLCSRecursiveHelper(int ind1, int ind2, String s1, String s2, int[][] dp){
        // base case
        if(ind1 == 0 || ind2 == 0){
            // either of the string is exhausted
            return 0;
        }

        if(dp[ind1][ind2] != -1){
            return dp[ind1][ind2];
        }

        if(s1.charAt(ind1-1) == s2.charAt(ind2-1)){
            // the characters match
            dp[ind1][ind2] = 1 + findLCSRecursiveHelper(ind1-1, ind2-1, s1, s2, dp);
            
        }else{
            // the characters do not match
            dp[ind1][ind2] = 0 + Math.max(findLCSRecursiveHelper(ind1-1, ind2, s1, s2, dp), findLCSRecursiveHelper(ind1, ind2-1, s1, s2, dp));
        }
        return dp[ind1][ind2];
        // TC -> O(n x m)
        // SC -> O(n x m) + O(n + m)
    }

    private static int findLCSRecursive(String s1, String s2){
        int n = s1.length();
        int m = s2.length();

        int[][] dp = new int[n+1][m+1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                dp[i][j] = -1;
            }
        }

        return findLCSRecursiveHelper(n, m, s1, s2, dp);
        // TC -> O(n x m)
        // SC -> O(n x m) + O(n + m)
    }

    private static int findLCSTabulation(String s1, String s2){
        int n = s1.length();
        int m = s2.length();

        int[][] dp = new int[n+1][m+1];

        // base case
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0;
        }

        for (int i = 0; i <= m; i++) {
            dp[0][i] = 0;
        }

        for (int ind1 = 1; ind1 <= n; ind1++) {
            for (int ind2 = 1; ind2 <= m; ind2++) {
                if(s1.charAt(ind1-1) == s2.charAt(ind2-1)){
                    // the characters match
                    dp[ind1][ind2] = 1 + dp[ind1-1][ind2-1];
                }else{
                    // the characters do not match
                    dp[ind1][ind2] = 0 + Math.max(dp[ind1-1][ind2], dp[ind1][ind2-1]);
                }
            }
        }

        return dp[n][m];
        // TC -> O(n x m)
        // SC -> O(n x m)
    }

    private static int findLCSSpaceOptimized(String s1, String s2){
        int n = s1.length();
        int m = s2.length();

        int[] dp = new int[m+1];

        // base case
        for (int i = 0; i <= m; i++) {
            dp[i] = 0;
        }

        for (int ind1 = 1; ind1 <= n; ind1++) {
            int[] current = new int[m+1];
            current[0] = 0;
            for (int ind2 = 1; ind2 <= m; ind2++) {
                if(s1.charAt(ind1-1) == s2.charAt(ind2-1)){
                    // the characters match
                    current[ind2] = 1 + dp[ind2-1];
                }else{
                    // the characters do not match
                    current[ind2] = 0 + Math.max(dp[ind2], current[ind2-1]);
                }
            }
            dp = current;
        }

        return dp[m];
        // TC -> O(n x m)
        // SC -> O(m)
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s1 = sc.nextLine();
        String s2 = sc.nextLine();

        System.out.println(findLCSRecursive(s1, s2));
        System.out.println(findLCSTabulation(s1, s2));
        System.out.println(findLCSSpaceOptimized(s1, s2));
        sc.close();
    }
}
