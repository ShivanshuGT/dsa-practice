import java.util.Stack;

class Pair{
    int first;
    int second;

    Pair(int first, int second){
        this.first = first;
        this.second = second;
    }
}
class MinStackImpl{
    private Stack<Pair> stack = new Stack<>();

    public void push(int x){
        if(this.stack.isEmpty()){
            this.stack.push(new Pair(x, x));
        }else{
            Pair top = this.stack.peek();
            if(top.second < x){
                this.stack.push(new Pair(x, top.second));
            }else{
                this.stack.push(new Pair(x, x));
            }
        }
        // TC -> O(1)
        // SC -> O(1)
    }

    public int pop(){
        if(this.stack.isEmpty()){
            System.out.println("Stack is empty... can't pop");
            return -1;
        }else{
            return this.stack.pop().first;
        }
        // TC -> O(1)
        // SC -> O(1)
    }

    public int top(){
        if(this.stack.isEmpty()){
            System.out.println("Stack is empty... can't find top");
            return -1;
        }else{
            return this.stack.peek().first;
        }
        // TC -> O(1)
        // SC -> O(1)
    }

    public int getMin(){
        if(this.stack.isEmpty()){
            System.out.println("Stack is empty... can't find min element");
            return -1;
        }else{
            return this.stack.peek().second;
        }
        // TC -> O(1)
        // SC -> O(1)
    }

    // TC -> O(1)
    // SC -> O(2 x n)
}

class MinStackOptimisedImpl{

    private int mini = Integer.MAX_VALUE;
    private Stack<Integer> stack = new Stack<>();

    public void push(int x){
        if(this.stack.isEmpty()){
            this.mini = x;
            this.stack.push(x);
        }else{
            if(x > mini){
                this.stack.push(x);
            }else{
                this.stack.push((2 * x) - mini);
                this.mini = x;
            }
        }
        // TC -> O(1)
        // SC -> O(1)
    }

    public int pop(){
        if(this.stack.isEmpty()){
            System.out.println("Stack is empty... can't pop");
            return -1;
        }else{
            int top = this.stack.peek();
            if(top < mini){
                // this is the case of modification
                int answer = mini;
                mini = (2 * mini) - top;
                this.stack.pop();
                return answer;
            }else{
                // this is the simple pop case
                return this.stack.pop();
            }
        }
        // TC -> O(1)
        // SC -> O(1)
    }

    public int top(){
        if(this.stack.isEmpty()){
            System.out.println("Stack is empty... can't find top");
            return -1;
        }else{
            int top = this.stack.peek();
            if(top < mini){
                // this is the case of modification
                return mini;
            }else{
                return top;
            }
        }
        // TC -> O(1)
        // SC -> O(1)
    }

    public int getMin(){
        if(this.stack.isEmpty()){
            System.out.println("Stack is empty... can't find min");
            return -1;
        }else{
            return this.mini;
        }
        // TC -> O(1)
        // SC -> O(1)
    }

    // TC -> O(1)
    // SC -> O(n)

}

public class MinStack {


    public static void main(String[] args) {
        // MinStackImpl stack = new MinStackImpl();
        MinStackOptimisedImpl stack = new MinStackOptimisedImpl();
        stack.push(12);
        stack.push(15);
        stack.push(10);
        System.out.println(stack.getMin());
        System.out.println(stack.pop());
        System.out.println(stack.getMin());
        System.out.println(stack.top());
        stack.push(10);
        System.out.println(stack.top());
    }
}
