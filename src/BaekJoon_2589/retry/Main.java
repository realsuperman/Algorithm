package BaekJoon_2589.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
public class Main {
    static int N,M;
    static char[][] array;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static int result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        array = new char[N][M];
        for(int i=0;i<N;i++){
            str = br.readLine().split("");
            for(int j=0;j<str.length;j++){
                array[i][j] = str[j].charAt(0);
            }
        }

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(array[i][j]=='L'){
                    int v = bfs(i,j);
                    result = result>v?result:v;
                }
            }
        }
        System.out.println(result);
    }

    public static int bfs(int startX, int startY){
        int max = 0;
        Queue<V> queue = new LinkedList<>();
        boolean[][] check = new boolean[N][M];
        queue.add(new V(startX,startY,0));
        check[startX][startY]=true;
        while(!queue.isEmpty()){
            V v = queue.remove();
            if(max<v.time) max = v.time;
            for(int i=0;i<4;i++){
                int x = v.x+dx[i];
                int y = v.y+dy[i];
                if(x>=N || x<0 || y>=M || y<0 || array[x][y]=='W' || check[x][y]) continue;
                queue.add(new V(x,y,v.time+1));
                check[x][y]=true;
            }
        }
        return max;
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