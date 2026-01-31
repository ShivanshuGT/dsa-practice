public class AddTwoNumbersInLinkedList {

    static class Node {
        int data;
        Node prev;
        Node next;

        Node(int data, Node prev, Node next){
            this.data = data;
            this.prev = prev;
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
            Node newNode = new Node(arr[i]);
            temp.next = newNode;
            temp = newNode;
        }
        return head;
        // TC -> O(n)
        // SC -> O(1)
    }

    private static Node addTwoLinkedLists(Node head1, Node head2){
        Node dummyNode = new Node(-1);
        Node current = dummyNode;
        int carry = 0;
        Node t1 = head1;
        Node t2 = head2;

        while ((t1 != null) || (t2 != null)) {
            int sum = carry;
            if(t1 != null){
                sum += t1.data;
                t1 = t1.next;
            }
            if(t2 != null){
                sum += t2.data;
                t2 = t2.next;
            }

            Node newNode = new Node(sum % 10);
            carry = sum / 10;
            current.next = newNode;
            current = newNode;
        }

        if(carry > 0){
            current.next = new Node(carry);
            current = current.next;
        }
        return dummyNode.next;
        // TC -> O(max (len(t1, t2)))
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
        int[] arr1 = {3, 5};
        int[] arr2 = {4, 5, 9, 9};

        Node head1 = buildLLFromArr(arr1);
        Node head2 = buildLLFromArr(arr2);
        Node sumListHead = addTwoLinkedLists(head1, head2);
        traverse(sumListHead);



    }
}
