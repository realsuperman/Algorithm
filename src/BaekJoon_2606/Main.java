package BaekJoon_2606;

import java.util.*;

public class Main {
    public static boolean[] check;
    static int conut = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int vCnt = sc.nextInt();
        int eCnt = sc.nextInt();
        int start = 1;
        int s,e;
        Graph[] graph = new Graph[vCnt];
        check = new boolean[vCnt];

        for(int i=0;i<vCnt;i++){
            check[i] = false;
            graph[i] = new Graph(i+1);
        }
        for(int i=0;i<eCnt;i++){
            s = sc.nextInt()-1;
            e = sc.nextInt()-1;

            graph[s].add(graph[e]);
            graph[e].add(graph[s]);
        }
        dfs(graph[start-1]);
        System.out.println(--conut);
    }
    public static void dfs(Graph graph){
        if(check[graph.no-1]) return;
        conut++;
        //System.out.print(graph.no+" ");
        check[graph.no-1]=true; // 그래프 방문함
        if(graph.child.size()>0){
            for(int i=0;i<graph.child.size();i++)
                dfs(graph.child.get(i));
        }
    }
}

class Graph implements Comparable<Graph>{
    int no;
    List<Graph> child=new ArrayList<>();

    public Graph(int no){
        this.no = no;
    }

    public void add(Graph graph){
        child.add(graph);
        Collections.sort(child);
    }
    public int getNo() {
        return this.no;
    }
    @Override
    public int compareTo(Graph s) {
        if (this.no < s.getNo()) {
            return -1;
        } else if (this.no > s.getNo()) {
            return 1;
        }
        return 0;
    }
}