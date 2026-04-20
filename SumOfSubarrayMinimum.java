import java.util.Scanner;
import java.util.Stack;

public class SumOfSubarrayMinimum {

    private static int findSumBrute(int[] arr){
        int n = arr.length;
        int answer = 0;

        for (int i = 0; i < n; i++) {
            int mini = arr[i];
            for (int j = i; j < n; j++) {
                mini = Math.min(mini, arr[j]);
                answer += mini;
            }
            
        }
        return answer;
        // TC -> O(n x n)
        // SC -> O(1)
    }

    private static int[] findNextSmallerIndex(int[] arr){
        int n = arr.length;
        Stack<Integer> stack = new Stack<>();
        int[] ans = new int[n];
        ans[n-1] = n;

        for (int i = n-2; i >= 0; i--) {
            while ((!stack.isEmpty()) && (arr[stack.peek()] >= arr[i])) {
                stack.pop();
            }
            ans[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }
        return ans;
        // TC -> O(2 x n)
        // SC -> O(2 x n)
    }

    private static int[] findPreviousSmallerOrEqualIndex(int[] arr){
        int n = arr.length;
        Stack<Integer> stack = new Stack<>();
        int[] ans = new int[n];
        ans[0] = -1;

        for (int i = 1; i < n; i++) {
            while ((!stack.isEmpty()) && (arr[stack.peek()] > arr[i])) {
                stack.pop();
            }
            ans[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        return ans;
        // TC -> O(2 x n)
        // SC -> O(2 x n)
    }
    private static int findSumOptimal(int[] arr){
        int n = arr.length;
        int answer = 0;
        int[] nsi = findNextSmallerIndex(arr);
        int[] psei = findPreviousSmallerOrEqualIndex(arr);

        for (int i = 0; i < n; i++) {
            int left = i-psei[i];
            int right = nsi[i] - i;
            answer += (left * right * arr[i]);
        }
        return answer;
        // TC -> O(5 x n)
        // SC -> O(4 x n)
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        System.out.println(findSumBrute(arr));
        System.out.println(findSumOptimal(arr));
        sc.close();
    }
}
