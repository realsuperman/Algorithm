package BaekJoon_17086;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
    static int[] dx = {1,-1,0,0,-1,-1,1,1};
    static int[] dy = {0,0,1,-1,-1,1,-1,1};
    static int N,M;
    static int[][] array;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        array = new int[N][M];

        for(int i=0;i<N;i++){
            str = br.readLine().split(" ");
            for(int j=0;j<str.length;j++){
                array[i][j]= Integer.parseInt(str[j]);
            }
        }

        int result = Integer.MIN_VALUE;
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(array[i][j]==0){
                    int value = bfs(i,j);
                    result = result<value?value:result;
                }
            }
        }

        System.out.println(result);
    }

    public static int bfs(int startX,int startY){
        Queue<V> queue = new PriorityQueue<>();
        queue.add(new V(startX,startY,0));
        boolean[][] check = new boolean[N][M];
        check[startX][startY]=true;

        while(!queue.isEmpty()){
            V v = queue.remove();
            if(v.dist!=0 && array[v.x][v.y]==1){
                return v.dist;
            }

            for(int i=0;i<8;i++){
                int x = dx[i]+v.x;
                int y = dy[i]+v.y;
                if(x<0||x>=N||y<0||y>=M||check[x][y]) continue;
                check[x][y]=true;
                queue.add(new V(x,y,v.dist+1));
            }
        }
        return 0;
    }
}

class V implements Comparable<V>{
    int x;
    int y;
    int dist;

    public V(int x,int y,int dist){
        this.x=x;
        this.y=y;
        this.dist=dist;
    }

    @Override
    public int compareTo(V o) {
        return this.dist-o.dist;
    }
}