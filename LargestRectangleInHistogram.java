import java.util.Scanner;
import java.util.Stack;

public class LargestRectangleInHistogram {

    private static int findMaxArea(int[] arr){
        int n = arr.length;
        int ans = 0;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i <= n; i++) {
            while (!stack.empty() && ((i == n ) || (arr[stack.peek()] >= arr[i]))) {
                int height = arr[stack.peek()];
                stack.pop();
                int width;
                if(stack.empty()){
                    width = i;
                }else{
                    width = (i - stack.peek() - 1);
                }
                ans = Math.max(ans, height * width);

            }
            stack.push(i);
        }
        return ans;
        // TC -> O(n) + O(n)
        // SC -> O(n)

    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        System.out.println(findMaxArea(arr));
        sc.close();
    }
}
