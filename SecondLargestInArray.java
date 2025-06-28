import java.util.Arrays;
import java.util.Scanner;

public class SecondLargestInArray {

    public static int secondLargestBrute(int arr[]){
        int n = arr.length;
        Arrays.sort(arr);
        int largest = arr[n-1];
        for (int i = n-2; i >=0; i--) {
            if(arr[i] != largest){
                return arr[i];
            }
        }
        return Integer.MIN_VALUE;
    }

    public static int secondLargestBetter(int arr[]){
        int n = arr.length;
        int largest = arr[0];
        int secondLargest = Integer.MIN_VALUE;
        for (int i = 1; i < n; i++) {
            if(arr[i] > largest)
                largest = arr[i];
        }

        for(int i = 0; i < n; i++){
            if(arr[i] < largest && arr[i] > secondLargest){
                secondLargest = arr[i];
            }
        }

        return secondLargest;
    }

    public static int secondLargestOptimal(int arr[]){
        int n = arr.length;
        int largest = arr[0];
        int secondLargest = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            
            if(arr[i] > largest){
                secondLargest = largest;
                largest = arr[i];
            }
            if(arr[i] > secondLargest && arr[i] < largest){
                secondLargest = arr[i];
            }
        }
        return secondLargest;
    }

    public static boolean isArraySorted(int arr[]){
        int n = arr.length;
        for (int i = 0; i < n-1; i++) {
            if(arr[i] > arr[i+1]){
                return false;
            }
        }
        return true;
    }

    public static int secondSmallestOptimal(int arr[]){
        int n = arr.length;
        int smallest = arr[0];
        int secondSmallest = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if(arr[i] < smallest){
                secondSmallest = smallest;
                smallest = arr[i];
            }

            if(arr[i] < secondSmallest && arr[i] > smallest){
                secondSmallest = arr[i];
            }
        }
        return secondSmallest;
    }

    
    
    public static void main(String[] args) {
        
        Scanner sc =new Scanner(System.in);

        int n = sc.nextInt();
        int arr[] = new int[n];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = sc.nextInt();
        }
        int secondLargest = secondLargestOptimal(arr);
        int secondSmallest = secondSmallestOptimal(arr);

        System.out.println(secondLargest == Integer.MIN_VALUE ? "No second largest exist" : secondLargest);
        System.out.println(secondSmallest == Integer.MAX_VALUE ? "No second smallest exist" : secondSmallest);
        System.out.print(isArraySorted(arr));
        sc.close();


    }
}
