import java.util.Scanner;

public class SpiralMatrixTraversal {

    public static void traverseMatrixInSpiralOrder(int matrix[][]){
        int n = matrix.length;
        int m = matrix[0].length;

        int top = 0;
        int bottom = n-1;
        int left = 0;
        int right = m-1;

        while (top <= bottom && left <= right) {
            
            for (int i = left; i <= right; i++) {
                System.out.print(matrix[top][i]+ " ");
            }
            top += 1;

            for (int i = top; i <= bottom; i++) {
                System.out.print(matrix[i][right] + " ");
            }
            right -= 1;

            if(top <= bottom){
                for (int i = right; i >= left; i--) {
                    System.out.print(matrix[bottom][i] + " ");
                }
                bottom -= 1;
            }

            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    System.out.print(matrix[i][left] + " ");
                }
                left += 1;
            }
        }

        // TC -> O(n x m)
        // SC -> O(1)
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int matrix[][] = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }

        traverseMatrixInSpiralOrder(matrix);

        sc.close();
    }
}
