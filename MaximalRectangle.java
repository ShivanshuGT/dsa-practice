import java.util.Scanner;
import java.util.Stack;

public class MaximalRectangle {

    private static int findMaximumRectangleInHistogram(int[] arr){
        int n = arr.length;
        Stack<Integer> stack = new Stack<>();
        int ans = 0;

        for (int i = 0; i < n; i++) {
            while ((!stack.isEmpty()) && (arr[stack.peek()] > arr[i])) {
                int element = stack.pop();
                int psi = stack.isEmpty() ? -1 : stack.peek();
                int nsi = i;
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

    private static int findMaximalRectangle(int[][] mat){

        int n = mat.length;
        int m = mat[0].length;
        int ans = 0;

        // calculate the prefix sum matrix
        int[][] prefixSum = new int[n][m];
        prefixSum[0] = mat[0];

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                prefixSum[i][j] = (mat[i][j] == 0) ? 0 : prefixSum[i-1][j] + mat[i][j];
            }
        }

        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, findMaximumRectangleInHistogram(prefixSum[i]));
        }
        return ans;
        // TC -> O(3 x n x m)
        // SC -> O(n x m)
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++){
                arr[i][j] = sc.nextInt();
            }
        }
        System.out.println(findMaximalRectangle(arr));
        sc.close();
    }
}
