import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LeaderElement {
    

    private static List<Integer> findLeaderElementsOptimal(int[] arr) {
        int n = arr.length;
        List<Integer> result = new ArrayList<>();
        int max = Integer.MIN_VALUE;
        for (int i = n-1; i >= 0; i--) {
            if(arr[i] > max){
                result.add(arr[i]);
                max = arr[i];
            }
        }
        return result.reversed();

        // TC -> O(n)
        // SC -> O(n)

    }

    private static List<Integer> findLeaderElementsBrute(int arr[]){
        int n = arr.length;
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            boolean isLeader = true;
            for (int j = i+1; j < n; j++) {
                if(arr[j] > arr[i]){
                    isLeader = false;
                    break;
                }
            }
            if(isLeader){
                result.add(arr[i]);
            }
        }
        return result;

        // TC -> O(n x n)
        // SC -> O(n)
    }

   
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int arr[] = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        List<Integer> result = findLeaderElementsOptimal(arr);
        // List<Integer> result = findLeaderElementsBrute(arr);
        for (Integer integer : result) {
            System.out.print(integer + " ");
        }

        sc.close();
    }

}
