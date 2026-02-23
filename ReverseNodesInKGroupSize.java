public class ReverseNodesInKGroupSize {

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

    private static Node buidLLFromArr(int[] arr){
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

    private static Node reverseLL(Node head){
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

    private static Node findKthNode(Node node, int k){
        k -= 1;
        while ((node != null) && (k > 0)) {
            k -= 1;
            node = node.next;
        }
        return node;
        // TC -> O(k)
        // SC -> O(1)
    }

    private static Node reverseLLInKGroups(Node head, int k){
        if(head == null || (head.next == null)){
            return head;
        }

        Node temp = head;
        Node prevNode = null;
        Node nextNode = null;
        while (temp != null) {
            Node kthNode = findKthNode(temp, k);

            if(kthNode == null){
                if(prevNode != null){
                    prevNode.next = temp;
                }
                break;
            }else{
                // store the next node after the kth Node
                nextNode = kthNode.next;
                // break the link after the kth Node
                kthNode.next = null;
                // call the revese() to reverse this group of k size
                reverseLL(temp);
            }

            if(temp == head){
                // this means its the first group that has been reversed
                // head needs to be updated here
                head = kthNode;
            }else{
                if(prevNode != null){
                    prevNode.next = kthNode;
                }
                
            }
            prevNode = temp;
            temp = nextNode;
        }
        return head;
        // TC -> O(2 x n)
        // SC -> O(1)
    }
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Node head = buidLLFromArr(arr);
        head = reverseLLInKGroups(head, 3);
        traverse(head);

    }
}
