import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class StronglyConnectedComponents {

    // SCC is applicable to only directed graphs

    static class Pair{
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

        int e = edgeInfo.size();
        for (int i = 0; i < e; i++) {
            List<Integer> edge = edgeInfo.get(i);
            graph.get(edge.get(0)).add(new Pair(edge.get(1), edge.get(2)));
        }
        return graph;
        // TC -> O(n + e)
        // SC -> O(e)
    }

    private static void dfsWithStack(int node, List<List<Pair>> graph, int[] visited, Stack<Integer> stack){
        visited[node] = 1;

        // traversing the neighbours
        List<Pair> neighbours = graph.get(node);
        for (Pair neighbour : neighbours) {
            if(visited[neighbour.nodeValue] != 1){
                dfsWithStack(neighbour.nodeValue, graph, visited, stack);
            }
        }
        stack.push(node);
        // TC -> O(n + e)
    }

    private static void dfs(int node, List<List<Pair>> graph, int[] visited, List<Integer> nodeList){
        visited[node] = 1;
        nodeList.add(node);
        // traversing the neighbours
        List<Pair> neighbours = graph.get(node);
        for (Pair neighbour : neighbours) {
            if(visited[neighbour.nodeValue] != 1){
                dfs(neighbour.nodeValue, graph, visited, nodeList);
            }
        }

        // TC -> O(n + e)
    }

    private static List<List<Integer>> kosaRajuAlgorithm(int nodes, List<List<Pair>> graph){
        int[] visited = new int[nodes];
        Stack<Integer> stack = new Stack<>();
        // doing a dfs and storing nodes in a stack
        for (int i = 0; i < nodes; i++) {
            if(visited[i] != 1){
                dfsWithStack(i, graph, visited, stack);
            }
        }
        
        // reversing the edges of the graph
        List<List<Pair>> reversedGraph = new ArrayList<>();

        for (int i = 0; i < nodes; i++) {
            reversedGraph.add(new ArrayList<>());
        }

        // iterating over the graph and reversing the edges 
        for (int i = 0; i < nodes; i++) {
            // marking each node as unvisited (to be used by further dfs traversal)
            visited[i] = 0;
            List<Pair> edges = graph.get(i);
            for (Pair edge : edges) {
                reversedGraph.get(edge.nodeValue).add(new Pair(i, edge.weight));
            }
        }


        List<List<Integer>> scc = new ArrayList<>();
        // again doing dfs traversal with the help of stack
        while (!stack.isEmpty()) {
            int node = stack.pop();
            if(visited[node] != 1){
                List<Integer> list = new ArrayList<>();
                dfs(node, reversedGraph, visited, list);
                scc.add(list);
            }
        }

        return scc;
        // TC -> O(n + e)
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

        List<List<Integer>> scc = kosaRajuAlgorithm(n, graph);
        for (List<Integer> component : scc) {
            for (int node : component) {
                System.out.print(node + " ");
            }
            System.out.println();
        }
        sc.close();
    }
}
