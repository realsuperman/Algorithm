package BaekJoon_10026.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int N;
    static char[][] array;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static boolean[][] check;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        array = new char[N][N];
        check = new boolean[N][N];

        for(int i=0;i<N;i++){
            String[] str = br.readLine().split("");
            for(int j=0;j<N;j++){
                array[i][j]=str[j].charAt(0);
            }
        }

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(!check[i][j]){result++; check[i][j]=true; bfs(i,j,array[i][j]);}
            }
        }
        System.out.print(result+" ");

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(array[i][j]=='G') array[i][j]='R';
            }
        }

        check = new boolean[N][N];
        result = 0;
        for(int i=0;i<N;i++) {
            for (int j = 0; j < N; j++) {
                if (!check[i][j]) {
                    result++;
                    check[i][j] = true;
                    bfs(i, j, array[i][j]);
                }
            }
        }
        System.out.println(result);
    }

    public static void bfs(int startX,int startY,char ch){
        Queue<V> queue = new LinkedList<>();
        queue.add(new V(startX,startY));
        while(!queue.isEmpty()){
            V v = queue.remove();
            for(int i=0;i<4;i++){
                int x = dx[i]+v.x;
                int y = dy[i]+v.y;
                if(x>=N || x<0 || y>=N || y<0 || array[x][y]!=ch || check[x][y]) continue;
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