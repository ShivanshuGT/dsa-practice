import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MergeSort {

    private static void mergeSort(int arr[], int beg, int end){
        if(beg == end){
            return;
        }
        int mid = (beg + end) /2;
        mergeSort(arr, beg, mid);
        mergeSort(arr, mid + 1, end);
        merge(arr, beg, mid, end);
    }

    private static void merge(int arr[], int beg, int mid, int end){
        List<Integer> temp = new ArrayList<>();
        int left = beg;
        int right = mid + 1;

        while(left <= mid && right <= end){
            if(arr[left] <= arr[right]){
                temp.add(arr[left]);
                left++;
            }else{
                temp.add(arr[right]);
                right++;
            }
        }

        while(left <= mid){
            temp.add(arr[left]);
            left++;
        }

        while (right <= end) {
            temp.add(arr[right]);
            right++;
        }

        // copying the values from temp to arr
        for(int i = beg; i <= end; i++){
            arr[i] = temp.get(i - beg);
        }
    }
 
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int arr[] = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        mergeSort(arr, 0, n-1);
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }


        sc.close();
    }
}
