import java.util.Scanner;

public class CelebrityProblem {

    private static int findCelebrityBrute(int[][] mat){
        int n = mat.length;

        int[] knowsMe = new int[n];
        int[] iknow = new int[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(mat[i][j] == 1){
                    knowsMe[j] += 1;
                    iknow[i] += 1;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if((knowsMe[i] == n-1) && (iknow[i] == 0)){
                return i;
            }
        }
        return -1;
        // TC -> O(n x n) + O(n)
        // SC -> O(2 x n)
    }

    private static int findCelebrityOptimal(int[][] mat){
        int n = mat.length;

        int top = 0;
        int down = n-1;

        while (top < down) {
            if(mat[top][down] == 1){
                // top knows down -> top is not a celebrity
                top += 1;
            }else if(mat[down][top] == 1){
                // down knows top -> down is not a celebrity
                down -= 1;
            }else{
                // neither knows none
                // none of them is a celebrity
                top += 1;
                down -= 1;
            }
        }   


        if(top == down){
            // verify explicitly that top is a celebrity

            for (int i = 0; i < n; i++) {
                if(i == top){
                    // its the diagonal cell -> skip it
                    continue;
                }

                if(!((mat[i][top] == 1) && (mat[top][i] == 0))){
                    return -1;
                }
            }

            return top;
        }
        return -1;
        // TC -> O(2 x n)
        // SC -> O(1)
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] mat = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                mat[i][j] = sc.nextInt();
            }
        }
        System.out.println(findCelebrityBrute(mat));
        System.out.println(findCelebrityOptimal(mat));
        sc.close();
    }
}
