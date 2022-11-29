package BaekJoon_5014.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int F = Integer.parseInt(str[0]);
        int S = Integer.parseInt(str[1]);
        int G = Integer.parseInt(str[2]);
        int U = Integer.parseInt(str[3]);
        int D = Integer.parseInt(str[4]);
        boolean[] check = new boolean[1000001];
        Queue<V> queue = new LinkedList<>();
        queue.add(new V(S,0));
        check[S]=true;
        while(!queue.isEmpty()){
            V v = queue.remove();
            if(v.v == G){System.out.println(v.time); return;}

            if(v.v+U<=F && !check[v.v+U]){ queue.add(new V(v.v+U,v.time+1)); check[v.v+U]=true;}
            if(v.v-D>=1 && !check[v.v-D]){ queue.add(new V(v.v-D,v.time+1)); check[v.v-D]=true;}
        }
        System.out.println("use the stairs");
        // 총 F층의 고층 건물임 , 목적지는 G층에 있음, 내위치는 S층임, U는 UP버튼 누르면 그만큼 올라간다는 의미, D는 Down버튼 누르면 그만큼 내려간다는 의미임
        // 만약 도착할 수 없다면 use the stairs를 출력하면 된다 ( 우리가 구하고자 하는 값은 최소 버튼을 누르는 횟수임 S->G)
    }
}

class V{
    int v;
    int time;
    public V(int v,int time){
        this.v=v;
        this.time=time;
    }
}