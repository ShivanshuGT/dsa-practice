import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class MinimumTimeToBurnTheBinaryTree {

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

    private static TreeNode findNodeFromValue(TreeNode root, int value){
        if(root == null){
            return null;
        }

        if(root.data == value){
            return root;
        }

        TreeNode leftSearchResult = findNodeFromValue(root.left, value);

        if(leftSearchResult != null){
            return leftSearchResult;
        }

        return findNodeFromValue(root.right, value);

        // TC -> O(n)
        // SC -> O(n) (memory stack space)
    }

    private static void markParents(TreeNode root, HashMap<TreeNode, TreeNode> map){
        if(root == null){
            return ;
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
        // SC -> O(n) (for map) + O(n) (for queue)

    }

    private static int findMinimumTimeToBurn(TreeNode root, int start){
        int answer = 0;
        if(root == null){
            return answer;
        }

        HashMap<TreeNode, TreeNode> parentMap = new HashMap<>();
        markParents(root, parentMap);
        HashMap<TreeNode, Boolean> visitedMap = new HashMap<>();
        TreeNode startNode = findNodeFromValue(root, start);
        visitedMap.put(startNode, true);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(startNode);

        while (!queue.isEmpty()) {
            int times = queue.size();
            boolean burntAnything = false;

            while (times > 0) {
                TreeNode current = queue.poll();
                // traverse upwards
                TreeNode parent = parentMap.get(current);
                if(parent != null && visitedMap.get(parent) == null){
                    queue.offer(parent);
                    visitedMap.put(parent, true);
                    burntAnything = true;
                }
                // traverse left
                if(current.left != null && visitedMap.get(current.left) == null){
                    queue.offer(current.left);
                    visitedMap.put(current.left, true);
                    burntAnything = true;
                }

                // traverse right
                if(current.right != null && visitedMap.get(current.right) == null){
                    queue.offer(current.right);
                    visitedMap.put(current.right, true);
                    burntAnything = true;
                }
                times -= 1;
            }

            if(burntAnything){
                answer += 1;
            }
        }
        return answer;

        // TC -> O(nlog(n))
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
        int start = sc.nextInt();
        System.out.println(levelOrderTraversal(root));
        System.out.println(findMinimumTimeToBurn(root, start));
        sc.close();
    }
}
