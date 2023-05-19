
import java.util.*;

class edge {
    int source;
    int destination;
    double value;

    edge(int source, int destination, double value) {
        this.source = source;
        this.destination = destination;
        this.value = value;
    }
}

class node {
    int index;
    double value;

    node(int index, double value) {
        this.index = index;
        this.value = value;
    }
}

public class bellmanFord {
    int size;// number of vertices
    int[] parent;// parent of each node
    double[] distance;// distance from source to destination
    boolean[] visited;// visited or not
    private final double INF = Double.MAX_VALUE;// infinity
    List<List<edge>> adjecencyList;// adjecency list of graph

    void createGraph(int V, int E) {
        if (V < 0 || E < 0) {// invalid graph if the number of vertices or edges is negative
            System.out.println("Invalid graph");
            return;
        }
        this.adjecencyList = new ArrayList<>();// create a new adjecency list
        for (int i = 0; i < V; i++) {// add V empty lists to the adjecency list
            this.adjecencyList.add(new ArrayList<>());
        }
        this.size = V;
    }

    bellmanFord(int V, int E) {
        this.createGraph(V, E);// create a graph with V vertices and E edges
        this.parent = new int[V];// create a parent array
        this.distance = new double[V];// create a distance array
        this.visited = new boolean[V];// create a visited array
    }

    void addEdge(int source, int destination, double value) {// add an edge to the graph
        if (source < 0 || destination < 0 || source >= this.adjecencyList.size()
                || destination >= this.adjecencyList.size()) {// invalid edge if the source or destination is negative
                                                              // or greater than the number of vertices
            System.out.println("Invalid edge");
            return;
        }
        this.adjecencyList.get(source).add(new edge(source, destination, value));// add the edge to the adjecency list
    }

    boolean bellmanford(int source) {
        boolean negative = false;
        Arrays.fill(this.distance, this.INF);// initialize the distance array with infinity
        Arrays.fill(this.parent, -1);// initialize the parent array with -1
        this.distance[source] = 0;// distance from source to source is 0
        for (int i = 0; i < this.size - 1; i++) {// run the loop for V-1 times
            List<edge> edges = adjecencyList.get(i);
            for (edge e : edges) {
                if (this.distance[e.destination] > this.distance[e.source] + e.value) {
                    this.distance[e.destination] = this.distance[e.source] + e.value;
                    this.parent[e.destination] = e.source;
                }
            }

        }
        for (int i = 0; i < this.size - 1; i++) {// run the loop for V-1 times
            for (edge e : this.adjecencyList.get(i)) {
                if (this.distance[e.destination] > this.distance[e.source] + e.value) {
                    System.out.println("Negative cycle detected");
                    this.distance[e.destination] = -INF;
                    negative = true;
                }
            }
        }
        return negative;
    }

}
