public class DeleteAllOccurrencesOfKeyInLL {

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
        Node head = new Node(arr[0]);
        Node temp = head;
        int n = arr.length;
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

    private static Node deleteAllOccurrences(Node head, int key){
        if(head == null){
            return null;
        }

        Node temp = head;

        while (temp != null) {
            if(temp.data == key){
                // we need to delete this node
                if(temp == head){
                    // case of head deletion
                    head = head.next;
                }
                Node prevNode = temp.prev;
                Node nextNode = temp.next;
                if(prevNode != null){
                    prevNode.next = nextNode;
                }
                if(nextNode != null){
                    nextNode.prev = prevNode;
                }
                temp = nextNode;
            }else{
                temp = temp.next;
            }
        }
        return head;
        // TC -> O(n)
        // SC -> O(1)
    }
    public static void main(String[] args) {
        int[] arr = {10, 10, 10, 10, 10, 10};
        Node head = buildDLLFromArr(arr);
        head = deleteAllOccurrences(head, 10);
        traverse(head);
    }
}
