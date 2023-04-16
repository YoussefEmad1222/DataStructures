
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

public class graphAdjecencyList {
    int size = 0;
    List<List<edge>> adjecencyList;
    graphAdjecencyList(int V, int E) {
        if (V < 0 || E < 0) {
            System.out.println("Invalid graph");
            return;
        }
        this.size = V;
        this.adjecencyList = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            this.adjecencyList.add(new ArrayList<>());
        }
    }

    void addEdge(int source, int destination, double value) {
        if (source < 0 || destination < 0 || source >= this.size || destination >= this.size) {
            System.out.println("Invalid edge");
            return;
        }
        this.adjecencyList.get(source).add(new edge(source, destination, value));
    }

    void addNode(int node) {
        this.size++;
        this.adjecencyList.add(new ArrayList<>());
    }

    void printGraph() {
        for (int i = 0; i < this.size; i++) {
            System.out.println("Adjecency list of vertex " + i);
            for (edge e : this.adjecencyList.get(i)) {
                System.out.print(" -> " + e.destination + " (" + e.value + ")");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        graphAdjecencyList graph = new graphAdjecencyList(5, 5);
        graph.addEdge(0, 1, 1);
        graph.addEdge(0, 4, 1);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 1);
        graph.addEdge(1, 4, 1);
        graph.printGraph();
    }
}
