package BaekJoon_1197;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int answer = 0;
        String[] str = br.readLine().split(" ");
        int V = Integer.parseInt(str[0]);
        int E = Integer.parseInt(str[1]);

        List<Node> list = new ArrayList<>();
        int[] array = new int[V+1];
        for(int i=0;i<=V;i++) array[i]=i;

        for(int i=0;i<E;i++){
            str = br.readLine().split(" ");
            int start = Integer.parseInt(str[0]);
            int end = Integer.parseInt(str[1]);
            int cost = Integer.parseInt(str[2]);
            list.add(new Node(start,end,cost));
        }
        Collections.sort(list);
        for(int i=0;i<list.size();i++){
            Node node = list.get(i);
            if(!find(array,node.from,node.to)){
                answer+=node.value;
                union(array,node.from,node.to);
            }
        }
        System.out.println(answer);
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