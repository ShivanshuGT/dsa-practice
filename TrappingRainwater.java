import java.util.Scanner;

public class TrappingRainwater {

    private static int findTotalWaterBrute(int[] arr){
        int n = arr.length;

        int[] prefixMax = new int[n];
        int[] suffixMax = new int[n];
        int answer = 0;

        prefixMax[0] = arr[0];
        for (int i = 1; i < n; i++) {
            prefixMax[i] = Math.max(prefixMax[i-1], arr[i]);
        }

        suffixMax[n-1] = arr[n-1];
        for (int i = n-2; i >= 0; i--) {
            suffixMax[i] = Math.max(suffixMax[i+1], arr[i]);
        }

        for (int i = 0; i < n; i++) {
            int leftMax = prefixMax[i];
            int rightMax = suffixMax[i];

            if((arr[i] < leftMax) && (arr[i] < rightMax)){
                answer += Math.min(leftMax, rightMax) - arr[i];
            }
        }
        return answer;
        // TC -> O(3 x n)
        // SC -> O(2 x n)
    }

    private static int findTotalWaterOptimal(int[] arr){
        int n = arr.length;
        int answer = 0;
        int leftMax = 0;
        int rightMax = 0;
        int left = 0;
        int right = n-1;

        while (left < right) {
            if(arr[left] <= arr[right]){
                if(arr[left] < leftMax){
                    answer += leftMax - arr[left];
                }else{
                    leftMax = arr[left];
                }
                left += 1;
            }else{
                if(arr[right] < rightMax){
                    answer += rightMax - arr[right];
                }else{
                    rightMax = arr[right];
                }
                right -= 1;
            }
        }
        return answer;
        // TC -> O(n)
        // SC -> O(1)
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        System.out.println(findTotalWaterBrute(arr));
        System.out.println(findTotalWaterOptimal(arr));
        sc.close();
    }
}
