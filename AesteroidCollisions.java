import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class AesteroidCollisions {

    private static List<Integer> getFinalState(int[] arr){
        int n = arr.length;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            if(arr[i] > 0){
                // it's a positive element
                stack.push(arr[i]);
            }else{
                // it's a negative element

                while ((!stack.isEmpty()) && (stack.peek() > 0) && (stack.peek() < Math.abs(arr[i]))) {
                    stack.pop();
                }

                if(!stack.isEmpty() && (stack.peek() == Math.abs(arr[i]))){
                    stack.pop();
                }else if(stack.isEmpty() || (stack.peek() < 0) ){
                    stack.push(arr[i]);
                }
            }
        }

        List<Integer> ans = new ArrayList<>();
        while (!stack.isEmpty()) {
            ans.add(stack.pop());
        }
        return ans.reversed();
        // TC -> O(4 x n)
        // SC -> O(2 x n)
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        List<Integer> ans = getFinalState(arr);
        for (Integer i : ans) {
            System.out.println(i + " ");
        }
        sc.close();
    }
}
