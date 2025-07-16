import java.util.Scanner;

public class PascalTriangle {

    public static int findncr(int n, int r){
        int result = 1;
        for (int i = 1; i <=r ; i++) {
            result *= (n-i+1);
            result /= i;
        }
        return result;
        // TC -> O(r)
        // SC -> O(1)
    }

    public static void printPascalTriangleElementOptimal(int row, int col){
        System.out.print(findncr(row-1, col-1)+ " ");
        // TC -> O(r)
        // SC -> O(1)
    }

    public static void printPascalTriangleRowBrute(int row){
        for (int i = 1; i <= row; i++) {
            printPascalTriangleElementOptimal(row, i);
        }

        // TC -> O(n x r)
        // SC -> O(1)
    }
    
    public static void printPascalTriangleRowOptimal(int row){
        int res = 1;
        System.out.print(res+ " ");
        for (int i = 1; i < row; i++) {
            res *= (row-i);
            res /= (i);
            System.out.print(res + " ");
        }

        // TC -> O(n)
        // SC -> O(1)
    }

    public static void printEntirePascalTriangleOptimal(int n){
        for (int i = 1; i <= n; i++) {
            printPascalTriangleRowOptimal(i);
            System.out.println();
        }

        // TC -> O(n x n)
        // SC -> O(1)
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // int row = sc.nextInt();
        // int col = sc.nextInt();
        // printPascalTriangleElementOptimal(row, col);

        // int row = sc.nextInt();
        // printPascalTriangleRowBrute(row);
        // printPascalTriangleRowOptimal(row);

        int n = sc.nextInt();
        printEntirePascalTriangleOptimal(n);
        sc.close();
    }
}
