import java.util.Scanner;

public class EditDistance {

    private static int findMinimumOperationsRecursiveHelper(int ind1, int ind2, String s1, String s2, int[][] dp){
        // base case
        if(ind1 <= 0){
            // s1 is exhausted
            return ind2;
        }

        if(ind2 <= 0){
            // s2 is exhausted
            return ind1;
        }

        if(dp[ind1][ind2] != -1){
            return dp[ind1][ind2];
        }

        if(s1.charAt(ind1-1) == s2.charAt(ind2-1)){
            // the characters match
            dp[ind1][ind2] = 0 + findMinimumOperationsRecursiveHelper(ind1-1, ind2-1, s1, s2, dp);
        }else{
            // the characters do not match
            int deleteOperation = 1 + findMinimumOperationsRecursiveHelper(ind1-1, ind2, s1, s2, dp);
            int insertOperation = 1 + findMinimumOperationsRecursiveHelper(ind1, ind2-1, s1, s2, dp);
            int replaceOperation = 1 + findMinimumOperationsRecursiveHelper(ind1-1, ind2-1, s1, s2, dp);
            dp[ind1][ind2] = Math.min(deleteOperation, Math.min(insertOperation, replaceOperation));
        }
        return dp[ind1][ind2];

        // TC -> O(n x m)
        // SC -> O(n x m) + O(n + m)
    }

    private static int findMinimumOperationsRecursive(String s1, String s2){
        int n = s1.length();
        int m = s2.length();
        int[][] dp = new int[n+1][m+1]; // 1 - based indexing

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                dp[i][j] = -1;
            }
        }

        return findMinimumOperationsRecursiveHelper(n, m, s1, s2, dp);
        // TC -> O(n x m)
        // SC -> O(n x m) + O(n + m)
    }

    private static int findMinimumOperationsTabulation(String s1, String s2){
        int n = s1.length();
        int m = s2.length();

        int[][] dp = new int[n+1][m+1];

        // base case
        for (int i = 0; i <= n; i++) {
            dp[i][0] = i;
        }

        for (int i = 0; i <= m; i++) {
            dp[0][i] = i;
        }

        for (int ind1 = 1; ind1 <= n; ind1++) {
            for (int ind2 = 1; ind2 <= m; ind2++) {
                if(s1.charAt(ind1-1) == s2.charAt(ind2-1)){
                    dp[ind1][ind2] = 0 + dp[ind1-1][ind2-1];
                }else{
                    int insertOperation = 1 + dp[ind1][ind2-1];
                    int deleteOperation = 1 + dp[ind1-1][ind2];
                    int replaceOperation = 1 + dp[ind1-1][ind2-1];
                    dp[ind1][ind2] = Math.min(deleteOperation, Math.min(insertOperation, replaceOperation));
                }
            }
        }

        return dp[n][m];
        // TC -> O(n x m)
        // SC -> O(n x m)

    }

    private static int findMinimumOperationsSpaceOptimized(String s1, String s2){
        int n = s1.length();
        int m = s2.length();

        int[] dp = new int[m+1];

        // base case
        for (int i = 0; i <= m; i++) {
            dp[i] = i;
        }

        for (int ind1 = 1; ind1 <= n; ind1++) {
            int[] current = new int[m+1];
            current[0] = ind1;
            for (int ind2 = 1; ind2 <= m; ind2++) {
                if(s1.charAt(ind1-1) == s2.charAt(ind2-1)){
                    current[ind2] = 0 + dp[ind2-1];
                }else{
                    int deleteOperation = 1 + dp[ind2];
                    int insertOperation = 1 + current[ind2-1];
                    int replaceOperation = 1 + dp[ind2-1];
                    current[ind2] = Math.min(insertOperation, Math.min(replaceOperation, deleteOperation));
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
        System.out.println(findMinimumOperationsRecursive(s1, s2));
        System.out.println(findMinimumOperationsTabulation(s1, s2));
        System.out.println(findMinimumOperationsSpaceOptimized(s1, s2));
        sc.close();
    }
}
