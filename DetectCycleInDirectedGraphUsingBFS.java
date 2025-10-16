import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class DetectCycleInDirectedGraphUsingBFS {
    
    static class Pair {
        int nodeValue;
        int weight;
        Pair(int nodeValue, int weight){
            this.nodeValue = nodeValue;
            this.weight = weight;
        }
        
    }

    private static List<List<Pair>> buildGraphFromInput(int nodes, List<List<Integer>> edgeInfo){
        List<List<Pair>> graph = new ArrayList<>();
        for (int i = 0; i <= nodes; i++) {
            graph.add(new ArrayList<>());
        }

        int edges = edgeInfo.size();
        for (int i = 0; i < edges; i++) {
            List<Integer> edge = edgeInfo.get(i);
            graph.get(edge.get(0)).add(new Pair(edge.get(1), edge.get(2)));
        }
        return graph;
        // TC -> O(n + e)
        // SC -> O(e)
    }

    private static boolean detectCycleUsingTopologicalSort(int nodes, List<List<Pair>> graph){
        int[] indegree = new int[nodes+1];

        // calculate the indegree of each node
        for (int i = 1; i <= nodes; i++) {
            List<Pair> edges = graph.get(i);
            for (Pair edge : edges) {
                indegree[edge.nodeValue] += 1;
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        // insert all the nodes with indegree 0 in the queue
        for (int i = 1; i <= nodes; i++) {
            if(indegree[i] == 0){
                queue.add(i);
            }
        }
        int topoLength = 0;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            topoLength += 1;

            // traversing the neighbours
            List<Pair> neighbours = graph.get(node);
            for (Pair neighbour : neighbours) {
                indegree[neighbour.nodeValue] -= 1;
                if(indegree[neighbour.nodeValue] == 0){
                    queue.add(neighbour.nodeValue);
                }
            }
        }
        if(topoLength != nodes){
            // there is a cycle
            return true;
        }
        return false;
        // TC -> O(n + e)
        // SC -> O(n)
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int e = sc.nextInt();
        List<List<Integer>> edgeInfo = new ArrayList<>();
        for (int i = 0; i < e; i++) {
            edgeInfo.add(List.of(sc.nextInt(), sc.nextInt(), sc.nextInt()));
        }
        List<List<Pair>> graph = buildGraphFromInput(n, edgeInfo);
        System.out.println(detectCycleUsingTopologicalSort(n, graph));
        sc.close();
    }
}
