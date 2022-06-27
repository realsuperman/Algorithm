package BaekJoon_1202;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    static int N,K;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        K = Integer.parseInt(str[1]);
        Queue<Jewel> jewel = new PriorityQueue<>();
        for(int i=0;i<N;i++){
            str = br.readLine().split(" ");
            int weight = Integer.parseInt(str[0]);
            int value = Integer.parseInt(str[1]);
            jewel.add(new Jewel(weight,value));
        }

        Queue<Bag> bag = new PriorityQueue<>();
        for(int i=0;i<K;i++){
            str = br.readLine().split(" ");
            int weight = Integer.parseInt(str[0]);
            bag.add(new Bag(weight));
        }

        long sum = 0;
        Queue<Integer> temp = new PriorityQueue<>(Collections.reverseOrder());
        while(!bag.isEmpty()){
            int v = bag.remove().weight;

            while(!jewel.isEmpty()){
                if(jewel.peek().weight>v) break;
                temp.add(jewel.remove().value);
            }
            if(temp.size()>0) sum+=temp.remove();
        }
        System.out.println(sum);
    }
}

class Jewel implements Comparable<Jewel>{
    int weight;
    int value;

    public Jewel(int weight,int value){
        this.weight=weight;
        this.value=value;
    }

    public int compareTo(Jewel o) {
        if(o.weight==this.weight){
            return this.value-o.value;
        }
        return this.weight-o.weight;
    }
}

class Bag implements Comparable<Bag>{
    int weight;
    public Bag(int weight){this.weight=weight;}

    public int compareTo(Bag o) {
        return this.weight-o.weight;
    }
}