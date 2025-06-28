import java.util.Scanner;

public class LeftRotateArray {

    private static void leftRotateBrute(int arr[], int d){
        int n = arr.length;
        d = d % n;
        int temp[] = new int[d];


        // TC -> O(d)
        for(int i = 0; i< d; i++){
            temp[i] = arr[i];
        }

        // TC -> O(n-d)
        for(int i = d; i < n ; i++){
            arr[i-d] = arr[i];
        }

        // TC -> O(d)
        for(int i = n-d ; i< n; i++){
            arr[i] = temp[i-(n-d)];
        }

        // TC -> O(n+d)
        // SC -> O(d)

    }

    private static void leftRotateOptimal(int arr[], int d){
        int n = arr.length;
        d = d % n;
        reverseSubArr(arr, 0, d-1); // TC -> O(d)
        reverseSubArr(arr, d, n-1);     // TC -> O(n-d)
        reverseSubArr(arr, 0, n-1); // TC -> O(n)

        // TC -> O(2n)
        // SC -> O(1)

    }

    private static void reverseSubArr(int arr[], int beg, int end){
        int left = beg;
        int right = end;
        while (left < right) {
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
        int d = sc.nextInt();
        int arr[] = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }


        // leftRotateBrute(arr, d);
        leftRotateOptimal(arr, d);
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }


        sc.close();
    }
}
