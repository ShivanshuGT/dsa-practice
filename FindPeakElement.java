import java.util.Scanner;

public class FindPeakElement {

    private static int findPeakElementIndexBrute(int arr[]){
        int n = arr.length;
        if(arr.length == 1){
            return 0;
        }

        if(arr[0] > arr[1]){
            return 0;
        }
        
        if(arr[n-1] > arr[n-2]){
            return n-1;
        }

        for (int i = 1; i < n-1; i++) {
            if(arr[i-1] < arr[i] && arr[i] > arr[i+1]){
                return i;
            }
        }

        return -1;

        // TC -> O(n)
        // SC -> O(1)
    }

    private static int findPeakElementIndexOptimal(int arr[]){
        int n = arr.length;
        if(n == 1){
            return 0;
        }

        if(arr[0] > arr[1]){
            return 0;
        }

        if(arr[n-1] > arr[n-2]){
            return n-1;
        }

        int beg = 1;
        int end = n-2;

        while (beg <= end) {
            int mid = (beg + end) / 2;
            if(arr[mid] > arr[mid - 1] && arr[mid] > arr[mid + 1]){
                return mid;
            }
            else if(arr[mid - 1] < arr[mid]){
                // we are on the increasing curve
                beg = mid + 1;
            }else if( arr[mid + 1] < arr[mid]){
                // we are on the decreasing curve
                end = mid - 1;
            }else{
                // condition of minima
                end = mid - 1; // or beg = mid + 1;
            }
        }
        return -1;

        // TC -> O(logn)
        // SC -> O(1)
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int arr[] = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        // System.out.println("Peak Element is at index " + findPeakElementIndexBrute(arr));
        System.out.println("Peak Element is at index " + findPeakElementIndexOptimal(arr));
        sc.close();
    }
}

