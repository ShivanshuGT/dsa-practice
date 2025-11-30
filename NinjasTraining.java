import java.util.Scanner;

public class NinjasTraining {

    private static int findMaxPointsRecursive(int[][] points, int[][] dp, int day, int last){
 
        int m = points[0].length;
        if(day == 0){
            int max = 0;
            for (int i = 0; i < m; i++) {
                if(i != last){
                    // it is not the activity done in the previous(next) day
                    max = Math.max(max, points[0][i]);
                }
            }
            return max;
        }

        if(dp[day][last] != -1){
            return dp[day][last];
        }

        int max = 0;
        for (int i = 0; i < m; i++) {
            if( i != last){
                int point = points[day][i] + findMaxPointsRecursive(points, dp, day-1, i);
                max = Math.max(max, point);
            }
            
        }
        dp[day][last] = max;
        return dp[day][last];
        // TC -> O(n x m)
        // SC -> O(n x m)
    }

    private static int findMaxPointsTabulation(int[][] points){
        int n = points.length;
        int m = points[0].length;

        if(n == 1){
            // there is only one day
            // then return the max of that day
            int max = 0;
            for (int i = 0; i < m; i++) {
                max = Math.max(max, points[0][i]);
            }
            return max;
        }

        int dp[][] = new int[n][m+1];

        // filling the table for n = 0
        for (int last = 0; last <= m; last++) {
            int max = 0;
            for (int j = 0; j < m; j++) {
                if(last != j){
                    max = Math.max(max, points[0][j]);
                }

            }
            dp[0][last] = max;
        }

        for (int day = 1; day < n; day++) {
            for (int last = 0; last <= m; last++) {
                int max = 0;
                for (int j = 0; j < m; j++) {
                    if(last != j){
                        max = Math.max(max, points[day][j] + dp[day-1][j]);
                    }
                }
                dp[day][last] = max;
            }
        }
        return dp[n-1][m];
        // TC -> O(n x m x m)
        // SC -> O(n x m)
    }

    private static int findMaxPointsSpaceOptimized(int[][] points){
        int n = points.length;
        int m = points[0].length;

        if(n == 1){
            // there is only one day
            // then return the max of that day
            int max = 0;
            for (int i = 0; i < m; i++) {
                max = Math.max(max, points[0][i]);
            } 
            return max;
        }

        int[] dp = new int[m+1];

        // filling the dp for n = 0
        for (int last = 0; last <= m; last++) {
            int max = 0;
            for (int i = 0; i < m; i++) {
                if(last != i){
                    max = Math.max(max, points[0][i]);
                }
            }
            dp[last] = max;
        }

        for (int day = 1; day < n; day++) {
            // creating a temporary array for the current day
            int[] temp = new int[m+1];
            for (int last = 0; last <= m; last++) {
                int max = 0;
                for (int i = 0; i < m; i++) {
                    if(last != i){
                        max = Math.max(max, points[day][i] + dp[i]);
                    }
                }
                temp[last] = max;
            }
            dp = temp;
        }

        return dp[m];
        // TC -> O(n x m x m)
        // SC -> O(m)
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] points = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                points[i][j] = sc.nextInt();
            }
        }

        int[][] dp = new int[n][m+1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= m; j++) {
                dp[i][j] = -1;
            }
        }
        System.out.println(findMaxPointsRecursive(points, dp, n-1, m));
        System.out.println(findMaxPointsTabulation(points));
        System.out.println(findMaxPointsSpaceOptimized(points));
        sc.close();
    }
}
