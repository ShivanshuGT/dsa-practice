import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class ShortestPathInDAG {

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

    private static void findTopologicalSortUsingDFS(int node, int[] visited, List<List<Pair>> graph, Stack<Integer> stack){
        visited[node] = 1;
        // traversing all the neighbour nodes
        List<Pair> neighbours = graph.get(node);
        for (Pair neighbour : neighbours) {
            if(visited[neighbour.nodeValue] != 1){
                // the neighbour is not visited yet
                findTopologicalSortUsingDFS(neighbour.nodeValue, visited, graph, stack);
            }
        }
        stack.add(node);
    }

    private static int[] findShortestDistanceInDAG(int nodes, List<List<Pair>> graph, int sourceNode){
        int[] visited = new int[nodes];
        Stack<Integer> stack = new Stack<>();

        // find the topological sort
        for (int i = 0; i < nodes; i++) {
            if(visited[i] != 1){
                // the node is not visited yet
                findTopologicalSortUsingDFS(i, visited, graph, stack);
            }
        }

        int[] distance = new int[nodes]; // array to keep track of the shortest distance;
        // marking initial distance as INT_MAX
        for (int i = 0; i < nodes; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        distance[sourceNode] = 0; // marking distance of sourceNode as 0
        
        while (!stack.isEmpty()) {
            int node = stack.pop();
            int nodeDistance = distance[node];// distance to travel from the source to this node

            // traversing all the neighbours
            // performing relaxation of edges
            List<Pair> neighbours = graph.get(node);
            for (Pair neighbour : neighbours) {
                if(nodeDistance != Integer.MAX_VALUE){// for just handling overflow
                    int newDistance = nodeDistance + neighbour.weight;
                    distance[neighbour.nodeValue] = Math.min(distance[neighbour.nodeValue], newDistance);
                }
            }
        }
        return distance;
        // TC -> O(n + e)
        // SC -> O(n)
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int e = sc.nextInt();
        int sourceNode = sc.nextInt();
        List<List<Integer>> edgeInfo = new ArrayList<>();
        for (int i = 0; i < e; i++) {
            edgeInfo.add(List.of(sc.nextInt(), sc.nextInt(), sc.nextInt()));
        }
        List<List<Pair>> graph = buildGraphFromInput(n, edgeInfo);
        int[] distance = findShortestDistanceInDAG(n, graph, sourceNode);
        for (int i = 0; i < distance.length; i++) {
            System.out.println("Shortest distance from " + sourceNode + " to " + i + " is " + distance[i] + " units.");
        }
        sc.close();
    }
}
