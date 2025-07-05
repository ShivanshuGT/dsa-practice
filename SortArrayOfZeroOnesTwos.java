import java.util.Scanner;

public class SortArrayOfZeroOnesTwos {
   
    public static void sortBrute(int arr[]){
        // we can use any sorting algo
        // TC -> O(n.log(n))
    }

    public static void sortBetter(int arr[]){
        int n = arr.length;
        int cZero = 0;
        int cOne = 0;
        int cTwo = 0;
        for (int i = 0; i < n; i++) {
            if(arr[i] == 0){
                cZero += 1;
            }else if(arr[i] == 1){
                cOne += 1;
            }else{
                cTwo += 1;
            }
        }
        int k = 0;
        while (cZero !=0) {
            arr[k] = 0;
            cZero -= 1;
            k +=1;
        }
        while (cOne !=0) {
            arr[k] = 1;
            cOne -= 1;
            k +=1;
        }
        while (cTwo !=0) {
            arr[k] = 2;
            cTwo -= 1;
            k +=1;
        }

        // TC -> O(2n)
        // SC -> O(1)
    }

    public static void sortOptimal(int arr[]){
        int low = 0;
        int mid = 0;
        int high = arr.length - 1;
        while (mid <= high) {
            if(arr[mid] == 0){
                swap(arr, mid, low);
                low += 1;
                mid += 1;
            }else if(arr[mid] == 1){
                mid += 1;
            }else{
                swap(arr, mid, high);
                high -= 1;
            }
        }

        // TC -> O(n)
        // SC -> O(1)
    }

    public static void swap(int arr[], int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public static void main(String[] args) {

        
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int arr[] = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        // sortBetter(arr);
        sortOptimal(arr);
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] +" ");
        }
        sc.close();
    }
}
