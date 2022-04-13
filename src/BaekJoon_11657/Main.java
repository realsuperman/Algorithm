package BaekJoon_11657;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static final int INF = 1000000000;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]); int M = Integer.parseInt(str[1]);
        Value[] value = new Value[M];
        for(int i=0;i<M;i++){
            str = br.readLine().split(" ");
            Value v = new Value(Integer.parseInt(str[0]),Integer.parseInt(str[1]),Integer.parseInt(str[2]));
            value[i] = v;
        }
        long dist[] = new long[N+1];
        for(int i=1; i<=N; i++) dist[i] = INF;
        dist[1] = 0;
        for(int i=0; i<N-1; i++) {
            for(int j=0; j<M; j++) {
                if(dist[value[j].from] == INF)
                    continue;
                // v로 가는 최단거리보다 dist[edge.u] + u에서 v로 가는 거리 가 더 짧을 때 갱신해준
                if(dist[value[j].to] > (dist[value[j].from] + value[j].weight)) {
                    dist[value[j].to] = dist[value[j].from] + value[j].weight;
                }
            }
        }

        // 음의 사이클 체크
        boolean check = false;
        for(int i=0; i<M; i++) {
            if(dist[value[i].from] != INF && dist[value[i].to] > dist[value[i].from] + value[i].weight) {
                check = true;
                break;
            }
        }

        if(check)
            System.out.println(-1);
        else {
            for(int i=2; i<=N; i++) {
                if(dist[i] == INF)
                    System.out.println("-1");
                else
                    System.out.println(dist[i]);
            }
        }
    }
}

class Value {
    int from;
    int to;
    int weight;
    public Value(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
}