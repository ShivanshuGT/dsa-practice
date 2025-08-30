import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.TreeMap;

public class VerticalOrderTraversalInBinaryTree {

    static class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;

        TreeNode(int data){
            this.data = data;
        }
        
    }

    static class TreeNodeVerticalLevel {
    
        TreeNode node;
        int vertical;
        int level;
        TreeNodeVerticalLevel(TreeNode node, int vertical, int level){
            this.node = node;
            this.vertical = vertical;
            this.level = level;
        }
    }

    private static TreeNode buildTreeFromInput(String[] input){
        if (input.length == 0 || input[0].equals("null")) {
            return null;
        }


        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.parseInt(input[0]));
        queue.offer(root);

        int n = input.length;
        int i = 1;
        while (i < n && !queue.isEmpty()) {
            TreeNode current = queue.poll();

            if(i < n && !input[i].equals("null")){
                current.left = new TreeNode(Integer.parseInt(input[i]));
                queue.offer(current.left);
            }
            i += 1;
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

    private static List<List<Integer>> verticalOrderTraversalUsingLevelOrderTraversal(TreeNode root){
        List<List<Integer>> result = new ArrayList<>();
        if(root == null){
            return result;
        }

        Queue<TreeNodeVerticalLevel> queue = new LinkedList<>();
        queue.offer(new TreeNodeVerticalLevel(root, 0, 0));
        TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> tempHolder = new TreeMap<>();
        while (!queue.isEmpty()) {
            int n = queue.size();
            while (n > 0) {
                TreeNodeVerticalLevel current = queue.poll();
                TreeNode node = current.node;
                int vertical = current.vertical;
                int level = current.level;
                // inserting into the tempHolder
                TreeMap<Integer, PriorityQueue<Integer>> innerMap = tempHolder.getOrDefault(vertical, new TreeMap<>());
                PriorityQueue<Integer> elements = innerMap.getOrDefault(level, new PriorityQueue<>());
                elements.offer(node.data);
                innerMap.put(level, elements);
                tempHolder.put(vertical, innerMap);

                if(node.left != null){
                    queue.offer(new TreeNodeVerticalLevel(node.left, vertical-1, level+1));
                }
                if(node.right != null){
                    queue.offer(new TreeNodeVerticalLevel(node.right, vertical+1, level+1));
                }
                n -= 1;
            }
        }

        for (TreeMap<Integer,PriorityQueue<Integer>> innerMap: tempHolder.values()) {
            List<Integer> list = new ArrayList<>();
            for(PriorityQueue<Integer> elements : innerMap.values()){
                while (!elements.isEmpty()) {
                    list.add(elements.poll());
                }
            }
            result.add(list);
        }
        
        return result;
        // TC -> O(nlogn(n)) 
        // SC -> O(n)
        
    }

    private static void traverseAndHoldValues(TreeNode root, int vertical, int level,TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> tempHolder){
        if(root == null){
            return;
        }
        traverseAndHoldValues(root.left, vertical-1,level+1,tempHolder);
        TreeMap<Integer, PriorityQueue<Integer>> innerMap = tempHolder.getOrDefault(vertical, new TreeMap<>());
        PriorityQueue<Integer> elements = innerMap.getOrDefault(level, new PriorityQueue<>());
        elements.add(root.data);
        innerMap.put(level, elements);
        tempHolder.put(vertical, innerMap);
        traverseAndHoldValues(root.right,vertical+1, level+1, tempHolder);

        // TC -> O(nlog(n))
        // SC -> O(n)
    }

    private static List<List<Integer>> verticalOrderTraversalUsingInOrderTraversal(TreeNode root){
        List<List<Integer>> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> tempHolder = new TreeMap<>();
        traverseAndHoldValues(root, 0, 0, tempHolder);

        for(TreeMap<Integer, PriorityQueue<Integer>> innerMap : tempHolder.values()){
            List<Integer> list = new ArrayList<>();
            for(PriorityQueue<Integer> elements : innerMap.values()){
                while (!elements.isEmpty()) {
                    list.add(elements.poll());
                }
            }
            result.add(list);
        }
        return result;

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
        String input[] = sc.nextLine().split("\\s+");
        TreeNode root = buildTreeFromInput(input);
        // System.out.println(levelOrderTraversal(root));
        System.out.println(verticalOrderTraversalUsingLevelOrderTraversal(root));
        System.out.println(verticalOrderTraversalUsingInOrderTraversal(root));
        sc.close();
    }
}
