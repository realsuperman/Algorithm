package BaekJoon_21736.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};
    static char[][] array;
    static int N,M,result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        array = new char[N][M];

        int startX=0,startY=0;
        for(int i=0;i<N;i++){
            str = br.readLine().split("");
            for(int j=0;j<M;j++){
                array[i][j]= str[j].charAt(0);
                if(array[i][j]=='I'){startX=i; startY=j;}
            }
        }
        bfs(startX,startY);
        System.out.println(result==0?"TT":result);
    }

    public static void bfs(int startX,int startY){
        Queue<V> queue = new LinkedList<>();
        boolean[][] check = new boolean[N][M];
        check[startX][startY]=true;
        queue.add(new V(startX,startY));
        while(!queue.isEmpty()){
            V v = queue.remove();
            if(array[v.x][v.y]=='P') result++;
            for(int i=0;i<4;i++){
                int x = v.x+dx[i];
                int y = v.y+dy[i];
                if(x>=N || x<0 || y>=M || y<0 || check[x][y] || array[x][y]=='X') continue;
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