package BaekJoon_14226.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int value = Integer.parseInt(br.readLine());
        boolean[][] check = new boolean[1001][1001]; // 현재,복사

        Queue<V> queue = new LinkedList<>();
        queue.add(new V(1,0,0));
        check[1][0]=true;

        while(!queue.isEmpty()){
            V v = queue.remove();
            if(v.value == value) { System.out.println(v.time); return;}

            if(!check[v.value][v.value]){
                check[v.value][v.value] = true;
                queue.add(new V(v.value,v.value,v.time+1));
            }
            if(v.copy>0 && v.value+v.copy<=1000 && !check[v.value][v.value+v.copy]){
                check[v.value][v.value+v.copy]=true;
                queue.add(new V(v.value+v.copy,v.copy,v.time+1));
            }
            if(v.value-1>=1 && !check[v.value-1][v.copy]){
                check[v.value-1][v.copy]=true;
                queue.add(new V(v.value-1,v.copy,v.time+1));
            }

        }
    }
}

class V{
    int value;
    int copy;
    int time;
    public V(int value,int copy,int time){
        this.value=value;
        this.copy=copy;
        this.time=time;
    }
}