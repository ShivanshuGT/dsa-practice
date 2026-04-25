import java.util.Scanner;
import java.util.Stack;

public class LargestRectangleInHistogramII {

    private static int[] findPreviousSmallerIndex(int[] arr){
        int n = arr.length;
        int[] ans = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            while ((!stack.isEmpty()) && (arr[stack.peek()] >= arr[i])) {
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

    private static int computeLargestAreaBrute(int[] arr){
        int n = arr.length;
        int[] psi = findPreviousSmallerIndex(arr);
        int[] nsi = findNextSmallerIndex(arr);
        int ans = 0;

        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, arr[i] * (nsi[i] - psi[i] -1));
        }
        return ans;
        // TC -> O(5 x n)
        // SC -> O(4 x n)

    }

    private static int computeLargestAreaOptimal(int[] arr){
        int n = arr.length;
        Stack<Integer> stack = new Stack<>();
        int ans = 0;

        for (int i = 0; i < n; i++) {
            while ((!stack.isEmpty()) && (arr[stack.peek()] > arr[i])) {
                int element = stack.pop();
                int nsi = i;
                int psi = stack.isEmpty() ? -1 : stack.peek();
                ans = Math.max(ans, arr[element] * (nsi - psi - 1));
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int element = stack.pop();
            int nsi = n;
            int psi = stack.isEmpty() ? -1 : stack.peek();
            ans = Math.max(ans, arr[element] * (nsi - psi - 1));
        }
        return ans;
        // TC -> O(2 x n)
        // SC -> O(n)
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int arr[] = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        System.out.println(computeLargestAreaBrute(arr));
        System.out.println(computeLargestAreaOptimal(arr));
        sc.close();
    }
}
