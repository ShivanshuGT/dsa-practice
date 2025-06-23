import java.util.Scanner;

public class SelectionSort {

    private static void selectionSort(int n, int[] arr){

        for(int i = 0; i< n -1 ; i++){
            int minIndex = i;
            for(int j = i; j < n ; j ++){
                if(arr[j] < arr[minIndex]){
                    minIndex = j;
                }
            }
            swap(arr, i, minIndex);
        }
    }

    private static void swap(int arr[],int l, int r){
        int temp = arr[l];
        arr[l] = arr[r];
        arr[r] = temp;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int arr[] = new int[n];

        for(int i = 0; i < n; i++){
            arr[i] = sc.nextInt();
        }
        
        selectionSort(n,arr);
        
        for(int i = 0; i < n; i++){
            System.out.print(arr[i] + " ");
        }

        sc.close();
    }
}
