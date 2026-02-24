public class RotateLL {
    static class Node{
        int data;
        Node next;
        Node prev;

        Node(int data){
            this.data = data;
        }

        Node(int data, Node next, Node prev){
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }

    private static Node buildLLFromArr(int[] arr){
        int n = arr.length;
        Node head = new Node(arr[0]);
        Node temp = head;
        
        for (int i = 1; i < n; i++) {
            Node newNode = new Node(arr[i], null, temp);
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

    private static Node findNthNode(Node head, int count){
        int n = 1;
        Node temp = head;
        while ((temp != null) && (count > n)) {
            temp = temp.next;
            n += 1;
        }
        return temp;
        // TC -> O(n)
        // SC -> O(1)
    }

    private static Node rotateKTimes(Node head, int k){
        if(head == null || (head.next == null)){
            return head;
        }

        // find the lenght of LL and find the tail node
        Node tail = head;
        int length = 1;
        while (tail.next != null) {
            tail = tail.next;
            length += 1;
        }

        if((k % length) == 0){
            // the LL would be in its original state only after rotating k times
            return head;
        }

        k = k % length;
        int count = length - k;
        Node lastNodeAfterRotation = findNthNode(head, count);
        tail.next = head;
        head = lastNodeAfterRotation.next;
        lastNodeAfterRotation.next = null;
        return head;
        // TC -> O(2 x n)
        // SC -> O(1)
    }
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        Node head = buildLLFromArr(arr);
        head = rotateKTimes(head, 12);
        traverse(head);
    }
}
