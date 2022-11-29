package BaekJoon_7562.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
public class Main {
    static int[] dx = {-2,-2,-1,-1,1,1,2,2};
    static int[] dy = {-1,1,-2,2,-2,2,-1,1};
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while(T-->0){
            N = Integer.parseInt(br.readLine());
            String[] str = br.readLine().split(" ");
            int startX = Integer.parseInt(str[0]);
            int startY = Integer.parseInt(str[1]);
            str = br.readLine().split(" ");
            int endX = Integer.parseInt(str[0]);
            int endY = Integer.parseInt(str[1]);
            System.out.println(bfs(startX,startY,endX,endY));
        }

    }

    public static int bfs(int startX,int startY,int endX,int endY){
        int cnt = 0;
        Queue<V> queue = new LinkedList<>();
        boolean[][] check = new boolean[N][N];
        check[startX][startY]=true;
        queue.add(new V(startX,startY,0));
        while(!queue.isEmpty()){
            V v = queue.remove();
            if(v.x == endX && v.y == endY){ cnt = v.time; break;}
            for(int i=0;i<8;i++){
                int x = dx[i]+v.x;
                int y = dy[i]+v.y;
                if(x>=N || x<0 || y>=N || y<0 || check[x][y]) continue;
                check[x][y]=true;
                queue.add(new V(x,y,v.time+1));
                cnt++;
            }
        }

        return cnt;
    }
}

class V{
    int x;
    int y;
    int time;
    public V(int x,int y,int time){
        this.x=x;
        this.y=y;
        this.time=time;
    }
}