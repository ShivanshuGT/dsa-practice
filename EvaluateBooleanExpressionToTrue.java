import java.util.Scanner;

public class EvaluateBooleanExpressionToTrue {

    private static int findNumberOfWaysRecursiveHelper(int i, int j, int isTrue, String expr, int[][][] dp){
        // base case
        if(i > j){
            // there is no expression left
            return 0;
        }

        if(i == j){
            // there is only one operand left
            if(isTrue == 1){
                // we are looking for the number of ways to be true
                if(expr.charAt(i) == 'T'){
                    return 1;
                }else{
                    return 0;
                }
            }else{
                // we are looking for the number of ways to be false
                if(expr.charAt(i) == 'F'){
                    return 1;
                }else{
                    return 0;
                }
            }
        }

        if(dp[i][j][isTrue] != -1){
            return dp[i][j][isTrue];
        }

        int ans = 0;

        for (int ind = i+1; ind < j; ind = ind+2) {
            int leftTrue = findNumberOfWaysRecursiveHelper(i, ind-1, 1, expr, dp);
            int leftFalse = findNumberOfWaysRecursiveHelper(i, ind-1, 0, expr, dp);
            int rightTrue = findNumberOfWaysRecursiveHelper(ind+1, j, 1, expr, dp);
            int rightFalse = findNumberOfWaysRecursiveHelper(ind+1, j, 0, expr, dp);

            if(isTrue == 1){
                // we are looking for the number of ways to be true
                // check the operator
                if(expr.charAt(ind) == '&'){
                    ans += (leftTrue * rightTrue);
                }else if(expr.charAt(ind) == '|'){
                    ans += (leftTrue * rightFalse) + (leftTrue * rightTrue) + (leftFalse * rightTrue);
                }else{
                    ans += (leftTrue * rightFalse) + (leftFalse * rightTrue);
                }
            }else{
                // we are looking for the number of ways to be false
                // check the operator
                if(expr.charAt(ind) == '&'){
                    ans += (leftFalse * rightTrue) + (leftFalse * rightFalse) + (leftTrue * rightFalse);
                }else if(expr.charAt(ind) == '|'){
                    ans += (leftFalse * rightFalse);
                }else{
                    ans += (leftFalse * rightFalse) + (leftTrue * rightTrue);
                }
            }
        }

        dp[i][j][isTrue] = ans;
        return ans;
        // TC -> O(n x n x 2 x n)
        // SC -> O(n x n x 2) + O(n)
    }

    private static int findNumberOfWaysRecursive(String expr){
        int n = expr.length();

        int[][][] dp = new int[n][n][2];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < 2; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }

        return findNumberOfWaysRecursiveHelper(0, n-1, 1, expr, dp);
        // TC -> O(n x n x 2 x n)
        // SC -> O(n x n x 2) + O(n)
    }

    private static int findNumberOfWaysTabulation(String expr){
        int n = expr.length();
        int[][][] dp = new int[n][n][2];

        // base case
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(i > j){
                    dp[i][j][0] = 0;
                    dp[i][j][1] = 0; 
                }
            }
        }

        // when i == j
        for (int i = 0; i < n; i++) {
            dp[i][i][0] = (expr.charAt(i) == 'F') ? 1 : 0;
            dp[i][i][1] = (expr.charAt(i) == 'T') ? 1 : 0;
        }

        for (int i = n-1; i >= 0; i--) {
            for (int j = i+1; j < n; j++) {
                for (int isTrue = 0; isTrue <= 1; isTrue++) {

                    int ans = 0;
                    for (int ind = i+1; ind < j; ind = ind+2) {
                        int leftTrue = dp[i][ind-1][1];
                        int leftFalse = dp[i][ind-1][0];
                        int rightTrue = dp[ind+1][j][1];
                        int rightFalse = dp[ind+1][j][0];
                        if(isTrue == 1){
                            if(expr.charAt(ind) == '&'){
                                ans += (leftTrue * rightTrue);

                            }else if(expr.charAt(ind) == '|'){
                                ans += (leftTrue * rightFalse) + (leftTrue * rightTrue) + 
                                (leftFalse * rightTrue);
                            }else{
                                ans += (leftFalse * rightTrue) + (leftTrue * rightFalse);
                            }
                        }else{
                            if(expr.charAt(ind) == '&'){
                                ans += (leftFalse * rightTrue) + (leftTrue * rightFalse) + 
                                (leftFalse * rightFalse);

                            }else if(expr.charAt(ind) == '|'){
                                ans += (leftFalse * rightFalse);
                            }else{
                                ans += (leftFalse * rightFalse) + (leftTrue * rightTrue);
                            }
                        }

                    }  
                    dp[i][j][isTrue] = ans; 
                }
                
            }
        }

        return dp[0][n-1][1];
        // TC -> O(n x n x 2 x n)
        // SC -> O(n x n x 2)
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String expr = sc.nextLine();
        System.out.println(findNumberOfWaysRecursive(expr));
        System.out.println(findNumberOfWaysTabulation(expr));
        sc.close();
    } 
}
