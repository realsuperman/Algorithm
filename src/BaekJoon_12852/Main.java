package BaekJoon_12852;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    static boolean[] check;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        check = new boolean[N+1];
        Queue<V> queue = new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        list.add(N);

        queue.add(new V(N,0,list));
        check[N]=true;
        while(!queue.isEmpty()){
            V v = queue.remove();
            if(v.v==1){
                System.out.println(v.time);
                for(int i : v.list) System.out.print(i+" ");
                return;
            }
            if(v.v%3==0 && !check[v.v/3]){
                check[v.v/3]=true;
                v.list.add(v.v/3);
                int size = v.list.size();
                queue.add(new V(v.v/3,v.time+1,v.list));
                v.list.remove(size-1);
            }
            if(v.v%2==0 && !check[v.v/2]){
                check[v.v/2]=true;
                v.list.add(v.v/2);
                int size = v.list.size();
                queue.add(new V(v.v/2,v.time+1,v.list));
                v.list.remove(size-1);
            }
            if(v.v-1>2 && !check[v.v-1]){
                check[v.v-1]=true;
                v.list.add(v.v-1);
                int size = v.list.size();
                queue.add(new V(v.v-1,v.time+1,v.list));
                v.list.remove(size-1);
            }
        }

    }
}

class V{
    int v;
    int time;
    List<Integer> list = new ArrayList<>();
    public V(int v,int time,List<Integer> list){
        this.v=v;
        this.time=time;
        for(int i : list) this.list.add(i);
    }
}