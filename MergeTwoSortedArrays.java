import java.util.Arrays;
import java.util.Scanner;

public class MergeTwoSortedArrays {

    private static void mergeSortedArraysBrute(int arr1[], int arr2[]){
        int n = arr1.length;
        int m = arr2.length;

        int left = 0;
        int right = 0;
        int arr3[] = new int[n+m];
        int k = 0;
        while (left < n && right < m) {
            if(arr1[left] < arr2[right]){
                arr3[k] = arr1[left];
                k += 1;
                left += 1;
            }else{
                arr3[k] = arr2[right];
                k += 1;
                right += 1;
            }
        }

        while (left < n) {
            arr3[k] = arr1[left];
            k += 1;
            left += 1;
        }

        while (right < m) {
            arr3[k] = arr2[right];
            k += 1;
            right += 1;
        }

        k = 0;
        for (int i = 0; i < n; i++) {
            arr1[i] = arr3[k];
            k += 1;
        }

        for (int i = 0; i < m; i++) {
            arr2[i] = arr3[k];
            k += 1;
        }

        // TC -> O(2(n+m))
        // Sc -> O(n+m)


    }

    private static void mergeSortedArraysOptimalOne(int arr1[], int arr2[]){
        int n = arr1.length;
        int m = arr2.length;
        int left = n-1;
        int right = 0;
        while (left >= 0 && right < m) {
            if(arr1[left] > arr2[right]){
                swap(arr1, arr2, left, right);
            }
            left -= 1;
            right += 1;
        }
        Arrays.sort(arr1);
        Arrays.sort(arr2);

        // TC -> O(min(n,m)) + O(nlogn) + O(mlogm)
        // Sc -> O(1)
    }

    private static void mergeSortedArraysOptimalTwo(int arr1[], int arr2[]){
        int n = arr1.length;
        int m = arr2.length;
        int gap = Math.ceilDiv(n+m, 2);
        while (gap > 0) {
            int left = 0;
            int right = gap;
            while (right < n+m) {
                if(left < n && right >= n){
                    // when left is at arr1 and right is at arr2
                    swapIfGreater(arr1, arr2, left, right-n);

                }else if(right < n){
                    // when left and right both are at arr1
                    swapIfGreater(arr1, arr1, left, right);

                }else{
                    // when left and right both are at arr2
                    swapIfGreater(arr2, arr2, left-n, right-n);
                }
                left += 1;
                right += 1;
            }

            if(gap == 1){
                break;
            }
            gap = Math.ceilDiv(gap, 2);
        }

        // TC -> O(log(m+n) x (m+n)) where base of the log is 2 because everytime we are dividing by 2
        // SC -> O(1)
    }

    private static void swap(int arr1[], int arr2[], int left, int right){
        int temp = arr1[left];
        arr1[left] = arr2[right];
        arr2[right] = temp;
    }

    private static void swapIfGreater(int arr1[], int arr2[], int left, int right){
        if(arr1[left] > arr2[right]){
            int temp = arr1[left];
            arr1[left] = arr2[right];
            arr2[right] = temp;
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int arr1[] = new int[n];
        int arr2[] = new int[m];
        for (int i = 0; i < n; i++) {
            arr1[i] = sc.nextInt();
        }
        for (int i = 0; i < m; i++) {
            arr2[i] = sc.nextInt();
        }
        
        // mergeSortedArraysBrute(arr1, arr2);
        // mergeSortedArraysOptimalOne(arr1, arr2);
        mergeSortedArraysOptimalTwo(arr1, arr2);

        for (int i = 0; i < n; i++) {
            System.out.print(arr1[i] + " ");
        }
        System.out.println();
        for (int i = 0; i < m; i++) {
            System.out.print(arr2[i] + " ");
        }

        sc.close();
    }
}
