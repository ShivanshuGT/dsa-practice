import java.util.Scanner;
import java.util.Stack;

public class BalancedParenthesis {

    private static boolean checkString(String str){
        Stack<Character> stack = new Stack<>();
        int n = str.length();

        for (int j = 0; j < n ; j++) {
            Character ch = str.charAt(j);
            if((ch == '(') || (ch == '[') || (ch == '{')){
                // it's an opening character
                stack.push(ch);
            }else{
                // it's a closing character

                if(stack.isEmpty()){
                    // there is no corresponding opening character for this closing character
                    return false;
                }else{
                    Character i = stack.pop();
                    if((ch == ')' && i == '(') || 
                        (ch == '}' && i == '{') ||
                        (ch == ']' && i == '[')
                    ){
                        continue;
                    }else{
                        return false;
                    }
                }
            }
        }
        return stack.isEmpty();
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        System.out.println(checkString(str));
        sc.close();
    }
}
