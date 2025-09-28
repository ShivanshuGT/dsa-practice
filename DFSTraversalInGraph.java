import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DFSTraversalInGraph {

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
        // TC -> O(N + E)
        // SC -> O(2 x E)
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
        // TC -> O(N + E)
        // SC -> O(E)
    }

    private static List<List<Pair>> buildGraphFromInput(int flag, int nodes, List<List<Integer>> edgeInfo){
        if(flag == 0){
            return buildUndirectedGraphFromInput(nodes, edgeInfo);
        }
        return buildDirectedGraphFromInput(nodes, edgeInfo);
    }

    private static void dfsTraversal(int[] visited, int node, List<List<Pair>> edgeInfo, List<Integer> traversal){
        visited[node] = 1;
        traversal.add(node);
        List<Pair> neighbours = edgeInfo.get(node);
        for (int i = 0; i < neighbours.size(); i++) {
            Pair neighbour = neighbours.get(i);
            if(visited[neighbour.nodeValue] != 1){
                dfsTraversal(visited, neighbour.nodeValue, edgeInfo, traversal);
            }
        }

        // TC -> O(n) + O(2 x e)
        // SC -> O(n)
    }

    private static List<Integer> traverse(int startingNode, int nodes, List<List<Pair>> edgInfo){
        int visited[] = new int[nodes+1];
        List<Integer> traversal = new ArrayList<>();
        for (int i = 0; i < visited.length; i++) {
            if(visited[i] != 1){
                dfsTraversal(visited, startingNode, edgInfo, traversal);
            } 
        }
        return traversal;
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
        for (Integer element : traversal) {
            System.out.print(element + " ");
        }
        sc.close();
    }
}
