import java.util.Scanner;

public class BinarySearch {

    private static int binarySearchIterative(int arr[], int target){
        int n = arr.length;
        int low = 0;
        int high = n-1;
        while (low <= high) {
            int mid = (low + high) / 2;
            // to avoid overflow we can also use
            // int mid = low + (high - low) / 2;
            if(arr[mid] == target){
                return mid;
            }
            else if (arr[mid] > target){
                high = mid - 1;
            }else{
                low = mid + 1;
            }
        }
        return -1;

        // TC -> O(logn) where base of log is 2
        // SC -> O(1)
    }

    private static int binarySearchRecursive(int arr[], int low, int high, int target){
        if (low > high){
            return -1;
        }
        int mid = (low + high) / 2;
        if(arr[mid] == target){
            return mid;
        }
        if(arr[mid] > target){
            return binarySearchRecursive(arr, low, mid-1, target);
        }
        return binarySearchRecursive(arr, mid+1, high, target);
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int arr[] = new int[n];
        int target = sc.nextInt();
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // int index = binarySearchIterative(arr, target);
        int index = binarySearchRecursive(arr,0, n-1, target);
        if(index != -1){
            System.out.println("Element found at index " + index);
        }else{
            System.out.println("Element is not present in the given array");
            
        }
        sc.close();
    }
}
