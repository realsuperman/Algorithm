package BaekJoon_1926;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int N,M;
    static int[][] array;
    static boolean[][] check;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        array = new int[N][M];
        check = new boolean[N][M];
        for(int i=0;i<N;i++){
            str = br.readLine().split(" ");
            for(int j=0;j<str.length;j++) array[i][j]= Integer.parseInt(str[j]);
        }

        int cnt = 0;
        int max = Integer.MIN_VALUE;
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(array[i][j]==1 && !check[i][j]){
                    check[i][j]=true;
                    cnt++;
                    int v = bfs(i,j);
                    max = max<v?v:max;
                }
            }
        }
        System.out.println(cnt);
        System.out.println(max==Integer.MIN_VALUE?0:max);
    }

    private static int bfs(int startX,int startY) {
        Queue<V> queue = new LinkedList<>();
        queue.add(new V(startX,startY));
        int cnt = 1;
        while(!queue.isEmpty()){
            V v = queue.remove();
            for(int i=0;i<4;i++){
                int x = v.x+dx[i];
                int y = v.y+dy[i];
                if(x<0 || x>=N || y<0 || y>=M || check[x][y] || array[x][y]==0) continue;
                cnt++;
                check[x][y]=true;
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