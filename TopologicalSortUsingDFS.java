import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class TopologicalSortUsingDFS {

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
        // TC -> O(n + e)
        // SC -> O(e)
    }

    private static void dfs(int node, int[] visited, List<List<Pair>> graph, Stack<Integer> stack){
        visited[node] = 1;
        // traversing the neighbours 
        List<Pair> neighbours = graph.get(node);
        for (Pair neighbour : neighbours) {
            if(visited[neighbour.nodeValue] == 0){
                // the neighbour is not visited yet
                dfs(neighbour.nodeValue, visited, graph, stack);
            }
        }
        stack.add(node);
    }

    private static List<Integer> topologicalSort(int nodes, List<List<Pair>> graph){
        int[] visited = new int[nodes];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < nodes; i++) {
            if(visited[i] != 1){
                dfs(i, visited, graph, stack);
            }
        }
        List<Integer> result = new ArrayList<>();
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
        return result;
        // TC -> O(n + e  + n)
        // SC -> O(n)
    }
    public static void main(String[] args) {
        // Topological sort can be applied to only a Directed Acyclic Graph(DAG)
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int e = sc.nextInt();
        List<List<Integer>> edgeInfo = new ArrayList<>();
        for (int i = 0; i < e; i++) {
            edgeInfo.add(List.of(sc.nextInt(), sc.nextInt(), sc.nextInt()));
        }

        List<List<Pair>> graph = buildGraphFromInput(n, edgeInfo);
        List<Integer> topologicalSort = topologicalSort(n, graph);
        for (Integer element : topologicalSort) {
            System.out.print(element+ " ");
        }
        sc.close();
    }
}
