import java.util.Scanner;

public class QuickSort {

    private static void quickSort(int arr[], int beg, int end){
        if(beg >= end)
            return;
        int pivotElement = arr[beg];
        int i = beg+1;
        int j = end;
        while (j >= i) {
            while(i <= end && arr[i] <= pivotElement){
                i+=1;
            }
            while(j >= beg && arr[j] > pivotElement){
                j-=1;
            }
            
            if( j >= i){
                swap(arr, i, j);
            }
        }
        swap(arr, beg, j);
        quickSort(arr, beg, j-1);
        quickSort(arr, j+1, end);
    }

    private static void quickSortDes(int arr[], int beg, int end){
        if(beg >= end)
            return;
        int pivotElement = arr[beg];
        int i = beg+1;
        int j = end;
        while (j >= i) {
            while(i <= end && arr[i] >= pivotElement){
                i+=1;
            }
            while(j >= beg && arr[j] < pivotElement){
                j-=1;
            }
            
            if( j >= i){
                swap(arr, i, j);
            }
        }
        swap(arr, beg, j);
        quickSortDes(arr, beg, j-1);
        quickSortDes(arr, j+1, end);
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

        for(int i = 0; i< n ; i++){
            arr[i] = sc.nextInt();
        }

        quickSortDes(arr, 0, n-1);
        for(int i = 0; i< n ; i++){
            System.out.print(arr[i] + " ");
        }
        sc.close();
    }
}
