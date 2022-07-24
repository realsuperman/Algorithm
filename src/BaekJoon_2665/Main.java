package BaekJoon_2665;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] array = new int[N][N];
        for(int i=0;i<N;i++){
            String[] str = br.readLine().split("");
            for(int j=0;j<str.length;j++) array[i][j]= Integer.parseInt(str[j]);
        }

        boolean[][][] check = new boolean[N*N][N][N];
        Queue<V> queue = new PriorityQueue<>();
        queue.add(new V(0,0,0));
        for(int i=0;i<N*N;i++) check[i][0][0]=true;

        while(!queue.isEmpty()){
            V v = queue.remove();
            if(v.x==N-1 && v.y==N-1){
                System.out.println(v.breakCnt);
                return;
            }
            for(int i=0;i<4;i++){
                int x = v.x+dx[i];
                int y = v.y+dy[i];
                if(x<0 || x>=N || y<0 || y>=N) continue;

                if(array[x][y]==0 && !check[v.breakCnt+1][x][y]){ // 검은방
                    check[v.breakCnt+1][x][y]=true;
                    queue.add(new V(x,y,v.breakCnt+1));
                }else if(array[x][y]==1 && !check[v.breakCnt][x][y]){ // 흰방
                    check[v.breakCnt][x][y]=true;
                    queue.add(new V(x,y,v.breakCnt));
                }
            }

        }
    }
}

class V implements Comparable<V>{
    int x;
    int y;
    int breakCnt;
    public V(int x,int y,int breakCnt){
        this.x=x;
        this.y=y;
        this.breakCnt=breakCnt;
    }

    @Override
    public int compareTo(V o) {
        return this.breakCnt-o.breakCnt;
    }
}