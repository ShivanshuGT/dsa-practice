import java.util.Arrays;
import java.util.Scanner;

public class LargestDivisibleSubset {

    private static void findLargestDivisibleSubset(int[] arr){
        int n = arr.length;

        Arrays.sort(arr);
        int[] dp = new int[n];
        int[] hash = new int[n];

        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            hash[i] = i;
        }

        int lastIndex = 0;
        int length = 0;

        for (int ind = 1; ind < n; ind++) {
            for (int prev = ind-1; prev >= 0; prev--) {
                if(((arr[ind] % arr[prev]) == 0) && ((dp[prev] + 1) > dp[ind])){
                    dp[ind] = 1 + dp[prev];
                    hash[ind] = prev;
                }
            }

            if(dp[ind] > length){
                length = dp[ind];
                lastIndex = ind;
            }
        }

        int[] ans = new int[length];
        int x = length-1;
        ans[x] = arr[lastIndex];
        x -= 1;

        while (hash[lastIndex] != lastIndex) {
            lastIndex = hash[lastIndex];
            ans[x] = arr[lastIndex];
            x -= 1;
        }
        for (int i = 0; i < length; i++) {
            System.out.print(ans[i] + " ");
        }
        // TC -> O(n x log(n)) + O(n x n) + O(n)
        // SC -> O(n)
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        findLargestDivisibleSubset(arr);
        sc.close();
    }
}
