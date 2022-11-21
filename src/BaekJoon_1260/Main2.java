package BaekJoon_1260;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main2 {
    static int N;
    static boolean[][] check;
    static boolean[] nodes;
    static int[] dx ={0,0,1,-1};
    static int[] dy ={1,-1,0,0};
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        int M = Integer.parseInt(str[1]);
        int start = Integer.parseInt(str[2]);
        check = new boolean[N][N];
        nodes = new boolean[N];

        while(M-->0){
            str=br.readLine().split(" ");
            check[Integer.parseInt(str[0])-1][Integer.parseInt(str[1])-1]=true;
            check[Integer.parseInt(str[1])-1][Integer.parseInt(str[0])-1]=true;
        }
        nodes[start-1]=true;
        sb.append(start+" ");
        dfs(start-1);
        sb.deleteCharAt(sb.length()-1);

        nodes = new boolean[N];
        nodes[start-1]=true;
        sb.append("\n");
        bfs(start-1);
        sb.deleteCharAt(sb.length()-1);

        System.out.println(sb);

    }

    static void dfs(int start){
        for(int i=0;i<N;i++){
            if(nodes[i] || !check[start][i]) continue;
            nodes[i]=true;
            sb.append((i+1)+" ");
            dfs(i);
        }
    }

    static void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        while(!queue.isEmpty()){
            int v = queue.remove();
            sb.append((v+1)+" ");
            for(int i=0;i<N;i++){
                if(nodes[i] || !check[v][i]) continue;
                nodes[i]=true;
                queue.add(i);
            }
        }
    }
}