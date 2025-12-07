import java.util.Scanner;

public class CherryPickup {

    private static int findMaxChocolatesRecursiveHelper(int row, int col1, int col2, int[][] arr, int[][][] dp){

        int m = arr[0].length;
        int n = arr.length;
        // bases cases

        // out of bound cases
        if(col1 >= m || col2 >= m || col1 < 0 || col2 < 0){
            return (int) -1e8;
        }

        if(row == n-1){
            if(col1 == col2){
                // Alice and Bob are at the same column
                return arr[row][col1];
            }else{
                // they are at different columns
                return arr[row][col1] + arr[row][col2];
            }
        }

        if(dp[row][col1][col2] != -1){
            return dp[row][col1][col2];
        }

        // exploring all the possible paths

        int max = -1;
        for (int dcol1 = -1; dcol1 <= 1; dcol1++) {
            for (int dcol2 = -1; dcol2 <= 1; dcol2++) {
                if(col1 == col2){
                    max = Math.max(max, arr[row][col1] + findMaxChocolatesRecursiveHelper(row+1, col1 + dcol1, col2 + dcol2, arr, dp));
                }else{
                    max = Math.max(max, arr[row][col1] + arr[row][col2] + findMaxChocolatesRecursiveHelper(row+1, col1 + dcol1, col2 + dcol2, arr, dp));
                }
            }
        }

        dp[row][col1][col2] = max;
        return dp[row][col1][col2];
        // TC -> O(n x m x m x 9)
        // SC -> O(n x m x m) + O(n)
    }

    private static int findMaxChocolatesRecursive(int[][] arr){
        int n = arr.length;
        int m = arr[0].length;

        int[][][] dp = new int[n][m][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int j2 = 0; j2 < m; j2++) {
                    dp[i][j][j2] = -1;
                }
            }
        }

        return findMaxChocolatesRecursiveHelper(0, 0, m-1, arr, dp);
        // TC -> O(n x m x m x 9)
        // SC -> O(n x m x m) + O(n)
    }

    private static int findMaxChocolatesTabulation(int[][] arr){
        int n = arr.length;
        int m = arr[0].length;

        int[][][] dp = new int[n][m][m];

        // base cases
        // filling for row = n-1
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                if(i == j){
                    // Alice and Bob are at the same column
                    dp[n-1][i][j] = arr[n-1][i];
                }else{
                    // Alice and Bob are at different column
                    dp[n-1][i][j] = arr[n-1][i] + arr[n-1][j];
                }
            }
        }

        for (int row = n-2; row >= 0; row--) {
            for (int col1 = 0; col1 < m; col1++) {
                for (int col2 = 0; col2 < m; col2++) {
                    int max = -1;
                    for (int dcol1 = -1; dcol1 <= 1; dcol1++) {
                        for (int dcol2 = -1; dcol2 <= 1; dcol2++) {
                            int val = 0;
                            if(col1 == col2){
                                if((col1+dcol1) >=0 && (col2+dcol2) >=0 && (col1+dcol1) <m && (col2+dcol2) <m){
                                    val = arr[row][col1] + dp[row+1][col1+dcol1][col2+dcol2];
                                }else{
                                    val = arr[row][col1] + (int) -1e8;
                                }
                            }else{
                                if((col1+dcol1) >=0 && (col2+dcol2) >=0 && (col1+dcol1) <m && (col2+dcol2) <m){
                                    val = arr[row][col1] + arr[row][col2] + dp[row+1][col1+dcol1][col2+dcol2];
                                }else{
                                    val = arr[row][col1] + arr[row][col2] + (int) -1e8;
                                }
                            }
                            max = Math.max(max, val);
                        }
                    }
                    dp[row][col1][col2] = max;
                }
            }
        }

        return dp[0][0][m-1];
        // TC -> O(n x m x m x 9)
        // SC -> O(n x m x m)
    }

    private static int findMaxChocolatesSpaceOptimized(int[][] arr){
        int n = arr.length;
        int m = arr[0].length;

        int[][] dp = new int[m][m];

        // filling for row = n-1
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                if(i == j){
                    dp[i][j] = arr[n-1][i];
                }else{
                    dp[i][j] = arr[n-1][i] + arr[n-1][j];
                }
            }
        }

        
        for (int row = n-2; row >=0; row--) {
            int[][] current = new int[m][m];
            for (int col1 = 0; col1 < m; col1++) {
                for (int col2 = 0; col2 < m; col2++) {
                    
                    int max = -1;
                    for (int dcol1 = -1; dcol1 <= 1; dcol1++) {
                        for (int dcol2 = -1; dcol2 <= 1; dcol2++) {
                            int val = 0;
                            if(col1 == col2){
                                if((col1+dcol1) >=0 && (col2+dcol2) >=0 && (col1+dcol1) < m && (col2+dcol2) < m){
                                    val = arr[row][col1] + dp[col1+dcol1][col2+dcol2];
                                }else{
                                    val = arr[row][col1] + (int) -1e8;
                                }
                            }else{
                                if((col1+dcol1) >=0 && (col2+dcol2) >=0 && (col1+dcol1) < m && (col2+dcol2) < m){
                                    val = arr[row][col1] + arr[row][col2] + dp[col1+dcol1][col2+dcol2];
                                }else{
                                    val = arr[row][col1] + arr[row][col2] + (int) -1e8;
                                }

                            }

                            max = Math.max(max, val);
                        }
                    }
                    current[col1][col2] = max;
                }
            }
            dp = current;
        }

        return dp[0][m-1];
        // TC -> O(n x m x m x 9)
        // SC -> O(m x m)
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

        System.out.println(findMaxChocolatesRecursive(arr));
        System.out.println(findMaxChocolatesTabulation(arr));
        System.out.println(findMaxChocolatesSpaceOptimized(arr));
        sc.close();
    }
}
