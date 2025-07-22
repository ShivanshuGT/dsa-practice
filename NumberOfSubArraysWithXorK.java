import java.util.HashMap;
import java.util.Scanner;

public class NumberOfSubArraysWithXorK {

    private static int findNumberOfSubArraysWithXorKBrute(int arr[], int target){
        int n = arr.length;
        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int xor = 0;
                for (int k = i; k <= j; k++) {
                    xor = xor^arr[k];
                }

                if(xor == target){
                    result += 1;
                }
            }
        }
        return result;

        // TC -> O(nxnxn)
        // SC -> O(1)
    }

    private static int findNumberOfSubArraysWithXorKBetter(int arr[], int target){
        int n = arr.length;
        int result = 0;
        for (int i = 0; i < n; i++) {
            int xor = 0;
            for (int j = i; j < n; j++) {
                xor = xor ^ arr[j];
                if(xor == target){
                    result += 1;
                }
            }
        }
        return result;

        // TC -> O(nxn)
        // SC -> O(1)
    }

    private static int findNumberOfSubArraysWithXorKOptimal(int arr[], int target){
        int n = arr.length;
        int result = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int xor = 0;
        for (int i = 0; i < n; i++) {
            xor = xor ^ arr[i];
            if(map.containsKey(xor^target)){
                result += map.get(xor^target);
            }
            map.put(xor, map.getOrDefault(xor, 0) + 1);
        }
        return result;

        // SC -> O(n)
        // TC -> can vary from O(n x 1) to O(n x n)
    }
    

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int arr[] = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        // System.out.print(findNumberOfSubArraysWithXorKBrute(arr, k));
        // System.out.print(findNumberOfSubArraysWithXorKBetter(arr, k));
        System.out.print(findNumberOfSubArraysWithXorKOptimal(arr, k));

        sc.close();
    }
}
