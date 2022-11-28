package BaekJoon_1926.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int N,M;
    static boolean[][] array,check;
    static int cnt,max;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        array = new boolean[N][M];
        check = new boolean[N][M];

        for(int i=0;i<N;i++){
            str = br.readLine().split(" ");
            for(int j=0;j<str.length;j++){
                if(str[j].equals("1")){
                    array[i][j]=true;
                }else{
                    array[i][j]=false;
                }
            }
        }

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(array[i][j] && !check[i][j]){
                    cnt++;
                    check[i][j]=true;
                    int v = bfs(i,j);
                    max = max<v?v:max;
                }
            }
        }

        System.out.print(cnt+"\n"+max);
    }

    static int bfs(int startX,int startY){
        Queue<V> queue = new LinkedList<>();
        int range = 1;
        queue.add(new V(startX,startY));
        while(!queue.isEmpty()){
            V v = queue.remove();
            for(int i=0;i<4;i++){
                int x = dx[i]+v.x;
                int y = dy[i]+v.y;
                if(x>=N || x<0 || y>=M || y<0 || !array[x][y] || check[x][y]) continue;
                check[x][y]=true;
                range++;
                queue.add(new V(x,y));
            }
        }

        return range;
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