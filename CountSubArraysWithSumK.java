import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

public class CountSubArraysWithSumK {

    public static int findNumberOfSubArraysWithSumKBrute(int arr[], int k){
        int n = arr.length;
        int result = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int sum = 0;
                for (int m = i; m <= j; m++) {
                    sum += arr[m];
                }
                if(sum == k){
                    result += 1;
                }
            }
        }

        return result;

        // TC -> O(n x n x n)
        // SC -> O(1)
    }

    public static int findNumberOfSubArraysWithSumKBetter(int arr[], int k){
        int n = arr.length;
        int result = 0;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += arr[j];
                if(sum == k){
                    result += 1;
                }
            }
        }

        return result;

        // TC -> O(n x n)
        // SC -> O(1)
    }

    public static int findNumberOfSubArraysWithSumKOptimal(int arr[], int k){
        int n = arr.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int presum = 0;
        int result = 0;
        for (int i = 0; i < n; i++) {
            presum += arr[i];
            if(map.containsKey(presum - k)){
                result += map.get(presum - k);
            }
            if (map.containsKey(presum)) {
                map.put(presum, map.get(presum) + 1);
            }else{
                map.put(presum, 1);
            }
        }

        return result;

        // TC -> can vary from O(n x 1) (in best and avg. case) -> O(n x n) (in worst case)
        // but if we use TreeMap then it will be O(n x logn)
        // SC -> O(n)
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int arr[] = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // System.out.print(findNumberOfSubArraysWithSumKBrute(arr, k));
        // System.out.print(findNumberOfSubArraysWithSumKBetter(arr, k));
        System.out.print(findNumberOfSubArraysWithSumKOptimal(arr, k));
        sc.close();
    }
}
