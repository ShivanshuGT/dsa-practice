import java.util.Scanner;

public class PrintLongestIncreasingSubsequence {

    private static void printLIS(int[] arr){
        int n = arr.length;

        int[] dp = new int[n];
        int[] hash = new int[n]; // for backtracking

        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            hash[i] = i;
        }

        int lisLength = 0;
        int lastIndex = -1;
        for (int ind = 0; ind < n; ind++) {
            for (int prev = ind-1; prev >= 0; prev--) {
                if((arr[prev] < arr[ind]) && (dp[ind] < (1 + dp[prev]))){
                    dp[ind] = 1 + dp[prev];
                    hash[ind] = prev;
                }

            }
            if(dp[ind] > lisLength){
                lisLength = dp[ind];
                lastIndex = ind;
            }
        }

        int[] lis = new int[lisLength];
        int x = lisLength - 1;
        lis[x] = arr[lastIndex];
        x -= 1;

        while (hash[lastIndex] != lastIndex) {
            lastIndex = hash[lastIndex];
            lis[x] = arr[lastIndex];
            x -= 1;
        }

        for (int i : lis) {
            System.out.print(i + " ");
        }

        // TC -> O(n x n)
        // SC -> O(n)
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        printLIS(arr);
        sc.close();
    }
}
