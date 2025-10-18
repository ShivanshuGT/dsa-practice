import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class EventualSafeStatesUsingTopoSort {

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
        for (int i = 0; i < nodes; i++) {
            graph.add(new ArrayList<>());
        }
        int edges = edgeInfo.size();
        for (int i = 0; i < edges; i++) {
            List<Integer> edge = edgeInfo.get(i);
            graph.get(edge.get(0)).add(new Pair(edge.get(1), edge.get(2)));
        }
        return graph;
        // TC -> O(n+e)
        // SC -> O(e)
    }

    private static List<Integer> findEventualSafeNodes(int nodes, List<List<Pair>> graph){
        List<Integer> safeNodes = new ArrayList<>();
        // reverse the edges 
        List<List<Pair>> revGraph = new ArrayList<>();
        for (int i = 0; i < nodes; i++) {
            revGraph.add(new ArrayList<>());
        }
        for (int i = 0; i < nodes; i++) {
            List<Pair> edges = graph.get(i);
            for (Pair edge : edges) {
                revGraph.get(edge.nodeValue).add(new Pair(i, edge.weight));
            }
        }

        // calculate the indegree of each node
        int[] indegree = new int[nodes];
        for (int i = 0; i < nodes; i++) {
            List<Pair> edges = revGraph.get(i);
            for (Pair edge : edges) {
                indegree[edge.nodeValue] += 1;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        // adding each node with indegree 0 in the queue
        for (int i = 0; i < nodes; i++) {
            if(indegree[i] == 0){
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int node = queue.poll();
            safeNodes.add(node);

            // traversing the neigbours
            List<Pair> neighbours = revGraph.get(node);
            for (Pair neighbour : neighbours) {
                indegree[neighbour.nodeValue] -= 1;
                if(indegree[neighbour.nodeValue] == 0){
                    queue.add(neighbour.nodeValue);
                }
            }
        }
        Collections.sort(safeNodes);
        return safeNodes;
        // TC -> O(n + e) + nlog(n)
        // SC -> O(n + e)
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
        List<Integer> safeNodes = findEventualSafeNodes(n, graph);
        for (Integer safeNode : safeNodes) {
            System.out.print(safeNode + " ");
        }
        sc.close();
    }
}
