public class LinkedListImpl {

    static class Node {
        
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
        int n = arr.length;
        Node head = new Node(arr[0]);
        Node mover = head;

        for (int i = 1; i < n; i++) {
            Node temp = new Node(arr[i]);
            mover.next = temp;
            mover = temp;
        }
        return head;
        // TC -> O(n)
        // SC -> O(1)
    }

    private static void traverseLL(Node head){
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        // TC -> O(n)
        // SC -> O(1)
    }

    private static int countLengthOfLL(Node head){
        Node temp = head;
        int ans = 0;
        while (temp != null) {
            ans += 1;
            temp = temp.next;
        }
        return ans;
        // TC -> O(n)
        // SC -> O(1)
    }

    private static Node searchElementInLL(Node head, int target){
        Node temp = head;
        while (temp != null) {
            if(target == temp.data){
                return temp;
            }
            temp = temp.next;
        }
        return null;
        // TC -> O(n)
        // SC -> O(1)
    }

    public static void main(String[] args) {
        int[] arr = {2,7,5,3,1};
        Node head = buildLLFromArr(arr);
        traverseLL(head);
        System.out.println();
        System.out.println(countLengthOfLL(head));
        Node result = searchElementInLL(head, 5);
        if(result == null){
            System.out.println("Target not found");
        }else{
            System.out.println("Target found");

        }
    }
}
