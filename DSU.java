package DSA;

import java.util.ArrayList;
import java.util.List;

public class DSU {
    List<Integer> rank = new ArrayList<>();
    List<Integer> parent = new ArrayList<>();
    public DSU(int n) {
        for(int i = 0; i <=n; i++) {
            rank.add(0);
            parent.add(i);
        }
    }
    public int findParent(int x){
        if(x == parent.get(x)) return x;
        int u_p = findParent(parent.get(x));
        parent.set(x,u_p);
        return u_p;
    }
    public void union(int x, int y) {
        int u_parentx = findParent(x);
        int u_parenty = findParent(y);

        if(u_parenty != u_parentx) {
            if(rank.get(u_parentx) < rank.get(u_parenty)) {
                parent.set(u_parentx,u_parenty);
            }else if(rank.get(u_parentx) > rank.get(u_parenty)) {
                parent.set(u_parenty, u_parentx);
            }else{
                parent.set(u_parenty, u_parentx);
                rank.set(u_parentx, rank.get(u_parentx) + 1);
            }
        }
    }
    public boolean isConnected(int x,int y) {
        return findParent(x) == findParent(y);
    }
    public static void main(String []args) {
        DSU d = new DSU(6);
        d.union(0,1);
        d.union(4,5);
        d.union(0,5);
        System.out.println(d.isConnected(0,5));
    }
}
