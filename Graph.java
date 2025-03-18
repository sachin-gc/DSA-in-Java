package DSA;
import java.util.*;

public class Graph{
    static class Edge{
        int source,dest;
        
        public Edge(int s, int d) {
            this.source = s;
            this.dest = d;
        }
    }
    void bfs(ArrayList<Edge> g[], int start) {
        Queue<Integer> q = new LinkedList<>();
        boolean visited[] = new boolean[g.length];
        q.offer(start);
        visited[start] = true;
        System.out.print("bfs is : ");
        while(!q.isEmpty()) {
        int v = q.poll();
        System.out.print(" " + v);
        for(Edge e: g[v]){
                if(!visited[e.dest]){
                q.offer(e.dest);
                visited[e.dest] = true;
                }
            
        }
        }
        System.out.println();
    }
    void dfs(ArrayList<Edge> g[], int start, boolean visited[]) {
         System.out.print(" "+ start);
         visited[start] = true;
         for(int i = 0; i < g[start].size(); i++) {
            Edge e = g[start].get(i);
            if(!visited[e.dest]) {
                dfs(g, e.dest, visited);
            }
         }
    }
    void printGraph(ArrayList<Edge> g[]) {
        int n = g.length;
        for(int i = 1; i < n; i++) {
            System.out.println(i + "  vertex : ");
            for(Edge e : g[i]) {
                System.out.println(" s : " +e.source + " dest : " + e.dest);
            }
            System.out.println();
        }
     }
    void printAllPath(ArrayList<Edge> g[], int start, boolean visited[], int target, String path) {
        if(start == target) {
            System.out.println(path);
            return;
        }
        for(int i = 0; i<g[start].size(); i++) {
            Edge e = g[start].get(i);
            if(!visited[e.dest]) {
                visited[start] = true;
                printAllPath(g, e.dest, visited, target, path + e.dest);
                visited[start] = false;
            }
        }
    }
    void printNeigh(ArrayList<Edge> g[], int neigh) {
        if(neigh >= g.length) {
            System.out.println("invalid vertex");
            return;
        }
        System.out.println("neighbours are : ");
        for(int i = 0; i<g[neigh].size(); i++) {
            Edge e = g[neigh].get(i);
            System.out.println( " " + e.dest);
        }
    }
        
        public void createGraph(ArrayList<Edge> g[]) {
            for(int i = 1; i < g.length; i++) {
                g[i] = new ArrayList<Edge>();

            }
            g[1].add(new Edge(1,2));
            g[1].add(new Edge(1, 5));
            g[1].add(new Edge(1, 3));

            g[2].add(new Edge(2, 1));
            g[2].add(new Edge(2, 3));
            g[2].add(new Edge(2, 6));

            g[3].add(new Edge(3, 1));
            g[3].add(new Edge(3, 2));
            g[3].add(new Edge(3, 4));

            g[4].add(new Edge(4, 3));
            g[4].add(new Edge(4, 6));

            g[5].add(new Edge(5, 1));
            g[5].add(new Edge(5, 6));

            g[6].add(new Edge(6, 4));
            g[6].add(new Edge(6, 5));




            printGraph(g);
            bfs(g, 6);
            System.out.print("dfs is : ");
            dfs(g,1,new boolean[g.length]);
        }
    
     public static void main(String []args) {
        int v = 6;
        @SuppressWarnings("unchecked")
        ArrayList<Edge> graph[] = new ArrayList[v + 1];
        Graph g = new Graph();
        g.createGraph(graph);
        System.out.println(" \nall paths are : ");
        g.printAllPath(graph, 1, new boolean[v + 1], 5, "1");

     }
}
