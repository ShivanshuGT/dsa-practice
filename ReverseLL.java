import java.util.Stack;

public class ReverseLL {

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

    private static Node buildLLFromArr(int[] arr){
        int n = arr.length;
        Node head = new Node(arr[0]);
        Node temp = head;
        for (int i = 1; i < n; i++) {
            temp.next = new Node(arr[i]);
            temp = temp.next;
        }
        return head;
        // TC -> O(n)
        // SC -> O(1)
    }

    private static void traverse(Node head){
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        // TC -> O(n)
        // SC -> O(1)
    }

    private static Node reverseBrute(Node head){
        if(head == null || (head.next == null)){
            return head;
        }

        Stack<Integer> stack = new Stack<>();
        Node temp = head;
        while (temp != null) {
            stack.add(temp.data);
            temp = temp.next;
        }

        temp = head;
        while (!stack.isEmpty()) {
            temp.data = stack.pop();
            temp = temp.next;
        }
        return head;
        // TC -> O(2 x n)
        // SC -> O(n)
    }

    private static Node reverseOptimal(Node head){
        if(head == null || (head.next == null)){
            return head;
        }

        Node temp = head;
        Node prev = null;

        while (temp != null) {
            Node front = temp.next;
            temp.next = prev;
            prev = temp;
            temp = front;
        }
        return prev;
        // TC -> O(n)
        // SC -> O(1)
    }

    private static Node reverseRecursive(Node head){
        // base case
        if(head == null || (head.next == null)){
            return head;
        }

        Node newHead = reverseRecursive(head.next);
        Node front = head.next;
        front.next = head;
        head.next = null;
        return newHead;
        // TC -> O(n)
        // SC -> O(n)
    }
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        Node head = buildLLFromArr(arr);

        // head = reverseBrute(head);
        // traverse(head);

        // head = reverseOptimal(head);
        // traverse(head);

        head = reverseRecursive(head);
        traverse(head);

    }
}
