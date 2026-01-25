import java.util.Scanner;

public class CountSquareSubmatricesWithAllOnes {

    private static int countSquareSubmatrices(int[][] mat){
        int n = mat.length;
        int m = mat[0].length;

        int[][] dp = new int[n][m];

        for (int i = 0; i < n; i++) {
            dp[i][0] = mat[i][0];
        }

        for (int i = 0; i < m; i++) {
            dp[0][i] = mat[0][i];
        }
        
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if(mat[i][j] == 0){
                    dp[i][j] = 0;
                }else{
                    dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1])) + 1;
                }
            }
        }
        int ans = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ans += dp[i][j];
            }
        }

        return ans;
        // TC -> O(n x m)
        // SC -> O(n x m)

    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] mat = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                mat[i][j] = sc.nextInt();
            }
        }
        System.out.println(countSquareSubmatrices(mat));
        sc.close();
    }
}
