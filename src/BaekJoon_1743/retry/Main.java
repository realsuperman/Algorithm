package BaekJoon_1743.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int N,M,result;
    static boolean[][] array,check;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        array = new boolean[N][M];
        check = new boolean[N][M];
        int K = Integer.parseInt(str[2]);
        while(K-->0){
            str = br.readLine().split(" ");
            array[Integer.parseInt(str[0])-1][Integer.parseInt(str[1])-1]=true;
        }

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(array[i][j] && !check[i][j]){
                    check[i][j]=true;
                    int value = bfs(i,j);
                    result = result<value?value:result;
                }
            }
        }
        System.out.println(result);
    }
    public static int bfs(int startX,int startY){
        Queue<V> queue = new LinkedList<>();
        queue.add(new V(startX,startY));
        int cnt = 1;

        while(!queue.isEmpty()){
            V v = queue.remove();
            for(int i=0;i<4;i++){
                int x = dx[i]+v.x;
                int y = dy[i]+v.y;
                if(x>=N || x<0 || y>=M || y<0 || check[x][y] || !array[x][y]) continue;
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