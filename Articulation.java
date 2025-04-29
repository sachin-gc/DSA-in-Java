package DSA;

import java.util.ArrayList;
//time complextiy = O(V + E)
public class Articulation {   // to find the bridge in a graph
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
    public static void dfs(ArrayList<Edge> graph[], int v, int time, boolean []visited,boolean []ap,
                           int dt[], int low[], int par, int curr) {
        visited[curr] = true;
        low[curr] = dt[curr] = ++time;
        int children = 0;
        for(int i = 0; i < graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);
            int neigh = e.dest;
            if(neigh == par) {
                continue;
            }else if(!visited[neigh]) {
                dfs(graph,v,time,visited,ap,dt,low,curr,neigh);
                low[curr] = Math.min(low[curr], low[neigh]);
                if(dt[curr] <= low[neigh] && par != -1) {
                    ap[curr] = true;
                }
                children++;
            }else{
                low[curr] = Math.min(low[curr], dt[neigh]);
            }
        }
        if(par == -1 && children > 1) {
            ap[curr] = true;
        }
    }
    public static void getPoint(ArrayList<Edge> graph[], int v) {
        int time = 0;
        boolean []visited = new boolean[v];
        int dt[] = new int[v];
        boolean []ap = new boolean[v];
        int low[] = new int[v];
        for(int i = 0; i < v; i++) {
            dfs(graph,v,time,visited,ap,dt,low,-1, i);
        }
        System.out.print("articulation points are : ");
        for(int i = 0; i < v; i++) {
            if(ap[i]) {
                System.out.print(" " + i);
            }
        }

    }


    public static void main(String []args) {
        int v = 5;
        ArrayList<Edge> graph[] = new ArrayList[v];
        createGraph(graph);
        getPoint(graph,v);

    }
}
