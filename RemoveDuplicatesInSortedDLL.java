public class RemoveDuplicatesInSortedDLL {

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

    private static Node buildDLLFromArr(int[] arr){
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

    private static Node removeDuplicates(Node head){
        if(head == null || (head.next == null)){
            return head;
        }

        Node temp = head;
        while ((temp != null) && (temp.next != null)) {
            Node nextNode = temp.next;
            while ((nextNode != null) && (nextNode.data == temp.data)) {
                nextNode = nextNode.next;
            }
            temp.next = nextNode;
            if(nextNode != null){
                nextNode.prev = temp;
            }
            temp = temp.next;
        }
        return head;
        // TC -> O(n)
        // SC -> O(1)
    }
    public static void main(String[] args) {
        int[] arr = {1, 1, 1, 2, 3, 3, 4};
        Node head = buildDLLFromArr(arr);
        head = removeDuplicates(head);
        traverse(head);
    }
}
