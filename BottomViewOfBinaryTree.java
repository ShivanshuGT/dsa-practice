import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.TreeMap;

public class BottomViewOfBinaryTree {

    static class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;

        TreeNode(int data){
            this.data = data;
        }
        
    }

    static class TreeNodeVertical {
    
        TreeNode node;
        int vertical;

        TreeNodeVertical(TreeNode node, int vertical){
            this.node = node;
            this.vertical = vertical;
        }
    }

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

    private static List<Integer> bottomViewOfBinaryTree(TreeNode root){
        List<Integer> result = new ArrayList<>();
        if(root ==  null){
            return result;
        }

        Queue<TreeNodeVertical> queue = new LinkedList<>();
        queue.offer(new TreeNodeVertical(root, 0));
        TreeMap<Integer, Integer> map = new TreeMap<>();

        while (!queue.isEmpty()) {
            int times = queue.size();
            TreeNodeVertical current = queue.poll();
            TreeNode node = current.node;
            int vertical = current.vertical;
            map.put(vertical, node.data);
            while (times > 0) {
                if(node.left != null){
                    queue.offer(new TreeNodeVertical(node.left, vertical-1));
                }
                if(node.right != null){
                    queue.offer(new TreeNodeVertical(node.right, vertical+1));
                }
                times -= 1;
            }
        }

        for(Integer elements : map.values()){
            result.add(elements);
        }
        return result;

        // TC -> O(nlog(n))
        // Sc -> O(n)
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input[] = sc.nextLine().split("\\s+");
        TreeNode root = buildTreeFromInput(input);
        System.out.println(levelOrderTraversal(root));
        System.out.println(bottomViewOfBinaryTree(root));
        sc.close();
    }
}
