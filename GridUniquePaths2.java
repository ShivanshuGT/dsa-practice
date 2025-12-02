import java.util.Scanner;

public class GridUniquePaths2 {

    private static int findNumberOfUniquePathsRecursiveHelper(int i, int j, int[][] arr, int[][] dp){
        if(i >= 0 && j >= 0 && arr[i][j] == -1){
            // since it is a blocker so there is no path
            return 0;
        }
        if(i == 0 && j == 0){
            return 1;
        }
        if(i < 0 || j < 0){
            // since it falls outside the matrix that'swhy there is no path
            return 0;
        }

        if(dp[i][j] != -1){
            return dp[i][j];
        }

        int up = findNumberOfUniquePathsRecursiveHelper(i-1, j, arr, dp);
        int left = findNumberOfUniquePathsRecursiveHelper(i, j-1, arr, dp);
        dp[i][j] = up + left;
        return dp[i][j];
        // TC -> O(n x m)
        // SC -> O(n x m)
    }

    private static int findNumberOfUniquePathsRecursive(int[][] arr){
        int n = arr.length;
        int m = arr[0].length;

        if(n == 1 && m == 1 && arr[0][0] == -1){
            return 0;
        }
        if(n == 1 && m == 1){
            return 1;
        }

        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dp[i][j] = -1;
            }
        }

        return findNumberOfUniquePathsRecursiveHelper(n-1, m-1, arr, dp);


    }

    private static int findNumberOfUniquePathsTabulation(int[][] arr){
        int n = arr.length;
        int m = arr[0].length;

        if(n == 1 && m == 1 && arr[0][0] == -1){
            return 0;
        }

        if(n == 1 && m == 1){
            return 1;
        }

        int[][] dp = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(arr[i][j] == -1){
                    // since it is a blocker, so there is no path
                    dp[i][j] = 0;
                }
                else if(i == 0 && j == 0){
                    dp[i][j] = 1;
                }
                else{

                    int up = 0;
                    int left = 0;
                    if(i > 0){
                        up = dp[i-1][j];
                    }
                    if(j > 0){
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

    private static int findNumberOfUniquePathsSpaceOptimized(int[][] arr){
        int n = arr.length;
        int m = arr[0].length;

        if(n == 1 && m == 1 && arr[0][0] == -1){
            return 0;
        }

        if(n == 1 && m == 1){
            return 1;
        }

        int[] dp = new int[m];

        for (int i = 0; i < n; i++) {
            int[] current = new int[m];
            for (int j = 0; j < m; j++) {
                if(arr[i][j] == -1){
                    current[j] = 0;
                }else if(i == 0 && j == 0){
                    current[j] = 1;
                }else{
                    int left = 0;
                    int up = dp[j];
                    if(j > 0){
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
        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        System.out.println(findNumberOfUniquePathsRecursive(arr));
        System.out.println(findNumberOfUniquePathsTabulation(arr));
        System.out.println(findNumberOfUniquePathsSpaceOptimized(arr));
        sc.close();
    }
}
