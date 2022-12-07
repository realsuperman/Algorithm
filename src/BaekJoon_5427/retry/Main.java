package BaekJoon_5427.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N,M;
    static char[][] array;
    static boolean[][] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while(T-->0){
            String[] str = br.readLine().split(" ");
            N = Integer.parseInt(str[1]);
            M = Integer.parseInt(str[0]);
            array = new char[N][M];
            check = new boolean[N][M];
            for(int i=0;i<N;i++){
                str = br.readLine().split("");
                for(int j=0;j<M;j++) array[i][j]=str[j].charAt(0);
            }

            int v = bfs();
            System.out.println(v==0?"IMPOSSIBLE":v);
        }
    }

    public static int bfs(){
        int result = 0;

        return result;
    }
}
