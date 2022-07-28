package BaekJoon_4963;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int N,M;
    static int[][] array;
    static boolean[][] check;
    static int[] dx = {1,-1,0,0,-1,-1,1,1};
    static int[] dy = {0,0,1,-1,-1,1,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            String[] str = br.readLine().split(" ");
            N = Integer.parseInt(str[1]);
            M = Integer.parseInt(str[0]);
            if(N==0 && M==0) break;

            int result = 0;
            array = new int[N][M];
            check = new boolean[N][M];
            for(int i=0;i<N;i++){
                str = br.readLine().split(" ");
                for(int j=0;j<M;j++) array[i][j]= Integer.parseInt(str[j]);
            }

            for(int i=0;i<N;i++){
                for(int j=0;j<M;j++){
                    if(check[i][j] || array[i][j]==0) continue;
                    result++;
                    bfs(i,j);
                }
            }
            System.out.println(result);
        }
    }
    public static void bfs(int startX,int startY){
        Queue<V> queue = new LinkedList<>();
        check[startX][startY]=true;
        queue.add(new V(startX,startY));

        while(!queue.isEmpty()){
            V v = queue.remove();
            for(int i=0;i<8;i++){
                int x = v.x+dx[i];
                int y = v.y+dy[i];
                if(x<0||x>=N||y<0||y>=M||check[x][y]||array[x][y]==0) continue;
                check[x][y]=true;
                queue.add(new V(x,y));
            }
        }
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