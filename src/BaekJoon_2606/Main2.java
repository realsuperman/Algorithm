package BaekJoon_2606;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main2 {
    static boolean[][] check;
    static boolean[] nodes;
    static int result,N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        check = new boolean[N][N];
        nodes = new boolean[N];
        nodes[0] = true;

        for(int i=0;i<M;i++){
            String[] str = br.readLine().split(" ");
            check[Integer.parseInt(str[0])-1][Integer.parseInt(str[1])-1] = true;
            check[Integer.parseInt(str[1])-1][Integer.parseInt(str[0])-1] = true;
        }

        solution(0);
        System.out.println(result);
    }

    static void solution(int start){
        for(int i=0;i<N;i++){
            if( !check[start][i] || nodes[i]) continue;
            nodes[i]=true;
            result++;
            solution(i);
        }
    }
}
