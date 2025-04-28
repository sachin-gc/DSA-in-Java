package DSA;

import java.util.ArrayList;

public class TarjansAlgo {   // to find the bridge in a graph
    static class Edge {
        int src, dest;

        public Edge(int s, int d) {
            this.dest = d;
            this.src = s;

        }
    }
    public static void createGraph(ArrayList<Edge> graph[]) {
        for(int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        graph[0].add(new Edge(0, 1));
        graph[0].add(new Edge(0, 2));
        graph[0].add(new Edge(0, 3));

        graph[1].add(new Edge(1, 0));
        graph[1].add(new Edge(1, 2));


        graph[2].add(new Edge(2, 0));
        graph[2].add(new Edge(2, 1));

        graph[3].add(new Edge(3, 0));
        graph[3].add(new Edge(3, 4));
        //graph[3].add(new Edge(3, 5));


        graph[4].add(new Edge(4, 3));
       // graph[4].add(new Edge(4, 5));

        //graph[5].add(new Edge(5, 3));

       // graph[5].add(new Edge(5, 4));
    }
    public static void  dfs(ArrayList<Edge> graph[], int curr, int v ,int []dst, boolean []vis,int []low,int time, int par) {
        vis[curr] = true;
        dst[curr] = low[curr] = ++time;

        for(int i = 0; i < graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);
            if(e.dest == par) {
                continue;
            }else if(!vis[e.dest]) {
                dfs(graph, e.dest, v, dst, vis, low, time, curr);
                low[curr] = Math.min(low[curr], low[e.dest]);
                if (dst[curr] < low[e.dest]) {
                    System.out.println(curr + " <------> " + e.dest);
                }

            }else{
                low[curr] = Math.min(low[curr], dst[e.dest]);

            }
        }

    }
    public static void bridge(ArrayList<Edge> graph[], int v) {
        int []dst = new int[v];
        boolean []visited = new boolean[v];
        int []low = new int[v];
        int time = 0;
        for(int i = 0; i < v; i++) {
            if(!visited[i]) {
                dfs(graph, i, v, dst, visited, low, time, -1);
            }
        }
    }
    public static void main(String []args) {
        int v = 5;
        ArrayList<Edge> graph[] = new ArrayList[v];
        createGraph(graph);
        bridge(graph,v);

    }
}
