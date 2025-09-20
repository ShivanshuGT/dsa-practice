import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class LowestCommonAncestorInBinarySearchTree {

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
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int i = 1;
        int n = input.length;

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

    private static TreeNode findLCA(TreeNode root, TreeNode node1, TreeNode node2){
        if(root == null){
            return null;
        }

        TreeNode current = root;
        while (current!=null) {
            if(current.data < node1.data && current.data < node2.data){
                // both nodes lie on the right hand side of the current node
                current = current.right;
                continue;
            }
            if(current.data > node1.data && current.data > node2.data){
                // both nodes lie on the left hand side of the current node
                current = current.left;
                continue;
            }
            // if the above two conditions fail
            return current;
        }
        return current;
        // TC -> O(log(n))
        // SC -> O(1)
    }

    private static TreeNode findNodeFromValue(TreeNode root, int target){
        if(root == null){
            return null;
        }
        if(root.data == target){
            return root;
        }
        TreeNode leftResult  = findNodeFromValue(root.left, target);
        if(leftResult != null){
            return leftResult;
        }
        return findNodeFromValue(root.right, target);
        // TC -> O(n)
        // SC -> O(n) (memory stack space)
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
        int n1 = sc.nextInt();
        int n2 = sc.nextInt();
        TreeNode node1 = findNodeFromValue(root, n1);
        TreeNode node2 = findNodeFromValue(root, n2);
        TreeNode lca = findLCA(root, node1, node2);
        System.out.println(lca == null ? "No LCA found" : lca.data);
        sc.close();
    }
}
