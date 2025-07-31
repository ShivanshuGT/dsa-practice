import java.util.Scanner;

public class SearchInserPosition {

    private static int lowerBound(int arr[], int target){
        int n = arr.length;
        int result = n;
        int low = 0;
        int high = n-1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if(arr[mid] >= target){
                result = mid;
                high = mid - 1;
            }else{
                low = mid + 1;
            }
        }
        return result;

        // TC -> O(logn)
        // SC -> O(1)
    }

    private static int findInsertPosition(int arr[], int target){
        // the answer is lower bound of target
        return lowerBound(arr, target);
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
        System.out.println(findInsertPosition(arr, target));
        sc.close();
    }
}
