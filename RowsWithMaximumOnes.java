import java.util.Scanner;

public class RowsWithMaximumOnes {

    private static int lowerBound(int arr[], int x){
        int n =  arr.length;
        int beg = 0;
        int end = n-1;
        int ans = n;
        while (beg <= end) {
            int mid = (beg + end) / 2;
            if(arr[mid] >= x){
                ans = mid;
                end = mid - 1;
            }else{
                beg = mid + 1;
            }
        }
        return ans;

        // TC -> O(log(n))
        // SC -> O(1)
    }

    private static int findRowWithMaximumOnesOptimal(int mat[][]){
        int counter = 0;
        int ans = -1;
        int n = mat.length;
        int m = mat[0].length;
        for (int i = 0; i < n; i++) {
            int numberOfOnes = m - lowerBound(mat[i], 1);
            if(numberOfOnes > counter){
                counter = numberOfOnes;
                ans = i;
            } 
        }
        return ans;

        // TC -> O(n x log(m))
        // SC- > O(1)
    }

    private static int findRowWithMaximumOnesBrute(int mat[][]){
        int n = mat.length;
        int m = mat[0].length;
        int ans = -1;
        int counter = 0;
        for (int i = 0; i < n; i++) {
            int numberOfOnes = 0;
            for (int j = 0; j < m; j++) {
                numberOfOnes += mat[i][j];
            }
            if(numberOfOnes > counter){
                counter = numberOfOnes;
                ans = i;
            }
        }
        return ans;

        // TC -> O(n x m)
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

        System.out.println(findRowWithMaximumOnesBrute(mat));
        System.out.println(findRowWithMaximumOnesOptimal(mat));
        sc.close();
    }
}
