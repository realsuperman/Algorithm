package BaekJoon_16953;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        long A = Long.parseLong(str[0]);
        long B = Long.parseLong(str[1]);
        Map<Long, Integer> map = new HashMap<>();

        Queue<V> queue = new LinkedList<>();
        queue.add(new V(A,1));
        map.put(A, 1);
        while (!queue.isEmpty()) {
            V v = queue.remove();
            if(v.number==B){System.out.println(v.time); return;}
            if(v.number*2<=B&&map.get(v.number*2)==null){
                queue.add(new V(v.number*2,v.time+1));
                map.put(v.number,1);
            }

            String st = String.valueOf(v.number)+"1";
            long num = Long.valueOf(st);
            if(num<=B&&map.get(num)==null){
                queue.add(new V(num,v.time+1));
                map.put(num,1);
            }
        }
        System.out.println(-1);
    }
}

class V{
    long number;
    int time;
    public V(long number,int time){
        this.number=number;
        this.time=time;
    }
}