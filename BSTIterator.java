import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class BSTIterator {
    
    static class TreeNode {
    
        int data;
        TreeNode left;
        TreeNode right;

        TreeNode(int data){
            this.data = data;
        }
        
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

    private static TreeNode buidlTreeFromInput(String[] input){
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

    private Stack<TreeNode> stack = new Stack<>();

    private void pushAllNodes(TreeNode node){
        while (node != null) {
            this.stack.add(node);
            node = node.left;
        }
    }

    BSTIterator(TreeNode root){
        pushAllNodes(root);
    }

    private TreeNode next(){
        TreeNode topNode = this.stack.pop();
        pushAllNodes(topNode.right);
        return topNode;
        // TC -> O(1) on an average because overall we are pushing 'n' nodes 
        // for 'n' next() method calls
        // SC -> O(1)
    }

    private boolean hasNext(){
        return !stack.isEmpty();
        // TC -> O(1)
        // SC -> (1)
    }

    // SC -> O(H) since the stack stores (at max) number of elements equal to the 
    // height of the tree at any point of time
    


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split("\\s+");
        TreeNode root = buidlTreeFromInput(input);
        System.out.println(levelOrderTraversal(root));
        BSTIterator iterator = new BSTIterator(root);
        while (iterator.hasNext()) {
            System.out.println(iterator.next().data);
        }
        sc.close();
    }
}
