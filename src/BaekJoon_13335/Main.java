package BaekJoon_13335;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int[] array = new int[n];
        int w = Integer.parseInt(str[1]);
        int l = Integer.parseInt(str[2]);

        str = br.readLine().split(" ");
        for(int i=0;i<str.length;i++) array[i] = Integer.parseInt(str[i]);

        int index=0;
        int minTime=1;
        Queue<V> queue = new LinkedList<>();
        queue.add(new V(array[index++],1));

        while(!queue.isEmpty()){
            int len = 0;
            int size = queue.size();
            while(!queue.isEmpty()){
                V v = queue.remove();
                v.time = v.time+1;
                if(v.time<=w) queue.add(v);
                len++;
                if(len>=size) break;
            }
            minTime++;

            if(index<array.length && queue.size()+1<=w) {
                int sum = 0;
                for (V v : queue) sum += v.weight;
                if (sum + array[index] <= l){
                    queue.add(new V(array[index++], 1));
                }
            }

        }
        System.out.println(minTime);
    }
}

class V{
    int weight;
    int time;
    public V(int weight,int time){
        this.weight=weight;
        this.time=time;
    }
}