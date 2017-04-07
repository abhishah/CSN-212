import java.util.*;
import java.lang.*;
import java.io.*;
 
 
class BellmanFordGraph
{
   
    class Edge {
        int src, dest, weight;
        Edge() {
            src = dest = weight = 0;
        }
    };
 
    int V, E;
    Edge edge[];
 
    BellmanFordGraph(int v, int e)
    {
        V = v;
        E = e;
        edge = new Edge[e];
        for (int i=0; i<e; ++i)
            edge[i] = new Edge();
    }
 
    // The main function that finds shortest distances from src
    // to all other vertices using Bellman-Ford algorithm.  The
    // function also detects negative weight cycle
    void BellmanFord(BellmanFordGraph graph,int src)
    {
        int V = graph.V, E = graph.E;
        int dist[] = new int[V];
 
        // Initialize distance as Infinity
        for (int i=0; i<V; ++i)
            dist[i] = Integer.MAX_VALUE;
        dist[src] = 0; // Distance from source to soure is 0
 
        // Step 2: Relax all edges |V| - 1 times. A simple
        // shortest path from src to any other vertex can
        // have at-most |V| - 1 edges
        for (int i=1; i<V; ++i)
        {
            for (int j=0; j<E; ++j)
            {
                int u = graph.edge[j].src;
                int v = graph.edge[j].dest;
                int weight = graph.edge[j].weight;
                if (dist[u]!=Integer.MAX_VALUE &&
                    dist[u]+weight<dist[v])
                    dist[v]=dist[u]+weight;
            }
        }
 
        // Step 3: check for negative-weight cycles.  The above
        // step guarantees shortest distances if graph doesn't
        // contain negative weight cycle. If we get a shorter
        //  path, then there is a cycle.
        for (int j=0; j<E; ++j)
        {
            int u = graph.edge[j].src;
            int v = graph.edge[j].dest;
            int weight = graph.edge[j].weight;
            if (dist[u]!=Integer.MAX_VALUE &&
                dist[u]+weight<dist[v])
              System.out.println("Graph contains negative weight cycle");
        }
        printArr(dist, V);
    }

    void printArr(int dist[], int V)
    {
        System.out.println("Vertex   Distance from Source");
        for (int i=0; i<V; ++i)
            System.out.println(i+"\t\t"+dist[i]);
    }
 
    public static void main(String[] args)
    {
        int V = 5;  // Number of vertices in graph
        int E = 8;  // Number of edges in graph
 
        BellmanFordGraph graph = new BellmanFordGraph(V, E);
 
        graph.edge[0].src = 0;
        graph.edge[0].dest = 1;
        graph.edge[0].weight = 2;

        graph.edge[1].src = 0;
        graph.edge[1].dest = 3;
        graph.edge[1].weight = 2;

        graph.edge[2].src = 1;
        graph.edge[2].dest = 2;
        graph.edge[2].weight = 4;
 
        graph.edge[3].src = 1;
        graph.edge[3].dest = 3;
        graph.edge[3].weight = 12;
 
        graph.edge[4].src = 1;
        graph.edge[4].dest = 4;
        graph.edge[4].weight = 1;
 
        graph.edge[5].src = 3;
        graph.edge[5].dest = 1;
        graph.edge[5].weight = 5;
 
        graph.edge[6].src = 3;
        graph.edge[6].dest = 2;
        graph.edge[6].weight = 1;
 
        graph.edge[7].src = 4;
        graph.edge[7].dest = 3;
        graph.edge[7].weight = -1;
 		long startTime = System.currentTimeMillis();
        graph.BellmanFord(graph, 0);
        long endTime = System.currentTimeMillis();
        long execTime = endTime-startTime;
        System.out.println("Execution Time: "+execTime+" start "+startTime+" end "+endTime+" V*E "+(V*E));

    }
}