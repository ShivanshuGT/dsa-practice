import java.util.Stack;

public class QueueUsingStack {

    // this approach is expensive when there are more push operations than pop and peek operations
    private int curSize;
    private Stack<Integer> s1 = new Stack<>();
    private Stack<Integer> s2 = new Stack<>();

    public void push(int x){
        // s1 -> s2
        // x -> s1
        // s2 -> s1

        while (!this.s1.isEmpty()) {
            this.s2.add(this.s1.peek());
            this.s1.pop();
        }

        this.s1.add(x);

        while (!this.s2.isEmpty()) {
            this.s1.add(this.s2.peek());
            this.s2.pop(); 
        }
        this.curSize += 1;
        // TC -> O(2 x n)
        // SC -> O(2 x n)
    }

    public int pop(){
       if(this.s1.empty()){
            return -1;
       }else{
            this.curSize -= 1;
            return this.s1.pop();
       }
        // TC -> O(1)
        // SC -> O(2 x n)
    }

    public int top(){
        if(this.s1.empty()){
            return -1;
        }else{
            return this.s1.peek();
        }
        // TC -> O(1)
        // SC -> O(2 x n)
    }

    public int size(){
        return this.curSize;
    }

    public static void main(String[] args) {
        QueueUsingStack queue = new QueueUsingStack();
        System.out.println(queue.pop());
        System.out.println(queue.top());
        queue.push(1);
        queue.push(2);
        System.out.println(queue.pop());
        System.out.println(queue.top());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
    }

}
