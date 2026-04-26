import java.util.Scanner;
import java.util.Stack;

public class RemoveKDigits {

    private static String getSmallestNumber(String str, int k){
        int n = str.length();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            Character ch = str.charAt(i);
            while ((!stack.isEmpty()) && (k > 0) && ((stack.peek()-'0') > (ch-'0'))){
                stack.pop();
                k -= 1;
            }
            stack.push(ch);
        }

        while ((k > 0) && (!stack.isEmpty())) {
            // case where we are not able to pop any char out
            // example str = '123456'
            // then we need to pop the last digits from the number
            stack.pop();
        }

        if(stack.isEmpty()){
            return "0";
        }

        // getting the digits from the stack to the StringBuilder now

        StringBuilder ans = new StringBuilder();
        while (!stack.isEmpty()) {
            ans.append(stack.pop());
        }
        
        // remove the trailling zeroes
        while ((ans.length() > 0) && (ans.charAt(ans.length()-1) == '0')) {
            ans = ans.deleteCharAt(ans.length()-1);
        }

        if(ans.length() == 0){
            return "0";
        }
        return ans.reverse().toString();
        // TC -> O(4 x n) + O(k)
        // SC -> O(2 x n)

    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        String str = sc.next();
        System.out.println(getSmallestNumber(str, k));
        sc.close();
    }
}
