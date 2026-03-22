import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SubarrayWithKDifferentIntegers {

    private static int countSubarraysWithDistinctIntegersLeK(int[] arr, int k){
        int n = arr.length;
        int left = 0;
        int right = 0;
        int count = 0;

        Map<Integer, Integer> map = new HashMap<>();

        while (right < n) {
            map.put(arr[right], map.getOrDefault(arr[right], 0) + 1);

            while (map.size() > k) {
                map.put(arr[left], map.get(arr[left])-1);
                if(map.get(arr[left]) == 0){
                    // erase if frequency becomes zero for a key
                    map.remove(arr[left]);
                }
                left += 1;
            }

            if(map.size() <= k){
                count += (right-left+1);
            }
            right += 1;
        }
        return count;
        // TC -> O(2n x logn)
        // SC -> O(n)
    }

    private static int countSubarrays(int[] arr, int k){
        return countSubarraysWithDistinctIntegersLeK(arr, k) - countSubarraysWithDistinctIntegersLeK(arr, k-1);
        // TC -> O(4n x logn)
        // SC -> O(2 x n)
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        System.out.println(countSubarrays(arr, k));
        sc.close();
    }
}
