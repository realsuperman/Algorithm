package BaekJoon_16724;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N,M,cnt;
    static char[][] array;
    static int[][] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        array = new char[N][M];
        check = new int[N][M];

        for(int i=0;i<N;i++){
            str = br.readLine().split("");
            for(int j=0;j<M;j++){
                array[i][j] = str[j].charAt(0);
            }
        }
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(check[i][j]==0) dfs(i,j);
            }
        }
        System.out.println(cnt);
    }
    public static void dfs(int startX,int startY){
        check[startX][startY]=1;
        int x,y;
        if(array[startX][startY]=='U'){
            x = startX-1; y=startY;
        }else if(array[startX][startY]=='D'){
            x = startX+1; y=startY;
        }else if(array[startX][startY]=='L'){
            x=startX; y=startY-1;
        }else{
            x=startX; y=startY+1;
        }
        if(check[x][y]==1) cnt++;
        if(check[x][y]==0) dfs(x,y);
        check[startX][startY]=2;
    }
}