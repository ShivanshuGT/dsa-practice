import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;



public class BinaryTreePreOrderTraversal {

    static class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;

        TreeNode(int data){
            this.data = data;
        }
        
    }

    public static TreeNode buildTreeFomInput(String[] input){
        if(input.length == 0 || input[0].equals("null")){
            return null;
        }

        TreeNode root = new TreeNode(Integer.parseInt(input[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int i = 1;
        int n = input.length;
        while (!queue.isEmpty() && i<n) {
            TreeNode current = queue.poll();

            // for left child
            if(i < n && !input[i].equals("null")){
                current.left = new TreeNode(Integer.parseInt(input[i]));
                queue.offer(current.left);
            }
            i += 1;

            // right child
            if(i < n && !input[i].equals("null")){
                current.right = new TreeNode(Integer.parseInt(input[i]));
                queue.offer(current.right);
            }
            i += 1;
        }

        return root;

        // TC -> O(n) where n is the number of inputs given
        // SC -> O(n)

    }

    private static void preOrderTraversal(TreeNode node){
        // Root Left Right
        if(node == null){
            return;
        }
        System.out.print(node.data + " ");
        preOrderTraversal(node.left);
        preOrderTraversal(node.right);

        // TC -> O(n)
        // SC -> O(n)
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split("\\s+");
        TreeNode root = buildTreeFomInput(input);
        preOrderTraversal(root);
        sc.close();
    }
}