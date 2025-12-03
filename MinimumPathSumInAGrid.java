import java.util.Scanner;

public class MinimumPathSumInAGrid {

    private static int findMinimumPathSumRecursiveHelper(int i, int j, int[][] arr, int[][] dp){
        if(i == 0 && j == 0){
            // the distance required from (0,0) to reach (0,0)
            return arr[0][0];
        }

        if(i < 0 || j < 0){
            // this is not a valid path
            // so return a massive answer, so that this path won't be considered
            return Integer.MAX_VALUE;
        }

        if(dp[i][j] != -1){
            return dp[i][j];
        }

        int up = findMinimumPathSumRecursiveHelper(i-1, j, arr, dp);
        int left = findMinimumPathSumRecursiveHelper(i, j-1, arr, dp);
        dp[i][j] = arr[i][j] + Math.min(up, left);
        return dp[i][j];
        // TC -> O(n x m)
        // SC -> O(n x m)
    }

    private static int findMinimumPathSumRecursive(int[][] arr){
        int n = arr.length;
        int m = arr[0].length;

        if(n == 1 && m == 1){
            return arr[0][0];
        }

        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dp[i][j] = -1;
            }
        }

        return findMinimumPathSumRecursiveHelper(n-1, m-1, arr, dp);
        // TC -> O(n x m)
        // SC -> O(n x m)
    }

    private static int findMinimumPathSumTabulation(int[][] arr){
        int n = arr.length;
        int m = arr[0].length;

        if(n == 1 && m == 1){
            return arr[0][0];
        }

        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(i == 0 && j == 0){
                    dp[i][j] = arr[i][j];
                }else{
                    int up = Integer.MAX_VALUE;
                    int left = Integer.MAX_VALUE;
                    if(i > 0){
                        up = dp[i-1][j];
                    }
                    if(j > 0){
                        left = dp[i][j-1];
                    }
                    dp[i][j] = arr[i][j] + Math.min(up, left);
                }
            }
        }

        return dp[n-1][m-1];
        // TC -> O(n x m)
        // SC -> O(n x m)
    }

    private static int findMinimumPathSumSpaceOptimized(int[][] arr){
        int n = arr.length;
        int m = arr[0].length;

        if(n == 1 && m == 1){
            return arr[0][0];
        }

        int[] dp = new int[m];
        for (int i = 0; i < m; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < n; i++) {
            int[] current = new int[m];
            for (int j = 0; j < m; j++) {
                if(i == 0 && j == 0){
                    current[j] = arr[i][j];
                }else{
                    int left = Integer.MAX_VALUE;
                    int up = dp[j];
                    if(j > 0){
                        left = current[j-1];
                    }
                    current[j] = arr[i][j] + Math.min(left, up);
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
        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = sc.nextInt();
            }
        }
        System.out.println(findMinimumPathSumRecursive(arr));
        System.out.println(findMinimumPathSumTabulation(arr));
        System.out.println(findMinimumPathSumSpaceOptimized(arr));
        sc.close();
    }
}
