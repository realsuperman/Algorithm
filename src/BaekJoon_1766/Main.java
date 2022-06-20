package BaekJoon_1766;

import java.io.*;
import java.util.*;

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int M = Integer.parseInt(str[1]);
        int[] array = new int[N+1];
        StringBuilder sb = new StringBuilder();

        List<List<Integer>> list = new ArrayList<>();
        for(int i=0;i<=N;i++) list.add(new ArrayList<>());
        for(int i=0;i<M;i++){
            str = br.readLine().split(" ");
            int from = Integer.parseInt(str[0]);
            int to = Integer.parseInt(str[1]);
            array[to]++;
            list.get(from).add(to);
        }
        Queue<V> queue = new PriorityQueue<>();
        for(int i=1;i<=N;i++) if(array[i]==0){ queue.add(new V(i));}
        while(!queue.isEmpty()){
            V v = queue.remove();
            sb.append(v.x+" ");
            for(int i=0;i<list.get(v.x).size();i++){
                if(--array[list.get(v.x).get(i)]==0){
                    queue.add(new V(list.get(v.x).get(i)));
                }
            }
        }
        System.out.println(sb);
    }
}
class V implements Comparable<V>{
    int x;
    public V(int x){
        this.x=x;
    }
    public int compareTo(V o) {
        return this.x-o.x;
    }
}