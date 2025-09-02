import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class LowestCommonAncestorInBinaryTree {

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

    private static TreeNode getNodeFromValue(TreeNode root, int value){
        if(root == null){
            return null;
        }
        if(root.data == value){
            return root;
        }
        TreeNode left = getNodeFromValue(root.left, value);
        TreeNode right = getNodeFromValue(root.right, value);
        if(left == null){
            return right;
        }else if(right == null){
            return left;
        }
        return null;

        // TC -> O(n)
        // SC -> O(n)

    }

    private static boolean findPath(TreeNode root, int target, List<Integer> path){
        if(root == null){
            return false;
        }
        path.add(root.data);
        if(root.data == target){
            return true;
        }

        if(findPath(root.left, target, path) || findPath(root.right, target, path)){
            return true;
        }
        path.remove(path.size() - 1);
        return false;

        // TC -> O(n)
        // SC -> O(n)

    }

    private static List<Integer> findPathFromRootToNode(TreeNode root, int target){
        if(root == null){
            return new ArrayList<>();
        }

        List<Integer> path = new ArrayList<>();
        if(findPath(root, target, path)){
            return path;
        }
        return new ArrayList<>();
        // TC -> O(n)
        // SC -> O(n)

    }

    private static TreeNode findLowestCommonAncestorBrute(TreeNode root, TreeNode p, TreeNode q){
        if(root == null || root == p || root == q){
            return root;
        }

        List<Integer> path1 = findPathFromRootToNode(root, p.data);
        List<Integer> path2 = findPathFromRootToNode(root, q.data);
        int n1 = path1.size();
        int n2 = path2.size();
        int min = Math.min(n1, n2);
        int result = Integer.MIN_VALUE;
        for (int i = 0; i < min; i++) {
            if(path1.get(i) == path2.get(i)){
                result = path1.get(i);
            }else{
                break;
            }
        }

        return getNodeFromValue(root, result);

        // TC -> O(n)
        // SC -> O(n) (for memory stack) + O(n) (for storing the paths)


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

    private static TreeNode findLowestCommonAncestorOptimal(TreeNode root, TreeNode p, TreeNode q){
        if(root==null || root==p || root==q){
            return root;
        }

        TreeNode left = findLowestCommonAncestorOptimal(root.left, p, q);
        TreeNode right = findLowestCommonAncestorOptimal(root.right, p, q);

        if(left == null){
            return right;
        }else if(right == null){
            return left;
        }else{
            return root;
        }

        // TC -> O(n)
        // SC -> O(n) (which is the memory stack space)
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input[] = sc.nextLine().split("\\s+");
        int v1 = sc.nextInt();
        int v2 = sc.nextInt();
        TreeNode root = buildTreeFromInput(input);
        // System.out.println(levelOrderTraversal(root));
        TreeNode p = getNodeFromValue(root, v1);
        TreeNode q = getNodeFromValue(root, v2);
        System.out.println(findLowestCommonAncestorBrute(root, p, q).data);
        System.out.println(findLowestCommonAncestorOptimal(root, p, q).data);
        sc.close();
    }
}
