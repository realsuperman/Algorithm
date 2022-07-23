package BaekJoon_7562;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int i=0;i<T;i++){
            int size = Integer.parseInt(br.readLine());
            String[] str = br.readLine().split(" ");
            int startX = Integer.parseInt(str[0]);
            int startY = Integer.parseInt(str[1]);
            str = br.readLine().split(" ");
            int endX = Integer.parseInt(str[0]);
            int endY = Integer.parseInt(str[1]);
            bfs(size,startX,startY,endX,endY);
        }
    }

    public static void bfs(int size,int startX,int startY,int endX,int endY){
        int[] dx = {-2,-2,-1,-1,1,1,2,2};
        int[] dy = {-1,1,-2,2,-2,2,-1,1};
        boolean[][] check = new boolean[size][size];
        Queue<V> queue = new LinkedList<>();
        queue.add(new V(startX,startY,0));
        check[startX][startY]=true;

        int cnt = 0;
        while(!queue.isEmpty()){
            V v= queue.remove();
            if(v.x==endX && v.y==endY) {System.out.println(v.cnt); return;}
            for(int i=0;i<8;i++){
                int x = v.x+dx[i];
                int y = v.y+dy[i];
                if(x<0 || x>=size || y<0 || y>=size) continue;
                if(check[x][y]) continue;
                check[x][y]=true;
                queue.add(new V(x,y,v.cnt+1));
            }
        }
    }
}

class V{
    int x;
    int y;
    int cnt;

    public V(int x,int y,int cnt){
        this.x=x;
        this.y=y;
        this.cnt=cnt;
    }
}