import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BinaryTreePostOrderTraversal {
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
       Queue<TreeNode> queue = new LinkedList<>();
       TreeNode root = new TreeNode(Integer.parseInt(input[0]));
       queue.offer(root);
       int i = 1;
       int n = input.length;

       while (i < n && !queue.isEmpty()) {
            TreeNode current = queue.poll();
            // for left child
            if(i < n && !input[i].equals("null")){
                current.left = new TreeNode(Integer.parseInt(input[i]));
                queue.offer(current.left);
            }
            i += 1;
            // for right child
            if(i < n && !input[i].equals("null")){
                current.right = new TreeNode(Integer.parseInt(input[i]));
                queue.offer(current.right);
            }
            i += 1;     
       }
       return root;

       // TC -> O(n)
       // SC -> O(n)
    }

    private static void postOrderTraversal(TreeNode node){
        // Left Right Node
        if(node == null){
            return;
        }

        postOrderTraversal(node.left);
        postOrderTraversal(node.right);
        System.out.print(node.data + " ");

        // TC -> O(n)
        // SC -> O(n)
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split("\\s+");
        TreeNode root = buildTreeFromInput(input);
        postOrderTraversal(root);
        sc.close();
    }
}
