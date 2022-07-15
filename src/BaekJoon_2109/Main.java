package BaekJoon_2109;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<V> list = new ArrayList<>();

        for(int i=0;i<N;i++){
            String[] str = br.readLine().split(" ");
            list.add(new V(Integer.parseInt(str[1]),Integer.parseInt(str[0])));
        }
        Collections.sort(list);

        Queue<Integer> queue = new PriorityQueue<>();
        for(int i=0;i<list.size();i++){
            if(queue.size()<list.get(i).d){
                queue.add(list.get(i).p);
            }else{
                if(queue.peek()<list.get(i).p){
                    queue.remove();
                    queue.add(list.get(i).p);
                }
            }
        }
        int sum = 0;
        while(!queue.isEmpty()) sum+=queue.remove();
        System.out.println(sum);
    }
}

class V implements Comparable<V>{
    int d,p;
    public V(int d,int p){
        this.d=d;
        this.p=p;
    }

    @Override
    public int compareTo(V o) {
        if(this.d==o.d) return this.p-o.p;
        return this.d-o.d;
    }
}