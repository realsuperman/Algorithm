package BaekJoon_1966.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while(T-->0){
            String[] str = br.readLine().split(" ");
            Integer.parseInt(str[0]);
            int findNumber = Integer.parseInt(str[1]);
            str = br.readLine().split(" ");
            Queue<V> queue = new LinkedList<>();
            for(int i=0;i<str.length;i++) queue.add(new V(i, Integer.parseInt(str[i])));
            solution(queue, findNumber);
        }

    }

    private static void solution(Queue<V> queue, int findNumber) {
        int time = 1;
        while(!queue.isEmpty()){
            V v = queue.remove();
            boolean isCheck = false;
            for(V check : queue){
                if(check.priority>v.priority){
                    queue.add(v);
                    isCheck=true;
                    break;
                }
            }
            if(isCheck) continue;
            if(v.number==findNumber){
                System.out.println(time);
                return;
            }
            time++;
        }
    }
}

class V {
    int number;
    int priority;
    public V(int number,int priority){
        this.number=number;
        this.priority=priority;
    }
}