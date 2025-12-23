import java.util.Scanner;

public class LongestCommonSubstring {

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

        int answer = 0;

        for (int ind1 = 1; ind1 <= n; ind1++) {
            for (int ind2 = 1; ind2 <= m; ind2++) {
                if(s1.charAt(ind1-1) == s2.charAt(ind2-1)){
                    dp[ind1][ind2] = 1 + dp[ind1-1][ind2-1];
                }else{
                    dp[ind1][ind2] = 0;
                }
                answer = Math.max(answer, dp[ind1][ind2]);
            }
        }

        return answer;
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

        int answer = 0;

        for (int ind1 = 1; ind1 <= n; ind1++) {
            int[] current = new int[m+1];
            current[0] = 0;
            for (int ind2 = 1; ind2 <= m; ind2++) {
                if(s1.charAt(ind1-1) == s2.charAt(ind2-1)){
                    current[ind2] = 1 + dp[ind2-1];
                }else{
                    current[ind2] = 0;
                }
                answer = Math.max(answer, current[ind2]);
            }
            dp = current;
        }

        return answer;
        // TC -> O(n x m)
        // SC -> O(m)
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s1 = sc.nextLine();
        String s2 = sc.nextLine();
        System.out.println(findLCSTabulation(s1, s2));
        System.out.println(findLCSSpaceOptimized(s1, s2));
        sc.close();
    }
}
