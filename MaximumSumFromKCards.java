import java.util.Scanner;

public class MaximumSumFromKCards {

    private static int findMaxSum(int[] arr, int k){
        int n = arr.length;
        int leftSum = 0;
        int rightSum = 0;
        int maxSum = 0;

        for (int i = 0; i < k; i++) {
            leftSum += arr[i];
        }
        maxSum = leftSum;
        int rightIndex = n-1;
        for (int i = k-1; i >= 0; i--) {
            leftSum -= arr[i];
            rightSum += arr[rightIndex];
            rightIndex -= 1;
            maxSum = Math.max(maxSum, leftSum + rightSum);
        }
        return maxSum;
        // TC -> O(2 x k)
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
        System.out.println(findMaxSum(arr, k));
        sc.close();
    }
}
