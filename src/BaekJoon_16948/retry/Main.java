package BaekJoon_16948.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
public class Main {
    static int[] dx = {-2,-2,0,0,2,2};
    static int[] dy = {-1,1,-2,2,-1,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] str = br.readLine().split(" ");
        int startX = Integer.parseInt(str[0]);
        int startY = Integer.parseInt(str[1]);
        int endX = Integer.parseInt(str[2]);
        int endY = Integer.parseInt(str[3]);
        boolean[][] check = new boolean[N][N];
        Queue<V> queue = new LinkedList<>();
        queue.add(new V(startX,startY,0));
        check[startX][startY]=true;
        while(!queue.isEmpty()){
            V v = queue.remove();
            if(v.x==endX && v.y==endY) {System.out.println(v.time); return;}
            for(int i=0;i<6;i++){
                int x = v.x+dx[i];
                int y = v.y+dy[i];
                if(x>=N || x<0 || y>=N || y<0 || check[x][y]) continue;
                check[x][y] = true;
                queue.add(new V(x,y,v.time+1));
            }
        }
        System.out.println(-1);
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