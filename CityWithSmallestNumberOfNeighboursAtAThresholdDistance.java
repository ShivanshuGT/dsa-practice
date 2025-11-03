import java.util.Scanner;

public class CityWithSmallestNumberOfNeighboursAtAThresholdDistance {

    private static int findCityWithSmallestNumberOfNeighbours(int[][] graph, int threshold){
        int n = graph.length;

        for (int via = 0; via < n; via++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if((graph[i][via] != Integer.MAX_VALUE) && (graph[via][j] != Integer.MAX_VALUE) && (graph[i][j] > graph[i][via] + graph[via][j])){
                        graph[i][j] = graph[i][via] + graph[via][j];
                    }
                }
            }
        }

        // step to detect negative cycle in the graph
        for (int i = 0; i < n; i++) {
            if(graph[i][i] < 0){
                // there is a negative weight cycle in the graph
                return -2;
            }
        }

        int city = -1;
        int cityCount = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if(graph[i][j] <= threshold){
                    count += 1;
                }
            }

            if(count <= cityCount){
                city = i;
                cityCount = count;
            }
        }
        return city;
        // TC -> O(nxnxn)
        // SC -> O(nxn)
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int e = sc.nextInt();
        int threshold = sc.nextInt();
        int[][] graph = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(i == j){
                    graph[i][j] = 0;
                }else{
                    graph[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        for (int i = 0; i < e; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int weight = sc.nextInt();
            graph[u][v] = weight;
            graph[v][u] = weight;
        }

        int city = findCityWithSmallestNumberOfNeighbours(graph, threshold);
        if(city == -2){
            System.out.println("There is a ngeative weight cycle present in the graph.");
        }else{
            System.out.println(city);
        }
        sc.close();
    }
}
