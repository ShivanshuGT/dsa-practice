import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class BreadthFirstSearchTraversalInGraph {

    static class Pair{
        int nodeValue;
        int weight;
        Pair(int nodeValue, int weight){
            this.nodeValue = nodeValue;
            this.weight = weight;
        }
    }

    private static List<List<Pair>> buildUndirectedGraphFromInput(int nodes, List<List<Integer>> edgeInfo){
        List<List<Pair>> graph = new ArrayList<>();

        int edges = edgeInfo.size();

        for (int i = 0; i <= nodes; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < edges; i++) {
            List<Integer> edge = edgeInfo.get(i);
            graph.get(edge.get(0)).add(new Pair(edge.get(1), edge.get(2)));
            graph.get(edge.get(1)).add(new Pair(edge.get(0), edge.get(2)));

        }
        return graph;
        // TC -> O(N + E)
        // SC -> O(2 X E)
    }


    private static List<List<Pair>> buildDirectedGraphFromInput(int nodes, List<List<Integer>> edgeInfo){
        List<List<Pair>> graph = new ArrayList<>();

        int edges = edgeInfo.size();

        for (int i = 0; i <= nodes; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < edges; i++) {
            List<Integer> edge = edgeInfo.get(i);
            graph.get(edge.get(0)).add(new Pair(edge.get(1), edge.get(2)));

        }
        return graph;
        // TC -> O(N + E)
        // SC -> O(E)
    }

    private static List<List<Pair>> buildGraphFromInput(int flag, int nodes, List<List<Integer>> edgeInfo){
        if(flag == 0 ){
            return buildUndirectedGraphFromInput(nodes, edgeInfo);
        }
        return buildDirectedGraphFromInput(nodes, edgeInfo);
    }

    private static List<Integer> breadthFirstSearch(int startingNode, int nodes, List<List<Pair>> graph, int[] visited){
        List<Integer> result = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startingNode);
        visited[startingNode] = 1;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            result.add(node);
            List<Pair> neighbours = graph.get(node);
            for (int i = 0; i < neighbours.size(); i++) {
                Pair neighbour = neighbours.get(i);
                if(visited[neighbour.nodeValue] != 1){
                    // that node is not visited
                    queue.add(neighbour.nodeValue);
                    visited[neighbour.nodeValue] = 1;
                }
            }
        }
        return result;
        // TC -> O(n) + O(2 x e)
        // SC -> O(3 x n) where n is the number of nodes and e is the number of edges
    }

    private static List<Integer> traverse(int startingNode, int nodes, List<List<Pair>> graph){
        int[] visited = new int[nodes+1];
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i <= nodes; i++) {
            if(visited[i] != 1){
                result.addAll(breadthFirstSearch(startingNode,nodes, graph, visited));
            }
        }
        return result;
        // TC -> O(n) + O(2 x e)
        // SC -> O(3 x n)
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int flag = sc.nextInt();
        int nodes = sc.nextInt();
        int edges = sc.nextInt();
        int startingNode = sc.nextInt();
        List<List<Integer>> edgeInfo = new ArrayList<>();
        for (int i = 0; i < edges; i++) {
            edgeInfo.add(List.of(sc.nextInt(), sc.nextInt(), sc.nextInt()));
        } 
        List<List<Pair>> graph = buildGraphFromInput(flag, nodes, edgeInfo);
        List<Integer> traversal = traverse(startingNode, nodes, graph);
        for (int i = 0; i < traversal.size(); i++) {
            System.out.print(traversal.get(i));
        }
        sc.close();
    }
}