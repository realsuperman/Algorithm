package BaekJoon_11724.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static boolean[] nodes;
    static boolean[][] check;
    static int N,result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        nodes = new boolean[N];
        check = new boolean[N][N];

        int M = Integer.parseInt(str[1]);
        if(M==0) {System.out.println(N); return;}

        while(M-->0){
            str = br.readLine().split(" ");
            check[Integer.parseInt(str[0])-1][Integer.parseInt(str[1])-1] = true;
            check[Integer.parseInt(str[1])-1][Integer.parseInt(str[0])-1] = true;
        }

        for(int i=0;i<N;i++){
            if(!nodes[i] ){ nodes[i]=true; result++; solution(i);}
        }

        System.out.println(result);
    }

    static void solution(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);

        while(!queue.isEmpty()){
            int v = queue.remove();
            for(int i=0;i<N;i++){
                if(!nodes[i] && check[v][i]){ queue.add(i); nodes[i]=true;}
            }
        }
    }

}
