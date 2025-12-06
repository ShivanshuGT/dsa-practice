import java.util.Scanner;

public class MaximumFallingPathSum {

    private static int findMaxPathSumRecursiveHelper(int row, int col, int[][] arr, int[][] dp){
        // it signifies the max path sum from (row, col)
        // to any cell in the first row

        int m = arr[0].length;
        if(col < 0 || col >= m){
            // out of matrix path
            // ignore this
            return Integer.MIN_VALUE;
        }

        if(row == 0){
            // we have reached the first row
            return arr[0][col];
        }

        if(dp[row][col] != -1){
            return dp[row][col];
        }

        int up = findMaxPathSumRecursiveHelper(row-1, col, arr, dp);
        int leftDiagonal = findMaxPathSumRecursiveHelper(row-1, col-1, arr, dp);
        int rightDiagonal = findMaxPathSumRecursiveHelper(row-1, col+1, arr, dp);

        dp[row][col] = arr[row][col] + Math.max(up, Math.max(leftDiagonal, rightDiagonal));
        return dp[row][col];
        // TC -> O(n x m)
        // SC -> O(n x m) + O(n)
    }

    private static int findMaxPathSumRecursive(int[][] arr){
        int n = arr.length;
        int m = arr[0].length;

        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dp[i][j] = -1;
            }
        }

        // calling for each cell in the last row
        int answer = Integer.MIN_VALUE;
        for (int i = 0; i < m; i++) {
            answer = Math.max(answer, findMaxPathSumRecursiveHelper(n-1, i, arr, dp));
        }
        return answer;

        // TC -> O(n x m)
        // SC -> O(n x m) + O(n)
    }

    private static int findMaxPathSumTabulation(int[][] arr){
        int n = arr.length;
        int m = arr[0].length;

        int[][] dp = new int[n][m];

        // base cases
        for (int i = 0; i < m; i++) {
            dp[0][i] = arr[0][i];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                
                int leftDiagonal = Integer.MIN_VALUE;
                int rightDiagonal = Integer.MAX_VALUE;

                int up = dp[i-1][j];
                if((j-1) >= 0){
                    // handling the out of bound cases
                    leftDiagonal = dp[i-1][j-1];
                }
                if((j+1) < m){
                    // handling the out of bound cases
                    rightDiagonal = dp[i-1][j+1];
                }
                dp[i][j] = arr[i][j] + Math.max(Math.max(rightDiagonal, leftDiagonal), up);
            }
        }

        int answer = Integer.MIN_VALUE;
        for (int i = 0; i < m; i++) {
            answer = Math.max(answer, dp[n-1][i]);
        }
        return answer;
        // TC -> O(n x m)
        // SC -> O(n x m)
    }

    private static int findMaxPathSumSpaceOptimized(int[][] arr){
        int n = arr.length;
        int m = arr[0].length;

        int[] dp = new int[m];

        // bases cases
        for (int i = 0; i < m; i++) {
            dp[i] = arr[0][i];
        }
        

        for (int i = 1; i < n; i++) {

            int[] current = new int[m];

            for (int j = 0; j < m; j++) {
                int up = dp[j];

                int leftDiagonal = Integer.MIN_VALUE;
                int rightDiagonal = Integer.MIN_VALUE;

                if((j-1) >= 0){
                    leftDiagonal = dp[j-1];
                }

                if((j+1) < m){
                    rightDiagonal = dp[j+1];
                }

                current[j] = arr[i][j] + Math.max(up, Math.max(leftDiagonal, rightDiagonal));
            }
            dp = current;
        }

        int answer = Integer.MIN_VALUE;
        for (int i = 0; i < m; i++) {
            answer = Math.max(answer, dp[i]);
        }

        return answer;
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
        System.out.println(findMaxPathSumRecursive(arr));
        System.out.println(findMaxPathSumTabulation(arr));
        System.out.println(findMaxPathSumSpaceOptimized(arr));
        sc.close();
    }
}
