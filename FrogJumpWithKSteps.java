import java.util.Scanner;

public class FrogJumpWithKSteps {

    private static int findMinimumEnergyRecursive(int ind, int k, int[] dp, int[] heights){

        if(ind == 0){
            return 0;
        }

        if(dp[ind] != -1){
            return dp[ind];
        }

        int minimumStep = Integer.MAX_VALUE;
        for (int i = 1; i <= k; i++) {
            if((ind - i) >= 0){
                int jumpEnergy = findMinimumEnergyRecursive(ind - i, k, dp, heights) + Math.abs(heights[ind-i] - heights[ind]);
                minimumStep = Math.min(minimumStep, jumpEnergy);
            }
        }

        dp[ind] = minimumStep;
        return dp[ind];
        // TC -> O(n x k)
        // SC -> O(n)
    }

    private static int findMinimumEnergyTabulation(int n, int k, int[] heights){
        int[] dp = new int[n];
        dp[0] = 0;

        for (int i = 1; i < n; i++) {
            int minimumStep = Integer.MAX_VALUE;
            for (int j = 1; j <= k; j++) {
                if((i-j) >= 0){
                    int jumpEnergy = dp[i-j] + Math.abs(heights[i-j] - heights[i]);
                    minimumStep = Math.min(minimumStep, jumpEnergy);
                }
            }
            dp[i] = minimumStep;
        }
        return dp[n-1];
        // TC -> O(n x k)
        // SC -> O(n)
    }

    // we cant space optimize this further since we have to carry more than 2 values here
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] dp = new int[n];
        int[] heights = new int[n];
        for (int i = 0; i < n; i++) {
            heights[i] = sc.nextInt();
            dp[i] = -1;
        }

        System.out.println(findMinimumEnergyRecursive(n-1, k, dp, heights));
        System.out.println(findMinimumEnergyTabulation(n, k, heights));
        sc.close();
    }
}
