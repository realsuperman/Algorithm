package BaekJoon_2151.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int N;
    static char[][] array;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int startX,startY,endX,endY;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        array = new char[N][N];
        boolean sw = true;
        for(int i=0;i<N;i++){
            String[] str = br.readLine().split("");
            for(int j=0;j<N;j++){
                array[i][j]=str[j].charAt(0);
                if(array[i][j]=='#'){
                    if(sw){
                        startX = i; startY = j;
                        sw=false;
                    }else{
                        endX = i; endY = j;
                    }
                }
            }
        }
        System.out.println(bfs());
    }

    public static int bfs(){
        Queue<V> queue = new LinkedList<>();
        int[][][] check = new int[N][N][4];
        for(int i=0;i<N;i++) for(int j=0;j<N;j++) for(int k=0;k<4;k++) check[i][j][k]=Integer.MAX_VALUE;
        queue.add(new V(startX,startY,0,-1));
        for(int i=0;i<4;i++) check[startX][startY][i]=Integer.MIN_VALUE;

        int[] result = {Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE};
        while(!queue.isEmpty()){
            V v = queue.remove();
            if(v.x == endX && v.y == endY){
                result[v.direction] = v.time;
                continue;
            }
            if(v.direction==-1){
                for(int i=0;i<4;i++){
                    int x = dx[i]+v.x;
                    int y = dy[i]+v.y;
                    if(x<0 || x>=N || y<0 || y>=N || check[x][y][i]<=v.time || array[x][y]=='*') continue;
                    check[x][y][i]=v.time;
                    queue.add(new V(x,y,v.time,i));
                }
                continue;
            }

            int x = dx[v.direction]+v.x;
            int y = dy[v.direction]+v.y;
            if(!(x<0 || x>=N || y<0 || y>=N || check[x][y][v.direction]<=v.time || array[x][y]=='*')) {
                check[x][y][v.direction] = v.time;
                queue.add(new V(x, y, v.time, v.direction));
            }

            if(array[v.x][v.y]=='!'){
                if(v.direction==0 || v.direction==1){
                    moveUpAndDown(queue, check, v.x, v.y, v.time);
                }else{
                    moveLeftAndRight(queue,check,v.x,v.y,v.time);
                }
            }
        }

        return Math.min(result[3],Math.min(result[2],Math.min(result[0],result[1])));
    }

    private static void moveUpAndDown(Queue<V> queue, int[][][] check, int x, int y, int time) {
        if ( ! (y - 1 < 0 || array[x][y-1]=='*' || check[x][y-1][2]<=time+1)) {
            check[x][y - 1][2] = time+1;
            queue.add(new V(x, y - 1, time + 1, 2));
        }

        if (!(y + 1 >= N || array[x][y+1]=='*'|| check[x][y+1][3]<=time+1)) {
            check[x][y + 1][3] = time+1;
            queue.add(new V(x, y + 1, time + 1, 3));
        }
    }

    private static void moveLeftAndRight(Queue<V> queue, int[][][] check, int x, int y, int time) {
        if (!(x - 1 < 0 || array[x-1][y]=='*' || check[x-1][y][0]<=time+1)) {
            check[x - 1][y][0] = time+1;
            queue.add(new V(x - 1, y, time + 1, 0));
        }

        if (!(x + 1 >= N || array[x+1][y]=='*' || check[x+1][y][1]<=time+1)) {
            check[x + 1][y][1] = time+1;
            queue.add(new V(x + 1, y, time + 1, 1));
        }
    }
}

class V{
    int x;
    int y;
    int time;
    int direction;
    public V(int x,int y,int time,int direction){
        this.x=x;
        this.y=y;
        this.time=time;
        this.direction=direction;
    }
}