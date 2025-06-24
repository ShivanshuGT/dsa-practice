import java.util.Scanner;

public class InsertionSort {

    private static void insertionSort(int n, int arr[]){
        for(int i = 1; i < n; i++){
            for(int j = i; j > 0; j--){
                if(arr[j] < arr[j-1]){
                    swap(arr, j, j-1);
                }else{
                    break;
                }

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

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        insertionSort(n, arr);

        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }

        sc.close();
    }
}
