import java.util.Scanner;

public class PrintLongestCommonSubsequence {

    private static String findLCSTabulation(String s1, String s2){
        int n = s1.length();
        int m = s2.length();

        int[][] dp = new int[n+1][m+1];

        // base case
        for (int i = 0; i <= n; i++) {
            dp[0][i] = 0;
        }

        for (int i = 0; i <= m; i++) {
            dp[i][0] = 0;
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

        int lengthOfLCS = dp[n][m];
        char[] lcs = new char[lengthOfLCS];
        int x = lengthOfLCS-1;

        // backtracking in the dp to get the lcs
        int i = n;
        int j = m;
        while ((i > 0) && (j > 0)) {
            // until one of the string is exhausted
            if(s1.charAt(i-1) == s2.charAt(j-1)){
                // the characters match
                // store the lcs
                lcs[x] = s1.charAt(i-1);
                x -= 1;
                i -= 1;
                j -= 1;
            }else{
                // the characters do not match
                if(dp[i-1][j] > dp[i][j-1]){
                    i -= 1;
                }else{
                    j -= 1;
                }
            }
        }

        return new String(lcs);
        // TC -> O(n x m) + O(n + m)
        // SC -> O(n x m) + O(len(lcs))
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s1 = sc.nextLine();
        String s2 = sc.nextLine();
        System.out.println(findLCSTabulation(s1, s2));
        sc.close();
    }
}
