package BaekJoon_14923.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int[][] array;
    static boolean[][][] check;
    static int startX,startY,endX,endY,N,M;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        array = new int[N][M];
        check = new boolean[2][N][M]; // 0은 안부숨, 1은 부숨

        str = br.readLine().split(" ");
        startX = Integer.parseInt(str[0])-1;
        startY = Integer.parseInt(str[1])-1;
        str = br.readLine().split(" ");
        endX = Integer.parseInt(str[0])-1;
        endY = Integer.parseInt(str[1])-1;
        for(int i=0;i<N;i++){
            str = br.readLine().split(" ");
            for(int j=0;j<M;j++){
                array[i][j]= Integer.parseInt(str[j]);
            }
        }

        bfs();
    }

    public static void bfs(){
        Queue<V> queue = new LinkedList<>();
        queue.add(new V(startX,startY,0,false));
        check[0][startX][startY]=true; check[1][startX][startY]=true;
        while(!queue.isEmpty()){
            V v = queue.remove();
            if(v.x==endX && v.y==endY){
                System.out.println(v.time);
                return;
            }

            for(int i=0;i<4;i++){
                int x = dx[i]+v.x;
                int y = dy[i]+v.y;
                int location = v.sw?1:0;
                if(x>=N || x<0 || y>=M || y<0 || check[location][x][y]) continue;

                if(array[x][y]==1){ // 벽
                    if(v.sw) continue;
                    queue.add(new V(x,y,v.time+1,true));
                    check[0][x][y]=true; check[1][x][y]=true;
                }else{ // 땅
                    queue.add(new V(x,y,v.time+1,v.sw));
                    check[location][x][y]=true;
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
    boolean sw;
    public V(int x,int y,int time,boolean sw){
        this.x=x;
        this.y=y;
        this.time=time;
        this.sw=sw;
    }
}