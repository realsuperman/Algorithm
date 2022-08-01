package Programmers_6;

import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        int n = 3;
        int[][] roads = {{1,2},{2,3}};
        int[] sources = {2,3};
        int destination = 1;
        int[] t = solution(n,roads,sources,destination);
        for(int v : t) System.out.print(v+" ");
        System.out.println();

        n = 5;
        int[][] roads1 = {{1,2},{1,4},{2,4},{2,5},{4,5}};
        int[] sources1 = {1,3,5};
        destination = 5;
        int[] t2 = solution(n,roads1,sources1,destination);
        for(int v : t2) System.out.print(v+" ");
        System.out.println();
    }

    public static int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer;
        List<Edge> graph[] = new ArrayList[n];
        for(int i=0; i<n; i++) graph[i] = new ArrayList<>();

        for(int i=0; i<roads.length; i++) {
            int a = roads[i][0];
            int b = roads[i][1];
            int c = 1;
            graph[a-1].add(new Edge(b-1,c));
            graph[b-1].add(new Edge(a-1,c));
        }

        answer = answer(n,destination,graph);
        for(int i=0;i<answer.length;i++) if(answer[i]==Integer.MAX_VALUE) answer[i]=-1;

        int[] t = new int[sources.length];
        for(int i=0;i<sources.length;i++) t[i] = answer[sources[i]-1];
        return t;
    }

    public static int[] answer(int n,int k,List<Edge> graph[]){
        int dist[] = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[k-1] = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.w - o2.w;
            }
        });
        pq.add(new Edge(k-1, 0));
        while(!pq.isEmpty()) {
            Edge now = pq.poll();
            if(now.w > dist[now.v]) continue;
            for(int i=0; i<graph[now.v].size(); i++) {
                Edge next = graph[now.v].get(i);
                if(dist[next.v] > dist[now.v] + next.w) {
                    dist[next.v] = dist[now.v] + next.w;
                    pq.add(new Edge(next.v, dist[next.v]));
                }
            }
        }
        return dist;
    }
}

class Edge {
    int v, w;
    public Edge(int v, int w) {
        this.v = v;
        this.w = w;
    }
}