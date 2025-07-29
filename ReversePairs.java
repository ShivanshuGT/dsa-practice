import java.util.ArrayList;
import java.util.Scanner;

public class ReversePairs {

    private static int countReversePairsBrute(int arr[]){
        int n = arr.length;
        int result = 0;
        for (int i = 0; i < n-1; i++) {
            for (int j = i+1; j < n; j++) {
                if(arr[i] > 2*arr[j]){
                    result += 1;
                }
            }
        }
        return result;

        // TC -> O(nxn)
        // SC -> O(1)
    }

    private static int mergeModified(int arr[], int beg, int mid, int end){
        ArrayList<Integer> temp = new ArrayList<>();
        int result = count(arr, beg, mid, end);
        int left = beg;
        int right = mid + 1;
        while (left <= mid && right <= end) {
            if(arr[left] > arr[right]){
                temp.add(arr[right]);
                right += 1;
            }else{
                temp.add(arr[left]);
                left += 1;
            }
        }

        while (left <= mid) {
            temp.add(arr[left]);
            left += 1;
        }

        while (right <= end) {
            temp.add(arr[right]);
            right += 1;
        }

        for (int i = beg; i <= end; i++) {
            arr[i] = temp.get(i - beg);
        }

        return result;

        // TC -> O(2n)
        // SC- > O(n)
        
    }

    private static int count(int arr[], int beg, int mid, int end){
        int result = 0;
        int mover = mid + 1;
        for (int i = beg; i <= mid; i++) {
            while (mover <= end && arr[i] > 2*arr[mover]) {
                mover += 1;
            }
            result += mover - (mid + 1);
        }

        return result;

        // TC -> O(n)
        // SC -> O(1)
    }

    private static int mergeSortModified(int arr[], int beg, int end){
        if(beg == end){
            return 0;
        }

        int mid = (beg + end) / 2;
        int count = 0;
        count += mergeSortModified(arr, beg, mid);
        count += mergeSortModified(arr, mid+1, end);
        count += mergeModified(arr, beg, mid, end);
        return count;

        // TC -> O(2n x logn)
        // Sc -> O(n)
    }

    private static int countReversePairsOptimal(int arr[]){
        return mergeSortModified(arr, 0, arr.length - 1);

        // TC -> O(2n x logn)
        // SC -> O(n)
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int arr[] = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // System.out.println("Number of reverse pairs are " + countReversePairsBrute(arr));
        System.out.println("Number of reverse pairs are " + countReversePairsOptimal(arr));

        sc.close();
    }
}

