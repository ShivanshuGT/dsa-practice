import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class CountInversions {

    private static int countInversionsBrute(int arr[]){
        int n = arr.length;
        int result = 0;
        for (int i = 0; i < n-1; i++) {
            for (int j = i+1; j < n; j++) {
                if(arr[i] > arr[j]){
                    result += 1;
                }
            }
        }
        return result;
    }

    private static int mergeModified(int arr[], int beg, int mid, int end){
        int count = 0;
        int left = beg;
        int right = mid + 1;
        ArrayList<Integer> temp = new ArrayList<>();
        while (left <= mid && right <= end) {
            if(arr[left] > arr[right]){
                // condition of occurrence of inversion
                count += mid - left + 1;
                temp.add(arr[right]);
                right += 1;
            }else{
                temp.add(arr[left]);
                left += 1;
            }
        }

        while (left <= mid) {
            temp.add(arr[left]);
            left += 1;
        }

        while (right <= end) {
            temp.add(arr[right]);
            right += 1;
        }

        // transferring elements back into arr
        for(int i = beg; i <= end; i++){
            arr[i] = temp.get(i - beg);
        }
        return count;
    }

    private static int mergeSortModified(int arr[], int beg, int end){
        if(beg == end){
            return 0;
        }
        int count = 0;
        int mid = (beg + end) / 2;
        count += mergeSortModified(arr, beg, mid);
        count += mergeSortModified(arr, mid + 1, end);
        count += mergeModified(arr, beg, mid, end);
        return count;
    }

    private static int countInversionsOptimal(int arr[]){
        return mergeSortModified(arr, 0, arr.length - 1);

        // TC -> O(NlogN)
        // SC -> O(N)
    }
    

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int arr[] = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        // System.out.print("Number of inversions = " + countInversionsBrute(arr));
        System.out.print("Number of inversions = " + countInversionsOptimal(arr));
        sc.close();
    }
}
