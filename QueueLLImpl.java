class Node{
    int data;
    Node next;

    Node(int data){
        this.data = data;
    }
}
public class QueueLLImpl {

    private Node start = null;
    private Node end = null;
    private int size = 0;

    public void push(int x){
        Node newNode = new Node(x);
        if(this.size == 0){
            this.start = newNode;
            this.end = newNode;
        }

        this.end.next = newNode;
        this.end = newNode;
        size += 1;
        // TC -> O(1)
        // SC -> O(1)
    }

    public Node pop(){
        if(this.size == 0){
            System.out.println("Queue is empty... can't pop");
            return null;
        }
        Node temp = this.start;

        if(this.size == 1){
            this.start = null;
            this.end = null;
        }else{
            this.start = this.start.next;
        }

        
        size -= 1;
        return temp;
        // TC -> O(1)
        // SC -> O(1)
    }

    public Node top(){
        return this.start;
        // TC -> O(1)
        // SC -> O(1)
    }

    public int size(){
        return this.size;
        // TC -> O(1)
        // SC -> O(1)
    }

    // TC -> O(1)
    // SC -> O(n)

    private static int getData(Node node){
        return (node ==  null) ? -1 : node.data;
    }
    public static void main(String[] args) {
        QueueLLImpl queue = new QueueLLImpl();
        System.out.println(getData(queue.pop()));
        System.out.println(getData(queue.top()));
        queue.push(1);
        queue.push(2);
        System.out.println(getData(queue.pop()));
        System.out.println(getData(queue.top()));
        System.out.println(getData(queue.pop()));
        System.out.println(getData(queue.pop()));
    }
    
}
