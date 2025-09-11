import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class SerializeAndDeserializeBinaryTree {

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

            if(i < n && !input[i].equals("null")){
                current.left = new TreeNode(Integer.parseInt(input[i]));
                queue.add(current.left);
            }
            i += 1;
            if(i < n && !input[i].equals("null")){
                current.right = new TreeNode(Integer.parseInt(input[i]));
                queue.add(current.right);
            }
            i += 1;
        }
        return root;
        // TC -> O(n)
        // SC -> O(n)
    }

    private static String serialize(TreeNode root){
        if(root == null){
            return null;
        }
        StringBuilder res = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int times = queue.size();
            while (times > 0) {
                TreeNode current = queue.poll();
                if(current == null){
                    res.append("null ");
                }else{
                    res.append(String.valueOf(current.data) +" ");
                }
                if(current.left != null){
                    queue.offer(current.left);
                }
                if(current.right != null){
                    queue.offer(current.right);
                }
                times -= 1;
            }
        }
        return res.toString();

        // TC -> O(n)
        // SC -> O(n)
    }

    private static TreeNode deSerialize(String serial){
        if(serial.isEmpty()){
            return null;
        }
        String input[] = serial.split("\\s+");
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

            if(i < n && !input[i].equals("null")){
                current.left = new TreeNode(Integer.parseInt(input[i]));
                queue.add(current.left);
            }
            i += 1;
            if(i < n && !input[i].equals("null")){
                current.right = new TreeNode(Integer.parseInt(input[i]));
                queue.add(current.right);
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

    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split("\\s+");
        TreeNode root = buildTreeFromInput(input);
        String serial = serialize(root);
        TreeNode treeRoot = deSerialize(serial);
        System.out.println(levelOrderTraversal(treeRoot));
        sc.close();
    }
}
