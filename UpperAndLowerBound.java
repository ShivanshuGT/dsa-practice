import java.util.Scanner;

public class UpperAndLowerBound {
    
    private static int lowerBound(int arr[], int target){
        int n = arr.length;
        int ans = n;
        int low = 0;
        int high = n-1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if(arr[mid] >= target){
                ans = mid;
                high = mid - 1;
            }else{
                low = mid + 1;
            }
        }
        return ans;

        // TC -> O(logn)
        // SC -> O(1)
    }

    private static int upperBound(int arr[], int target){
        int n = arr.length;
        int ans = n;
        int low = 0;
        int high = n-1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if(arr[mid] > target){
                ans = mid;
                high = mid - 1;
            }else{
                low = mid + 1;
            }
        }
        return ans;
    }
    public static void main(String[] args) {

        
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int target = sc.nextInt();
        int arr[] = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.println(lowerBound(arr, target));
        System.out.println(upperBound(arr, target));
        sc.close();
    }
}
