package BaekJoon_2573.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int N,M;
    static int[][] array;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        array = new int[N][M];

        for(int i=0;i<N;i++){
            str = br.readLine().split(" ");
            for(int j=0;j<M;j++) array[i][j]= Integer.parseInt(str[j]);
        }

        int result = 0;
        boolean sw = false;
        while(checkGround()){
            if(checkSector()){sw=true; break;}
            simulation();
            result++;
        }

        System.out.println(sw?result:0);
    }

    public static void simulation() {
        int[][] temp = new int[N][M];

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(array[i][j]>0){
                    int sum = 0;
                    for(int k=0;k<4;k++){
                        int x = dx[k]+i;
                        int y = dy[k]+j;
                        if(x<0 || x>=N || y<0 || y>=M || array[x][y]>0) continue;
                        sum++;
                    }
                    temp[i][j] = array[i][j]-sum;
                    if(temp[i][j]<0) temp[i][j]=0;
                }else{
                    temp[i][j]=0;
                }
            }
        }
        for(int i=0;i<N;i++) for(int j=0;j<M;j++) array[i][j]=temp[i][j];
    }

    public static boolean checkGround(){
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(array[i][j]>0) return true;
            }
        }
        return false;
    }

    public static boolean checkSector(){
        int value = 0;
        boolean[][] check = new boolean[N][M];
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(value>=2) return true;
                if(array[i][j]>0 && !check[i][j]){ check[i][j]=true; bfs(i,j,check); value++;}
            }
        }
        return false;
    }

    private static void bfs(int startX,int startY, boolean[][] check) {
        Queue<V> queue = new LinkedList<>();
        queue.add(new V(startX,startY));
        while(!queue.isEmpty()){
            V v = queue.remove();
            for(int i=0;i<4;i++){
                int x = dx[i]+v.x;
                int y = dy[i]+v.y;
                if(check[x][y] || x<0 || x>=N || y<0 || y>=M || array[x][y]<=0) continue;
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