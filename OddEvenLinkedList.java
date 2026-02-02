import java.util.ArrayList;
import java.util.List;

public class OddEvenLinkedList {

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
            Node newNode = new Node(arr[i]);
            temp.next = newNode;
            temp = newNode;
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

    private static Node groupListBrute(Node head){
        if(head == null || (head.next == null)){
            return head;
        }
        Node temp = head;
        List<Integer> list = new ArrayList<>();

        // collecting all the odd nodes
        while (temp != null && (temp.next != null)) {
            list.add(temp.data);
            temp = temp.next.next;
        }
        if(temp != null){
            list.add(temp.data);
        }

        // collecting all the even nodes
        temp = head.next;
        while (temp != null && (temp.next != null)) {
            list.add(temp.data);
            temp = temp.next.next;
        }
        if(temp != null){
            list.add(temp.data);
        }

        temp = head;
        int n = list.size();
        for (int i = 0; i < n; i++) {
            temp.data = list.get(i);
            temp = temp.next;
        }
        return head;
        // TC -> O(2 x n)
        // SC -> O(n)

    }

    private static Node groupListOptimal(Node head){
        if(head == null || (head.next == null)){
            return head;
        }

        Node odd = head;
        Node even = head.next;
        Node evenHead = even;

        while (even != null && (even.next != null)) {
            odd.next = odd.next.next;
            even.next = even.next.next;

            odd = odd.next;
            even = even.next;
        }

        odd.next = evenHead;
        return head;
        // TC -> O(n)
        // SC -> O(1)
    }
    public static void main(String[] args) {
        int[] arr = {1, 3, 4, 2, 5, 6};
        Node head = buildLLFromArr(arr);

        // head = groupListBrute(head);
        // traverse(head);

        head = groupListOptimal(head);
        traverse(head);

    }
}
