import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NumberOfProvinces {

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

        for (int i = 0; i <= nodes; i++) {
            graph.add(new ArrayList<>());
        }

        int edges = edgeInfo.size();
        for (int i = 0; i < edges; i++) {
            List<Integer> edge = edgeInfo.get(i);
            graph.get(edge.get(0)).add(new Pair(edge.get(1), edge.get(2)));
            graph.get(edge.get(1)).add(new Pair(edge.get(0), edge.get(2)));
        }

        return graph;
        // TC -> O(n + e)
        // SC -> O(2 x e)
    }

    private static List<List<Pair>> buildDirectedGraphFromInput(int nodes, List<List<Integer>> edgeInfo){
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

    private static List<List<Pair>> buildGraphFromInput(int flag, int nodes, List<List<Integer>> edgeInfo){
        if(flag == 0){
            return buildUndirectedGraphFromInput(nodes, edgeInfo);
        }
        return buildDirectedGraphFromInput(nodes, edgeInfo);
    }

    private static void dfsTraversal(int[] visited, int node, List<List<Pair>> graph, List<Integer> traversal){
        visited[node] = 1;
        traversal.add(node);

        List<Pair> neighbours = graph.get(node);
        for (Pair neighbour : neighbours) {
            if(visited[neighbour.nodeValue] != 1){
                dfsTraversal(visited, neighbour.nodeValue, graph, traversal);
            }
        }
        // TC -> O(n + 2 x e)
        // SC -> O(n)
    }

    private static int countNumberOfProvinces(int nodes, List<List<Pair>> graph){
        int[] visited = new int[nodes+1];
        List<Integer> traversal = new ArrayList<>();
        int numberofProvinces = 0;
        for (int i = 1; i <= nodes; i++) {
            if(visited[i] != 1){
                numberofProvinces += 1;
                dfsTraversal(visited, i, graph, traversal);
            }
        }
        return numberofProvinces;
        // TC -> O(n + 2 x e)
        // SC -> O(n)

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int flag = sc.nextInt();
        int nodes = sc.nextInt();
        int edges = sc.nextInt();
        List<List<Integer>> edgeInfo = new ArrayList<>();
        for (int i = 0; i < edges; i++) {
            edgeInfo.add(List.of(sc.nextInt(), sc.nextInt(), sc.nextInt()));
        }
        List<List<Pair>> graph = buildGraphFromInput(flag, nodes, edgeInfo);
        System.out.println(countNumberOfProvinces(nodes, graph));
        sc.close();
    }
}
