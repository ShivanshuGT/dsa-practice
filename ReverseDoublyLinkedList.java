import java.util.Stack;

public class ReverseDoublyLinkedList {

    static class Node{
        int data;
        Node prev;
        Node next;

        Node(int data){
            this.data = data;
        }

        Node(int data, Node prev, Node next){
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }

    private static Node buildDLL(int[] arr){
        int n = arr.length;
        Node head = new Node(arr[0]);

        Node temp = head;
        for (int i = 1; i < n; i++) {
            Node newNode = new Node(arr[i], temp, null);
            temp.next = newNode;
            temp = newNode;
        }
        return head;
        // TC -> O(n)
        // SC -> O(1)
    }

    private static Node reverseDLLUsingStack(Node head){
        if(head == null || (head.next == null)){
            return head;
        }

        Stack<Integer> stack = new Stack<>();

        Node temp = head;
        while (temp != null) {
            stack.push(temp.data);
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

    private static Node reverseDLLOptimal(Node head){
        if(head == null || (head.next == null)){
            return head;
        }

        Node temp = head;
        Node back = null;

        while (temp != null) {
            back = temp.prev;
            temp.prev = temp.next;
            temp.next = back;
            temp = temp.prev;
        }
        return back.prev;
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
    public static void main(String[] args) {
        int arr[] = {1,2,3,4};
        Node head = buildDLL(arr);
        // head = reverseDLLUsingStack(head);
        // traverse(head);
        head = reverseDLLOptimal(head);
        traverse(head);

    }
}
