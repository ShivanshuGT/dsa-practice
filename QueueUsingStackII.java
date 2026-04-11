import java.util.Stack;

public class QueueUsingStackII {

    // this approach is expensive when there are more pop and peek operations than push operations
    int curSize;
    private Stack<Integer> s1 = new Stack<>();
    private Stack<Integer> s2 = new Stack<>();

    public void push(int x){
        this.s1.push(x);
        this.curSize += 1;
        // TC -> O(1)
        // SC -> O(2 x n)
    }

    public int pop(){
        if(!this.s2.isEmpty()){
            this.curSize -= 1;
            return this.s2.pop();
        }else if(!this.s1.isEmpty()){
            while (!this.s1.isEmpty()) {
                this.s2.push(this.s1.peek());
                this.s1.pop();

            }
            this.curSize -= 1;
            return this.s2.pop();
        }else{
            return -1;
        }
        // TC -> O(n)
        // SC -> O(2 x n)
    }

    public int top(){
        if(!this.s2.isEmpty()){
            return this.s2.peek();
        }else if(!this.s1.isEmpty()){
            while (!this.s1.isEmpty()) {
                this.s2.push(this.s1.peek());
                this.s1.pop();
            }
            return this.s2.peek();
        }else{
            return -1;
        }
        // TC -> O(n)
        // SC -> O(2 x n)
    }

    public int size(){
        return this.curSize;
    }

    public static void main(String[] args) {
        QueueUsingStackII queue = new QueueUsingStackII();
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
