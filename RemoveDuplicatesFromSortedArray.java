import java.util.LinkedHashSet;
import java.util.Scanner;

public class RemoveDuplicatesFromSortedArray {

    public static void removeDuplicatesBrute(int arr[]){

        int n = arr.length;
        LinkedHashSet<Integer> set = new LinkedHashSet<>();
        for (int i = 0; i < n; i++) { // TC -> O(n)
            set.add(arr[i]); // TC -> O(log(n))
        }
        int i = 0;
        for(int element : set){ // TC -> O(n)
            arr[i] = element;
            i += 1;
        }

        // TC -> O(n + nlog(n)) 
        // SC -> O(n) 
    }

    public static void removeDuplicatesOptimal(int arr[]){
        int n = arr.length;
        int left = 0;
        int right = 1;
        while (right < n) {
            if(arr[right] != arr[left]){
                left +=1;
                arr[left] = arr[right];
            }
            right += 1;
        }

        // TC -> O(n)
        System.out.println("Number of unique elements : " + (left +1));
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int arr[] = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        removeDuplicatesBrute(arr);
        // removeDuplicatesOptimal(arr);

        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }

        sc.close();
    }
}
