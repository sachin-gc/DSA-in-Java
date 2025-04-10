package DSA;

import java.util.ArrayList;
import java.util.Stack; 

//time complexity : O(V + E)
public class SCC {
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
            graph[0].add(new Edge(0, 3));
            graph[0].add(new Edge(0, 2));

            graph[1].add(new Edge(1, 0));


            graph[2].add(new Edge(2, 1));



            graph[3].add(new Edge(3, 4));
    }
    public static void toposort(ArrayList<Edge> graph[], boolean visited[], Stack<Integer> s, int curr) {
        visited[curr] = true;
        for(int i =0; i < graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);
            if(!visited[e.dest]) {
                toposort(graph,visited,s,e.dest);
            }
        }
        s.push(curr);
    }
    public static void dfs(int curr, ArrayList<Edge> g[], boolean vis[]) {
        vis[curr] = true;
        System.out.print(curr + " ");

        for(int i = 0; i < g[curr].size(); i++) {
            Edge e = g[curr].get(i);
            if(!vis[e.dest]) {
                dfs(e.dest, g, vis);
            }
        }

    }
        public static void kosarajualgo(ArrayList<Edge> graph[], int v) {
            Stack<Integer> stack = new Stack<>();
            boolean visited[] = new boolean[v];
            //step 1 -> topological sorting in stack
            for(int i = 0; i < v; i++) {
                if(!visited[i]) {
                    toposort(graph, visited, stack, i);
                }
            }
            //step 2 -> transpose the graph
            ArrayList<Edge> transpose[] = new ArrayList[v];
            for(int i = 0; i < graph.length; i++) {
                visited[i] = false;
                transpose[i] = new ArrayList<>();
            }
            for(int i = 0; i < v; i++) {
                for(int j = 0; j < graph[i].size(); j++) {
                    Edge e = graph[i].get(j);
                    transpose[e.dest].add(new Edge(e.dest,e.src));
                }
            }
            //step 3-> apply dfs on the transpose graph
            for(int i = 0; i < v; i++) {
                int curr = stack.pop();
                if(!visited[curr]) {
                    dfs(curr,transpose,visited);
                    System.out.println();
                }
            }


        }
    //SCC -> strongly connected component
    public static void main(String []args) {
      int v = 5;
      ArrayList<Edge> graph[] = new ArrayList[v];
      createGraph(graph);
      kosarajualgo(graph,v);
    }
}
