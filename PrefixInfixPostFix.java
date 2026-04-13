import java.util.Scanner;
import java.util.Stack;

public class PrefixInfixPostFix {

    // priorities of operators
    // ^ -> 3
    // * / -> 2
    // + - -> 1
    // else -> -1

    private static int getPriority(Character ch){
        if(ch == '^'){
            return 3;
        }else if((ch == '*') || (ch == '/')){
            return 2;
        }else if((ch == '+') || (ch == '-')){
            return 1;
        }else{
            return -1;
        }
        // TC -> O(1)
        // SC -> O(1)
    }

    private static String infixToPostFix(String input){
        int n = input.length();
        Stack<Character> stack = new Stack<>();
        StringBuilder answer = new StringBuilder();

        for (int i = 0; i < n; i++) {
            Character ch = input.charAt(i);

            if((ch >= 'A' && ch <= 'Z') ||
                (ch >= 'a' && ch <= 'z') ||
                (ch >= '0' && ch <= '9'))
            {
                // it's an operand
                answer.append(ch);
            }else if(ch == '('){
                stack.push(ch);
            }else if(ch == ')'){
                while ((!stack.isEmpty()) && (stack.peek() != '(')) {
                    answer.append(stack.pop());
                }
                stack.pop();
            }else{
                // it's an operator
                while ((!stack.isEmpty()) && (getPriority(stack.peek()) >= getPriority(ch))){
                    answer.append(stack.pop());
                }
                stack.push(ch);
            }

        }

        while (!stack.isEmpty()) {
            answer.append(stack.pop());
        }
        return answer.toString();
        // TC -> O(2 x n)
        // SC -> O(2 x n)
    }

    private static String infixToPrefix(String input){
        int n = input.length();
        Stack<Character> stack = new Stack<>();
        StringBuilder answer = new StringBuilder();

        // reverse the input
        input = new StringBuilder(input).reverse().toString();

        // replace '(' by ')' and vice versa
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            Character ch = input.charAt(i);
            if(ch == '('){
                builder.append(')');
            }else if(ch == ')'){
                builder.append('(');
            }else{
                builder.append(ch);
            }
        }
        input = builder.toString();

        for (int i = 0; i < n; i++) {
            Character ch = input.charAt(i);

            if((ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z') || (ch >= '0' && ch <= '9')){
                // it's an operand
                answer.append(ch);
            }else if(ch == '('){
                stack.push(ch);
            }else if(ch == ')'){
                while ((!stack.isEmpty()) && (stack.peek() != '(')) {
                    answer.append(stack.pop());
                }
                stack.pop();
            }else{
                // it's an operand
                if(ch == '^'){
                    while ((!stack.isEmpty()) && (getPriority(stack.peek()) >= getPriority(ch))) {
                        answer.append(stack.pop());
                    }
                    stack.push(ch);
                }else{
                    while ((!stack.isEmpty()) && (getPriority(stack.peek()) > getPriority(ch))) {
                        answer.append(stack.pop());
                    }
                    stack.push(ch);
                }
            }
        }

        while (!stack.isEmpty()) {
            answer.append(stack.pop());
        }

        // reverse the answer again
        answer = answer.reverse();
        return answer.toString();
        // TC -> O(4 x n)
        // SC -> O(3 x n)

    }

    private static String postfixToInfix(String input){
        int n = input.length();
        Stack<String> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            Character ch = input.charAt(i);

            if((ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z') || (ch >= '0' && ch <= '9')){
                // it's an operand
                stack.push(String.valueOf(ch));
            }else{
                // it's an operator
                String top1 = stack.pop();
                String top2 = stack.pop();
                stack.push('(' + top2 + String.valueOf(ch) + top1 + ')');
            }
        }
        return stack.pop();
        // TC -> O(2 x n)
        // SC -> O(n)
    }

    private static String prefixToInfix(String input){
        int n = input.length();
        Stack<String> stack = new Stack<>();

        for (int i = n-1; i >= 0; i--) {
            Character ch = input.charAt(i);

            if((ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z') || (ch >= '0' && ch <= '9')){
                // it's an operand
                stack.push(String.valueOf(ch));
            }else{
                // it's an operator
                String top1 = stack.pop();
                String top2 = stack.pop();
                stack.push('(' + top1 + String.valueOf(ch) + top2 + ')');
            }
        }
        return stack.pop();
        // TC -> O(2 x n)
        // SC -> O(n)
    }

    private static String postfixToPrefix(String input){
        int n = input.length();
        Stack<String> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            Character ch = input.charAt(i);
            if((ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z') || (ch >= '0' && ch <= '9')){
                // it's an operand
                stack.push(String.valueOf(ch));
            }else{
                String top1 = stack.pop();
                String top2 = stack.pop();
                stack.push(String.valueOf(ch) + top2 + top1);
            }
        }
        return stack.pop();
        // TC -> O(2 x n)
        // SC -> O(n)
    }

    private static String prefixToPostfix(String input){
        int n = input.length();
        Stack<String> stack = new Stack<>();

        for (int i = n-1; i >= 0; i--) {
            Character ch = input.charAt(i);

            if((ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z') || (ch >= '0' && ch <= '9')){
                // it's an operand
                stack.push(String.valueOf(ch));
            }else{
                // it's an operator
                String top1 = stack.pop();
                String top2 = stack.pop();
                stack.push(top1 + top2 + String.valueOf(ch));
            }
        }
        return stack.pop();
        // TC -> O(2 x n)
        // SC -> O(n)
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        // System.out.println(infixToPostFix(input));
        // System.out.println(infixToPrefix(input));
        // System.out.println(postfixToInfix(input));
        // System.out.println(prefixToInfix(input));
        // System.out.println(postfixToPrefix(input));
        System.out.println(prefixToPostfix(input));
        sc.close();
    }
}
