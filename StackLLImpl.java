class Node{
    int data;
    Node next;

    Node(int data){
        this.data = data;
    }
}

public class StackLLImpl {

    int size = 0;
    Node top = null;

    public void push(int x){
        Node newNode = new Node(x);

        newNode.next = this.top;
        this.top = newNode;
        this.size += 1;
        // TC -> O(1)
        // SC -> O(1)
    }

    public Node pop(){
        if(this.size == 0){
            System.out.println("Stack is empty... can't pop");
            return null;
        }

        Node temp = this.top;
        this.top = this.top.next;
        size -= 1;
        return temp;

        // TC -> O(1)
        // SC -> O(1)
    }

    public Node top(){
        return this.top;
        // TC -> O(1)
        // SC -> O(1)
    }

    public int size(){
        return this.size;
        // TC -> O(1)
        // SC -< O(1)
    }

    // TC -> O(1)
    // SC -> O(n)

    private static int getData(Node node){
        if(node ==  null){
            return -1;
        }
        return node.data;
    }
    
    public static void main(String[] args) {
        StackLLImpl stack = new StackLLImpl();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(getData(stack.top()));
        System.out.println(getData(stack.pop()));
        System.out.println(getData(stack.pop()));
        System.out.println(getData(stack.top()));
        System.out.println(getData(stack.pop()));
        System.out.println(getData(stack.pop()));
        stack.push(4);
        System.out.println(getData(stack.top()));
    }
}
