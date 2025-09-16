import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class InsertionAndDeletionInBinarySearchTree {

    static class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;

        TreeNode(int data){
            this.data = data;
        }
        
    }

    private static TreeNode buildTreeFromInput(String[] input){
        if(input.length == 0 || input[0].equals("null")){
            return null;
        }

        TreeNode root = new TreeNode(Integer.parseInt(input[0]));
        int i = 1;
        int n = input.length;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

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

    private static TreeNode insert(TreeNode root, int target){
        if(root ==  null){
            return new TreeNode(target);
        }
        TreeNode current = root;
        while (true) {
            if(current.data <= target){
                if(current.right == null){
                    current.right = new TreeNode(target);
                    break;
                }else{
                    current = current.right;
                }
            }else{
                if(current.left == null){
                    current.left = new TreeNode(target);
                    break;
                }else{
                    current = current.left;
                }
            }
        }
        return root;
        // TC -> O(log(n))
        // SC -> O(1)
    }

    private static TreeNode findRigtMostChild(TreeNode node){
        TreeNode current = node;
        while (current.right != null) {
            current = current.right;
        }
        return current;
        // TC -> O(log(n))
        // SC -> O(1)
    }

    private static TreeNode findParent(TreeNode root, int target){
        if(root == null){
            return null;
        }
        TreeNode current = root;
        TreeNode prev = null;
        while (current != null) {
            if(current.data == target){
                return prev;
            }else if(current.data > target){
                prev = current;
                current = current.left;
            }else{
                prev = current;
                current = current.right;
            }
        }
        return prev;

        // TC -> O(log(n))
        // SC -> O(1)
    }

    private static TreeNode deleteHelper(TreeNode nodeToBeDeleted){
        if(nodeToBeDeleted.left == null){
            return nodeToBeDeleted.right;
        }else if(nodeToBeDeleted.right == null){
            return nodeToBeDeleted.left;
        }else{
            TreeNode rightMostChild = findRigtMostChild(nodeToBeDeleted.left);
            rightMostChild.right = nodeToBeDeleted.right;
            return nodeToBeDeleted.left;
        }
        // TC -> O(log(n))
        // SC -> O(1)
    }

    private static TreeNode delete(TreeNode root, int target){
        if(root == null){
            return null;
        }

        if(root.data == target){
            // we are given to delete the root
            return deleteHelper(root);
        }

        TreeNode parentOfNodeToBeDelted = findParent(root, target);
        if(parentOfNodeToBeDelted.left.data == target){
            // the node to be delted is on the left hand side of the parent
            parentOfNodeToBeDelted.left = deleteHelper(parentOfNodeToBeDelted.left);
        }else{
            // the node to be delted is on the right hand side of the parent
            parentOfNodeToBeDelted.right = deleteHelper(parentOfNodeToBeDelted.right);
        }
        return root;

        // TC -> O(log(n))
        // SC -> O(1)
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

    
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split("\\s+");
        int target = sc.nextInt();
        TreeNode root = buildTreeFromInput(input);
        System.out.println(levelOrderTraversal(root));
        // insertion
        // root = insert(root, target);
        // System.out.println(levelOrderTraversal(root));
        // deletion
        root = delete(root, target);
        System.out.println(levelOrderTraversal(root));
        sc.close();
    }
}
