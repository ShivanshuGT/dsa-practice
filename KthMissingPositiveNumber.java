import java.util.Scanner;

public class KthMissingPositiveNumber {

    private static int findKthMissingPositiveNumberBrute(int arr[], int k){
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            if(arr[i] <= k){
                k += 1;
            }else{
                break;
            }
        }
        return k;

        // TC -> O(n)
        // SC -> O(1)
    }

    private static int findKthMissingPositiveNumberOptimal(int arr[], int k){
        int n = arr.length;
        int beg = 0;
        int end = n-1;
        while (beg <= end) {
            int mid = (beg + end) / 2;
            int missingAtMid = arr[mid] - (mid + 1);
            if(missingAtMid < k){
                beg = mid + 1;
            }else{
                end = mid - 1;
            }
        }
        return k + end + 1;

        // TC -> O(logn)
        // SC -> O(1)
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int arr[] = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        // System.out.println(findKthMissingPositiveNumberBrute(arr, k));
        System.out.println(findKthMissingPositiveNumberOptimal(arr, k));
        sc.close();
    }
}
