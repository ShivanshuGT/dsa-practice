import java.util.Scanner;

public class SingleElementInSortedArray {

    private static int findSingleElementBrute(int arr[]){
        int n = arr.length;
        if(n==1)
            return arr[0];
        for (int i = 0; i < n; i++) {
            if(i == 0 && arr[i] != arr[i+1]){
                return arr[i];
            }
            if(i == n-1 && arr[i] != arr[i-1]){
                return arr[i];
            }

            if(arr[i] != arr[i+1] && arr[i] != arr[i-1]){
                return arr[i];
            }
        }
        return Integer.MIN_VALUE;

        // TC -> O(n)
        // SC -> O(1)
    }

    private static int findSingleElementOptimal(int arr[]){
        int n = arr.length;
        if(n==1)
            return arr[0];

        // handle the first element of the array
        if(arr[0] != arr[1]){
            return arr[0];
        }

        // handle the last element of the array
        if(arr[n-1] != arr[n-2]){
            return arr[n-1];
        }

        int beg = 1;
        int end = n-2;
        while (beg <= end) {
            int mid = (beg + end) / 2;
            if(arr[mid] != arr[mid - 1] && arr[mid] != arr[mid + 1]){
                return arr[mid];
            }

            // check on which half we are standing 

            if(((mid % 2 == 0) && (arr[mid] == arr[mid + 1])) ||
             ((mid % 2 == 1) && (arr[mid] == arr[mid - 1]))){
                // we are at the left half
                beg = mid + 1;
            }else{
                // we are at the right half
                end = mid - 1;
            }
        }

        return Integer.MIN_VALUE;

        // TC -> O(logn)
        // SC -> O(1)
    }
    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int arr[ ]= new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // System.out.println("Single Element is " + findSingleElementBrute(arr));
        System.out.println("Single Element is " + findSingleElementOptimal(arr));
        sc.close();
    }
}
