import java.util.Scanner;

public class FirstAndLastOccurrenceInArray {

    private static int[] findFirstAndLastOccurenceBrute(int arr[], int target){
        int n = arr.length;
        int first = -1;
        int last = -1;

        for (int i = 0; i < n; i++) {
            if(arr[i] == target){
                if(first == -1){
                    first = i;
                }
                last = i;
            }
        }
        int[] result = new int[2];
        result[0] = first;
        result[1] = last;
        return result;

        // TC -> O(n)
        // SC -> O(1)

    }

    private static int[] findFirstAndLastOccurenceOptimalOne(int arr[], int target){
        int lowerBound = lowerBound(arr, target);
        int result[] = new int[2];
        if(lowerBound == arr.length || arr[lowerBound] != target){
            // this means the target is not present in the array
            result[0] = -1;
            result[1] = -1;
        }
        else{
            result[0] = lowerBound;
            result[1] = upperBound(arr, target) - 1;
        }
        return result;

        // TC -> O(2log(n))
        // SC -> O(1)
    }

    private static int[] findFirstAndLastOccurenceOptimalTwo(int arr[], int target){
        int first = -1;
        int last = -1;
        int n = arr.length;
        int result[] = new int[2];

        // find first occurrence
        int beg = 0;
        int end = n-1;
        while (beg <= end) {
            int mid = (beg + end) / 2;
            if(arr[mid] == target){
                first = mid;
                end = mid - 1;
            }else if(arr[mid] > target){
                end = mid - 1;
            }else{
                beg = mid + 1;
            }
        }

        if(first == -1){
            result[0] = -1;
            result[1] = -1;
            return result;
        }

        // find last occurrence
        beg = 0;
        end = n-1;
        while (beg <= end) {
            int mid = (beg + end) / 2;
            if(arr[mid] == target){
                last = mid;
                beg = mid + 1;
            }else if(arr[mid] < target){
                beg = mid + 1;
            }else{
                end = mid - 1;
            }
        }

        result[0] = first;
        result[1] = last;
        return result;

        // TC -> O(2logn)
        // SC -> O(1)
    }

    private static int lowerBound(int arr[], int target){
        int n = arr.length;
        int result = n;
        int beg = 0;
        int end = n-1;
        while (beg <= end) {
            int mid = (beg + end) / 2;
            if(arr[mid] >= target){
                result = mid;
                end = mid - 1;
            }else{
                beg = mid + 1;
            }
        }
        return result;
    }

    private static int upperBound(int arr[], int target){
        int n = arr.length;
        int result = n;
        int beg = 0;
        int end = n-1;
        while (beg <= end) {
            int mid = (beg + end) / 2;
            if(arr[mid] > target){
                result = mid;
                end = mid - 1;
            }else{
                beg = mid + 1;
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

        // int[] result = findFirstAndLastOccurenceBrute(arr, target);
        // int[] result = findFirstAndLastOccurenceOptimalOne(arr, target);
        int[] result = findFirstAndLastOccurenceOptimalTwo(arr, target);
        System.out.println("First occurrence is at index " + result[0]);
        System.out.println("Last occurrence is at index " + result[1]);

        sc.close();
    }
}
