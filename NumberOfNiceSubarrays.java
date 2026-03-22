import java.util.Scanner;

public class NumberOfNiceSubarrays {

    private static int countSubarraysWithOddNumberCountLeK(int[] arr, int k){
        int n = arr.length;
        int count = 0;
        int sum = 0;
        int left = 0;
        int right = 0;

        while (right < n) {
            sum += arr[right] % 2;

            while (sum > k) {
                sum -= arr[left] % 2;
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

    private static int countNiceSubarrays(int[] arr, int k){
        return countSubarraysWithOddNumberCountLeK(arr, k) - countSubarraysWithOddNumberCountLeK(arr, k-1);
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
        System.out.println(countNiceSubarrays(arr, k));
        sc.close();
    }
}
