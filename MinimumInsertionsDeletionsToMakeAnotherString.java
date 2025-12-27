import java.util.Scanner;

public class MinimumInsertionsDeletionsToMakeAnotherString {

    /*
    Minimum number of insertions and deletions = len(s1) + len(s2) - 2 x (len(lcs(s1, s2)))
    */

    private static int findLcsRecusrive(int ind1, int ind2, String s1, String s2, int[][] dp){
        // base case
        if((ind1 <= 0) || (ind2 <= 0)){
            return 0;
        }

        if(dp[ind1][ind2] != -1){
            return dp[ind1][ind2];
        }

        if(s1.charAt(ind1-1) == s2.charAt(ind2-1)){
            dp[ind1][ind2] =  1 + findLcsRecusrive(ind1-1, ind2-1, s1, s2, dp);
        }else{
            dp[ind1][ind2] =  0 + Math.max(findLcsRecusrive(ind1, ind2-1, s1, s2, dp), findLcsRecusrive(ind1-1, ind2, s1, s2, dp));
        }
        return dp[ind1][ind2];
        // TC -> O(n x m)
        // SC -> O(n x m) + O(n + m)
    }

    private static int findMinimumInsertionsDeletionsRecursive(String s1, String s2){
        int n = s1.length();
        int m = s2.length();

        int[][] dp = new int[n+1][m+1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                dp[i][j] = -1;
            }
        }

        int lengthOfLCS = findLcsRecusrive(n, m, s1, s2, dp);
        return (n + m - (2 * lengthOfLCS));
        // TC -> O(n x m)
        // SC -> O(n x m) + O(n + m)
    }

    private static int findMinimumInsertionsDeletionsTabulation(String s1, String s2){
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
                    dp[ind1][ind2] = 1 + dp[ind1-1][ind2-1];
                }else{
                    dp[ind1][ind2] = 0 + Math.max(dp[ind1][ind2-1], dp[ind1-1][ind2]);
                }
            }
        }

        int lengthOfLCS = dp[n][m];
        return (n + m - (2 * lengthOfLCS));
        // TC -> O(n x m)
        // SC -> O(n x m)
    }

    private static int findMinimumInsertionsDeletionsSpaceOptimized(String s1, String s2){
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
                    current[ind2] = 1 + dp[ind2-1];
                }else{
                    current[ind2] = 0 + Math.max(dp[ind2], current[ind2-1]);
                }
            }
            dp = current;
        }
        int lengthOfLCS = dp[m];

        return (n + m - (2 * lengthOfLCS));
        // TC -> O(n x m)
        // SC -> O(m)
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s1 = sc.nextLine();
        String s2 = sc.nextLine();
        System.out.println(findMinimumInsertionsDeletionsRecursive(s1, s2));
        System.out.println(findMinimumInsertionsDeletionsTabulation(s1, s2));
        System.out.println(findMinimumInsertionsDeletionsSpaceOptimized(s1, s2));
        sc.close();
    }
}
