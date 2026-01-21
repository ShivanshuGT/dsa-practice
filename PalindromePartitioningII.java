import java.util.Scanner;

public class PalindromePartitioningII {

    private static boolean isPalindrome(int i, int j, String str){
        while (i < j) {
            if(str.charAt(i) != str.charAt(j)){
                return false;
            }
            i += 1;
            j -= 1;
        }
        return true;
        // TC -> O(n)
    }

    private static int findMinimumNumberOfPartitionsRecursiveHelper(int ind, String str, int[] dp){
        int n = str.length();
        // base case
        if(ind == n){
            return 0;
        }

        if(dp[ind] != -1){
            return dp[ind];
        }

        int ans = Integer.MAX_VALUE;

        for (int j = ind; j < n; j++) {
            if(isPalindrome(ind, j, str)){
                int cost = 1 + findMinimumNumberOfPartitionsRecursiveHelper(j+1, str, dp);
                ans = Math.min(ans, cost);
            }
        }
        dp[ind] = ans;
        return dp[ind];
        // TC -> O(n x n x n)
        // SC -> O(n) + O(n)
    }

    private static int findMinimumNumberOfPartitionsRecursive(String str){
        int n = str.length();

        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            dp[i] = -1;
        }

        return findMinimumNumberOfPartitionsRecursiveHelper(0, str, dp)-1;
        // TC -> O(n x n x n)
        // SC -> O(n) + O(n)
    }

    private static int findMinimumNumberOfPartitionsTabulation(String str){
        int n = str.length();

        int[] dp = new int[n+1];

        // base case
        dp[n] = 0;

        for (int ind = n-1; ind >= 0; ind--) {
            int ans = Integer.MAX_VALUE;
            for (int j = ind; j < n; j++) {
                if(isPalindrome(ind, j, str)){
                    int cost = 1 + dp[j+1];
                    ans = Math.min(ans, cost);
                }
            }
            dp[ind] = ans;
        }

        return dp[0]-1;
        // TC -> O(n x n x n)
        // SC -> O(n)
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        System.out.println(findMinimumNumberOfPartitionsRecursive(str));
        System.out.println(findMinimumNumberOfPartitionsTabulation(str));
        sc.close();
    }
}
