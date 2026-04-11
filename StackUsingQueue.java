import java.util.LinkedList;
import java.util.Queue;

public class StackUsingQueue {
    private Queue<Integer> queue = new LinkedList<>();

    public void push(int x){
        int size = this.queue.size();
        this.queue.add(x);

        for (int i = 1; i <= size; i++) {
            this.queue.add(this.queue.peek());
            this.queue.poll();
        }
        // TC -> O(n)
        // SC -> O(n)
    }

    public int pop(){
        if(!this.queue.isEmpty()){
            return this.queue.poll();
        }else{
            return -1;
        }
        // TC -> O(1)
        // SC -> O(n)
    }

    public int top(){
        if(!this.queue.isEmpty()){
            return this.queue.peek();
        }else{
            return -1;
        }
        // TC -> O(1)
        // SC -> O(n)
    }

    public static void main(String[] args) {
        StackUsingQueue stack = new StackUsingQueue();
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
