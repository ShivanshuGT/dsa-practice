import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

import javax.swing.tree.TreeNode;

public class CheckWhetherABinaryTreeIsBinarySearchTree {

    static class  TreeNode {
        int data;
        TreeNode left;
        TreeNode right;

        TreeNode(int data){
            this.data = data;
        }
        
    }

    private static TreeNode buildTreeFromInput(String input[]){
        if(input.length == 0 || input[0].equals("null")){
            return null;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.parseInt(input[0]));
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

    private static boolean check(TreeNode node, int lowerRange, int upperRange){
        if(node == null){
            return true;
        }
        if(!(node.data > lowerRange && node.data < upperRange)){
            return false;
        }
        return check(node.left, lowerRange, node.data) && check(node.right, node.data, upperRange);
        // TC -> O(n)
        // SC -> O(n) (memory stack space)
    }

    private static boolean checkWhetherBinaryTreeIsBinarySearchTree(TreeNode root){
        if(root == null){
            return true;
        }
        return check(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
        // TC -> O(n)
        // SC -> O(n)
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split("\\s+");
        TreeNode root = buildTreeFromInput(input);
        System.out.println(levelOrderTraversal(root));
        boolean result = checkWhetherBinaryTreeIsBinarySearchTree(root);
        if (result) {
            System.out.println("Given binary tree is a binary search tree.");
        }else{
            System.out.println("Given binary tree is not a binary search tree.");

        }
        sc.close();
    }
}
