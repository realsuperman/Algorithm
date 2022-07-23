package BaekJoon_1697;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");

        int subin = Integer.parseInt(str[0]);
        int brother = Integer.parseInt(str[1]);
        int[] check = new int[100001];

        Queue<V> queue = new LinkedList<>();
        queue.add(new V(subin,0));
        check[subin]=Integer.MIN_VALUE;

        int MIN = Integer.MAX_VALUE;
        while(!queue.isEmpty()){
            V v = queue.remove();
            if(v.v == brother){
                if(MIN>v.time) MIN = v.time;

                continue;
            }

            if(v.v-1>=0 && (check[v.v-1]==0 || v.time+1<check[v.v-1])){
                check[v.v-1]=v.time+1;
                queue.add(new V(v.v-1,v.time+1));
            }
            if(v.v+1<=100000 && (check[v.v+1]==0 || v.time+1<check[v.v+1]) ){
                check[v.v+1] = v.time+1;
                queue.add(new V(v.v+1,v.time+1));
            }
            if(v.v*2>=0 && v.v*2<=100000 && (check[v.v*2]==0 || v.time+1<check[v.v*2]) ){
                check[v.v*2]=v.time+1;
                queue.add(new V(v.v*2,v.time+1));
            }
        }
        System.out.println(MIN);
    }
}

class V{
    int v;
    int time;
    public V(int v,int time){this.v=v; this.time=time;}
}