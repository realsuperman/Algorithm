package BaekJoon_14226.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int result = Integer.parseInt(br.readLine());
        boolean[] check = new boolean[1001];

        Queue<V> queue = new LinkedList<>();
        queue.add(new V(1,0));
        while(!queue.isEmpty()){
            V v = queue.remove();
            int sum = v.value+v.value;
            if(sum == result){System.out.println(v.time+2); break;}
            
        }
    }
}

class V{
    int value;
    int time;
    public V(int value,int time){
        this.value=value;
        this.time=time;
    }
}