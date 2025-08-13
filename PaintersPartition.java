import java.util.Scanner;

public class PaintersPartition {

    private static int numberOfPaintersRequiredWithGivenMaximumCapacity(int arr[], int capacity){
        int n = arr.length;
        int i = 0;
        int numberOfPainters = 0;
        while (i < n) {
            int leftCapacity = capacity;
            while (i < n && arr[i] <= leftCapacity) {
                leftCapacity -= arr[i];
                i += 1;
            }
            numberOfPainters += 1;
        }
        return numberOfPainters;

        // TC -> O(n)
        // SC -> O(1)
    }

    private static int findMax(int arr[]){
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

    private static int findSum(int arr[]){
        int result = 0;
        for (int i : arr) {
            result += i;
        }
        return result;
        // TC -> O(n)
        // SC -> O(1)
    }

    private static int findMinimumValueOfMaximumCapacityBrute(int arr[], int painters){
        if(arr.length < painters){
            return -1;
        }
        int minSearchSpaceValue = findMax(arr);
        int maxSearchSpaceValue = findSum(arr);

        for (int i = minSearchSpaceValue; i <= maxSearchSpaceValue; i++) {
            if(numberOfPaintersRequiredWithGivenMaximumCapacity(arr, i) == painters){
                return i;
            }
        }
        return -1;

        // TC -> O(maxSearchSpaceValue - minSearchSpaceValue + 1) x O(n)
        // SC -> O(1)
    }

    private static int findMinimumValueOfMaximumCapacityOptimal(int arr[], int painters){
        if(arr.length < painters){
            return -1;
        }

        int beg = findMax(arr);
        int end = findSum(arr);
        int result = -1;

        while (beg <= end) {
            int mid = (beg + end) / 2;
            int paintersAtMid = numberOfPaintersRequiredWithGivenMaximumCapacity(arr, mid);

            if(paintersAtMid == painters){
                result = mid;
                end = mid - 1;
            }else if(paintersAtMid < painters){
                end = mid - 1;
            }else{
                beg = mid + 1;
            }
        }
        return result;

        // TC -> O(log(maxSearchSpaceValue - minSearchSpaceValue + 1)) x O(n)
        // SC -> O(1)

    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int painters = sc.nextInt();
        int arr[] = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        // System.out.println(findMinimumValueOfMaximumCapacityBrute(arr, painters));
        System.out.println(findMinimumValueOfMaximumCapacityOptimal(arr, painters));
        sc.close();

    }
}
