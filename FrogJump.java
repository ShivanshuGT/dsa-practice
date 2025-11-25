import java.util.Scanner;

public class FrogJump {

    private static int findMinimumEnergyRecursive(int ind, int[] height, int[] dp){
        // it will return the minimum amount of energy taken to climb 'ind'th stair
        // from 0th stair
        // TOP-DOWN approach

        if(ind == 0){
            // to climb '0'th stair from 0th stair, we require '0' energy
            return 0;
        }

        if(dp[ind] != -1){
            return dp[ind];
        }

        int firstStep = findMinimumEnergyRecursive(ind-1, height, dp) + Math.abs(height[ind] - height[ind-1]);
        int secondStep = Integer.MAX_VALUE;
        if(ind > 1){
            // we cant jump two steps back from index 1
            // we can only jump one step back from index 1
            secondStep = findMinimumEnergyRecursive(ind-2, height, dp) + Math.abs(height[ind] - height[ind-2]);
        }
        dp[ind] = Math.min(firstStep, secondStep); // take the minimum out of the two steps computed
        return dp[ind];
        // TC -> O(n)
        // SC -> O(n)
    }

    private static int findMinimumEnergyTabulation(int n, int[] height){
        // BOTTOM-UP APPROACH

        int[] dp = new int[n];
        dp[0] = 0; // since we require '0' energy to climb 0th stair from 0th stair
        dp[1] = Math.abs(height[1] - height[0]);

        for (int i = 2; i < n; i++) {
            int firstStep = dp[i-1] + Math.abs(height[i] - height[i-1]);
            int secondStep = dp[i-2] + Math.abs(height[i] - height[i-2]);
            dp[i] = Math.min(firstStep, secondStep);
        }
        return dp[n-1];
        // TC -> O(n)
        // SC -> O(n)
    }

    private static int findMinimumEnergySpaceOptimized(int n, int[] height){
        if(n == 0){
            return 0;
        }
        
        if(n == 1){
            return Math.abs(height[1] - height[0]);
        }
        
        int prev = Math.abs(height[1] - height[0]);
        int prev2 = 0;
        for (int i = 2; i < n; i++) {
            int firstStep = prev + Math.abs(height[i] - height[i-1]);
            int secondStep = prev2 + Math.abs(height[i] = height[i-2]);
            int val = Math.min(firstStep, secondStep);
            prev2 = prev;
            prev = val;
        }
        return prev;
        // TC -> O(n)
        // SC -> O(1)
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] heights = new int[n];
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            heights[i] = sc.nextInt();
            dp[i] = -1;
        }
        System.out.println(findMinimumEnergyRecursive(n-1, heights, dp));
        System.out.println(findMinimumEnergyTabulation(n, heights));
        System.out.println(findMinimumEnergySpaceOptimized(n, heights));
        sc.close();
    }
}
