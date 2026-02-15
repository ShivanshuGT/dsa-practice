public class DeleteMiddleOfLL {
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

    private static Node buildLLFromArr(int[] arr){
        Node head = new Node(arr[0]);
        Node temp = head;
        int n = arr.length;
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

    private static Node deleteMiddleBrute(Node head){
        if(head == null || (head.next == null)){
            return null;
        }
        int n = 0;
        Node temp = head;

        while (temp != null) {
            n += 1;
            temp = temp.next;
        }

        int count = n/2;
        temp = head;
        
        while (temp != null) {
            count -= 1;
            if(count == 0){
                temp.next = temp.next.next;
                break;
            }
            temp = temp.next;
        }
        return head;
        // TC -> O(n + n/2)
        // SC -> O(1)
    }

    private static Node deleteMiddleOptimal(Node head){
        if(head == null || (head.next == null)){
            return null;
        }

        Node slow = head;
        Node fast = head;
        // skipping one jump of slow pointer
        fast = fast.next.next;

        while ((fast != null) && (fast.next != null)) {
            slow = slow.next;
            fast = fast.next.next;
        }
        slow.next = slow.next.next;
        return head;
        // TC -> O(n/2)
        // SC -> O(1)
    }
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4};
        Node head = buildLLFromArr(arr);

        // head = deleteMiddleBrute(head);
        // traverse(head);

        head = deleteMiddleOptimal(head);
        traverse(head);
    }
}
