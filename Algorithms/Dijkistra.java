package Algorithms;
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

public class Dijkistra {
    int size;
    int[] parent;
    double[] distance;
    boolean[] visited;
    List<List<edge>> adjecencyList;
    PriorityQueue<node> pq;
    private final double INF = Double.MAX_VALUE;
    private final double EPS = 1e-9;

    void createGraph(int V, int E) {
        if (V < 0 || E < 0) {
            System.out.println("Invalid graph");
            return;
        }
        this.adjecencyList = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            this.adjecencyList.add(new ArrayList<>());
        }
        this.size = V;
    }

    void addEdge(int source, int destination, double value) {
        if (source < 0 || destination < 0 || source >= this.adjecencyList.size()
                || destination >= this.adjecencyList.size()) {
            System.out.println("Invalid edge");
            return;
        }
        this.adjecencyList.get(source).add(new edge(source, destination, value));
    }

    Dijkistra(int V, int E) {
        this.createGraph(V, E);
        this.parent = new int[V];
        this.distance = new double[V];
        this.visited = new boolean[V];
        this.pq = new PriorityQueue<>(new Comparator<node>() {
            @Override
            public int compare(node o1, node o2) {
                if (Math.abs(o1.value - o2.value) < EPS) {
                    return 0;
                }
                return o1.value < o2.value ? -1 : 1;
            }
        });
    }

    void dik(int source) {
        Arrays.fill(this.distance, INF);
        Arrays.fill(this.visited, false);
        Arrays.fill(this.parent, -1);
        this.distance[source] = 0;
        this.pq.add(new node(source, 0));
        while (!pq.isEmpty()) {
            node n = pq.poll();
            if (visited[n.index] == true) {
                continue;
            }
            visited[n.index] = true;
            if (n.value > distance[n.index]) {
                continue;
            }
            List<edge> edges = this.adjecencyList.get(n.index);
            for (int i = 0; i < edges.size(); i++) {
                edge e = edges.get(i);
                if (visited[e.destination])
                    continue;
                if (distance[e.destination] > distance[e.source] + e.value) {
                    distance[e.destination] = distance[e.source] + e.value;
                    parent[e.destination] = e.source;
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

    void constructPath(int destination) {
        if (destination == -1) {
            return;
        }
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
