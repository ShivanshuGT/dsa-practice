import java.util.Scanner;

public class CountOccurrencesInArray {

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

    private static int findOccurrences(int arr[], int target){
        int firstAndLastOccurrence[] = findFirstAndLastOccurenceOptimalTwo(arr, target);
        if(firstAndLastOccurrence[0] == -1){
            return 0;
        }
        return firstAndLastOccurrence[1] - firstAndLastOccurrence[0] + 1;

        // TC -> O(2logn)
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
        System.out.println("Number of occurrences = " + findOccurrences(arr, target));
        sc.close();
    }
}
