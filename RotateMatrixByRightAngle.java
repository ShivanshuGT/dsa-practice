import java.util.Scanner;

public class RotateMatrixByRightAngle {

    public static int[][] rotatematrixByRightAngleBrute(int matrix[][]){
        int n = matrix[0].length;
        int ans[][] = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ans[j][n-i-1] = matrix[i][j];
            }
        }

        return ans;

        // TC -> O(n x n)
        // SC -> O(n x n)
    }

    public static void rotatematrixByRightAngleOptimal(int matrix[][]){
        int n = matrix[0].length;

        // transposing the matrix
        for (int i = 0; i < n-1; i++) {
            for(int j = i+1 ; j < n ; j++){
                int temp = matrix[j][i];
                matrix[j][i] = matrix[i][j];
                matrix[i][j] = temp;
            }
        }

        // reversing each row
        for (int i = 0; i < n; i++) {
            reverseRow(matrix[i]);
        }

        // TC -> O(n x n)
        // SC -> O(1)
    }

    public static void reverseRow(int arr[]){
        int n = arr.length;
        int left = 0;
        int right = n-1;
        while(left <= right){
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left += 1;
            right -= 1;
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int matrix[][] = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }

        // int ans[][] = rotatematrixByRightAngleBrute(matrix);
        // for (int i = 0; i < n; i++) {
        //     for (int j = 0; j < n; j++) {
        //         System.out.print(ans[i][j]+ " ");
        //     }
        //     System.out.println();
        // }
        rotatematrixByRightAngleOptimal(matrix);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        sc.close();
    }
}
