import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArticulationPoints {

    static class Pair {
        int nodeValue;
        int weight;

        Pair(int nodeValue, int weight){
            this.nodeValue = nodeValue;
            this.weight = weight;
        }
        
    }

    static int timer = 1;

    private static List<List<Pair>> buildGraphFromInput(int nodes, List<List<Integer>> edgeInfo){
        List<List<Pair>> graph = new ArrayList<>();
        int e = edgeInfo.size();
        for (int i = 0; i <= nodes; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < e; i++) {
            List<Integer> edge = edgeInfo.get(i);
            graph.get(edge.get(0)).add(new Pair(edge.get(1), edge.get(2)));
            graph.get(edge.get(1)).add(new Pair(edge.get(0), edge.get(2)));
        }
        return graph;
        // TC- > O(n + 2xe)
        // SC -> O(2xe)
    }

    private static void dfs(int node, int parent, List<List<Pair>> graph, int[] visited, int[] timeOfInsertion, int[] lowestTimeOfInsertion, int[] mark){
        visited[node] = 1;
        timeOfInsertion[node] = timer;
        lowestTimeOfInsertion[node] = timer;
        timer += 1;

        int child = 0;
        // traverse over the neighbours
        List<Pair> neighbours = graph.get(node);
        for (Pair neighbour : neighbours) {
            if(neighbour.nodeValue == parent){
                // skip if it is the parent
                continue;
            }
            if(visited[neighbour.nodeValue] == 0){
                // the neighbour node is not visited yet
                dfs(neighbour.nodeValue, node, graph, visited, timeOfInsertion, lowestTimeOfInsertion, mark);
                lowestTimeOfInsertion[node] = Math.min(lowestTimeOfInsertion[node], lowestTimeOfInsertion[neighbour.nodeValue]);
                // check if node can be the aritculation point
                if(lowestTimeOfInsertion[neighbour.nodeValue] >= timeOfInsertion[node] && parent != -1){
                    // it is an articulation point
                    mark[node] = 1;
                }
                child += 1;
            }else{
                // the neighbour node is visited
                lowestTimeOfInsertion[node] = Math.min(lowestTimeOfInsertion[node], timeOfInsertion[neighbour.nodeValue]);
            }
        }

        // if it is the starting point
        if(parent == -1 && child > 1){
            mark[node] = 1;
        }

        // TC -> O(n + 2xe)

    }

    private static List<Integer> findArticulationPoints(int nodes, List<List<Pair>> graph){
        int[] visited = new int[nodes];
        int[] timeOfInsertion = new int[nodes];
        int[] lowestTimeOfInsertion = new int[nodes];
        int[] mark = new int[nodes];

        for (int i = 0; i < nodes; i++) {
            if(visited[i] != 1){
                dfs(i, -1, graph, visited, timeOfInsertion, lowestTimeOfInsertion, mark);
            }
        }
        List<Integer> articulationPoints = new ArrayList<>();
        for (int i = 0; i < nodes; i++) {
            if(mark[i] == 1){
                articulationPoints.add(i);
            }
        }

        if(articulationPoints.size() > 1){
            return articulationPoints;
        }else{
            return List.of(-1);
        }
        // TC -> O(n + 2xe)
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
        List<Integer> articulationPoints = findArticulationPoints(n, graph);
        if(articulationPoints.size() == 1 && articulationPoints.get(0) == -1){
            System.out.println("There is no articulation point.");
        }else{
            for (Integer point : articulationPoints) {
                System.out.println(point);
            }
        }
        sc.close();
    }
}
