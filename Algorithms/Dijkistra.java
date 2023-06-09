
import java.util.*;

//edge class to store the source and the destination of the edge and the value of the edge
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

// node class to store the index and the value of the node in the priority queue
class node {
    int index;
    double value;

    node(int index, double value) {
        this.index = index;
        this.value = value;
    }
}

public class Dijkistra {
    int size;// number of vertices
    int[] parent;// parent of each node
    double[] distance;// distance from source to destination
    boolean[] visited;// visited or not
    List<List<edge>> adjecencyList;// adjecency list of graph
    PriorityQueue<node> pq;// priority queue to store nodes
    private final double INF = Double.MAX_VALUE;// infinity
    private final double EPS = 1e-9;// epsilon

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

    void addEdge(int source, int destination, double value) {// add an edge to the graph
        if (source < 0 || destination < 0 || source >= this.adjecencyList.size()
                || destination >= this.adjecencyList.size()) {// invalid edge if the source or destination is negative
                                                              // or greater than the number of vertices
            System.out.println("Invalid edge");
            return;
        }
        this.adjecencyList.get(source).add(new edge(source, destination, value));// add the edge to the adjecency list
    }

    Dijkistra(int V, int E) {// constructor of the dijkstra class
        this.createGraph(V, E);// create a graph with V vertices and E edges
        this.parent = new int[V];// create a parent array
        this.distance = new double[V];// create a distance array
        this.visited = new boolean[V];// create a visited array
        this.pq = new PriorityQueue<>(new Comparator<node>() {
            @Override
            // comparator to compare two nodes by their values in the priority
            // queue not by their indexes
            public int compare(node o1, node o2) {
                if (Math.abs(o1.value - o2.value) < EPS) {
                    return 0;
                }
                return o1.value < o2.value ? -1 : 1;
            }
        });
    }

    void dik(int source) {
        // initialize the distance array with infinity as the distance from the source
        // to all other nodes is infinity at the beginning
        Arrays.fill(this.distance, INF);
        // initialize the visited array with false as no node is visited at the
        // beginning
        Arrays.fill(this.visited, false);
        // initialize the parent array with -1 as the parent of all nodes is nothing at
        // the beginning
        Arrays.fill(this.parent, -1);
        // initialize the distance of the source to itself with 0
        this.distance[source] = 0;
        // add the source to the priority queue
        this.pq.add(new node(source, 0));
        while (!pq.isEmpty()) {
            node node = pq.poll();// get the node with the minimum value
            if (visited[node.index] == true) {// if the node is visited before
                continue;
            }
            visited[node.index] = true;// mark the node as visited
            // if the value of the node is greater than the distance of the node from the
            // source then do nothing
            // because the node is already visited and the distance of the node from the
            // source is the minimum
            if (node.value > distance[node.index]) {
                continue;
            }
            // get the edges of the node
            List<edge> edges = this.adjecencyList.get(node.index);
            for (int i = 0; i < edges.size(); i++) {
                edge edge = edges.get(i);// get the edge
                if (visited[edge.destination])// if the destination of the edge is visited before
                    continue;
                // if the distance of the destination of the edge from the source is greater
                // than the distance of the source of the edge from the source plus the value of
                // the edge
                double newDist = distance[edge.source] + edge.value;
                if (distance[edge.destination] > newDist) {
                    distance[edge.destination] = newDist;// update the distance of the destination of the edge
                    parent[edge.destination] = edge.source;// update the parent of the destination of the edge
                    // add the neighbor of the node to the priority queue
                    pq.add(new node(edge.destination, distance[edge.destination]));
                }
            }
        }
    }

    public void printGraph() {
        for (int i = 0; i < this.adjecencyList.size(); i++) {
            System.out.println("Vertex " + i);
            List<edge> edges = this.adjecencyList.get(i);
            for (int j = 0; j < edges.size(); j++) {
                edge e = edges.get(j);
                System.out.print(" --> " + e.destination + " " + "(" + e.value + ")");
            }
            System.out.println();
        }
    }

    public void constructPath(int destination) {
        // if the destination is the source or there is no path from the source to the
        // destination
        if (destination == -1) {
            return;
        }
        // recursively call the function to get the path from the source to the parent
        // of the destination
        constructPath(parent[destination]);
        System.out.print(destination + " ");
    }

    public static void main(String[] args) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (Math.abs(o1[2] - o2[2]) < 1e-9) {
                    return 0;
                }
                return o1[2] > o2[2] ? -1 : 1;
            }
        });
        pq.add(new int[] { 1, 2, 3 });
        pq.add(new int[] { 1, 2, 1 });
        pq.add(new int[] { 1, 2, 2 });
        pq.add(new int[] { 1, 2, 4 });
        pq.add(new int[] { 1, 2, 5 });
        pq.add(new int[] { 1, 2, 6 });
        pq.add(new int[] { 1, 2, 7 });
        pq.add(new int[] { 14, 22, 8 });
        pq.add(new int[] { 1, 2, 9 });
        for (int[] i : pq) {
            System.out.println(i[2]);
        }
    }
}
