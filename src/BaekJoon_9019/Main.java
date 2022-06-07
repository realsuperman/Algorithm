package BaekJoon_9019;

import java.io.*;
import java.util.*;

public class Main{
    static boolean[] visited;

    public static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for(int i=0;i<N;i++){
            String[] str = br.readLine().split(" ");
            visited = new boolean[10000];
            bfs(Integer.parseInt(str[0]),Integer.parseInt(str[1]));
        }
        System.out.println(sb);
    }
    public static void bfs(int a,int b){
        Queue<V> queue = new LinkedList<>();
        queue.add(new V(a,""));
        visited[a] = true;
        while(!queue.isEmpty()){
            V v = queue.remove();
            int value = v.array;
            if(value == b){
                sb.append(v.str+"\n");
                return;
            }

            int num = (value*2)%10000;
            if(visited[num] == false){
                queue.add(new V(num,v.str+"D"));
                visited[num]=true;
            }

            if(value==0 && visited[9999]==false){ queue.add(new V(9999,v.str+"S")); visited[9999]=true;}
            else if(value!=0 && visited[value-1]==false){queue.add(new V(value-1,v.str+"S")); visited[value-1]=true;}

            int s1 = (value%1000)*10+(value/1000);
            if(visited[s1]==false){
                queue.add(new V(s1,v.str+"L"));
                visited[s1]=true;
            }

            s1 = (value%10)*1000+(value/10);
            if(visited[s1]==false){
                queue.add(new V(s1,v.str+"R"));
                visited[s1]=true;
            }
        }
    }
}

class V{
    int array;
    String str="";

    public V(int v,String str){
        array = v;
        this.str=str;
    }
}