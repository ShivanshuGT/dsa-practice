import java.util.Scanner;

public class BubbleSort {

    private static void bubbleSort(int n, int arr[]){
        boolean anySwapDone = false;
        for(int i = 0; i < n-1 ; i++){
            for(int j = 0; j < n-i-1; j++){
                if(arr[j] > arr[j+1]){
                    swap(arr, j, j+1);
                    anySwapDone = true;
                }
            }

            // if no swap happens during the first check of the array, it means all 
            // the values are already in sorted order
            // in that case we should not check in further loops
            if(!anySwapDone){
                break;
            }
        }


    }

    private static void swap(int arr[], int l, int r){
        int temp = arr[l];
        arr[l] = arr[r];
        arr[r] = temp;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int arr[] = new int[n];

        for(int i = 0; i< n; i++){
            arr[i] = sc.nextInt();
        }

        bubbleSort(n, arr);
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        sc.close();
    }
}
