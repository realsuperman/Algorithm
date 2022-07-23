package BaekJoon_2012;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Queue<V> queue = new PriorityQueue<>();
        for(int i=0;i<N;i++) queue.add(new V(Integer.parseInt(br.readLine())));

        int realRank = 1;
        long sum =0;
        while(!queue.isEmpty()){
            V v = queue.remove();
            sum+=Math.abs(v.rank-realRank);
            realRank++;
        }
        System.out.println(sum);
    }
}

class V implements Comparable<V>{
    int rank;
    public V(int rank){this.rank=rank;}

    @Override
    public int compareTo(V o) {
        return this.rank-o.rank;
    }
}