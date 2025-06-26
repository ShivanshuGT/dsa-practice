import java.util.Arrays;
import java.util.Scanner;

public class LargestInArray {

    private static int getLargestBruteForce(int arr[]){
        Arrays.sort(arr);
        return arr[arr.length-1];
    }

    private static int getLargestOptimal(int arr[]){
        int result = arr[0];
        for (int i = 1; i < arr.length; i++) {
           if(arr[i] > result)
                result = arr[i]; 
        }
        return result;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int arr[] = new int[n];
        
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.println(getLargestBruteForce(arr));
        System.out.println(getLargestOptimal(arr));
        sc.close();
    }
}
