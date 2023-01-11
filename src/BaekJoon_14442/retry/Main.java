package BaekJoon_14442.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N,M,K;
    static int[][] array;
    static boolean[][][] check;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        K = Integer.parseInt(str[2]);
        array = new int[N][M];
        check = new boolean[N][M][K+1];
        for(int i=0;i<N;i++){
            str = br.readLine().split("");
            for(int j=0;j<M;j++) array[i][j]= Integer.parseInt(str[j]);
        }
        bfs();
    }

    public static void bfs(){
        Queue<V> queue = new LinkedList<>();
        queue.add(new V(0,0,0,0));
        for(int index=0;index<K;index++) check[0][0][index]=true;

        while(!queue.isEmpty()){
            V v = queue.remove();
            if(v.x==N-1 && v.y==M-1) {System.out.println(v.time+1); return;}
            for(int i=0;i<4;i++){
                int x = dx[i]+v.x;
                int y = dy[i]+v.y;
                if(x<0||x>=N||y<0||y>=M||check[x][y][v.breckCnt]) continue;
                if(array[x][y]==0){ // 이동가능
                    check[x][y][v.breckCnt]=true;
                    queue.add(new V(x,y,v.time+1,v.breckCnt));
                }else{ // 벽
                    if(v.breckCnt>=K || check[x][y][v.breckCnt+1]) continue;
                    check[x][y][v.breckCnt+1] = true;
                    queue.add(new V(x,y,v.time+1,v.breckCnt+1));
                }
            }
        }
        System.out.println(-1);
    }
}

class V{
    int x;
    int y;
    int time;
    int breckCnt;
    public V(int x,int y,int time, int breckCnt){
        this.x=x;
        this.y=y;
        this.time=time;
        this.breckCnt=breckCnt;
    }
}