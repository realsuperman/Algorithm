package BaekJoon_1715;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] array = new int[N];
        Queue<V> queue = new PriorityQueue<>();
        for(int i=0;i<N;i++) queue.add(new V(Integer.parseInt(br.readLine())));
        int sum = 0;
        while(!queue.isEmpty()){
            if(queue.size()==1){
                System.out.println(sum);
                return;
            }
            V from = queue.remove();
            V to = queue.remove();
            sum+=from.value+to.value;
            queue.add(new V(from.value+to.value));
        }
    }
}

class V implements Comparable<V>{
    int value;
    public V(int value){this.value=value;}

    @Override
    public int compareTo(V o) {
        return this.value-o.value;
    }
}