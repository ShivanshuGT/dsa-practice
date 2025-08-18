import java.util.Scanner;

public class BinarySearchinMatrix {

    private static boolean findElementBrute(int mat[][], int target){
        int n = mat.length;
        int m = mat[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(mat[i][j] == target){
                    return true;
                }
            }
        }
        return false;
        // TC -> O(n x m)
        // SC -> O(1)
    }

    private static boolean binarySearch(int arr[], int target){
        int n = arr.length;
        int beg = 0;
        int end = n-1;
        while (beg <= end) {
            int mid = (beg + end) / 2;
            if(arr[mid] == target){
                return true;
            }else if(arr[mid] < target){
                beg = mid + 1;
            }else{
                end = mid - 1;
            }
        }
        return false;

        // TC -> O(log(n))
        // SC -> O(1)

    }

    private static boolean findElementBetter(int mat[][], int target){
        int n = mat.length;
        int m = mat[0].length;
        for (int i = 0; i < n; i++) {
            if((mat[i][0] <= target) && (target <= mat[i][m-1])){
                if(binarySearch(mat[i], target)){
                    return true;
                }
            }
        }
        return false;

        // TC -> O(n x log(m))
        // SC- > O(1)
    }

    private static boolean findElementOptimal(int mat[][], int target){
        int n = mat.length;
        int m = mat[0].length;
        int beg = 0;
        int end = n*m - 1;
        while (beg <= end) {
            int mid = (beg + end) / 2;
            int row = mid / m;
            int col = mid % m;
            int elementAtMid = mat[row][col];
            if(elementAtMid == target){
                return true;
            }else if(elementAtMid < target){
                beg = mid + 1;
            }else{
                end = mid - 1;
            }
        }
        return false;

        // TC -> O(log(n x m))
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
        System.out.println(findElementBrute(mat, target));
        System.out.println(findElementBetter(mat, target));
        System.out.println(findElementOptimal(mat, target));
        sc.close();
    }
}
