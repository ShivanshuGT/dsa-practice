import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class MedianInRowwiseSortedMatrix {

    private static int findMedianBrute(int mat[][]){
        ArrayList<Integer> list = new ArrayList<>();
        int n = mat.length;
        int m = mat[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                list.add(mat[i][j]);
            }
        }
        Collections.sort(list);
        return list.get((n * m)/2);

        // TC -> O(n x m) + O(n+m)log(n+m)
        // SC -> O(1)
    }

    private static int findMax(int mat[][]){
        int result = Integer.MIN_VALUE;
        int n = mat.length;
        int m = mat[0].length;
        for (int i = 0; i < n; i++) {
            if(result < mat[i][m-1]){
                result = mat[i][m-1];
            }
        }
        return result;
        // TC -> O(n)
        // SC -> O(1)
    }

    private static int findMin(int mat[][]){
        int n = mat.length;
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if(result > mat[i][0]){
                result = mat[i][0];
            }
        }
        return result;
        // TC -> O(n)
        // SC -> O(1)
    }

    private static int upperBound(int arr[], int target){
        int n = arr.length;
        int result = n;
        int beg = 0;
        int end = n-1;
        while (beg <= end) {
            int mid = (beg + end) / 2;
            if(arr[mid] > target){
                result = mid;
                end = mid - 1;
            }else{
                beg = mid + 1;
            }
        }
        return result;

        // TC -> O(log(n))
        // SC -> O(1)
    }


    private static int countElements(int mat[][], int target){
        int result = 0;
        int n = mat.length;
        for (int i = 0; i < n; i++) {
            result += upperBound(mat[i], target);
        }
        return result;
        // TC -> O(n x log(m))
        // SC -> O(1)
    }
    

    private static int findMedianOptimal(int mat[][]){
        int n = mat.length;
        int m = mat[0].length;
        int beg = findMin(mat);
        int end = findMax(mat);
        int result = -1;
        while (beg <= end) {
            int mid = (beg + end) / 2;
            int elements = countElements(mat, mid);
            if(elements > ((n * m)/2)){
                result = mid;
                end = mid - 1;
            }else{
                beg = mid + 1;
            }
        }

        return result;

        // In worst case the minimum value in the matrix can be 1
        // and the maximum value can be 10^9
        // TC -> O(log(10^9)) x O(n x log(m))
        // SC- > O(1)
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
        System.out.println(findMedianBrute(mat));
        System.out.println(findMedianOptimal(mat));
        sc.close();
    }
}
