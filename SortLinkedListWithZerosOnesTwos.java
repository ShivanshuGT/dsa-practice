public class SortLinkedListWithZerosOnesTwos {

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

    private static Node sortLLBrute(Node head){

        if(head == null || (head.next == null)){
            return head;
        }
        Node temp = head;
        int countZero = 0;
        int countOne = 0;
        int countTwo = 0;

        // traversing and counting each number node
        while (temp != null) {
            if(temp.data == 0){
                countZero += 1;
            }else if(temp.data == 1){
                countOne += 1;
            }else{
                countTwo += 1;
            }
            temp = temp.next;
        }

        // traversing and replacing node data

        temp = head;
        while (temp != null) {
            if(countZero > 0){
                temp.data = 0;
                countZero -= 1;
            }else if(countOne > 0){
                temp.data = 1;
                countOne -= 1;
            }else{
                temp.data = 2;
                countTwo -= 1;
            }
            temp = temp.next;
        }

        return head;
        // TC -> O(2 x n)
        // SC -> O(1)
    }

    private static Node sortLLOptimal(Node head){

        if(head == null || (head.next == null)){
            return head;
        }
        Node temp = head;
        Node zeroHead = new Node(-1);
        Node oneHead = new Node(-1);
        Node twoHead = new Node(-1);
        Node zeroMover = zeroHead;
        Node oneMover = oneHead;
        Node twoMover = twoHead;

        // traversing and linking corresponding nodes together

        while (temp != null) {
            if(temp.data == 0){
                zeroMover.next = temp;
                zeroMover = temp;

            }else if(temp.data == 1){
                oneMover.next = temp;
                oneMover = temp;
            }else{
                twoMover.next = temp;
                twoMover = temp;
            }
            temp = temp.next;
        }

        // linking the lists together 
        zeroMover.next = (oneHead.next != null) ? oneHead.next : twoHead.next;
        oneMover.next = twoHead.next;
        twoMover.next = null;
        return zeroHead.next;
        // TC -> O(n)
        // SC -> O(1)

    }
    public static void main(String[] args) {
        int arr[] = {1, 0, 2, 0, 2, 1, 0};
        Node head = buildLLFromArr(arr);

        // head = sortLLBrute(head);
        // traverse(head);

        head = sortLLOptimal(head);
        traverse(head);
    }
}
