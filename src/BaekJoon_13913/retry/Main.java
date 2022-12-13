package BaekJoon_13913.retry;

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
        int from = Integer.parseInt(str[0]);
        int to = Integer.parseInt(str[1]);
        int[][] check = new int[100001][1];
        for(int i=0;i<=100000;i++) check[i][0]=Integer.MAX_VALUE;
        check[from][0]=Integer.MIN_VALUE;

        Queue<V> queue = new LinkedList<>();
        List<Integer> temp = new ArrayList<>();
        temp.add(from);
        queue.add(new V(from,0,temp));
        int result = Integer.MAX_VALUE;
        List<Integer> list = new ArrayList<>();
        while(!queue.isEmpty()){
            V v = queue.remove();
            if(v.value == to){
                result=v.time;
                list = v.list;
                break;
            }

            addQueue(v.value+1,check,v.time+1,queue,v.list);
            addQueue(v.value-1,check,v.time+1,queue,v.list);
            addQueue(v.value*2,check,v.time+1,queue,v.list);

        }

        System.out.println(result);
        StringBuilder sb = new StringBuilder();
        for(int v : list) sb.append(v+" ");
        System.out.println(sb);
    }

    public static void addQueue(int value, int[][] check, int time, Queue<V> queue, List<Integer> copy ){
        if(value>=0 && value<=100000 && check[value][0]>time){
            List<Integer> paste = new ArrayList<>();
            for(int v : copy) paste.add(v);
            paste.add(value);
            check[value][0]=time;
            queue.add(new V(value,time,paste));
        }
    }
}

class V{
    int value;
    int time;
    List<Integer> list;
    public V(int value, int time, List<Integer> list){
        this.value = value;
        this.time = time;
        this.list = list;
    }
}