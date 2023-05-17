import java.util.HashSet;
import java.util.Set;

public class prim {
    int prims(int[][] graph) {
        int V = graph.length;
        int[] values = new int[V];
        int[] parents = new int[V];
        boolean[] includedInMST = new boolean[V];

        // Initialize all values to infinity and parents to -1
        for (int i = 0; i < V; i++) {
            parents[i] = -1;
            includedInMST[i] = false;
            values[i] = Integer.MAX_VALUE;
        }

        // Set value of first vertex to 0 and start from there
        values[0] = 0;

        // Loop over all vertices except the last one
        for (int i = 0; i < V - 1; i++) {
            int minIndex = getMinimumEdgeIndex(includedInMST, values);
            includedInMST[minIndex] = true;
            // Update the values of all adjacent vertices
            for (int j = 0; j < V; j++) {
                if (graph[minIndex][j] != 0 && !includedInMST[j] && graph[minIndex][j] < values[j]) {
                    parents[j] = minIndex;
                    values[j] = graph[minIndex][j];
                }
            }
        }
        // printArray(parents);
        for (int i = 0; i < V; i++) {
            System.out.println("parents[" + i + "] => " + parents[i] + " values[" + i + "] => " + values[i]);
        }
        // get the value of the spanning tree
        int sum = 0;
        for (int i = 0; i < V; i++) {
            sum += values[i];
        }
        return sum;
    }

    // Helper function to get the index of the vertex with the minimum value
    private int getMinimumEdgeIndex(boolean[] includedInMST, int[] values) {
        int minIndex = -1;
        int minValue = Integer.MAX_VALUE;
        for (int i = 0; i < values.length; i++) {
            if (!includedInMST[i] && values[i] < minValue) {
                minIndex = i;
                minValue = values[i];
            }
        }
        return minIndex;
    }

    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        int[][] graph = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                graph[i][j] = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                graph[j][i] = graph[i][j];
            }
        }
        return prims(graph);
    }

    public int minCostOfEdgeList(int[][] edges) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < edges.length; i++) {
            int source = edges[i][0];
            int destination = edges[i][1];
            set.add(source);
            set.add(destination);
        }
        int[][] graph = new int[set.size()][set.size()];
        for (int i = 0; i < edges.length; i++) {
            int source = edges[i][0];
            int destination = edges[i][1];
            graph[source][destination] = edges[i][2];
        }
        return prims(graph);
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] edges = { { 0, 1, 1 }, { 0, 2, 2 }, { 1, 2, 5 }, { 1, 3, 1 }, { 2, 3, 8 } };
        System.out.println(s.minCostOfEdgeList(edges));
        int[][] points = { { 0, 0 }, { 2, 2 }, { 3, 10 }, { 5, 2 }, { 7, 0 } };
        System.out.println(s.minCostConnectPoints(points));
        int graph[][] = { { 0, 4, 6, 0, 0, 0 },
                { 4, 0, 6, 3, 4, 0 },
                { 6, 6, 0, 1, 8, 0 },
                { 0, 3, 1, 0, 2, 3 },
                { 0, 4, 8, 2, 0, 7 },
                { 0, 0, 0, 3, 7, 0 } };
        System.out.println(s.prims(graph));
    }
}
