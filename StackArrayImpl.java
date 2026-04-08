public class StackArrayImpl {

    private int top = -1;
    private int[] stack;

    StackArrayImpl(int size){
        this.stack = new int[size];
    }

    public void push(int x){
        if(top == (this.stack.length-1)){
            System.out.println("Stack is full... can't push new element");
            return;
        }

        top = top + 1;
        this.stack[top] = x;
        // TC -> O(1)
        // SC -> O(1)
    }

    public int pop(){
        if(top == -1){
            System.out.println("Stack is empty... can't pop");
            return -1;
        }

        int element = this.stack[top];
        top -= 1;
        return element;
        // TC -> O(1)
        // SC -> O(1)
    }

    public int top(){
        if(top == -1){
            System.out.println("Stack is empty... no top element exists");
            return -1;
        }
        return stack[top];
        // TC -> O(1)
        // SC -> O(1)
    }

    public int size(){
        return top + 1;
        // TC -> O(1)
        // SC -> O(1)
    }

    // TC -> O(1)
    // SC - > O(n)

    public static void main(String[] args) {
        StackArrayImpl stack = new StackArrayImpl(10);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.top());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.top());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        stack.push(4);
        System.out.println(stack.top());
    }


    
}
