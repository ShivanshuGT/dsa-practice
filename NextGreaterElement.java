import java.util.Scanner;
import java.util.Stack;

public class NextGreaterElement {

    private static int[] nextGreaterElement(int[] arr){
        int n = arr.length;
        Stack<Integer> stack = new Stack<>();
        int[] nge = new int[n];
        nge[n-1] = -1;

        for (int i = n-1; i >= 0; i--) {
            while (!stack.isEmpty() && (stack.peek() <= arr[i])) {
                stack.pop();
            }
            if(stack.isEmpty()){
                nge[i] = -1;
            }else{
                nge[i] = stack.peek();
            }
            stack.push(arr[i]);
        }
        return nge;
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
        int[] nge = nextGreaterElement(arr);
        for (int i : nge) {
            System.out.print(i+ " ");
        }
        sc.close();
    }
}
