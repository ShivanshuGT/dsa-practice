import java.util.ArrayList;
import java.util.Scanner;

public class MoveZeroesToEnd {

    public static void moveZeroesBrute(int arr[]){
        int n = arr.length;
        ArrayList<Integer> temp = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if(arr[i] != 0){
                temp.add(arr[i]);
            }
        }

        for (int i = 0; i < temp.size(); i++) {
            arr[i] = temp.get(i);
        }

        for (int i = temp.size(); i < n; i++) {
            arr[i] = 0;
        }
    }

    public static void moveZeroesOptimal(int arr[]){
        int j = -1;
        int n = arr.length;

        // fetching the index at which first 0 element is present
        for (int i = 0; i < n; i++) {
            if(arr[i] == 0){
                j = i;
                break;
            }
        }

        if(j ==-1){
            return;
        }
        for (int i = j+1; i < n; i++) {
            if(arr[i] !=0){
                swap(arr, i ,j);
                j+=1;
            }
        }
    }

    
    
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int arr[] = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // moveZeroesBrute(arr);
        moveZeroesOptimal(arr);

        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }

        sc.close();
    }
}
