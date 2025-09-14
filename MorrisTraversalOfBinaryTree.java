import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class MorrisTraversalOfBinaryTree {

    static class TreeNode{
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

    private static List<Integer> morrisTraversalInorder(TreeNode root){
        // left root right
        List<Integer> result = new ArrayList<>();
        if(root == null){
            return result;
        } 

        TreeNode current = root;
        while (current != null) {
            if(current.left == null){
                result.add(current.data);
                current = current.right;
            }else{
                // find the rightmost node on the left side
                TreeNode prev = current.left;
                while (prev.right!=null && prev.right!=current) {
                    prev = prev.right;
                }

                if(prev.right == null){
                    // we have reached the rightmost node on the left side
                    prev.right = current; // creating the thread to the root
                    current = current.left;
                }else{
                    // we have encountered the case when there was already 
                    // a thread between the rightmost node and the root
                    prev.right = null; // breaking the thread
                    result.add(current.data);
                    current = current.right;
                }
            }
        }

        return result;
        // TC -> O(n)
        // SC -> O(1)
    }

    private static List<Integer> morrisTraversalPreorder(TreeNode root){
        // root left right
        List<Integer> result = new ArrayList<>();
        if(root == null){
            return result;
        }

        TreeNode current = root;
        while (current != null) {
            if(current.left == null){
                result.add(current.data);
                current = current.right;
            }else{
                TreeNode prev = current.left;
                while (prev.right!=null && prev.right!=current) {
                    prev = prev.right;
                }

                if(prev.right == null){
                    prev.right = current;
                    result.add(current.data);
                    current = current.left;
                }else{
                    prev.right = null;
                    current = current.right;
                }
            }
        }
        return result;

        // TC -> O(n)
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
        TreeNode root = buildTreeFromInput(input);
        System.out.println(levelOrderTraversal(root));
        System.out.println(morrisTraversalInorder(root));
        System.out.println(morrisTraversalPreorder(root));
        sc.close();
    }
}
