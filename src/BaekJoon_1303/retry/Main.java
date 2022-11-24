package BaekJoon_1303.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static int N,M;
    static char[][] array;
    static boolean[][] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] result = new int[2];
        char[] switching = {'W','B'};

        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[1]);
        M = Integer.parseInt(str[0]);
        array = new char[N][M];
        check = new boolean[N][M];
        for(int i=0;i<N;i++){
            str = br.readLine().split("");
            for(int j=0;j<str.length;j++) array[i][j]=str[j].charAt(0);
        }

        for(int k=0;k<2;k++){
            for(int i=0;i<N;i++){
                for(int j=0;j<M;j++){
                    if(switching[k]==array[i][j] && !check[i][j]){ check[i][j]=true; result[k]+=bfs(i,j,switching[k]);}
                }
            }
        }

        System.out.println(result[0]+" "+result[1]);
    }

    public static int bfs(int startX,int startY,char target){
        int cnt = 1;
        Queue<V> queue = new LinkedList<>();
        queue.add(new V(startX,startY));

        while(!queue.isEmpty()){
            V v = queue.remove();
            for(int i=0;i<4;i++){
                int x = dx[i]+v.x;
                int y = dy[i]+v.y;
                if(x>=N || x<0 || y>=M || y<0 || array[x][y]!=target || check[x][y]) continue;
                check[x][y]=true;
                cnt++;
                queue.add(new V(x,y));
            }
        }

        return cnt*cnt;
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