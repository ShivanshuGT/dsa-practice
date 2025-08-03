import java.util.Scanner;

public class SearchElementInSortedArray {

    private static int findElement(int arr[], int target){

        // when there are unique elements in the array
        int n = arr.length;
        int beg = 0;
        int end = n-1;
        while (beg <= end) {
            int mid = (beg + end) / 2;
            if(arr[mid] == target){
                return mid;
            }

            // check which half is sorted
            if(arr[mid] <= arr[end]){
                // right half is sorted
                if(arr[mid] <= target && target <= arr[end]){
                    beg = mid + 1;
                }else{
                    end = mid - 1;
                }
            }else{
                // left half is sorted
                if(arr[beg] <= target && target <= arr[mid]){
                    end = mid - 1;
                }else{
                    beg = mid + 1;
                }
            }
        }
        return -1;

        // TC -> O(logn)
        // SC -> O(1)
    }
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int target = sc.nextInt();
        int arr[] = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.println("Element found at index " + findElement(arr, target));
        sc.close();
    }
}
