import java.util.Scanner;

public class NumberOfTimesTheArrayIsRotated {

    private static int findNumberOfTimesTheArrayIsRotated(int arr[]){
        // when the array has unique elements
        int result = -1;
        int min = Integer.MAX_VALUE;
        int n = arr.length;
        int beg = 0;
        int end = n-1;
        while (beg <= end) {
            int mid = (beg + end) / 2;

            if(arr[beg] <= arr[end]){
                if(arr[beg] < min){
                    min = arr[beg];
                    result = beg;
                }
            }

            if(arr[beg] <= arr[mid]){
                if(arr[beg] < min){
                    min = arr[beg];
                    result = beg;
                }
                beg = mid + 1;
            }else{
                if(arr[mid] < min){
                    min = arr[mid];
                    result = mid;
                }
                end = mid - 1;
            }
        }
        return result;

        // TC -> O(logn)
        // SC -> O(1)
    }

    private static int findNumberOfTimesTheArrayIsRotatedIfArrayHasDuplicates(int arr[]){
        int n = arr.length;
        int beg = 0;
        int end = n-1;
        int min = Integer.MAX_VALUE;
        int result = -1;
        while (beg <= end) {
            int mid = (beg + end) / 2;
            if(arr[mid] == arr[beg] && arr[mid] == arr[end]){
                if(arr[beg] < min){
                    min = arr[beg];
                    result = beg;
                }
                beg = beg + 1;
                end = end - 1;
            }

            if(arr[beg] <= arr[mid]){
                if(arr[beg] < min){
                    min = arr[beg];
                    result = beg;
                }
                beg = mid + 1;
            }else{
                if(arr[mid] < min){
                    min = arr[mid];
                    result = mid;
                }
                end = mid - 1;
            }
        }
        return result;

        // TC -> O(logn) in best and average case
        // TC -> O(n/2) in worst case
        // SC -> O(1)
    }
    

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int arr[] = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        // System.out.println("The array has been rotated " + findNumberOfTimesTheArrayIsRotated(arr) + " times.");
        System.out.println("The array has been rotated " + findNumberOfTimesTheArrayIsRotatedIfArrayHasDuplicates(arr) + " times.");
        sc.close();
    }
}
