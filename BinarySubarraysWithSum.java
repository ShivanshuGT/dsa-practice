import java.util.Scanner;

public class BinarySubarraysWithSum {

    private static int countSubArraysWithSumLeK(int[] arr, int k){
        if(k < 0){
            // since arr can contain only 0 and 1, there is
            // no possibility of having a negative sum
            return 0;
        }
        int n = arr.length;
        int count = 0;
        int sum = 0;
        int left = 0;
        int right = 0;

        while (right < n) {
            sum += arr[right];

            while (sum > k) {
                // is sum exceeds k then reduce window from back
                sum -= arr[left];
                left += 1;
            }

            if(sum <= k){
                count += (right-left+1);
            }

            right += 1;
        }
        return count;
        // TC -> O(2 x n)
        // SC -> O(1)
    }

    private static int countSubArrays(int[] arr, int k){
        return (countSubArraysWithSumLeK(arr, k) - countSubArraysWithSumLeK(arr, k-1));
        // TC -> O(4 x n)
        // SC -> O(1)
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        System.out.println(countSubArrays(arr, k));
        sc.close();
    }
}
