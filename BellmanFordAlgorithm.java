import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BellmanFordAlgorithm {

    // works for negative weights as well
    // and helps to detect negative cycles as well

    private static int[] findShortestPath(int n, int source, List<List<Integer>> edgeInfo){
        int[] distance = new int[n];

        for (int i = 0; i < n; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        distance[source] = 0;
        int e = edgeInfo.size();

        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < e; j++) {
                List<Integer> edge = edgeInfo.get(j);
                // edge from 'u' to 'v' with edge weight as 'weight'
                int u = edge.get(0);
                int v = edge.get(1);
                int weight = edge.get(2);

                if(distance[u] != Integer.MAX_VALUE){
                    // we have reached node 'u' previously
                    if(distance[u] + weight < distance[v]){
                        distance[v] = distance[u] + weight;
                    }
                }
            }
        }

        // step to detect whether a negative cycle exists or not
        for (int i = 0; i < e; i++) {
            List<Integer> edge = edgeInfo.get(i);
            int u = edge.get(0);
            int v = edge.get(1);
            int weight = edge.get(2);
            if(distance[u] != Integer.MAX_VALUE){
                if(distance[u] + weight < distance[v]){
                    // there is a negative cycle 
                    int[] arr = {1};
                    return arr;
                }
            }
        }
        return distance;
        // TC -> O( V x E)
        // SC -> O(V)
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int e = sc.nextInt();
        int source = sc.nextInt();
        List<List<Integer>> edgeInfo = new ArrayList<>();
        for (int i = 0; i < e; i++) {
            edgeInfo.add(List.of(sc.nextInt(), sc.nextInt(), sc.nextInt()));
        }
        int[] shortestPath = findShortestPath(n, source, edgeInfo);
        if(shortestPath.length == 1 && shortestPath[0] == 1){
            System.out.println("There is a negative cycle present in the graph.");
        }else{
            for (int i = 0; i < n; i++) {
                System.out.println("The shortest distance from " + source + " to "+ i + " is " + shortestPath[i] + " units.");
            }
        }
        sc.close();
    }
}
