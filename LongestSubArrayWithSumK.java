import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LongestSubArrayWithSumK {

    public static int findLongestSubArrayWithSumKBrute(int arr[], int k){
        int n = arr.length;
        int result = 0;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += arr[j];

                if (sum == k) {
                    result = Math.max(result, j-i+1);
                }
            }
        }

        return result;
        // TC -> O(n x n)
        // SC -> O(1)
    }

    public static int findLongestSubArrayWithSumKBetter(int arr[], int k){
        int n = arr.length;
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        int result = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
            if (sum == k) {
                result = Math.max(result, i+1);
            }
            if(map.get(sum - k) != null){
                result = Math.max(result, i-map.get(sum - k));
            }
            map.put(sum, i);
        }
        return result;

        // TC -> O(n)
        // SC -> O(n)
        // but this approach is applicable only when there are positive 
        // elements in the array
    }
    
    public static int findLongestSubArrayWithSumKOptimalForNegPosZero(int arr[], int k){
        int n = arr.length;
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        int result = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
            if (sum == k) {
                result = Math.max(result, i+1);
            }
            if(map.get(sum - k) != null){
                result = Math.max(result, i-map.get(sum - k));
            }
            if(map.get(sum) == null){
                map.put(sum, i);
            }
        }
        return result;

        // TC -> O(n)
        // SC -> O(n)
        // this approach is optimal when there are -ve, +ve and zero 
        // elements in the array
    }


    public static int findLongestSubArrayWithSumKOptimalForPostiveElements(int arr[], int k){
        int n = arr.length;
        int result = 0;
        int sum = 0;
        int i = 0;
        int j = 0;
        while (j < n) {
            sum += arr[j];
            while (i < j && sum > k ) {
             sum -= arr[i];
             i +=1;   
            }

            if(sum == k){
                result = Math.max(result, j-i+1);
            }
            j+=1;
        }
        return result;

        // TC -> O(n)
        // SC -> O(1)
    }
    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int arr[] = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // System.out.print(findLongestSubArrayWithSumKBrute(arr, k));
        // System.out.print(findLongestSubArrayWithSumKBetter(arr, k));
        // System.out.print(findLongestSubArrayWithSumKOptimalForNegPosZero(arr, k));
        System.out.print(findLongestSubArrayWithSumKOptimalForPostiveElements(arr, k));
        sc.close();
    }
}
