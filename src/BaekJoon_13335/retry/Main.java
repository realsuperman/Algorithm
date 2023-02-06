package BaekJoon_13335.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        Integer.parseInt(str[0]);
        int W = Integer.parseInt(str[1]); // 다리 길이
        int L = Integer.parseInt(str[2]); // 다리 최대 무게
        int time = 0;

        List<Integer> list = new LinkedList<>();
        str = br.readLine().split(" ");
        for(int i=0;i<str.length;i++) list.add(Integer.valueOf(str[i]));

        Queue<V> queue = new LinkedList<>();
        while(true){
            int currentWeight = 0;
            int currentCount = 0;
            Queue<V> temp = new LinkedList<>();

            while(!queue.isEmpty()){ // 시간을 하나씩 더해줌
                V v = queue.remove();
                v.location++;
                if(v.location<=W){
                    currentCount++;
                    currentWeight+=v.weight;
                    temp.add(new V(v.weight, v.location));
                }
            }
            queue = temp;

            if(list.size()>0) {
                if (currentCount < W && currentWeight + list.get(0) <= L) {
                    int v = list.remove(0);
                    queue.add(new V(v, 1));
                }
            }
            time++;

            if(queue.isEmpty()) break;
        }

        System.out.println(time);
    }
}

class V{
    int weight; // 자동차 무게
    int location; // 최대 다리 길이까지 늘어난다(1부터 시작)
    public V(int weight,int location){
        this.weight=weight;
        this.location=location;
    }
}