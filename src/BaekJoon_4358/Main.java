package BaekJoon_4358;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<String,Integer>  map = new HashMap<>();

        String str="";
        double cnt = 0;
        while(true) {
            str = br.readLine();
            if(str==null || str.length()==0) break;
            if(map.get(str)==null) map.put(str,1);
            else map.put(str,map.get(str)+1);
            cnt++;
        }

        Queue<V> queue = new PriorityQueue<>();
        for(String v : map.keySet()) queue.add(new V(v,map.get(v),cnt));

        StringBuilder sb = new StringBuilder();
        while(!queue.isEmpty()){
            V v = queue.remove();
            sb.append(v.str+" "+String.format("%.4f",v.v*100)+"\n");
        }
        System.out.println(sb);
    }
}

class V implements Comparable<V>{
    String str;
    double v;
    public V(String str,double num1,double num2){
        this.str=str;
        this.v=num1/num2;
    }

    @Override
    public int compareTo(V o) {
        return this.str.compareTo(o.str);
    }
}