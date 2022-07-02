package BaekJoon_1781;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        V[] list = new V[N];
        for(int i=0;i<N;i++){
            String[] str = br.readLine().split(" ");
            int d = Integer.parseInt(str[0]);
            int w = Integer.parseInt(str[1]);
            list[i] = new V(d,w);
        }
        Arrays.sort(list);
        Queue<Integer> queue = new PriorityQueue<>();
        int sum = 0;
        for(int i=0;i<list.length;i++){
            int size = list[i].day;
            queue.add(list[i].value);
            if(size<queue.size()) queue.remove();
        }
        while(!queue.isEmpty()){
            sum+=queue.remove();
        }
        System.out.println(sum);
    }
}

class V implements Comparable<V>{
    int day;
    int value;
    public V(int day,int value){
        this.day=day;
        this.value=value;
    }

    @Override
    public int compareTo(V o) {
        if(this.day==o.day) return this.value-o.value;
        return this.day-o.day;
    }
}
