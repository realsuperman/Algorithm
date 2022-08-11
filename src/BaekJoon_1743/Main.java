package BaekJoon_1743;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    static int N,M;
    static int[][] array;
    static boolean[][] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        int K = Integer.parseInt(str[2]);

        array = new int[N][M];
        check = new boolean[N][M];

        while(K-->0){
            str = br.readLine().split(" ");
            array[Integer.parseInt(str[0])-1][Integer.parseInt(str[1])-1]=1;
        }

        int result = 0;
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(check[i][j] || array[i][j]==0) continue;
                check[i][j]=true;
                int v = bfs(i,j);
                result = result<v?v:result;
            }
        }
        System.out.println(result);
    }
    public static int bfs(int startX,int startY){
        int cnt = 1;
        Queue<V> queue = new LinkedList<>();
        queue.add(new V(startX,startY));
        while(!queue.isEmpty()){
            V v = queue.remove();
            for(int i=0;i<4;i++){
                int x = dx[i]+v.x;
                int y = dy[i]+v.y;
                if(x<0 || x>=N || y<0 || y>=M || check[x][y] || array[x][y]==0) continue;
                check[x][y]=true;
                cnt++;
                queue.add(new V(x,y));
            }
        }
        return cnt;
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