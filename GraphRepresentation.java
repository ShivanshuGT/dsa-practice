import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GraphRepresentation {

    static class Pair{
        int nodeValue;
        int weight;

        Pair(int nodeValue, int weight){
            this.nodeValue = nodeValue;
            this.weight = weight;
        }
    }
    // assuming we are having 1-based indexing

    private static List<List<Pair>> buildUndirectedGraphListFromInput(int nodes, int edges, List<List<Integer>> edgeInfo){
        List<List<Pair>> result = new ArrayList<>();
        for (int i = 0; i <= nodes; i++) {
            result.add(new ArrayList<>());
        }

        for (int i = 0; i < edges; i++) {
            List<Integer> edge =  edgeInfo.get(i);
            result.get(edge.get(0)).add(new Pair(edge.get(1), edge.get(2)));
            result.get(edge.get(1)).add(new Pair(edge.get(0), edge.get(2)));
        }
        return result;
        // TC -> O(N + E)
        // SC -> O(2 x E) where N is the number of nodes and E is the number of edges

    }

    private static List<List<Pair>> buildDirectedGraphListFromInput(int nodes, int edges, List<List<Integer>> edgeInfo){
        List<List<Pair>> result = new ArrayList<>();
        for (int i = 0; i <= nodes; i++) {
            result.add(new ArrayList<>());
        }

        for (int i = 0; i < edges; i++) {
            List<Integer> edge =  edgeInfo.get(i);
            result.get(edge.get(0)).add(new Pair(edge.get(1), edge.get(2)));
        }
        return result;
        // TC -> O(N + E)
        // SC -> O(E)

    }

    private static List<List<Pair>> buildGraphListFromInput(int flag, int nodes, int edges, List<List<Integer>> edgeInfo){
        if(flag == 0){
            // its an undirected graph
            return buildUndirectedGraphListFromInput(nodes, edges, edgeInfo);
        }
        return buildDirectedGraphListFromInput(nodes, edges, edgeInfo);
    }

    private static int[][] buildUndirectedGraphMatrixFromInput(int nodes, int edges, List<List<Integer>> edgeInfo){
        int[][] result = new int[nodes+1][nodes+1];

        for (int i = 0; i < edges; i++) {
            List<Integer> edge = edgeInfo.get(i);
            result[edge.get(0)][edge.get(1)] = edge.get(2);
            result[edge.get(1)][edge.get(0)] = edge.get(2);
        }
        return result;
        // TC -> O(E
        // SC -> O(N x N)
    }

    private static int[][] buildDirectedGraphMatrixFromInput(int nodes, int edges, List<List<Integer>> edgeInfo){
        int[][] result = new int[nodes+1][nodes+1];

        for (int i = 0; i < edges; i++) {
            List<Integer> edge = edgeInfo.get(i);
            result[edge.get(0)][edge.get(1)] = edge.get(2);
        }
        return result;
        // TC -> O(E)
        // SC -> O(N x N)
    }

    private static int[][] buildGraphMatrixFromInput(int flag, int nodes, int edges, List<List<Integer>> edgeInfo){
        if(flag == 0){
            // its an undirected graph
            return buildUndirectedGraphMatrixFromInput(nodes, edges, edgeInfo);
        }
        return buildDirectedGraphMatrixFromInput(nodes, edges, edgeInfo);
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
        List<List<Pair>> graph = buildGraphListFromInput(flag, nodes, edges, edgeInfo);
        for (int i = 0; i < graph.size(); i++) {
            if(i == 0)
                continue;
            List<Pair> list = graph.get(i);
            for (int j = 0; j < list.size(); j++) {
                System.out.println(i + " -> " + list.get(j).nodeValue + " weight : " + list.get(j).weight);
            }
        }

        // int[][] graph = buildGraphMatrixFromInput(flag, nodes, edges, edgeInfo);
        // for (int i = 0; i < graph.length; i++) {
        //     for (int j = 0; j < graph.length; j++) {
        //         System.out.print(graph[i][j] + " ");
        //     }
        //     System.out.println();
        // }

        sc.close();
    }
}
