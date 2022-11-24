package BaekJoon_1697.retry;

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
        boolean[] check = new boolean[100001];

        Queue<V> queue = new LinkedList<>();
        queue.add(new V(N,0));
        check[N]=true;
        while(!queue.isEmpty()){
            V v = queue.remove();
            if(v.v == M){ System.out.println(v.time); return;}

            if((v.v+1>=0 && v.v+1<=100000) && !check[v.v+1]){ check[v.v+1]=true; queue.add(new V(v.v+1,v.time+1));}
            if((v.v-1>=0 && v.v-1<=100000) && !check[v.v-1]){ check[v.v-1]=true; queue.add(new V(v.v-1,v.time+1));}
            if((v.v*2>=0 && v.v*2<=100000) && !check[v.v*2]){ check[v.v*2]=true; queue.add(new V(v.v*2,v.time+1));}
        }

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