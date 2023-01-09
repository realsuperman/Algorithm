package BaekJoon_2638.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int N,M;
    static int[][] array;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        array = new int[N][M];

        for(int i=0;i<N;i++){
            str = br.readLine().split(" ");
            for(int j=0;j<M;j++){
                array[i][j] = Integer.parseInt(str[j]);
            }
        }
        bfs();
    }

    public static void bfs(){
        int time = 0;
        boolean[][] check;
        Queue<V> queue;
        int[][] temp;

        while(!isEnd()){
            time++;

            check = new boolean[N][M];
            check[0][0]=true;
            queue = new LinkedList<>();
            queue.add(new V(0,0));
            temp = new int[N][M];

            while(!queue.isEmpty()){
                V v = queue.remove();
                for(int i=0;i<4;i++){
                    int x = dx[i]+v.x;
                    int y = dy[i]+v.y;
                    if(x<0 || x>=N || y<0 || y>=M || check[x][y]) continue;
                    if(array[x][y]==0){
                        queue.add(new V(x,y));
                        check[x][y]=true;
                    }else{
                        temp[x][y]++;
                    }
                }
            }

            for(int i=0;i<N;i++){
                for(int j=0;j<M;j++){
                    if(temp[i][j]>=2) array[i][j]=0;
                }
            }

        }

        System.out.println(time);
    }

    public static boolean isEnd(){
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(array[i][j]==1) return false;
            }
        }
        return true;
    }

}

class V{
    int x;
    int y;
    public V(int x,int y){
        this.x=x;
        this.y=y;
    }
}