package BaekJoon_1647;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int M = Integer.parseInt(str[1]);

        List<Node> list = new ArrayList<>();
        int[] array = new int[N];
        for(int i=0;i<N;i++) array[i]=i;

        for(int i=0;i<M;i++){
            str = br.readLine().split(" ");
            int from = Integer.parseInt(str[0])-1;
            int to = Integer.parseInt(str[1])-1;
            int value = Integer.parseInt(str[2]);
            list.add(new Node(from,to,value));
        }

        int answer = 0;
        Collections.sort(list);
        int minus=0;
        for(int i=0;i<list.size();i++){
            Node node = list.get(i);
            if(!find(array,node.from,node.to)){ // 사이클 여부 판단
                minus=node.value;
                answer+=node.value;
                union(array,node.from,node.to);
            }
        }
        System.out.println(answer-minus);
    }

    public static int getParent(int array[],int x){
        if(array[x]==x) return x;
        return array[x] = getParent(array,array[x]);
    }

    public static void union(int array[],int x,int y){
        int a = getParent(array,x);
        int b = getParent(array,y);
        if(a<b) array[b]=a;
        else array[a]=b;
    }

    public static Boolean find(int array[],int x,int y){
        int a = getParent(array,x);
        int b = getParent(array,y);
        return a==b;
    }
}

class Node implements Comparable<Node>{
    int from,to,value;
    public Node(int from,int to,int value){
        this.from = from;
        this.to = to;
        this.value = value;
    }
    public int compareTo(Node node){
        return this.value-node.value;
    }
}