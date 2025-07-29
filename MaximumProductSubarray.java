import java.util.Scanner;

public class MaximumProductSubarray {

    private static int maximumProductSubarrayBrute(int arr[]){
        int n = arr.length;
        int result = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int product = 1;
                for (int k = i; k <= j; k++) {
                    product *= arr[k];
                }
                result = Math.max(result, product);
            }
        }
        return result;
        // TC -> O(nxnxn)
        // SC -> O(1)
    }

    private static int maximumProductSubarrayBetter(int arr[]){
        int n = arr.length;
        int result = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int product = 1;
            for (int j = i; j < n; j++) {
                product *= arr[j];
                result = Math.max(result, product);
            }
        }

        return result;
        // TC -> O(nxn)
        // SC -> O(1)
    }

    private static int maximumProductSubarrayOptimal(int arr[]){
        int prefix = 1;
        int suffix = 1;
        int result = Integer.MIN_VALUE;
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            if (prefix == 0){
                // 0 was encountered in the previous iteration
                prefix = 1;
            }

            if (suffix == 0){
                // 0 was encountered in the previous iteration
                suffix = 1;
            }

            prefix = prefix * arr[i];
            suffix = suffix * arr[n-i-1];
            result = Math.max(result, Math.max(prefix, suffix));
        }
        return result;

        // TC -> O(n)
        // SC -> O(1)
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int arr[] = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        // System.out.println(maximumProductSubarrayBrute(arr));
        // System.out.println(maximumProductSubarrayBetter(arr));
        System.out.println(maximumProductSubarrayOptimal(arr));
        sc.close();
    }
}
