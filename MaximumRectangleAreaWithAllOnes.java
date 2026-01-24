import java.util.Scanner;
import java.util.Stack;

public class MaximumRectangleAreaWithAllOnes {

    private static int findMaxAreaInHistogram(int[] arr){
        int n = arr.length;

        Stack<Integer> stack = new Stack<>();

        int ans = 0;

        for (int i = 0; i <= n; i++) {
            while ((!stack.isEmpty()) && ((i == n) || (arr[stack.peek()] >= arr[i]))) {
                int height = arr[stack.peek()];
                stack.pop();
                int width;
                if(stack.isEmpty()){
                    width = i;
                }else{
                    width = i - stack.peek() - 1;
                }
                ans = Math.max(ans, height * width);
            }
            stack.push(i);
        }

        return ans;
        // TC -> O(m) + O(m)
        // SC -> O(m)
    }

    private static int findMaxArea(int[][] mat){
        int n = mat.length;
        int m = mat[0].length;

        int[] histogram = new int[m];
        for (int i = 0; i < m; i++) {
            histogram[i] = 0;
        }
        int ans = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // prepare the histogram
                if(mat[i][j] == 1){
                    histogram[j] += 1;
                }else{
                    histogram[j] = 0;
                }
            }

            ans = Math.max(ans, findMaxAreaInHistogram(histogram));
        }

        return ans;

        // TC -> O(n x m)
        // SC -> O(m)
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] mat = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                mat[i][j] = sc.nextInt();
            }
        }
        System.out.println(findMaxArea(mat));
        sc.close();
    }
}
