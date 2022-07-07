package BaekJoon_19598;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Queue<Integer> queue = new PriorityQueue<>();
        Queue<V> v = new PriorityQueue<>();
        for(int i=0;i<N;i++){
            String[] str = br.readLine().split(" ");
            v.add(new V(Integer.parseInt(str[0]),Integer.parseInt(str[1])));
        }

        int cnt=1;
        queue.add(v.remove().end);

        while(!v.isEmpty()){
            V temp = v.remove();
            if(queue.peek()>temp.start){
                cnt++;
            }else{
                queue.remove();
            }
            queue.add(temp.end);
        }
        System.out.println(cnt);
    }
}

class V implements Comparable<V>{
    int start;
    int end;
    public V(int start,int end){
        this.start=start;
        this.end=end;
    }

    @Override
    public int compareTo(V o) {
        return this.start-o.start;
    }
}