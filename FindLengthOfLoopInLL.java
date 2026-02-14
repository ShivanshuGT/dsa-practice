import java.util.HashMap;


public class FindLengthOfLoopInLL {

    static class Node {
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

    private static int findLengthBrute(Node head){
        if(head == null || (head.next == null)){
            return 0;
        }

        HashMap<Node, Integer> map = new HashMap<>();

        Node temp = head;
        int timer = 1;

        while (temp != null) {
            if(map.containsKey(temp)){
                return timer - map.get(temp);
            }
            map.put(temp, timer);
            timer += 1;
            temp = temp.next;
        }
        return 0;
        // TC -> O(n x log(n))
        // SC -> O(n)
    }

    private static int findLengthOptimalHelper(Node slow, Node fast){
        int count = 1;
        fast = fast.next;

        while (slow != fast) {
            fast = fast.next;
            count += 1;
        }
        return count;
        // TC -> O(len(loop))
    }

    private static int findLengthOptimal(Node head){
        if(head == null || (head.next == null)){
            return 0;
        }

        Node slow = head;
        Node fast = head;

        while ((fast != null) && (fast.next != null)) {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                return findLengthOptimalHelper(slow, fast);
            }

        }
        return 0;
        // TC -> O(n) + O(len(loop))
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

        System.out.println(findLengthBrute(head));
        System.out.println(findLengthOptimal(head));
    }
}
