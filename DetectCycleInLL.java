import java.util.HashMap;

public class DetectCycleInLL {

    static class Node{
        int data;
        Node next;

        Node(int data){
            this.data = data;
        }

        Node(int data, Node next){
            this.data = data;
            this.next = next;
        }
    }

    private static boolean hasCycleBrute(Node head){
        if(head == null || (head.next == null)){
            return false;
        }

        HashMap<Node, Integer> map = new HashMap<>();

        Node temp = head;
        while (temp != null) {
            if(map.containsKey(temp)){
                return true;
            }
            map.put(temp, 1);
            temp = temp.next;
        }

        return false;
        // TC -> O(n x log(n))
        // SC -> O(n)
    }

    private static boolean hasCycleOptimal(Node head){
        if(head == null || (head.next == null)){
            return false;
        }

        Node slow = head;
        Node fast = head;

        while ((fast != null) && (fast.next != null)) {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                return true;
            }
        }
        return false;
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
        temp.next = point;

        System.out.println(hasCycleBrute(head));
        System.out.println(hasCycleOptimal(head));

    }
}
