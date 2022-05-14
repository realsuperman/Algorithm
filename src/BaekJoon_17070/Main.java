package BaekJoon_17070;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static int value;
    public static int N;
    public static int[][] array;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        array = new int[N+1][N+1];

        for(int i=1;i<=N;i++){
            String[] str = br.readLine().split(" ");
            for(int j=0;j<str.length;j++){
                array[i][j+1] = Integer.parseInt(str[j]);
            }
        }
        dfs(1,2,0);
        System.out.println(value);
    }

    public static void dfs(int x,int y,int rotate){
        if(x==N && y==N){
            value++;
            return;
        }
        if(rotate==0){ // 가로
            if(y+1<=N && array[x][y+1]==0){
                dfs(x,y+1,0);
            }
            if(x+1<=N && y+1<=N && array[x+1][y+1]==0 && array[x][y+1]==0 && array[x+1][y]==0){
                dfs(x+1,y+1,2);
            }
        }else if(rotate==1){ // 세로
            if(x+1<=N && array[x+1][y]==0){
                dfs(x+1,y,1);
            }
            if(x+1<=N && y+1<=N && array[x+1][y+1]==0 && array[x][y+1]==0 && array[x+1][y]==0){
                dfs(x+1,y+1,2);
            }
        }else{ // 대각선
            if(y+1<=N && array[x][y+1]==0){
                dfs(x,y+1,0);
            }
            if(x+1<=N && array[x+1][y]==0){
                dfs(x+1,y,1);
            }
            if(x+1<=N && y+1<=N && array[x+1][y+1]==0 && array[x][y+1]==0 && array[x+1][y]==0){
                dfs(x+1,y+1,2);
            }
        }
    }
}