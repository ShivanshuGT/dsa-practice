import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

class TreeNode {
    int data;
    TreeNode left;
    TreeNode right;

    TreeNode(int data){
        this.data = data;
    }

    int getData(){
        return this.data;
    }
    
}

class RecoverBSTOptimal{
    private TreeNode prev;
    private TreeNode first;
    private TreeNode middle;
    private TreeNode last;

    RecoverBSTOptimal(TreeNode prev){
        this.prev = prev;
    }

    public void traverse(TreeNode root){
        // left root right traversal
        if(root == null){
            return;
        }

        traverse(root.left);
        if(root.data < this.prev.data){
            // sorted rule is violated
            if(this.first == null){
                // rule is violated first time
                this.first = prev;
                this.middle = root;
            }else{
                // rule is violated second time
                this.last = root;
            }
        }
        this.prev = root;
        traverse(root.right);
        // TC -> O(n)
        // SC -> O(n) (memory stack space)
    }

    public TreeNode getFirst(){
        return this.first;
    }
    public TreeNode getMiddle(){
        return this.middle;
    }
    public TreeNode getLast(){
        return this.last;
    }


}

public class RecoverBST {


    private static TreeNode buildTreeFromInput(String[] input){
        if(input.length == 0 || input[0].equals("null")){
            return null;
        }

        TreeNode root = new TreeNode(Integer.parseInt(input[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int n = input.length;
        int i = 1;

        while (i<n && !queue.isEmpty()) {
            TreeNode current = queue.poll();
            if(i<n && !input[i].equals("null")){
                current.left = new TreeNode(Integer.parseInt(input[i]));
                queue.offer(current.left);
            }
            i += 1;
            if(i<n && !input[i].equals("null")){
                current.right = new TreeNode(Integer.parseInt(input[i]));
                queue.offer(current.right);
            }
            i += 1;
        }
        return root;
        // TC -> O(n)
        // SC -> O(n)
    }

    private static List<List<Integer>> levelOrderTraversal(TreeNode root){
        
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null){
            return ans;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int times = queue.size();
            List<Integer> list = new ArrayList<>();
            while (times > 0) {
                if(queue.peek().left != null){
                    queue.offer(queue.peek().left);
                }
                if(queue.peek().right != null){
                    queue.offer(queue.peek().right);
                }
                list.add(queue.poll().data);
                times -= 1;
            }
            ans.add(list);
        }

        return ans;

        // TC -> O(n) since we are touching each node only once
        // SC -> O(n) since we are storing the nodes in a queue
        // where n is the number of nodes in the tree
    }

    private static TreeNode findNodeFromValue(TreeNode root, int target){
        if(root == null){
            return null;
        }
        if(root.data == target){
            return root;
        }
        TreeNode leftResult = findNodeFromValue(root.left, target);
        if(leftResult != null){
            return leftResult;
        }
        return findNodeFromValue(root.right, target);
        // TC -> O(n)
        // SC -> O(n)
    }

    private static void recoverBrute(TreeNode root){
        if(root == null){
            return ;
        }

        List<Integer> inorder = new ArrayList<>();
        TreeNode current = root;
        while (current != null) {
            if(current.left == null){
                inorder.add(current.data);
                current = current.right;
            }else{
                TreeNode prev = current.left;
                while (prev.right != null && prev.right != current) {
                    prev = prev.right;
                }
                if(prev.right == null){
                    prev.right = current;
                    current = current.left;
                }else{
                    prev.right = null;
                    inorder.add(current.data);
                    current = current.right;
                }
            }
        }

        List<Integer> correctInorder = new ArrayList<>();
        for (int element : inorder) {
            correctInorder.add(element);
        }
        Collections.sort(correctInorder);

        int n = correctInorder.size();
        for (int i = 0; i < n; i++) {
            if(correctInorder.get(i) != inorder.get(i)){
                TreeNode node1 = findNodeFromValue(root, correctInorder.get(i));
                TreeNode node2 = findNodeFromValue(root, inorder.get(i));
                int temp = node1.data;
                node1.data = node2.data;
                node2.data = temp;
                return; 
            }
        }
        // TC -> O(nlog(n))
        // SC -> O(n)
    }


    private static void recoverOptimal(TreeNode root){
        if(root == null){
            return;
        }

        RecoverBSTOptimal recoverBSTOptimal = new RecoverBSTOptimal(new TreeNode(Integer.MIN_VALUE));
        recoverBSTOptimal.traverse(root);
        if(recoverBSTOptimal.getFirst() != null && recoverBSTOptimal.getLast() != null){
            // this is the case when the swapped nodes are not ajacent
            int temp = recoverBSTOptimal.getFirst().data;
            recoverBSTOptimal.getFirst().data = recoverBSTOptimal.getLast().data;
            recoverBSTOptimal.getLast().data = temp;
        }else if(recoverBSTOptimal.getMiddle() != null){
            // this is the case when the swapped nodes are adjacent
            int temp = recoverBSTOptimal.getFirst().data;
            recoverBSTOptimal.getFirst().data = recoverBSTOptimal.getMiddle().data;
            recoverBSTOptimal.getMiddle().data = temp;
        }
        // TC -> O(n)
        // SC -> O(n) (memory stack space)
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split("\\s+");
        TreeNode root = buildTreeFromInput(input);
        System.out.println(levelOrderTraversal(root));
        // recoverBrute(root);
        // System.out.println(levelOrderTraversal(root));
        recoverOptimal(root);
        System.out.println(levelOrderTraversal(root));
        sc.close();
    }
}
