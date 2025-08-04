import java.util.Scanner;

public class MinimumInRotatedSortedArray {

    private static int findMinimum(int arr[]){
        // when the array has unique elements
        int n = arr.length;
        int result = Integer.MAX_VALUE;
        int beg = 0;
        int end = n-1;
        while (beg <= end) {
            int mid = (beg + end) / 2;
            if(arr[beg] <= arr[end]){

                // this means the entire search space is sorted
                result = Math.min(result, arr[beg]);
                return result;
            }

            // check which of the half is sorted
            if(arr[beg] <= arr[mid]){
                // left half is sorted
                result = Math.min(result, arr[beg]);
                beg = mid + 1;
            }else{
                // right half is sorted
                result = Math.min(result, arr[mid]);
                end = mid - 1;
            }
        }
        return result;

        // TC -> O(logn)
        // SC -> O(1)
    }

    private static int findMinimumWhenArrayHasDuplicates(int arr[]){
        int result = Integer.MAX_VALUE;
        int beg = 0;
        int n = arr.length;
        int end = n-1;
        while (beg <= end) {
            int mid = (beg + end) / 2;
            if(arr[beg] == arr[mid] && arr[mid] == arr[end]){
                result = Math.min(result, arr[beg]);
                beg = beg + 1;
                end = end - 1;
                continue;
            }


            // check which of the half is sorted
            if(arr[beg] <= arr[mid]){
                // left half is sorted
                result = Math.min(result, arr[beg]);
                beg = mid + 1;
            }else{
                // right half is sorted
                result = Math.min(result, arr[mid]);
                end = mid - 1;
            }
        }
        return result;

        // TC -> O(logn) in best and average case
        // TC -> O(n/2) in worst case , when there are huge number of duplicates in the array
        // example : arr[] = [3,3,3,3,3,3]
        // SC -> O(1)
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int arr[] = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // System.out.println("Minimum element is "+ findMinimum(arr));
        System.out.println("Minimum element is "+ findMinimumWhenArrayHasDuplicates(arr));
        sc.close();
    }
}
