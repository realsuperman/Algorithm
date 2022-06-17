package BaekJoon_1987;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static int N,M;
    public static Character[][] array;
    public static boolean[][] check;
    public static int MAX = Integer.MIN_VALUE;
    public static int[] dx = {1,-1,0,0};
    public static int[] dy = {0,0,1,-1};
    public static boolean[] dup = new boolean[26];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        array = new Character[N][M];
        check = new boolean[N][M];

        for(int i=0;i<N;i++){
            str = br.readLine().split("");
            for(int j=0;j<str.length;j++) array[i][j]=str[j].charAt(0);
        }

        check[0][0] = true;
        dup[array[0][0]-65] = true;
        dfs(0,0,1);
        System.out.println(MAX);
    }

    public static void dfs(int x,int y,int v){
        if(v>MAX) MAX = v;
        for(int i=0;i<4;i++){
            int v1 = x+dx[i];
            int v2 = y+dy[i];
            if(v1<0 || v1>=N ||v2<0 || v2>=M) continue;
            if(!check[v1][v2] && !dup[array[v1][v2]-65]){
                check[v1][v2] = true;
                dup[array[v1][v2]-65]=true;
                dfs(v1,v2,v+1);
                check[v1][v2] = false;
                dup[array[v1][v2]-65]=false;
            }
        }

    }
}