package DSA;
//time complexity : O((V + E) log V ) because of adjacency list

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Dijkstra {
    static class Edge{
        int source,dest,w;
        
        public Edge(int s, int d, int w) {
            this.source = s;
            this.dest = d;
            this.w = w;
        }
    }
    static class Pair implements Comparable<Pair>{
        int node,dist;
        public Pair(int n,  int d) {
            this.node = n;
            this.dist = d;
        }

        @Override
        public int compareTo(Pair p) {
            return this.dist - p.dist;
        }
    }
     public void createGraph(ArrayList<Edge> g[]) {
            for(int i = 0; i < g.length; i++) {
                g[i] = new ArrayList<Edge>();

            }
            g[0].add(new Edge(0,1,2));
            g[0].add(new Edge(0, 2,4));

            g[1].add(new Edge(1,3,7));
            g[1].add(new Edge(1, 2,1));
            
            g[2].add(new Edge(2, 4,3));

            g[3].add(new Edge(3, 5,1));

            g[4].add(new Edge(4, 3,2));
            g[4].add(new Edge(4, 5,5));
        }
    public static void algo(ArrayList<Edge> g[], int start, int v)  {
     PriorityQueue<Pair> pair = new PriorityQueue<>();
     int distance[] = new int[v];
     boolean visisted[] = new boolean[v];
     for(int i = 0;  i < v; i++) {
        if(i != start) distance[i] = Integer.MAX_VALUE;
     }
     pair.add(new Pair(start, 0));
     while(!pair.isEmpty()) {
        Pair n = pair.remove(); // removes the pair which has shortest dist
        if(!visisted[n.node]) {
            visisted[n.node] = true;
            for(int i = 0; i < g[n.node].size(); i++) {
                Edge e = g[n.node].get(i);
                int src = e.source;
                int dest = e.dest;
                if(distance[src] + e.w  < distance[dest]) {
                    distance[dest] = distance[src] + e.w;
                    
                    pair.add(new Pair(dest, distance[dest]));
                }
            }
        }
     }
     System.out.print("shortest distance from the source " + start + " is : ");
     for(int i : distance) {
        System.out.print(i + " ");
     }
    }
    public static void main(String[] args) {
        Dijkstra d = new Dijkstra();
        @SuppressWarnings("unchecked")
        ArrayList<Edge> graph[] = new ArrayList[6];
        d.createGraph(graph);
        algo(graph, 0, 6);
    }
}
