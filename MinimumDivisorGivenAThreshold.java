import java.util.Scanner;

public class MinimumDivisorGivenAThreshold {

    private static int findMaxValue(int arr[]){
        int result = Integer.MIN_VALUE;
        for (int i : arr) {
            if(result < i){
                result = i;
            }
        }
        return result;

        // TC -> O(n)
        // SC -> O(1)
    }

    private static int calculateSumOfDivision(int arr[], int den){
        int result = 0;
        for (int i : arr) {
            result += Math.ceilDiv(i, den);
        }
        return result;
        // TC -> O(n)
        // SC -> O(1)
    }

    private static int findMinimumDivisorOptimal(int arr[], int threshold){
        int n = arr.length;
        int result = -1;
        if(n > threshold){
            return -1;
        }

        int beg = 1;
        int end = findMaxValue(arr);
        while (beg <= end) {
            int mid = (beg + end) / 2;
            if(calculateSumOfDivision(arr, mid) <= threshold){
                result = mid;
                end = mid - 1;
            }else{
                beg = mid + 1;
            }
        }
        return result;

        // TC -> O(log(maxElement) x n)
        // SC -> O(1)
    }

    private static int findMinimumDivisorBrute(int arr[], int threshold){
        int n = arr.length;
        if(n > threshold){
            return -1;
        }
        int maxElement = findMaxValue(arr);

        for (int i = 1; i <= maxElement; i++) {
            if(calculateSumOfDivision(arr, i) <= threshold){
                return i;
            }
        }
        return -1;

        // TC -> O(maxElement x n)
        // SC -> O(1)
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int threshold = sc.nextInt();
        int arr[] = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        // System.out.println(findMinimumDivisorBrute(arr, threshold));
        System.out.println(findMinimumDivisorOptimal(arr, threshold));
        sc.close();
    }
}
