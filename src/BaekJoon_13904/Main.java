package BaekJoon_13904;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Queue<V> queue = new PriorityQueue<>();
        for(int i=0;i<N;i++){
            String[] str = br.readLine().split(" ");
            int d = Integer.parseInt(str[0]);
            int w = Integer.parseInt(str[1]);
            queue.add(new V(d,w));
        }

        int sum = 0;
        int time = queue.peek().day;


        while(time>0){
            Queue<V2> temp = new PriorityQueue<>();

            while(!queue.isEmpty()){
                if(queue.peek().day<time) break;
                V v = queue.remove();
                temp.add(new V2(v.day,v.value));
            }
            if(temp.size()>0){
                sum+=temp.remove().value;
                while(!temp.isEmpty()){
                    V2 v2 = temp.remove();
                    queue.add(new V(v2.day,v2.value));
                }
            }
            time--;
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
        if(o.day==this.day){
            return o.value-this.value;
        }
        return o.day-this.day;
    }
}

class V2 implements Comparable<V2>{
    int day;
    int value;
    public V2(int day,int value){
        this.day=day;
        this.value=value;
    }

    @Override
    public int compareTo(V2 o) {
        return o.value-this.value;
    }
}