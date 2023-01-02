package BaekJoon_2146.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int N;
    static int[][] array;
    static boolean[][] check;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static int MIN = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        array = new int[N][N];
        check = new boolean[N][N];
        for(int i=0;i<N;i++){
            String[] str = br.readLine().split(" ");
            for(int j=0;j<str.length;j++) array[i][j]= Integer.parseInt(str[j]);
        }
        solution();
        System.out.println(MIN);
    }

    public static void solution(){
        int depth = 1;
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(array[i][j]==1 && !check[i][j]){
                    array[i][j]=depth;
                    check[i][j]=true;
                    bfs(i,j,depth++);
                }
            }
        }

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(array[i][j]==0){
                    calculate(i,j);
                }
            }
        }
    }

    public static void bfs(int startX,int startY, int depth){
        Queue<V> queue = new LinkedList<>();
        queue.add(new V(startX,startY));
        while(!queue.isEmpty()){
            V v = queue.remove();
            for(int i=0;i<4;i++){
                int x = dx[i]+v.x;
                int y = dy[i]+v.y;
                if(x>=N || x<0 || y>=N || y<0 || check[x][y] || array[x][y]!=1) continue;
                check[x][y]=true;
                array[x][y]=depth;
                queue.add(new V(x,y));
            }
        }
    }

    public static void calculate(int startX, int startY){
        int startLocation = 0;
        for(int i=0;i<4;i++){
            int x = dx[i]+startX;
            int y = dy[i]+startY;
            if(x>=N || x<0 || y>=N || y<0 || array[x][y]<1) continue;
            startLocation = array[x][y];
            break;
        }
        if(startLocation<1) return;

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if( (i==startX && j==startY) || array[i][j]==0 || array[i][j]==startLocation) continue;
                int value = Math.abs(startX-i) + Math.abs(startY-j);
                MIN = MIN>value?value:MIN;
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