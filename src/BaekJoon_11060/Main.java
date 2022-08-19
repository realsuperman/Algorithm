package BaekJoon_11060;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] str = br.readLine().split(" ");
        int[] array = new int[N];
        for(int i=0;i<str.length;i++) array[i]= Integer.parseInt(str[i]);
        int[][] check = new int[N][1];
        for(int i=0;i<N;i++) check[i][0]=Integer.MAX_VALUE;
        check[0][0]=Integer.MIN_VALUE;

        Queue<V> queue = new LinkedList<>();
        queue.add(new V(0,0));
        int MIN = Integer.MAX_VALUE;

        while(!queue.isEmpty()){
            V v = queue.remove();
            if(v.value==N-1){
                MIN = MIN>v.time?v.time:MIN;
                continue;
            }

            for(int i=array[v.value];i>=1;i--){
                if(v.value+i>=N || check[v.value+i][0]<v.time+1) continue;
                check[v.value+i][0]=v.time+1;
                queue.add(new V(v.value+i,v.time+1));
            }
        }
        System.out.println(MIN==Integer.MAX_VALUE?-1:MIN);
    }
}

class V{
    int value;
    int time;
    public V(int value,int time){
        this.value=value;
        this.time=time;
    }
}