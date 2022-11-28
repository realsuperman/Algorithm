package BaekJoon_2468.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int MAX = Integer.MIN_VALUE;
    static int MIN = Integer.MAX_VALUE;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static int N,result=1;
    static int[][] array;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        array = new int[N][N];
        for(int i=0;i<N;i++){
            String[] str = br.readLine().split(" ");
            for(int j=0;j<str.length;j++){
                array[i][j]= Integer.parseInt(str[j]);
                if(MAX<array[i][j]) MAX = array[i][j];
                if(MIN>array[i][j]) MIN = array[i][j];
            }
        }

        for(int k=1;k<=100;k++){
            int v = solution(k);
            result = result<v?v:result;
        }
        System.out.println(result);
    }

    public static int solution(int k){
        int cnt = 0;

        int[][] temp = new int[N][N];
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++) {
                temp[i][j] = array[i][j];
                if(k>=temp[i][j]) temp[i][j]=0;
            }
        }

        boolean[][] check = new boolean[N][N];
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(!check[i][j] && temp[i][j]!=0){
                    cnt++;
                    check[i][j]=true;
                    bfs(temp,check,i,j);
                }
            }
        }

        return cnt;
    }

    public static void bfs(int[][] temp,boolean[][] check,int startX,int startY){
        Queue<V> queue = new LinkedList<>();
        queue.add(new V(startX,startY));
        while(!queue.isEmpty()){
            V v = queue.remove();
            for(int i=0;i<4;i++){
                int x = dx[i]+v.x;
                int y = dy[i]+v.y;
                if(x<0 || x>=N || y<0 || y>=N || check[x][y] || temp[x][y]==0) continue;
                queue.add(new V(x,y));
                check[x][y]=true;
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