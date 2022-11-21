package BaekJoon_1012;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main2 {
    static int N,M;
    static boolean[][] array;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        while(T-->0){
            String[] str = br.readLine().split(" ");
            N = Integer.parseInt(str[0]);
            M = Integer.parseInt(str[1]);
            array = new boolean[N][M];
            int K = Integer.parseInt(str[2]);
            while(K-->0){
                str = br.readLine().split(" ");
                array[Integer.parseInt(str[0])][Integer.parseInt(str[1])]=true;
            }
            sb.append(solution()+"\n");
        }
        System.out.println(sb);
    }

    public static int solution(){
        int result = 0;
        boolean[][] check = new boolean[N][M];
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(check[i][j] || !array[i][j]) continue;
                check[i][j]=true;
                bfs(i,j,check);
                result++;
            }
        }
        return result;
    }

    public static void bfs(int startX,int startY,boolean[][] check){
        Queue<V> queue = new LinkedList<>();
        queue.add(new V(startX,startY));
        while(!queue.isEmpty()){
            V v = queue.remove();
            for(int i=0;i<4;i++){
                int x = v.x+dx[i];
                int y = v.y+dy[i];
                if(x>=N || x<0 || y>=M || y<0 || check[x][y] || !array[x][y]) continue;
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