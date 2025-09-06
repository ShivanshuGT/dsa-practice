import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class PrintAllNodesAtDistanceKInBinaryTree {


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

        int n = input.length;
        int i = 1;
        TreeNode root = new TreeNode(Integer.parseInt(input[0]));
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
    }

    private static TreeNode findNodeFromData(TreeNode root, int value){
        if(root == null){
            return null;
        }

        if(root.data == value){
            return root;
        }

        TreeNode leftSearch = findNodeFromData(root.left, value);
        if(leftSearch != null){
            return leftSearch;
        }
        return findNodeFromData(root.right, value);
        // TC -> O(n)
        // SC -> O(n) (memory stack space)
    }

    private static void markParents(TreeNode root, HashMap<TreeNode, TreeNode> map){
        if(root == null){
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int times = queue.size();
            while (times > 0) {
                TreeNode current = queue.poll();
                if(current.left != null){
                    map.put(current.left, current);
                    queue.offer(current.left);
                }
                if(current.right != null){
                    map.put(current.right, current);
                    queue.offer(current.right);
                }
                times -= 1;
            }
        }

        // TC -> O(nlog(n))
        // SC -> O(n) (memory stack space) + O(n) (for map)
    }

    private static List<Integer> findAllNodesAtDistanceK(TreeNode root, int node, int k){
        List<Integer> result = new ArrayList<>();

        if(root == null){
            return result;
        }

        HashMap<TreeNode, TreeNode> parentMap = new HashMap<>(); // stores the parent address of a node
        markParents(root, parentMap); // O(nlog(n))
        HashMap<TreeNode, Boolean> visitedMap = new HashMap<>(); // keeps the track that which all nodes are visited

        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode targetNode = findNodeFromData(root, node); // O(n)
        queue.offer(targetNode);
        visitedMap.put(targetNode, true);
        int currentDistance = 0;

        while (!queue.isEmpty()) {
            if(currentDistance == k){
                // since we have all the nodes in the 'queue' which are present at distance 'k' from the target node
                break;
            }
            currentDistance += 1;
            int times = queue.size();

            while (times > 0) {
                TreeNode current = queue.poll();
                // travel upwards
                TreeNode parent = parentMap.get(current);
                if(parent != null && visitedMap.get(parent) == null){
                    queue.offer(parent);
                    visitedMap.put(parent, true);
                }
                // travel left
                if(current.left != null && visitedMap.get(current.left) == null){
                    queue.offer(current.left);
                    visitedMap.put(current.left, true);
                }
                // travel right
                if(current.right != null && visitedMap.get(current.right) == null){
                    queue.offer(current.right);
                    visitedMap.put(current.right, true);
                }
                times -= 1;
            }
        }

        while (!queue.isEmpty()) {
            result.add(queue.poll().data);
        }
        return result;

        // TC -> O(nlog(n))
        // SC -> O(5n) = O(n)
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
        int target = sc.nextInt();
        int k = sc.nextInt();
        TreeNode root = buildTreeFromInput(input);
        System.out.println(levelOrderTraversal(root));
        System.out.println(findAllNodesAtDistanceK(root, target, k));
        sc.close();
    }
}
