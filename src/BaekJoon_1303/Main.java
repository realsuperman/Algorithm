package BaekJoon_1303;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int N,M;
    static char[][] array;
    static boolean[][] check;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[1]);
        M = Integer.parseInt(str[0]);
        array = new char[N][M];
        check = new boolean[N][M];
        for(int i=0;i<N;i++){
            str = br.readLine().split("");
            for(int j=0;j<str.length;j++) array[i][j]=str[j].charAt(0);
        }

        int[] result = new int[2];
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(!check[i][j]){
                    int v = bfs(i,j,array[i][j]);
                    if(array[i][j]=='W') result[0]+=v;
                    else result[1]+=v;
                }
            }
        }
        System.out.println(result[0]+" "+result[1]);
    }

    public static int bfs(int startX,int startY,char target){
        Queue<V> queue = new LinkedList<>();
        queue.add(new V(startX,startY));
        check[startX][startY]=true;
        int result = 1;
        while(!queue.isEmpty()){
            V v = queue.remove();
            for(int i=0;i<4;i++){
                int x = dx[i]+v.x;
                int y = dy[i]+v.y;
                if(x<0 || x>=N || y<0 || y>=M || check[x][y] || array[x][y]!=target) continue;
                result++;
                check[x][y]=true;
                queue.add(new V(x,y));
            }
        }
        return result*result;
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