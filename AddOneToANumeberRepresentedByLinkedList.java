public class AddOneToANumeberRepresentedByLinkedList {

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

    private static Node reverse(Node head){
        if(head == null || (head.next == null)){
            return head;
        }

        Node prev = null;
        Node temp = head;
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

    private static Node addOneIterative(Node head){
        head = reverse(head);
        int carry = 1;
        Node temp = head;
        while (temp != null) {
            int sum = temp.data + carry;
            temp.data = sum % 10;
            carry = sum / 10;
            temp = temp.next;
        }
        head = reverse(head);
        if(carry >= 1){
            head = new Node(carry, head);
        }
        return head;
        // TC -> O(3 x n)
        // SC -> O(1)
    }

    private static Node addOneRecursive(Node head){
        int carry = addOneRecursiveHelper(head);
        if(carry >= 1){
            head = new Node(carry, head);
        }
        return head;
        // TC -> O(n)
        // SC -> O(n)
    }

    private static int addOneRecursiveHelper(Node node){
        // base case
        if(node == null){
            return 1;
        }

        int carry = addOneRecursiveHelper(node.next);
        int sum = node.data + carry;
        node.data = sum % 10;
        carry = sum / 10;
        return carry;
        // TC -> O(n)
        // SC -> O(n)
    }
    public static void main(String[] args) {
        int[] arr = {1,2,3};
        Node head = buildLLFromArr(arr);
        head = addOneIterative(head);
        traverse(head);
        // head = addOneRecursive(head);
        // traverse(head);

    }
}
