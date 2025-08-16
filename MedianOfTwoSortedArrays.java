import java.util.Scanner;

public class MedianOfTwoSortedArrays {

    private static double findMedianBrute(int arr1[], int arr2[]){
        int n2 = arr2.length;
        int n1 = arr1.length;
        int arr3[] = new int[n1+n2];
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < n1 && j < n2) {
            if(arr1[i] < arr2[j]){
                arr3[k] = arr1[i];
                k += 1;
                i += 1;
            }else{
                arr3[k] = arr2[j];
                k += 1;
                j += 1;
            }
        }
        while (i < n1) {
            arr3[k] = arr1[i];
            k += 1;
            i += 1;
        }
        while (j < n2) {
            arr3[k] = arr2[j];
            k += 1;
            j += 1;
        }
        int ind = (n1 + n2) / 2;
        if((n1 + n2) % 2 == 0){
            return (arr3[ind] + arr3[ind - 1]) / 2;
        }else{
            return arr3[ind];
        }

        // TC -> O(n1 + n2)
        // SC -> O(n1 + n2)
    }

    private static double findMedianBetter(int arr1[], int arr2[]){
        int n1 = arr1.length;
        int n2 = arr2.length;
        int i = 0;
        int j = 0;
        int ind2 = (n1 + n2) / 2;
        int ind1 = ind2 - 1;
        int el2 = -1;
        int el1 = -1;
        int indexCounter = 0;
        while (i < n1 && j < n2) {
            if(arr1[i] < arr2[j]){
                if(indexCounter == ind2){
                    el2 = arr1[i];
                }
                if(indexCounter == ind1){
                    el1 = arr1[i];
                }
                indexCounter += 1;
                i += 1;
            }else{
                if(indexCounter == ind2){
                    el2 = arr2[j];
                }
                if(indexCounter == ind1){
                    el1 = arr2[j];
                }
                indexCounter += 1;
                j += 1;
            }
        }

        while (i < n1) {
            if(indexCounter == ind2){
                el2 = arr1[i];
            }
            if(indexCounter == ind1){
                el1 = arr1[i];
            }
            indexCounter += 1;
            i += 1;
        }

        while (j < n2) {
            if(indexCounter == ind2){
                el2 = arr2[j];
            }
            if(indexCounter == ind1){
                el1 = arr2[j];
            }
            indexCounter += 1;
            j += 1;
        }

        int totalElements = (n1 + n2);
        if(totalElements % 2 == 0){
            return (el1 + el2) / 2;
        }else{
            return el2;
        }

        // TC -> O(n1 + n2)
        // SC -> O(1)
    }

    private static double findMedianOptimal(int arr1[], int arr2[]){
        int n1 = arr1.length;
        int n2 = arr2.length;
        if(n1 > n2){
            return findMedianOptimal(arr2, arr1);
        }

        int beg = 0;
        int end = n1;
        int numberOfElementsInOneHalf = (n1 + n2 + 1) / 2; // this will work in both even and odd cases
        while (beg <= end) {
            int mid1 = (beg + end) / 2;
            int mid2 = numberOfElementsInOneHalf - mid1;
            int l1 = Integer.MIN_VALUE;
            int l2 = Integer.MIN_VALUE;
            int r1 = Integer.MAX_VALUE;
            int r2 = Integer.MAX_VALUE;

            if(mid1 < n1){
                r1 = arr1[mid1];
            }
            if (mid2 < n2) {
                r2 = arr2[mid2];
            }
            if((mid1 - 1) >= 0){
                l1 = arr1[mid1 - 1];
            }
            if((mid2 - 1) >= 0){
                l2 = arr2[mid2 - 1];
            }

            if((l1 <= r2) && (l2 <= r1)){
                if((n1 + n2)% 2 == 0){
                    // even case
                    return (Math.max(l1, l2) + Math.min(r1, r2))/2;
                }else{
                    // odd case
                    return Math.max(l1, l2);
                }
            }else if(l1 > r2){
                end = mid1 - 1;
            }else{
                beg = mid1 + 1;
            }
        }
        return 0;

        // TC -> O(log(min(n1, n2)))
        // SC -> O(1)
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n1 = sc.nextInt();
        int n2 = sc.nextInt();
        int arr1[] = new int[n1];
        int arr2[] = new int[n2];
        for (int i = 0; i < n1; i++) {
            arr1[i] = sc.nextInt();
        }
        for (int i = 0; i < n2; i++) {
            arr2[i] = sc.nextInt();
        }
        System.out.println(findMedianBrute(arr1, arr2));
        System.out.println(findMedianBetter(arr1, arr2));
        System.out.println(findMedianOptimal(arr1, arr2));
        sc.close();
    }
}
