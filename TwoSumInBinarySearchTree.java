import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

class TreeNode{
    int data;
    TreeNode left;
    TreeNode right;

    TreeNode(int data){
        this.data = data;
    }
}

class BSTIterator{

    private Stack<TreeNode> stack = new Stack<>();
    private boolean reverse;
    // reverse = false means it will work as normal iterator - it will give the next node on calling the next() method
    // reverse = true means it will work as a reverse iterator - it will give the before node on calling the next() method

    private void pushAllNodes(TreeNode node, boolean reverse){
        while (node != null) {
            this.stack.add(node);
            if(reverse){
                node = node.right;
            }else{
                node = node.left;
            }
        }
    }

    BSTIterator(TreeNode root, boolean reverse){
        this.pushAllNodes(root, reverse);
    }

    public TreeNode next(){
        TreeNode topNode = stack.pop();
        if(reverse){
            this.pushAllNodes(topNode.left, this.reverse);
        }else{
            this.pushAllNodes(topNode.right, this.reverse);
        }
        return topNode;
    }

    public boolean hasNext(){
        return !stack.isEmpty();
    }
    // TC -> O(1) // on an average
    // SC -> O(H)
}

public class TwoSumInBinarySearchTree {


    private static TreeNode buildTreeFromInput(String[] input){
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

    private static boolean findTwoSumBrute(TreeNode root, int target){
        if(root == null){
            return false;
        }

        TreeNode current = root;
        List<Integer> inorder = new ArrayList<>();
        while (current!=null) {
            if(current.left == null){
                inorder.add(current.data);
                current = current.right;
            }else{
                TreeNode prev = current.left;
                while (prev.right!=null && prev.right!=current) {
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

        int j = inorder.size() - 1;
        int i = 0;
        while (i < j) {
            int sum = inorder.get(i) + inorder.get(j);
            if(sum == target){
                return true;
            }else if(sum > target){
                j -= 1;
            }else{
                i += 1;
            }
        }
        return false;
        // TC -> O(n)
        // SC -> O(n)
    }
    
    

    private static boolean findTwoSumOptimal(TreeNode root, int target){
        if(root == null){
            return false;
        }

        BSTIterator left = new BSTIterator(root, false);
        BSTIterator right = new BSTIterator(root, true);
        int i = left.next().data;
        int j = right.next().data;

        while(i < j){
            if ((i + j) == target){
                return true;
            }else if((i + j) < target){
                if(left.hasNext()){
                    i = left.next().data;
                }else{
                    return false;
                }
            }else{
                if(right.hasNext()){
                    j = right.next().data;
                }else{
                    return false;
                }
            }
        }
        return false;
        // TC -> O(n)
        // SC -> O(H) + O(H) (for the two stacks for 'left' and 'right' object)
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split("\\s+");
        int target = sc.nextInt();
        TreeNode root = buildTreeFromInput(input);
        System.out.println(levelOrderTraversal(root));
        System.out.println(findTwoSumBrute(root, target));
        System.out.println(findTwoSumOptimal(root, target));
        sc.close();
    }
}
