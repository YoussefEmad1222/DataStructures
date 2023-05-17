import java.util.HashSet;
import java.util.Set;

public class prim {
    int prims(int[][] graph) {
        int numVertices = graph.length;
        int[] vertexValues = new int[numVertices];
        int[] parentVertices = new int[numVertices];
        boolean[] includedInMST = new boolean[numVertices];

        // Initialize all vertexValues to infinity and parentVertices to -1
        for (int i = 0; i < numVertices; i++) {
            parentVertices[i] = -1;
            includedInMST[i] = false;
            vertexValues[i] = Integer.MAX_VALUE;
        }

        // Set value of first vertex to 0 and start from there
        vertexValues[0] = 0;

        // Loop over all vertices except the last one to find the minimum spanning tree
        for (int i = 0; i < numVertices - 1; i++) {
            // Find the vertex with the minimum value
            int minIndex = getMinimumEdgeIndex(includedInMST, vertexValues);
            includedInMST[minIndex] = true;// mark the vertex as included in the MST
            // Update the values of all adjacent vertices
            for (int j = 0; j < numVertices; j++) {
                if (graph[minIndex][j] != 0 && !includedInMST[j] && graph[minIndex][j] < vertexValues[j]) {
                    parentVertices[j] = minIndex;
                    vertexValues[j] = graph[minIndex][j];
                }
            }
        }

        // get the total weight of the spanning tree
        int totalWeight = 0;
        for (int i = 0; i < numVertices; i++) {
            totalWeight += vertexValues[i];
        }
        return totalWeight;
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

    // Function to find the minimum cost to connect all the points in a 2d plane
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
        Set<Integer> nodes = new HashSet<>();
        for (int i = 0; i < edges.length; i++) {
            int source = edges[i][0];
            int destination = edges[i][1];
            nodes.add(source);
            nodes.add(destination);
        }

        int[][] graph = new int[nodes.size()][nodes.size()];
        for (int i = 0; i < edges.length; i++) {
            int source = edges[i][0];
            int destination = edges[i][1];
            int cost = edges[i][2];
            graph[source][destination] = cost;
            graph[destination][source] = cost;
        }

        return prims(graph);
    }

    public static void main(String[] args) {
        prim s = new prim();
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
