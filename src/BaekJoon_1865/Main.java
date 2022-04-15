package BaekJoon_1865;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static final int INF = 1000000000;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str,temp;
        StringBuilder sb = new StringBuilder();
        int tc = Integer.parseInt(br.readLine());
        while(tc>0){
            str = br.readLine().split(" ");
            int N = Integer.parseInt(str[0]);
            int M = Integer.parseInt(str[1]);
            int W = Integer.parseInt(str[2]);
            V[] value = new V[M*2+W];

            int j = 0;
            for(int i=0;i<M;i++){
                temp = br.readLine().split(" ");
                V v = new V(Integer.parseInt(temp[0]),Integer.parseInt(temp[1]),Integer.parseInt(temp[2]));
                value[j++] = v;
                v = new V(Integer.parseInt(temp[1]),Integer.parseInt(temp[0]),Integer.parseInt(temp[2]));
                value[j++] = v;
            }
            if(W>=1){
                for(int i=0;i<W;i++){
                    temp = br.readLine().split(" ");
                    V v = new V(Integer.parseInt(temp[0]),Integer.parseInt(temp[1]),Integer.parseInt(temp[2])*-1);
                    value[j++] = v;
                }
            }

            boolean sw = false;
            if(bell(N,value)) sw = true;

            if(sw) sb.append("YES");
            else sb.append("NO");
            sb.append("\n");
            tc--;
        }
        System.out.print(sb);
    }
    public static boolean bell(int N,V[] value){
        int dist[] = new int[N+1];
        for(int i=1; i<=N; i++) dist[i] = INF;
        dist[1] = 0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<value.length; j++) {
                //if(dist[value[j].from] == INF) // 한점에서 하므로 이건 필요 없음
                    //continue;
                // v로 가는 최단거리보다 dist[edge.u] + u에서 v로 가는 거리 가 더 짧을 때 갱신해준
                if(dist[value[j].to] > (dist[value[j].from] + value[j].weight)) {
                    dist[value[j].to] = dist[value[j].from] + value[j].weight;
                }
            }
        }

        // 음의 사이클 체크
        boolean check = false;
        for(int i=0; i<value.length; i++) {
            if(dist[value[i].to] > (dist[value[i].from] + value[i].weight)) {
                check = true;
                break;
            }
        }
        return check;
    }
}

class V {
    int from;
    int to;
    int weight;
    public V(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
}