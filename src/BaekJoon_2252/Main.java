package BaekJoon_2252;

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
        StringBuilder sb = new StringBuilder();

        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int M = Integer.parseInt(str[1]);

        List<List<Integer>> graph = new ArrayList<>();
        int[] indegree = new int[N+1];
        for (int i = 0; i <=N; i++) graph.add(new ArrayList<>());

        Queue<Integer> queue = new LinkedList<>();
        for(int i=0;i<M;i++){
            str = br.readLine().split(" ");
            int x = Integer.parseInt(str[0]);
            int y = Integer.parseInt(str[1]);
            indegree[y]++;
            graph.get(x).add(y);
        }

        for(int i=1;i<=N;i++) if(indegree[i]==0){ queue.add(i); sb.append(i+" ");}

        while(!queue.isEmpty()){
            int v = queue.remove();
            for(int i=0;i<graph.get(v).size();i++){
                int curr = graph.get(v).get(i);
                indegree[curr]--;
                if(indegree[curr] == 0){
                    sb.append(curr+" ");
                    queue.add(curr);
                }
            }
        }
        System.out.println(sb);
    }
}