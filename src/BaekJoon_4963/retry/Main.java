package BaekJoon_4963.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int[] dx = {1,-1,0,0,-1,-1,1,1};
    static int[] dy = {0,0,1,-1,-1,1,-1,1};
    static StringBuilder sb = new StringBuilder();
    static int[][] array;
    static boolean[][] check;
    static int w,h;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(;;){
            String[] str = br.readLine().split(" ");
            w = Integer.parseInt(str[1]);
            h = Integer.parseInt(str[0]);
            if(w==0 && h==0) break;

            array = new int[w][h];
            check = new boolean[w][h];
            for(int i=0;i<w;i++){
                str = br.readLine().split(" ");
                for(int j=0;j<str.length;j++) array[i][j] = Integer.parseInt(str[j]);
            }

            solution();

        }
        System.out.println(sb);
    }

    public static void solution(){
        int cnt = 0;
        for(int i=0;i<w;i++){
            for(int j=0;j<h;j++){
                if(check[i][j] || array[i][j]==0) continue;
                check[i][j]=true;
                bfs(i,j);
                cnt++;
            }
        }
        sb.append(cnt+"\n");
    }

    public static void bfs(int startX,int startY){
        Queue<V> queue = new LinkedList<>();
        queue.add(new V(startX,startY));
        while(!queue.isEmpty()){
            V v = queue.remove();
            for(int i=0;i<8;i++){
                int x = v.x+dx[i];
                int y = v.y+dy[i];
                if(x<0 || x>=w || y<0 || y>=h || check[x][y] || array[x][y]==0) continue;
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