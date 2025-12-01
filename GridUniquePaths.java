import java.util.Scanner;

public class GridUniquePaths {

    private static int findNumberOfPathsRecursiveHelper(int i, int j, int[][] dp){
        if( i == 0 && j == 0){
            // there is only one way to go from (0,0) to (0,0)
            return 1;
        }

        if( i < 0 || j < 0){
            // it means this path exceeds the boundary of the matrix
            // it is not a valid path
            return 0;
        }

        if(dp[i][j] != -1){
            return dp[i][j];
        }

        int up = findNumberOfPathsRecursiveHelper(i-1, j, dp);
        int left = findNumberOfPathsRecursiveHelper(i, j-1, dp);
        dp[i][j] = up + left;
        return dp[i][j];
        // TC -> O(m x n)
        // SC -> O(m x n)
    }

    private static int findNumberOfPathsRecursive(int n, int m){
        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dp[i][j] = -1;
            }
        }
        return findNumberOfPathsRecursiveHelper(n-1, m-1, dp);
        // TC -> O(n x m)
        // SC -> O(n x m)
    }

    private static int findNumberOfPathsTabulation(int n, int m){
        if( n == 1 && m == 1){
            return 1;
        }

        int[][] dp = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(i == 0 && j == 0){
                    dp[i][j] = 1;
                }
                else{
                    int up = 0;
                    int left = 0;
                    if(i > 0){
                        // safe check
                        up = dp[i-1][j];
                    }
                    if(j > 0){
                        // safe check
                        left = dp[i][j-1];
                    }
                    dp[i][j] = up + left;
                }
            }
        }
        return dp[n-1][m-1];
        // TC -> O(n x m)
        // SC -> O(n x m)
    }

    private static int findNumberOfPathsSpaceOptimized(int n, int m){
        if(n == 1 && m == 1){
            return 1;
        }

        int[] dp = new int[m];
        
        for (int i = 0; i < n; i++) {
            int[] current = new int[m];
            for (int j = 0; j < m; j++) {
                if(i == 0 && j == 0){
                    current[j] = 1;
                }else{
                    int left = 0;
                    int up = dp[j];
                    if(j > 0){
                        // safe check
                        left = current[j-1];
                    }
                    current[j] = up + left;

                }
            }
            dp = current;
        }
        return dp[m-1];
        // TC -> O(n x m)
        // SC -> O(m)
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        System.out.println(findNumberOfPathsRecursive(n, m));
        System.out.println(findNumberOfPathsTabulation(n, m));
        System.out.println(findNumberOfPathsSpaceOptimized(n, m));
        sc.close();
    }
}
