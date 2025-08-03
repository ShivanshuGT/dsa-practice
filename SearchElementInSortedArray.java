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

    private static boolean checkIfElementIsPresentIfArrayHasDuplicates(int arr[], int target){
        int n = arr.length;
        int beg = 0;
        int end = n-1;
        while (beg <= end) {
            int mid = (beg + end) / 2;
            if(arr[mid] == target){
                return true;
            }

            if(arr[mid] == arr[beg] && arr[mid] == arr[end]){
                beg = beg + 1;
                end = end - 1;
                continue;
            }

            if(arr[mid] <= arr[end]){
                if(arr[mid] <= target && target <= arr[end]){
                    beg = mid + 1;
                }
                else{
                    end = mid - 1;
                }
            }else{
                if(arr[beg] <= target && target <= arr[mid]){
                    end = mid - 1;
                }else{
                    beg = mid + 1;
                }
            }
        }

        return false;

        // TC -> O(logn) -> best and average case
        // TC -> O(n/2) -> worst case, when there are huge number of duplicates in the array
        // like arr[] = [3,3,3,3,3,3,3] and target = 1
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

        // System.out.println("Element found at index " + findElement(arr, target));
        boolean isPresent = checkIfElementIsPresentIfArrayHasDuplicates(arr, target);
        if(isPresent){
            System.out.println("Element is present in the array.");
        }else{
            
            System.out.println("Element is not present in the array.");
        }
        sc.close();
    }
}
