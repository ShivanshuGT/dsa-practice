import java.util.Scanner;
import java.util.Stack;

public class NextGreaterElementII {

    private static int[] ngeBrute(int[] arr){
        int n = arr.length;
        int[] nge = new int[n];
        for (int i = 0; i < n; i++) {
            nge[i] = -1;
        }

        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < (i+n-1); j++) {
                int ind = j % n;
                if(arr[ind] > arr[i]){
                    nge[i] = arr[ind];
                    break;
                }
            }
        }
        return nge;
        // TC -> O(n x n)
        // SC -> O(n)
    }

    private static int[] ngeOptimal(int[] arr){
        int n = arr.length;
        Stack<Integer> stack = new Stack<>();
        int[] nge = new int[n];

        for (int i = (2*n -1); i >= 0; i--) {
            while ((!stack.isEmpty()) && (stack.peek() <= arr[i % n])) {
                stack.pop();
            }

            if(i < n){
                // we need to store nge for this index
                if(stack.isEmpty()){
                    nge[i] = -1;
                }else{
                    nge[i] = stack.peek();
                }
            }
            stack.push(arr[i % n]);
        }
        return nge;
        // TC -> O(4 x n)
        // SC -> O(3 x n)

    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        int[] ngeBrute = ngeBrute(arr);
        int[] ngeOptimal = ngeOptimal(arr);
        for (int i : ngeBrute) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i : ngeOptimal) {
            System.out.print(i + " ");
        }
        sc.close();
    }
}
