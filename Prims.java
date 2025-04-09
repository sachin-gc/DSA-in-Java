package DSA;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Prims {
    static class Edge {
        int src,dest,w;
        public Edge(int s, int d, int w) {
            this.dest = d;
            this.src = s;
            this.w = w;
        }

    }
    static class Pairs implements Comparable<Pairs>{
        int node,cost;
        public Pairs(int n, int c) {
            this.node = n;
            this.cost = c;
        }
        @Override
        public int compareTo(Pairs p) {
            return this.cost - p.cost;
        }
    }
    public static void createGraph(ArrayList<Edge> graph[]) {
        for(int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        graph[0].add(new Edge(0, 1, 10));
        graph[1].add(new Edge(1, 0, 10));

        graph[0].add(new Edge(0, 2, 15));
        graph[2].add(new Edge(2, 0, 15));

        graph[0].add(new Edge(0, 3, 30));
        graph[3].add(new Edge(3, 0, 30));

        graph[1].add(new Edge(1, 3, 40));
        graph[3].add(new Edge(3, 1, 40));

        graph[2].add(new Edge(2, 3, 50));
        graph[3].add(new Edge(3, 2, 50));

    }
    public static void primsalgo(ArrayList<Edge> graph[], int v) {
        PriorityQueue<Pairs> pq = new PriorityQueue<>();
        boolean visited[] = new boolean[v];
        pq.add(new Pairs(0,0));
        int mst = 0;
        while(!pq.isEmpty()) {
          Pairs curr = pq.remove();
          if(!visited[curr.node]) {
              visited[curr.node] = true;
              mst += curr.cost;
              for(int i = 0; i < graph[curr.node].size(); i++) {
                  Edge e = graph[curr.node].get(i);
                  if(!visited[e.dest]) {
                      pq.add(new Pairs(e.dest, e.w));
                  }
              }
          }
        }
        System.out.println("the mst of the graph is : " + mst);
    }
    public static void main(String []args) {
        int v = 4;
        ArrayList<Edge> graph[] = new ArrayList[v];
        createGraph(graph);
        primsalgo(graph, v);
    }
}
