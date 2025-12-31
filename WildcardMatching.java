import java.util.Scanner;

public class WildcardMatching {

    private static boolean wildcardMatchRecursiveHelper(int ind1, int ind2, String s1, String s2, int[][] dp){
        // base cases
        if((ind1 == 0) && (ind2 == 0)){
            // both the strings are exhausted
            return true;
        }
        if((ind1 == 0) && (ind2 > 0)){
            // s1 is exhausted and s2 is not exhausted
            return false;
        }

        if((ind2 == 0) && (ind1 > 0)){
            // s2 is exhausted and s1 is not exhausted
            // we can return true only if all the remaining characters of s1 are '*'

            for (int i = 1; i <= ind1; i++) {
                if(s1.charAt(ind1-1) != '*'){
                    // if at least one of the character in s1 is not '*'
                    return false;
                }
            }
            return true;
        }

        if(dp[ind1][ind2] != -1){
            return dp[ind1][ind2] == 1;
        }

        if((s1.charAt(ind1-1) == s2.charAt(ind2-1)) || (s1.charAt(ind1-1) == '?')){
            // either both the characters match
            // s1 has '?' character
            dp[ind1][ind2] =  wildcardMatchRecursiveHelper(ind1-1, ind2-1, s1, s2, dp) ? 1 : 0;
        }else if(s1.charAt(ind1-1) == '*'){
            // s1 has '*' character
            dp[ind1][ind2] =  (wildcardMatchRecursiveHelper(ind1-1, ind2, s1, s2, dp) || wildcardMatchRecursiveHelper(ind1, ind2-1, s1, s2, dp) )? 1 : 0;
        }
        else{
            dp[ind1][ind2] = 0;
        }
        return dp[ind1][ind2] == 1;

        // TC -> O(n x m)
        // SC -> O(n x m) + O(n + m)

    }

    private static boolean wildcardMatchRecursive(String s1, String s2){
        int n = s1.length();
        int m = s2.length();

        int[][] dp = new int[n+1][m+1]; // 1-based indexing

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                dp[i][j] = -1;
            }
        }

        return wildcardMatchRecursiveHelper(n, m, s1, s2, dp);
        // TC -> O(n x m)
        // SC -> O(n x m) + O(n + m)
    }

    private static boolean wildcardMatchTabulation(String s1, String s2){
        int n = s1.length();
        int m = s2.length();

        boolean[][] dp = new boolean[n+1][m+1];

        // base cases
        dp[0][0] = true;

        for (int ind2 = 1; ind2 <= m; ind2++) {
            dp[0][ind2] = false;
        }

        for (int ind1 = 1; ind1 <= n; ind1++) {
            boolean flag = true;
            for (int i = 1; i <= ind1; i++) {
                if(s1.charAt(i-1) != '*'){
                    flag = false;
                    break;
                }
            }
            dp[ind1][0] = flag;
        }

        for (int ind1 = 1; ind1 <= n; ind1++) {
            for (int ind2 = 1; ind2 <= m; ind2++) {
                if((s1.charAt(ind1-1) == s2.charAt(ind2-1)) || (s1.charAt(ind1-1) == '?')){
                    dp[ind1][ind2] = dp[ind1-1][ind2-1];
                }else if(s1.charAt(ind1-1) == '*'){
                    dp[ind1][ind2] = dp[ind1-1][ind2] || dp[ind1][ind2-1];
                }else{
                    dp[ind1][ind2] = false;
                }
            }
        }

        return dp[n][m];
        // TC -> O(n x m)
        // SC -> O(n x m)

    }

    private static boolean wildcardMatchSpaceOptimized(String s1, String s2){
        int n = s1.length();
        int m = s2.length();

        boolean[] dp = new boolean[m+1];

        // base cases
        dp[0] = true;
        
        for (int ind2 = 1; ind2 <= m; ind2++) {
            dp[ind2] = false;
        }

        for (int ind1 = 1; ind1 <= n; ind1++) {
            boolean flag = true;
            boolean[] current = new boolean[m+1];
            for (int i = 1; i <= ind1; i++) {
                if(s1.charAt(ind1-1) != '*'){
                    flag = false;
                    break;
                }
            }
            current[0] = flag;

            for (int ind2 = 1; ind2 <= m; ind2++) {
                if((s1.charAt(ind1-1) == s2.charAt(ind2-1)) || (s1.charAt(ind1-1) == '?')){
                    current[ind2] = dp[ind2-1];
                }else if(s1.charAt(ind1-1) == '*'){
                    current[ind2] = dp[ind2] || current[ind2-1];
                }else{
                    current[ind2] = false;
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
        System.out.println(wildcardMatchRecursive(s1, s2));
        System.out.println(wildcardMatchTabulation(s1, s2));
        System.out.println(wildcardMatchSpaceOptimized(s1, s2));
        sc.close();
    }
}
