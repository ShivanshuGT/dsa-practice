import java.util.List;
import java.util.Scanner;

public class FindPeakElementPartTwo {

    private static int findMaxInCol(int mat[][], int col){
        int n = mat.length;
        int maxValue = Integer.MIN_VALUE;
        int maxValueIndex = -1;
        for (int i = 0; i < n; i++) {
            if(mat[i][col] > maxValue){
                maxValue = mat[i][col];
                maxValueIndex = i;
            }
        }
        return maxValueIndex;
        // TC -> O(n)
        // SC -> O(1)
    }

    private static List<Integer> findPeakElement(int mat[][]){
        int n = mat.length;
        int m = mat[0].length;
        int beg = 0;
        int end = m-1;
        while (beg <= end) {
            int mid = (beg + end) / 2;
            int row = findMaxInCol(mat, mid);
            int element = mat[row][mid];
            int leftElement = (mid-1) >= 0 ? mat[row][mid-1] : -1;
            int rightElement = (mid+1) < m ? mat[row][mid+1] : -1;
            if((element > leftElement) && (element > rightElement)){
                return List.of(row, mid);
            }else if(element < leftElement){
                // we are on the declining side of the peak
                end = mid - 1;
            }else{
                // we are on the inclining side of the peak
                beg = mid + 1;
            }
        }
        return List.of(-1, -1);

        // TC -> O(logm x n)
        // SC -> O(1)
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int mat[][] = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                mat[i][j] = sc.nextInt();
            }
        }
        System.out.println(findPeakElement(mat));
        sc.close();
    }
}
