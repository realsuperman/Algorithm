package BaekJoon_12851.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int from = Integer.parseInt(str[0]);
        int to = Integer.parseInt(str[1]);
        int[] result = new int[2]; // 빠른시간, 찾는 방법의 수
        result[0] = Integer.MAX_VALUE;
        int[][] check = new int[100001][1];
        for(int i=0;i<=100000;i++) check[i][0]=Integer.MAX_VALUE;
        check[from][0]=Integer.MIN_VALUE;

        Queue<V> queue = new LinkedList<>();
        queue.add(new V(from,0));
        while(!queue.isEmpty()){
            V v = queue.remove();
            if(v.value == to){
                if(result[0]>v.time){ result[0]=v.time; result[1]=1;}
                else if(result[0]==v.time){result[1]+=1;}
                continue;
            }
            addQueue(v.value+1, check, v.time+1, queue);
            addQueue(v.value-1, check, v.time+1, queue);
            addQueue(v.value*2, check, v.time+1, queue);
        }

        System.out.print(result[0]+"\n"+result[1]);
    }

    public static void addQueue(int value,int[][] check, int time, Queue<V> queue){
        if(value>=0 && value<=100000 && check[value][0]>=time){
            check[value][0]=time;
            queue.add(new V(value,time));
        }
    }
}

class V{
    int value;
    int time;
    public V(int value,int time){
        this.value = value;
        this.time = time;
    }
}