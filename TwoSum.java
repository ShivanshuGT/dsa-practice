import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class TwoSum {

    public static void findTwoSumBrute(int arr[], int k){
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                if(arr[i] + arr[j] == k){
                    System.out.print("YES with indices [" + i + ", "+ j+"]");
                    return;
                }
            }
        }
        System.out.print("NO");
        // TC -> O(n x n)
        // SC -> O(1)
    }

    public static void findTwoSumBetter(int arr[], int k){
        int n = arr.length;
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            if(map.get(k-arr[i]) != null){
                int j = map.get(k-arr[i]);
                if(i < j){
                    System.out.print("YES with indices [" + i + ", "+ j+"]");
                }else{
                    System.out.print("YES with indices [" + j + ", "+ i+"]");

                }
                return;
            }
            map.put(arr[i], i);
        }

        System.out.print("NO");

        // TC -> can vary from O(n x 1) (in best and average cases) to O(n x n) (in worst case)-> if we use HashMap
        // TC -> O(n x log(n)) if we use TreeMap or LinkedHashMap
        // SC -> O(n)
        // This approach is the optimal approach if we want to print the indices
        // of the elements as well
        // But if we just want to print YES or NO , then we have another appraoch which is
        // optimal
    }

    public static void findTwoSumOptimal(int arr[], int k){
        Arrays.sort(arr);
        int left = 0;
        int right = arr.length - 1;
        while(left < right){
            if(arr[left] + arr[right] > k){
                right -= 1;
            }
            else if(arr[left] + arr[right] < k){
                left += 1;
            }else{
                System.out.print("YES");
                return;
            }
        }
        System.out.print("NO");

        // TC -> O(n.log(n)) (for sorting) + O(n)
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

        // findTwoSumBrute(arr, k);
        // findTwoSumBetter(arr, k);
        findTwoSumOptimal(arr, k);
        sc.close();
    }
}
