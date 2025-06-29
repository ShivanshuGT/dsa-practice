import java.util.Scanner;

public class RightRotate {

    private static void rotateRightBrute(int arr[], int d){
        int n = arr.length;
        d = d % n;
        int temp[] = new int[n];

        for(int i = n-d; i<n; i++){
            temp[i-(n-d)] = arr[i];
        }

        for(int i =n-d-1; i>=0; i --){
            arr[i+d] = arr[i];
        }

        for(int i=0;i<d; i++){
            arr[i] = temp[i];
        }

    }

    private static void rotateRightOptimal(int arr[], int d){
        int n = arr.length;
        d = d % n;
        reverseSubArray(arr, 0, n-d-1);
        reverseSubArray(arr, n-d, n-1);
        reverseSubArray(arr, 0, n-1);
    }

    private static void reverseSubArray(int arr[], int beg, int end){
        int left = beg;
        int right = end;
        while(left < right){
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            right -= 1;
            left += 1;
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

        // rotateRightBrute(arr, d);
        rotateRightOptimal(arr, d);

        for (int i = 0; i < n; i++) {
            System.out.print(arr[i]+ " "); 
        }


        sc.close();
    }
}
