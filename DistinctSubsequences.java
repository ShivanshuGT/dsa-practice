import java.util.Scanner;

public class DistinctSubsequences {

    private static int findDistinctSubsequencesRecursiveHelper(int ind1, int ind2, String s1, String s2, int[][] dp){
        // base case
        if(ind2 <= 0){
            // the second string is exhausted
            // this means it is matched completely
            return 1;
        }
        if(ind1 <= 0){
            // the first string is exhausted and the second string is not exhausted yet
            // this means it is not matched completely
            return 0;
        }

        if(dp[ind1][ind2] != -1){
            return dp[ind1][ind2];
        }

        if(s1.charAt(ind1-1) == s2.charAt(ind2-1)){
            // the characters match
            dp[ind1][ind2] = findDistinctSubsequencesRecursiveHelper(ind1-1, ind2-1, s1, s2, dp) // consider this match
            + findDistinctSubsequencesRecursiveHelper(ind1-1, ind2, s1, s2, dp); // do not consider this match    

        }else{
            // the characters do not match
            dp[ind1][ind2] = findDistinctSubsequencesRecursiveHelper(ind1-1, ind2, s1, s2, dp);
        }

        return dp[ind1][ind2];
        // TC -> O(n x m)
        // SC -> O(n x m) + O(n + m)
    }

    private static int findDistinctSubsequencesRecursive(String s1, String s2){
        int n = s1.length();
        int m = s2.length();

        int[][] dp = new int[n+1][m+1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                dp[i][j] = -1;
            }
        }

        return findDistinctSubsequencesRecursiveHelper(n, m, s1, s2, dp);
        // TC -> O(n x m)
        // SC -> O(n x m) + O(n + m)
    }

    private static int findDistinctSubsequencesTabulation(String s1, String s2){
        int n = s1.length();
        int m = s2.length();

        int[][] dp = new int[n+1][m+1];

        // base case
        for (int i = 0; i <= n; i++) {
            // the second string is exhausted
            dp[i][0] = 1;
        }

        for (int i = 1; i <= m; i++) {
            // the first string is exhausted
            dp[0][i] = 0;
        }

        for (int ind1 = 1; ind1 <= n; ind1++) {
            for (int ind2 = 1; ind2 <= m; ind2++) {
                if(s1.charAt(ind1-1) == s2.charAt(ind2-1)){
                    dp[ind1][ind2] = dp[ind1-1][ind2-1] + dp[ind1-1][ind2];
                }else{
                    dp[ind1][ind2] = dp[ind1-1][ind2];
                }
            }
        }

        return dp[n][m];
        // TC -> O(n x m)
        // SC -> O(n x m)
    }

    private static int findDistinctSubsequencesSpaceOptimizedTwoRows(String s1, String s2){
        int n = s1.length();
        int m = s2.length();

        int[] dp = new int[m+1];

        // base case
        dp[0] = 1;
        for (int i = 1; i <= m; i++) {
            dp[i] = 0;
        }

        for (int ind1 = 1; ind1 <= n; ind1++) {
            int[] current = new int[m+1];
            current[0] = 1;
            for (int ind2 = 1; ind2 <= m; ind2++) {
                if(s1.charAt(ind1-1) == s2.charAt(ind2-1)){
                    current[ind2] = dp[ind2-1] + dp[ind2];
                }else{
                    current[ind2] = dp[ind2];
                }
            }
            dp = current;
        }

        return dp[m];
        // TC -> O(n x m)
        // SC -> O(m)
    }

    private static int findDistinctSubsequencesSpaceOptimizedOneRow(String s1, String s2){
        int n = s1.length();
        int m = s2.length();
        int[] dp = new int[m+1];
        dp[0] = 1;

        for (int ind1 = 1; ind1 <= n; ind1++) {
            for (int ind2 = m; ind2 >= 1; ind2--) {
                if(s1.charAt(ind1-1) == s2.charAt(ind2-1)){
                    dp[ind2] = dp[ind2-1] + dp[ind2];
                }else{
                    dp[ind2] = dp[ind2];
                }
            }
        }

        return dp[m];
        // TC -> O(n x m)
        // SC -> O(m)
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s1 = sc.nextLine();
        String s2 = sc.nextLine();
        System.out.println(findDistinctSubsequencesRecursive(s1, s2));
        System.out.println(findDistinctSubsequencesTabulation(s1, s2));
        System.out.println(findDistinctSubsequencesSpaceOptimizedTwoRows(s1, s2));
        System.out.println(findDistinctSubsequencesSpaceOptimizedOneRow(s1, s2));
        sc.close();
    }
}
