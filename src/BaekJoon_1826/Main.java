package BaekJoon_1826;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<V> list = new ArrayList<>();
        String[] str;

        for(int i=0;i<N;i++){
            str = br.readLine().split(" ");
            list.add(new V(Integer.parseInt(str[0]),Integer.parseInt(str[1])));
        }

        Collections.sort(list);
        str = br.readLine().split(" ");
        int endPoint = Integer.parseInt(str[0]);
        int init = Integer.parseInt(str[1]);
        Queue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        int index = 0;
        int result = 0;

        while(index<list.size()){
            if(list.get(index).location>init){
                if(queue.size()>0){result++; init+=queue.remove();}
                else {System.out.println(-1); return;}

                if(init>=endPoint){System.out.println(result); return;}
                index--;
            }else{
                queue.add(list.get(index).value);
            }
            index++;
        }

        if(init>=endPoint){System.out.println(result); return;}
        while(!queue.isEmpty()){
            init = init+queue.remove();
            result++;
            if(init>=endPoint){System.out.println(result); return;}
        }
        System.out.println(-1);
    }
}

class V implements Comparable<V>{
    int location;
    int value;
    public V(int location,int value){
        this.location=location;
        this.value=value;
    }

    @Override
    public int compareTo(V o) {
        if(this.location==o.location){
            return o.value-this.value;
        }
        return this.location-o.location;
    }
}