import java.util.Scanner;

public class FloodFillAlgorithm {

    private static void dfsTraversal(int currentRow, int currentCol, int[][] answer, int newColor, int initialColor){
        int n = answer.length;
        int m = answer[0].length;
        answer[currentRow][currentCol] = newColor;
        int[] delRow = {1,0,0,-1};
        int[] delCol = {0,1,-1,0};
        for (int i = 0; i < 4; i++) {
            int row = currentRow + delRow[i];
            int col = currentCol + delCol[i];
            if(row>=0 && row<n && col>=0 && col<m){
                // it is a valid row and column
                if(answer[row][col] == initialColor && answer[row][col] != newColor){
                    // it needs to be coloured and it is not visited earlier
                    dfsTraversal(row, col, answer, newColor, initialColor);
                }
            }
            
        }
    }

    private static int[][] floodFill(int[][] matrix, int startRow, int startCol, int newColor){

        int[][] answer = matrix;
        int initialColor = answer[startRow][startCol];
        dfsTraversal(startRow, startCol, answer, newColor, initialColor);
        return answer;
        // SC -> O(n x m)
        // TC -> O(n x m)

    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int startRow = sc.nextInt();
        int startCol = sc.nextInt();
        int newColor = sc.nextInt();
        int matrix[][] = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }
        int[][] answer = floodFill(matrix, startRow, startCol, newColor);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(answer[i][j] + " ");
            }
            System.out.println();
        }
        sc.close();
    }   
}
