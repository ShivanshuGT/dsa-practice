import java.util.Scanner;
import java.util.Stack;

public class PreviousSmallerElement {

    private static int[] pse(int[] arr){
        int n = arr.length;
        int[] pse = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            while ((!stack.isEmpty()) && (stack.peek() >= arr[i])) {
                stack.pop();
            }
            pse[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(arr[i]);
        }
        return pse;
        // TC -> O(2 x n)
        // SC -> O(2 x n)
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        int[] pse = pse(arr);
        for (int i : pse) {
            System.out.print(i + " ");
        }
        sc.close();
    }
}
