package BaekJoon_2665.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
    static boolean[][][] check;
    static boolean[][] array;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        array = new boolean[N][N];
        check = new boolean[2498][N][N];
        for(int i=0;i<N;i++){
            String[] str = br.readLine().split("");
            for(int j=0;j<N;j++) array[i][j]=str[j].equals("1")?true:false;
        }

        System.out.println(bfs());
    }

    public static int bfs() {
        Queue<V> queue = new PriorityQueue<>();
        queue.add(new V(0,0,0,0));
        check[0][0][0]=true;

        int result = 0;
        while(!queue.isEmpty()){
            V v = queue.remove();
            if(v.x == N-1 && v.y == N-1) {result = v.cnt; break;}
            for(int i=0;i<4;i++){
                int x = dx[i]+v.x;
                int y = dy[i]+v.y;
                if(x<0 || x>=N || y<0 || y>=N) continue;

                if(!array[x][y]){ // 벽인경우
                    if(check[v.cnt+1][x][y]) continue;
                    check[v.cnt+1][x][y]=true;
                    queue.add(new V(x,y,v.cnt+1,v.time+1));
                }
                else{ // 육지인경우
                    if(check[v.cnt][x][y]) continue;
                    check[v.cnt][x][y]=true;
                    queue.add(new V(x,y,v.cnt,v.time+1));
                }
            }
        }

        return result;
    }
}

class V implements Comparable<V>{
    int x;
    int y;
    int cnt; // 벽을 부순 갯수
    int time; // BFS 횟수

    public V(int x,int y,int cnt,int time){
        this.x=x;
        this.y=y;
        this.cnt=cnt;
        this.time=time;
    }

    @Override
    public int compareTo(V o) {
        if(this.cnt==o.cnt) return this.time-o.time;
        return this.cnt-o.cnt;
    }
}