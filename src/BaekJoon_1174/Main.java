package BaekJoon_1174;

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
        for(int i=0;i<=9;i++) queue.add(new V(i));
        int cnt = 1;

        while(!queue.isEmpty()){
            V v = queue.remove();
            if(cnt==N) {System.out.println(v.value); return;}

            String str = String.valueOf(v.value);
            for(int i=0;i<=9;i++){
                if((str.charAt(str.length()-1)-48)>i) queue.add(new V(Long.parseLong(str+i)));
            }
            cnt++;
        }

        System.out.println(-1);
    }
}

class V implements Comparable<V>{
    long value;
    public V(long value){this.value=value;}

    @Override
    public int compareTo(V o) {
        return (int) (this.value-o.value);
    }
}