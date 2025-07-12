import java.util.Scanner;

public class SetMatrixZeroes {

    public static void setMatrixZeroesBrute(int n , int m,int matrix[][]){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(matrix[i][j] != 0){
                    if(check_row(i, m, matrix) || check_col(j, n, matrix)){
                        matrix[i][j] = -1;
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(matrix[i][j] == -1){
                    matrix[i][j] = 0;
                }
            }
            
        }

        // TC -> O(n x n x n)
        // SC -> O(1)
    }

    public static boolean check_row(int i, int m, int matrix[][]){
        for (int k = 0; k < m; k++) {
            if(matrix[i][k] == 0)
                return true;
        }
        return false;
    }

    public static boolean check_col(int j, int n, int matrix[][]){
        for (int k = 0; k < n; k++) {
            if(matrix[k][j] == 0)
                return true;
        }
        return false;
    }

    public static void setMatrixZeroesBetter(int n , int m , int matrix[][]){
        int horizontal[] = new int[m];
        int vertical[] = new int[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(matrix[i][j] == 0){
                    horizontal[j] = 1;
                    vertical[i] = 1;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(matrix[i][j] != 0){
                    if(horizontal[j] ==1 || vertical[i] == 1){
                        matrix[i][j] = 0;
                    }
                }
            }
        }

        // TC -> O(n x n)
        // SC -> O(n)
    }

    public static void setMatrixZeroesOptimal(int n , int m, int matrix[][]){
        int horz0 = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(matrix[i][j] == 0){
                    if(j == 0){
                        horz0 = 0;
                    }else{
                        matrix[0][j] = 0;
                    }
                    matrix[i][0] = 0;
                }
            }
        }

        // submatrix
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if(matrix[i][j] != 0){
                    if(matrix[i][0] == 0 || matrix[0][j] == 0){
                        matrix[i][j] = 0;
                    }
                }
            }
        }

        // first row from index 1 to the end
        if(matrix[0][0] == 0){
            for (int j = 1; j < m; j++) {
                    matrix[0][j] = 0;
            }
        }

        // first column from index 0 to the end
        if(horz0 == 0){
            for (int i = 0; i < n; i++) {
                matrix[i][0] = 0;
            }
        }

        // TC -> O(n x n)
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

        // setMatrixZeroesBrute(n, m, matrix);
        // setMatrixZeroesBetter(n, m, matrix);
        setMatrixZeroesOptimal(n, m, matrix);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }

        sc.close();


    }
}
