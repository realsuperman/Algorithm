package BaekJoon_11123;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while(T-->0) {
            String[] str = br.readLine().split(" ");
            int N = Integer.parseInt(str[0]);
            int M = Integer.parseInt(str[1]);
            char[][] array = new char[N][M];
            for(int i=0;i<N;i++){
                str = br.readLine().split("");
                for(int j=0;j<str.length;j++) array[i][j]=str[j].charAt(0);
            }
            boolean[][] check = new boolean[N][M];
            int sum = 0;
            for(int i=0;i<N;i++){
                for(int j=0;j<M;j++){
                    if(array[i][j]=='#' && !check[i][j]){
                        check[i][j]=true;
                        sum+=solution(N,M,i,j,array,check);
                    }
                }
            }
            sb.append(sum+"\n");
        }
        System.out.println(sb);
    }

    public static int solution(int N,int M,int startX,int startY,char[][] array,boolean[][] check){
        Queue<V> queue = new LinkedList<>();
        queue.add(new V(startX,startY));
        int cnt = 1;
        while(!queue.isEmpty()){
            V v = queue.remove();
            for(int i=0;i<4;i++){
                int x = dx[i]+v.x;
                int y = dy[i]+v.y;
                if(x>=N || x<0 || y>=M || y<0 || check[x][y] || array[x][y]=='.') continue;
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