import java.util.Scanner;

public class FloorAndCeil {


    private static void floorOfTarget(int arr[], int target){
        // largest number that is <= target
        int n = arr.length;
        int result = n;
        int low = 0;
        int high = n-1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if(arr[mid] <= target){
                result = mid;
                low = mid + 1;
            }else{
                high = mid - 1;
            }
        }
        if(result == n){
            System.out.println("There is no floor value of the target");
        }else{
            System.out.println("The floor of target is " + arr[result]);
        }

        // TC -> O(logn)
        // SC -> O(1)
    }

    private static void ceilOfTarget(int arr[], int target){
        // smallest number >= target
        int result = lowerBound(arr, target);
        if(result == arr.length){
            System.out.println("There is no ceil value of target");
        }else{
            System.out.println("The ceil value if target is " + arr[result]);
        }

        // TC -> O(logn)
        // SC -> O(1)
    }

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

    }
    

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int target = sc.nextInt();
        int arr[] = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        floorOfTarget(arr, target);
        ceilOfTarget(arr, target);
        sc.close();
    }
}
