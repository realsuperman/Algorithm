package BaekJoon_2565;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<V> list = new ArrayList<>();
        for(int i=0;i<N;i++){
            String[] str = br.readLine().split(" ");
            list.add(new V(Integer.parseInt(str[0]),Integer.parseInt(str[1])));
        }
        Collections.sort(list);
        int[] array = new int[list.size()];
        array[0] = 1;
        int MAX = 1;

        for(int i=1;i<list.size();i++){
            V v = list.get(i);
            int value = 0;
            for(int j=i-1;j>=0;j--){
                V v2 = list.get(j);
                if(v.y>v2.y && (value<array[j])){
                   value = array[j];
                }
            }
            array[i] = value+1;
            if(MAX<array[i]) MAX = array[i];
        }

        System.out.println(N-MAX);
    }
}

class V implements Comparable<V>{
    int x;
    int y;
    public V(int x,int y){
        this.x=x;
        this.y=y;
    }

    @Override
    public int compareTo(V o) {
        return this.x-o.x;
    }
}