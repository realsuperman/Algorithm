package BaekJoon_2623;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int M = Integer.parseInt(str[1]);

        List<List<Integer>> list = new ArrayList<>();
        int[] degree = new int[N+1];
        for(int i=0;i<=N;i++) list.add(new ArrayList<>());
        for(int i=0;i<M;i++){
            str = br.readLine().split(" ");
            for(int j=1;j<str.length-1;j++){
                list.get(Integer.parseInt(str[j])).add(Integer.valueOf(str[j+1]));
                degree[Integer.parseInt(str[j+1])]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for(int i=1;i<=N;i++) if(degree[i]==0) queue.add(i);

        if(queue.size()==0) {System.out.println(0); return;}
        StringBuilder sb = new StringBuilder();
        while(!queue.isEmpty()){
            int v = queue.remove();
            sb.append(v+"\n");
            for(int i=0;i<list.get(v).size();i++){
                if(--degree[list.get(v).get(i)]==0) queue.add(list.get(v).get(i));
            }
        }
        for(int i=1;i<=N;i++) if(degree[i]>0) {System.out.println(0); return;}
        System.out.println(sb);
    }
}