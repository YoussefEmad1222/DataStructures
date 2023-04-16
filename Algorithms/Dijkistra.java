package Algorithms;
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
//node class to store the index and the value of the node in the priority queue
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

    Dijkistra(int V, int E) {// constructor of the dijkistra class
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
            node n = pq.poll();// get the node with the minimum value
            if (visited[n.index] == true) {// if the node is visited before
                continue;
            }
            visited[n.index] = true;// mark the node as visited
            // if the value of the node is greater than the distance of the node from the
            // source then do nothing
            // because the node is already visited and the distance of the node from the
            // source is the minimum
            if (n.value > distance[n.index]) {
                continue;
            }
            // get the edges of the node
            List<edge> edges = this.adjecencyList.get(n.index);
            for (int i = 0; i < edges.size(); i++) {
                edge e = edges.get(i);// get the edge
                if (visited[e.destination])// if the destination of the edge is visited before
                    continue;
                // if the distance of the destination of the edge from the source is greater
                // than the distance of the source of the edge from the source plus the value of
                // the edge
                if (distance[e.destination] > distance[e.source] + e.value) {
                    distance[e.destination] = distance[e.source] + e.value;
                    parent[e.destination] = e.source;// update the parent of the destination of the edge
                    // add the neighbor of the node to the priority queue
                    pq.add(new node(e.destination, distance[e.destination]));
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
        Dijkistra graph = new Dijkistra(8, 15);
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 7, 8);
        graph.addEdge(1, 2, 8);
        graph.addEdge(1, 7, 11);
        graph.addEdge(2, 3, 7);
        graph.addEdge(2, 5, 4);
        graph.addEdge(3, 4, 9);
        graph.addEdge(3, 5, 14);
        graph.addEdge(4, 5, 10);
        graph.addEdge(5, 6, 2);
        graph.addEdge(6, 7, 1);
        graph.addEdge(6, 7, 6);
        graph.addEdge(7, 1, 8);
        graph.addEdge(7, 2, 11);
        graph.addEdge(7, 6, 7);
        graph.dik(0);
        graph.printGraph();
        for (int i = 0; i < graph.distance.length; i++) {
            System.out.println("Distance from 0 to " + i + " is " + graph.distance[i]);
        }
    }
}
