package BaekJoon_6087.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int N,M,startX,startY,endX,endY;
    static char[][] array;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[1]);
        M = Integer.parseInt(str[0]);
        boolean sw = true;
        array = new char[N][M];
        for(int i=0;i<N;i++){
            str = br.readLine().split("");
            for(int j=0;j<str.length;j++){
                array[i][j]=str[j].charAt(0);
                if(array[i][j]=='C' && sw) { startX = i; startY=j; sw = false;}
                else if(array[i][j]=='C' && !sw) { endX = i; endY = j;}
            }
        }
        bfs();
    }

    public static void bfs(){
        Queue<V> queue = new LinkedList<>();
        int[][][] check = new int[N][M][4];
        for(int i=0;i<N;i++) for(int j=0;j<M;j++) for(int k=0;k<4;k++) check[i][j][k]=Integer.MAX_VALUE;

        for(int i=0;i<4;i++){
            int x = dx[i]+startX;
            int y = dy[i]+startY;
            if(x>=N || x<0 ||  y>=M || y<0 || array[x][y]=='*') continue;
            queue.add(new V(x,y,0,i));
            check[x][y][i] = 0;
        }

        while(!queue.isEmpty()){
            V v = queue.remove();

            // 1. 거울을 설치하지않고 직진
            int x = dx[v.direction]+v.x;
            int y = dy[v.direction]+v.y;
            if(!(x>=N || x<0 ||  y>=M || y<0 || array[x][y]=='*' || check[x][y][v.direction]<=v.mirrorCnt)){
                queue.add(new V(x,y,v.mirrorCnt,v.direction));
                check[x][y][v.direction] = v.mirrorCnt;
            }

            // 2. 거울을 설치한다(2방향)
            if(v.direction==0 || v.direction==1){
                rotateMethod(queue, check, 2, v);
                rotateMethod(queue, check, 3, v);
            }else{
                rotateMethod(queue, check, 0, v);
                rotateMethod(queue, check, 1, v);
            }
        }

        System.out.println(Math.min(check[endX][endY][3],Math.min(check[endX][endY][2],Math.min(check[endX][endY][0],check[endX][endY][1]))));
    }

    private static void rotateMethod(Queue<V> queue, int[][][] check, int index, V v) {
        int x = dx[index]+v.x;
        int y = dy[index]+v.y;
        if(!(x>=N || x<0 ||  y>=M || y<0 || array[x][y]=='*' || check[x][y][index]<=v.mirrorCnt+1)){
            queue.add(new V(x,y,v.mirrorCnt+1,index));
            check[x][y][index] = v.mirrorCnt+1;
        }
    }

}

class V{
    int x;
    int y;
    int mirrorCnt;
    int direction; // 상하좌우
    public V(int x,int y,int mirrorCnt,int direction){
        this.x=x;
        this.y=y;
        this.mirrorCnt=mirrorCnt;
        this.direction=direction;
    }
}