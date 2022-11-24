package BaekJoon_2178.retry;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int N,M;
    static int[][] array;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};

    public static void main(String[] args)throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        array = new int[N][M];
        for(int i=0;i<N;i++){
            str = br.readLine().split("");
            for(int j=0;j<M;j++) array[i][j]= Integer.parseInt(str[j]);
        }

        bfs();
    }

    public static void bfs(){
        Queue<V> queue = new LinkedList<>();
        boolean[][] check = new boolean[N][M];
        queue.add(new V(0,0,1));
        check[0][0]=true;

        while(!queue.isEmpty()){
            V v = queue.remove();
            if(v.x == N-1 && v.y==M-1){System.out.println(v.time); return;}
            for(int i=0;i<4;i++){
                int x = v.x+dx[i];
                int y = v.y+dy[i];
                if(x>=N || x<0 || y>=M || y<0 || array[x][y]==0 || check[x][y]) continue;
                check[x][y]=true;
                queue.add(new V(x,y,v.time+1));
            }
        }
    }
}

class V{
    int x;
    int y;
    int time;

    public V(int x,int y,int time){
        this.x=x;
        this.y=y;
        this.time=time;
    }
}