package DSA;

import java.util.ArrayList;

public class BellmanFord {
    static class Edge{
        int source,dest,w;
        
        public Edge(int s, int d, int w) {
            this.source = s;
            this.dest = d;
            this.w = w;
        }
    }
    public void createGraph(ArrayList<Edge> g[]) {
            for(int i = 0; i < g.length; i++) {
                g[i] = new ArrayList<Edge>();

            }
            g[0].add(new Edge(0,1,2));
            g[0].add(new Edge(0, 2,4));

            
            g[1].add(new Edge(1, 2,-4));
            
            g[2].add(new Edge(2, 3,2));

            g[3].add(new Edge(3, 4,4));

            g[4].add(new Edge(4, 1,-1));
           
        }
    static void bellmanford(ArrayList<Edge> g[], int start, int V) {
      int distances[] = new int[V];
      for(int i = 0; i < V; i++) {
        if(i != start) distances[i] = Integer.MAX_VALUE;
      }
     
      for(int j = 0; j < V - 1; j++) {
       for(int i = 0; i < V; i++)    {
         for(int k = 0; k < g[i].size(); k++) {
            Edge e = g[i].get(k);
            int u = e.source, v = e.dest , w = e.w;
            if(distances[u] != Integer.MAX_VALUE && distances[u] + w < distances[v]) {
                distances[v] = distances[u] + w;
            }
        }
        }
      }
      for(int i = 0; i < V; i++)    {
        for(int k = 0; k < g[i].size(); k++) {
           Edge e = g[i].get(k);
           int u = e.source, v = e.dest , w = e.w;
           if(distances[u] != Integer.MAX_VALUE && distances[u] + w < distances[v]) {
               System.out.println("graph contains negative cycle!!!");
               return;
           }
       }
       }
      for(int d: distances) {
        System.out.print(d + " ");
      }
      System.out.println();
      
    }
    public static void main(String[] args) {
     BellmanFord b = new BellmanFord();
     @SuppressWarnings("unchecked")
     ArrayList<Edge> graph[] = new ArrayList[5];
     b.createGraph(graph);   
     b.bellmanford(graph, 0, 5);
    }
}
