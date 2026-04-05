import java.util.Scanner;

public class ValidParenthesis {

    private static boolean isValidParenthesisRecursiveHelper(String str, int ind, int count){
        int n = str.length();

        if(count < 0){
            return false;
        }

        if(ind == n){
            return (count == 0);
        }

        Character ch = str.charAt(ind);

        if(ch == '('){
            return isValidParenthesisRecursiveHelper(str, ind+1, count+1);
        }
        else if(ch == ')'){
            return isValidParenthesisRecursiveHelper(str, ind+1, count-1);
        }else{
            return isValidParenthesisRecursiveHelper(str, ind+1, count+1) || // * replaced by (
            isValidParenthesisRecursiveHelper(str, ind+1, count) || // * replaced by empty char
            isValidParenthesisRecursiveHelper(str, ind+1, count-1); // * replaced by )
        }

        // TC ->O(3 ^ n)
        // SC -> O(n)
    }

    private static boolean isValidParenthesisRecursive(String str){
        return isValidParenthesisRecursiveHelper(str, 0, 0);
        // TC -> O(3 ^ n)
        // SC -> O(n)
    }

    private static boolean isValidParenthesisOptimal(String str){
        int min = 0;
        int max = 0;
        for (Character ch : str.toCharArray()) {
            if(ch == '('){
                min += 1;
                max += 1;
            }else if(ch == ')'){
                min -= 1;
                max -= 1;
            }else{
                min -= 1;
                max += 1;
            }

            if(min < 0){
                min = 0;
            }

            if(max < 0){
                return false;
            }
        }

        if(min == 0){
            return true;
        }
        return false;
        // TC -> O(n)
        // SC -> O(1)
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        System.out.println(isValidParenthesisRecursive(str));
        System.out.println(isValidParenthesisOptimal(str));
        sc.close();
    }
}
