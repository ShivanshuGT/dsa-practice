import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class MaxDepthInBinaryTree {
    
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

    private static int maxDepthRecursive(TreeNode node){
        if(node == null){
            return 0;
        }

        int maxHeightOfLeft = maxDepthRecursive(node.left);
        int maxHeightOfRight = maxDepthRecursive(node.right);
        return 1 + Math.max(maxHeightOfLeft, maxHeightOfRight);

        // TC -> O(n) since we are traversing each node once
        // SC -> O(n) here it will be the auxilliary space used by the memory stack (not any direct space)
    }


    private static int maxDepthUsingLevelOrderTraversal(TreeNode root){
        if(root == null){
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int ans= 0;

        while (!queue.isEmpty()) {
            ans += 1;
            int times = queue.size();
            while (times > 0) {
                TreeNode temp = queue.peek();
                if(temp.left != null){
                    queue.add(temp.left);
                }
                if(temp.right != null){
                    queue.add(temp.right);
                }
                queue.poll();
                times -=1 ;
            }
        }

        return ans;

        // TC -> O(n) since we are traversing each node once
        // SC -> O(n) where n is the number of nodes
    }

    

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split("\\s+");
        TreeNode root = buildTreeFromInput(input);
        System.out.println(maxDepthRecursive(root));
        System.out.println(maxDepthUsingLevelOrderTraversal(root));
        sc.close();
    }
}
