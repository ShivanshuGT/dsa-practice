import java.util.Scanner;

public class KthElementOfTwoSortedArrays {

    private static int findKthElementOptimal(int arr1[], int arr2[], int k){
        int n1 = arr1.length;
        int n2 = arr2.length;
        if(n1 > n2){
            return findKthElementOptimal(arr2, arr1,k);
        }
        int beg = Math.max(0, k - n2);
        int end = Math.min(n1, k);
        while (beg <= end) {
            int mid1 = (beg + end) / 2;
            int mid2 = k - mid1;
            int l1 = Integer.MIN_VALUE;
            int l2 = Integer.MIN_VALUE;
            int r1 = Integer.MAX_VALUE;
            int r2 = Integer.MAX_VALUE;

            if(mid1 < n1){
                r1 = arr1[mid1];
            }
            if(mid2 < n2){
                r2 = arr2[mid2];
            }
            if((mid1 - 1) >= 0){
                l1 = arr1[mid1 - 1];
            }
            if((mid2 - 1) >= 0){
                l2 = arr2[mid2 - 1];
            }
            if((l1 <= r2) && (l2 <= r1)){
                return Math.max(l1, l2);
            }else if(l1 > r2){
                end = mid1 - 1;
            }else{
                beg = mid1 + 1;
            }
        }
        return -1;

        // TC -> O(log(min(n1,n2)))
        // SC -> O(1)
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n1 = sc.nextInt();
        int n2 = sc.nextInt();
        int k = sc.nextInt();
        int arr1[] = new int[n1];
        int arr2[] = new int[n2];
        for (int i = 0; i < n1; i++) {
            arr1[i] = sc.nextInt();
        }

        for (int i = 0; i < n2; i++) {
            arr2[i] = sc.nextInt();
        }
        System.out.println(findKthElementOptimal(arr1, arr2, k));
        sc.close();
    }
}
