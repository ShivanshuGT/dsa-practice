import java.util.Scanner;

public class CountLongestIncreasingSubsequence {

    private static int countLIS(int[] arr){
        int n = arr.length;
        int[] dp = new int[n];
        int[] count = new int[n];

        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            count[i] = 1;
        }

        int lisLength = 0;

        for (int ind = 1; ind < n; ind++) {
            for (int prev = 0; prev < ind; prev++) {
                if(arr[prev] < arr[ind]){
                    if(dp[ind] < (1 + dp[prev])){
                        dp[ind] = 1 + dp[prev];
                        count[ind] = count[prev];
                    }else if(dp[ind] == (1 + dp[prev])){
                        count[ind] += count[prev];
                    } 
                }
            }

            if(lisLength < dp[ind]){
                lisLength = dp[ind];
            }
        }

        int ans = 0;

        for (int i = 0; i < n; i++) {
            if(dp[i] == lisLength){ 
                ans += count[i];
            }
        }

        return ans;
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

        System.out.println(countLIS(arr));
        sc.close();
    }
}
