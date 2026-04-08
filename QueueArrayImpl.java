public class QueueArrayImpl {
    private int start = -1;
    private int end = -1;
    private int curSize = 0;
    private int[] queue;

    QueueArrayImpl(int size){
        this.queue = new int[size];
    }

    public void push(int x){

        if(this.curSize == (this.queue.length)){
            // queue is full
            System.out.println("Queue is full... can't insert new item");
            return;
        }
        if(this.curSize == 0){
            this.start = 0;
            this.end = 0;
        }else{
            this.end += (this.end+1) % (this.queue.length);
        }
        
        this.queue[end] = x;
        this.curSize += 1;
        // TC -> O(1)
        // SC -> O(1)
    }

    public int pop(){
        if(this.curSize == 0){
            // queue is empty
            System.out.println("Queue is empty... can't pop");
            return -1;
        }

        if(this.curSize == 1){
            // last element is present
            int element = this.queue[this.start];
            this.start = -1;
            this.end = -1;
            this.curSize -= 1;
            return element;
        }
        int element = this.queue[this.start];
        this.start = (this.start + 1) % (this.queue.length);
        this.curSize -= 1;
        return element;
        // TC -> O(1)
        // SC -> O(1)

    }

    public int top(){
        if(this.curSize == 0){
            System.out.println("Queue is empty... can't find top");
            return -1;
        }

        return this.queue[this.start];
        // TC -> O(1)
        // SC -> O(1)
    }

    public int size(){
        return this.curSize;
        // TC -> O(1)
        // SC -> O(1)
    }

    // TC -> O(1)
    // SC -> O(n)

    public static void main(String[] args) {
        QueueArrayImpl queue = new QueueArrayImpl(10);
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
