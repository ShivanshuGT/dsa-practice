import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TriangleUsingDP {

    private static int findMinimumPathSumRecursiveHelper(int row, int col, List<List<Integer>> triangle, int[][] dp){
        // f(row, col, triangle, dp) represents the minimum path sum 
        // from (row, col) co-ordinates to the last row

        int n = triangle.size();
        // base case
        if(row == n-1){
            // this is the last row
            return triangle.get(row).get(col);
        }

        if(dp[row][col] != -1){
            return dp[row][col];
        }

        int down = triangle.get(row).get(col) + findMinimumPathSumRecursiveHelper(row+1, col, triangle, dp);
        int diagonal = triangle.get(row).get(col) + findMinimumPathSumRecursiveHelper(row+1, col+1, triangle, dp);
        dp[row][col] = Math.min(down, diagonal);
        return dp[row][col];
        // TC -> O(n x n)
        // SC -> O(n x n)
    }

    private static int findMinimumPathSumRecursive(List<List<Integer>> triangle){
        int n = triangle.size();

        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = -1;
            }
        }

        return findMinimumPathSumRecursiveHelper(0, 0, triangle, dp);
        // TC -> O(n x n)
        // SC -> O(n x n)

    }

    private static int findMinimumPathSumTabulation(List<List<Integer>> triangle){
        int n = triangle.size();

        int[][] dp = new int[n][n];

        // base cases
        for (int i = 0; i < n; i++) {
            dp[n-1][i] = triangle.get(n-1).get(i);
        }

        for (int i = n-2; i >= 0; i--) {
            for (int j = i; j >= 0; j--) {
                int down = triangle.get(i).get(j) + dp[i+1][j];
                int diagonal = triangle.get(i).get(j) + dp[i+1][j+1];
                dp[i][j] = Math.min(down, diagonal);
            }
        }
        return dp[0][0];
        // TC -> O(n x n)
        // SC -> O(n x n)
    }

    private static int findMinimumPathSumSpaceOptimized(List<List<Integer>> triangle){
        int n = triangle.size();

        int[] dp = new int[n];

        // base cases
        for (int i = 0; i < n; i++) {
            dp[i] = triangle.get(n-1).get(i);
        }

        for (int i = n-2; i >=0; i--) {
            int[] current = new int[n];
            for (int j = i; j >= 0; j--) {
                int down = triangle.get(i).get(j) + dp[j];
                int diagonal = triangle.get(i).get(j) + dp[j+1];
                current[j] = Math.min(down, diagonal);
            }
            dp = current;
        }
        return dp[0];
        // TC -> O(n x n)
        // SC -> O(n)
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<List<Integer>> triangle = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                list.add(sc.nextInt());
            }
            triangle.add(list);
        }
        System.out.println(findMinimumPathSumRecursive(triangle));
        System.out.println(findMinimumPathSumTabulation(triangle));
        System.out.println(findMinimumPathSumSpaceOptimized(triangle));
        sc.close();
    }
}
