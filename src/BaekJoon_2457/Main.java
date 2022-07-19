package BaekJoon_2457;

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
        for(int i=0;i<N;i++){
            String[] str = br.readLine().split(" ");
            int num1 = Integer.parseInt(str[0])*100;
            int num2 = Integer.parseInt(str[1]);
            int num3 = Integer.parseInt(str[2])*100;
            int num4 = Integer.parseInt(str[3]);
            queue.add(new V(num1+num2,num3+num4));
        }

        if(queue.peek().start>301) { System.out.println(0); return;}

        int start = 301;
        int max = 0;
        int cnt = 1;

        int tempX=0,tempY=0;
        while(!queue.isEmpty()){
            V v = queue.remove();
            if(max>=1201){System.out.println(cnt); return;}

            if(start>=v.start && v.end>max){
                max = v.end;
            }else if(start<v.start){
                cnt++;
                start = max;
                if(tempX == v.start && tempY == v.end) break;

                tempX = v.start;
                tempY = v.end;
                queue.add(v);
            }
        }
        if(max>=1201){System.out.println(cnt); return;}
        System.out.println(0);
    }
}

class V implements Comparable<V>{
    int start;
    int end;
    public V(int start,int end){
        this.start=start;
        this.end=end;
    }

    @Override
    public int compareTo(V o) {
        if(this.start==o.start){
            return o.end-this.end;
        }
        return this.start-o.start;
    }
}