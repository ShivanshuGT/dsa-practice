import java.util.Scanner;

public class NextPermutation {

    public static void findNextPermutation(int arr[]){
        int n = arr.length;
        int ind = -1;

        // find the dip
        for (int i = n-2; i >= 0; i--) {
            if(arr[i] < arr[i+1]){
                ind = i;
                break;
            }
        }

        // last permutation use case
        if(ind == -1){
            reverse(arr, 0, n-1);
            return;
        } 

        // replace the dip
        for (int i = n-1; i > ind; i--) {
            if(arr[i] > arr[ind]){
                swap(arr, ind, i);
                break;
            }
        }

        // reverse the array from index ind+1 to the end
        reverse(arr, ind+1, n-1);

        // TC -> O(n)
        // SC-> O(1)
        
    }

    public static void reverse(int arr[], int beg, int end){
        int left = beg;
        int right = end;
        while (left <= right) {
            swap(arr, left, right);
            left += 1;
            right -= 1;
        }
    }

    public static void swap(int arr[], int left, int right){
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }
    

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int arr[] = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        findNextPermutation(arr);
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }

        sc.close();
    }
}
