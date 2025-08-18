import java.util.List;
import java.util.Scanner;

public class BinarySearchInMatrixPartTwo {

    private static List<Integer> findElementOptimal(int mat[][], int target){
        int n = mat.length;
        int m = mat[0].length;
        int row = 0;
        int col = m - 1;
        while ((row < n) && (col >= 0) ) {
            int element = mat[row][col];
            if(element == target){
                return List.of(row, col);
            }else if(element > target){
                col -= 1;
            }else{
                row += 1;
            }
        }
        return List.of(-1, -1);

        // TC -> O(n + m)
        // SC -> O(1)
    }

    private static List<Integer> findElementBetter(int mat[][], int target){
        int n = mat.length;
        int m = mat[0].length;
        for (int i = 0; i < n; i++) {
            if(mat[i][0] <= target && target <= mat[i][m-1]){
                int col = binarySearch(mat[i], target);
                if(col != -1){
                    return List.of(i, col);
                }
            }
        }
        return List.of(-1, -1);
        // TC -> O(n x log(m))
        // SC -> O(1)

    }

    private static int binarySearch(int arr[], int target){
        int beg = 0;
        int end = arr.length - 1;
        while (beg <= end) {
            int mid = (beg + end) / 2;
            if(arr[mid] == target){
                return mid;
            }else if(arr[mid] > target){
                end = mid - 1;
            }else{
                beg = mid + 1;
            }
        }
        return -1;

        // TC -> O(log(n))
        // SC -> O(1)
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int target = sc.nextInt();
        int mat[][] = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                mat[i][j] = sc.nextInt();
            }
        }
        System.out.println(findElementBetter(mat, target));
        System.out.println(findElementOptimal(mat, target));
        sc.close();
    }
}
