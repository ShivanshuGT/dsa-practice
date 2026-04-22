import java.util.Scanner;
import java.util.Stack;

public class SumOfSubarrayRanges {

    private static int findRangesSumBrute(int[] arr){
        int n = arr.length;
        int answer = 0;
        
        for (int i = 0; i < n; i++) {
            int largest = arr[i];
            int smallest = arr[i];
            for (int j = i+1; j < n; j++) {
                largest = Math.max(largest, arr[j]);
                smallest = Math.min(smallest, arr[j]);
                answer += largest - smallest;
            }
        }
        return answer;
        // TC -> O(n x n)
        // SC -> O(1)
    }

    private static int[] findPreviousSmallerOrEqualIndex(int[] arr){
        int n = arr.length;
        int[] ans = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
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

    private static int[] findPreviousGreaterOrEqualIndex(int[] arr){
        int n = arr.length;
        int[] ans = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            while ((!stack.isEmpty()) && (arr[stack.peek()] < arr[i])) {
                stack.pop();
            }
            ans[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        return ans;
        // TC -> O(2 x n)
        // SC -> O(2 x n)
    }

    private static int[] findNextSmallerIndex(int[] arr){
        int n = arr.length;
        int[] ans = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = n-1; i >= 0; i--) {
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

    private static int[] findNextGreaterIndex(int[] arr){
        int n = arr.length;
        int[] ans = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = n-1; i >= 0; i--) {
            while ((!stack.isEmpty()) && (arr[stack.peek()] <= arr[i])) {
                stack.pop();
            }
            ans[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }
        return ans;
        // TC -> O(2 x n)
        // SC -> O(2 x n)
    }

    private static int findMinimumSum(int[] arr){
        int n = arr.length;
        int answer = 0;
        int[] psei = findPreviousSmallerOrEqualIndex(arr);
        int[] nsi = findNextSmallerIndex(arr);

        for (int i = 0; i < n; i++) {
            int left = i - psei[i];
            int right = nsi[i] - i;
            answer += (right * left * arr[i]);
        }
        return answer;
        // TC -> O(5 x n)
        // SC -> O(4 x n)
    }

    private static int findMaximumSum(int[] arr){
        int n = arr.length;
        int answer = 0;
        int[] pgei = findPreviousGreaterOrEqualIndex(arr);
        int[] ngi = findNextGreaterIndex(arr);

        for (int i = 0; i < n; i++) {
            int left = i - pgei[i];
            int right = ngi[i] - i;
            answer += right * left * arr[i];
        }
        return answer;
        // TC -> O(5 x n)
        // SC -> O(4 x n)
    }

    private static int findRangesSumOptimal(int[] arr){
        return findMaximumSum(arr) - findMinimumSum(arr);
        // Tc -> O(10 x n)
        // SC -> O(4 x n)
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        System.out.println(findRangesSumBrute(arr));
        System.out.println(findRangesSumOptimal(arr));
        sc.close();
    }
}
