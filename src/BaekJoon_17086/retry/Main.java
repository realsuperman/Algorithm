package BaekJoon_17086.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int N,M;
    static int[][] array;
    static int[] dx = {0,0,1,-1,-1,-1,1,1};
    static int[] dy = {1,-1,0,0,-1,1,-1,1};
    static int MAX = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        array = new int[N][M];
        for(int i=0;i<N;i++){
            str = br.readLine().split(" ");
            for(int j=0;j<M;j++){
                array[i][j]= Integer.parseInt(str[j]);
            }
        }

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(array[i][j]==0){
                    bfs(i,j);
                }
            }
        }
        System.out.println(MAX);
    }

    public static void bfs(int startX,int startY){
        Queue<V> queue = new LinkedList<>();
        queue.add(new V(startX,startY,0));
        boolean[][] check = new boolean[N][M];
        check[startX][startY]=true;

        while(!queue.isEmpty()){
            V v = queue.remove();
            if(array[v.x][v.y]==1){ MAX = MAX<v.v?v.v:MAX; return;}
            for(int i=0;i<8;i++){
                int x = v.x+dx[i];
                int y = v.y+dy[i];
                if(x>=N || x<0 || y>=M || y<0 || check[x][y]) continue;
                check[x][y]=true;
                queue.add(new V(x,y,v.v+1));
            }
        }
    }

}

class V{
    int x;
    int y;
    int v;
    public V(int x,int y,int v){
        this.x=x;
        this.y=y;
        this.v=v;
    }
}