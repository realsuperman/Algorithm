package BaekJoon_13913;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");

        int N = Integer.parseInt(str[0]); // from
        boolean[] check = new boolean[100001];
        int K = Integer.parseInt(str[1]); // to

        if(N==K){ System.out.println("0"); System.out.println(N); return;}
        if(N>K){
            System.out.println(N-K);
            for(int i=N;i>=0;i--){
                System.out.print(i+" ");
                if(i==K) break;
            }
            System.out.println();
            return;
        }
        Queue<V> queue = new LinkedList<>();

        List<Integer> list = new ArrayList<>(); list.add(N);
        queue.add(new V(N,list,0));
        check[N]=true;

        while(!queue.isEmpty()){
            V v = queue.remove();
            if(v.x==K){
                System.out.println(v.time);
                for (int loop : v.list) System.out.print(loop + " ");
                System.out.println();
                break;
            }

            if(v.x-1>=0&&!check[v.x-1]) {
                list = new ArrayList<>();
                for (int loop : v.list) list.add(loop);
                list.add(v.x - 1);
                queue.add(new V(v.x - 1, list, v.time + 1));
                check[v.x-1]=true;
            }

            if(v.x+1<=100000&&!check[v.x+1]) {
                list = new ArrayList<>();
                for (int loop : v.list) list.add(loop);
                list.add(v.x + 1);
                queue.add(new V(v.x + 1, list, v.time + 1));
                check[v.x+1]=true;
            }

            if(v.x*2<=100000&&!check[v.x*2]) {
                list = new ArrayList<>();
                for (int loop : v.list) list.add(loop);
                list.add(v.x * 2);
                queue.add(new V(v.x * 2, list, v.time + 1));
                check[v.x*2]=true;
            }
        }

    }
}

class V{
    int x;
    List<Integer> list;
    int time;
    public V(int x,List<Integer> list,int time){
        this.x=x;
        this.list = list;
        this.time=time;
    }
}