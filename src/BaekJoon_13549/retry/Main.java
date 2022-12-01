package BaekJoon_13549.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int M = Integer.parseInt(str[1]);
        int[][] check = new int[100001][1];
        for(int i=0;i<100001;i++) check[i][0]=Integer.MAX_VALUE;
        check[N][0]=0;
        Queue<V> queue = new LinkedList<>();
        queue.add(new V(N,0));
        while(!queue.isEmpty()){
            V v = queue.remove();
            if(v.v == M){
                if(v.time<check[M][0]) check[M][0]=v.time;
                continue;
            }

            if(v.v+1<=100000 && v.time+1<check[v.v+1][0]) {queue.add(new V(v.v+1,v.time+1)); check[v.v+1][0]=v.time+1;}
            if(v.v-1>=0 && v.time+1<check[v.v-1][0]){queue.add(new V(v.v-1,v.time+1)); check[v.v-1][0]=v.time+1;}
            if(v.v*2<=100000 && v.time<check[v.v*2][0]){queue.add(new V(v.v*2,v.time)); check[v.v*2][0]=v.time;}
        }
        System.out.println(check[M][0]);
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
