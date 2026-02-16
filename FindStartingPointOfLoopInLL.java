import java.util.HashMap;

public class FindStartingPointOfLoopInLL {

    static class Node{
        int data;
        Node next;

        Node(int data, Node next){
            this.data = data;
            this.next = next;
        }

        Node(int data){
            this.data = data;
        }
    }

    private static Node findStartOfLoopBrute(Node head){
        if(head == null || (head.next == null)){
            return null;
        }

        HashMap<Node, Integer> map = new HashMap<>();
        Node temp = head;

        while (temp != null) {
            if(map.containsKey(temp)){
                return temp;
            }
            map.put(temp, 1);
            temp = temp.next;
        }
        return null;
        // TC -> O(n x log(n))
        // SC -> O(n)
    }

    private static Node findStartOfLoopOptimal(Node head){
        if(head == null || (head.next == null)){
            return null;
        }

        Node fast = head;
        Node slow = head;

        while ((fast != null) && (fast.next != null)) {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                slow = head;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }
        return null;
        // TC -> O(n)
        // SC -> O(1)
    }    
    public static void main(String[] args) {
        Node head = new Node(1);
        Node temp = head;
        temp.next = new Node(2);
        temp = temp.next;
        temp.next = new Node(3);
        Node point = temp.next;
        temp = temp.next;
        temp.next = new Node(4);
        temp = temp.next;
        temp.next = new Node(5);
        temp = temp.next;
        temp.next = new Node(6);
        temp = temp.next;
        temp.next = new Node(7);
        temp = temp.next;
        temp.next = new Node(8);
        temp = temp.next;
        temp.next = new Node(9);
        temp = temp.next;
        // temp.next = point;

        Node startBrute = findStartOfLoopBrute(head);
        Node startOptimal = findStartOfLoopOptimal(head);
        System.out.println(startBrute == null ? "No loop detected" : startBrute.data);
        System.out.println(startOptimal == null ? "No loop detected" : startOptimal.data);
    }
}
