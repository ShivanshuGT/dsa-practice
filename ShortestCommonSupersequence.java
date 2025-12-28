import java.util.Scanner;

public class ShortestCommonSupersequence {

    private static String findShortestCommonSuperSequence(String s1, String s2){
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
                    dp[ind1][ind2] = 0 + Math.max(dp[ind1-1][ind2], dp[ind1][ind2-1]);
                }
            }
        }

        int lengthLCS = dp[n][m];

        // length of shortest common supersequence = n + m - len(lcs)

        int lengthSCS = n + m - lengthLCS;
        char[] scs = new char[lengthSCS];
        int x = lengthSCS - 1;
        int i = n;
        int j = m;
        while ((i > 0) && (j > 0)) {
            if(s1.charAt(i-1) == s2.charAt(j-1)){
                scs[x] = s1.charAt(i-1);
                x -= 1;
                i -= 1;
                j -= 1;
            }else{
                if(dp[i-1][j] > dp[i][j-1]){
                    scs[x] = s1.charAt(i-1);
                    x -= 1;
                    i -= 1;
                }else{
                    scs[x] = s2.charAt(j-1);
                    x -= 1;
                    j -= 1;
                }
            }
        }

        while (i > 0) {
            scs[x] = s1.charAt(i-1);
            x -= 1;
            i -= 1;
        }

        while (j > 0) {
            scs[x] = s2.charAt(j-1);
            x -= 1;
            j -= 1;
        }

        return new String(scs);

        // TC -> O(n x m) + O(n + m)
        // SC -> O(n x m) + O(len(n + m - len(lcs)))
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s1 = sc.nextLine();
        String s2 = sc.nextLine();
        System.out.println(findShortestCommonSuperSequence(s1, s2));
        sc.close();
    }
}
