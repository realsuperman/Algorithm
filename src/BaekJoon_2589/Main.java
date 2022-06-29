package BaekJoon_2589;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int N,M;
    static char[][] array;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        array = new char[N][M];
        int result = Integer.MIN_VALUE;

        for(int i=0;i<N;i++){
            str = br.readLine().split("");
            for(int j=0;j<str.length;j++) array[i][j]= str[j].charAt(0);
        }
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(array[i][j]=='L'){
                    int value = bfs(i,j);
                    if(result<value) result = value;
                }
            }
        }
        System.out.println(result);
    }

    private static int bfs(int x, int y) {
        boolean[][] check = new boolean[N][M];
        Queue<V> queue = new LinkedList<>();
        check[x][y] = true;
        queue.add(new V(x,y,0));
        int value = Integer.MIN_VALUE;

        while(!queue.isEmpty()){
            V v = queue.remove();
            if(value<v.time) value = v.time;
            for(int i=0;i<4;i++){
                int solutionX = dx[i]+v.x;
                int solutionY = dy[i]+v.y;
                if(solutionX<0 || solutionX>=N || solutionY<0 || solutionY>=M) continue;
                if(!check[solutionX][solutionY] && array[solutionX][solutionY]=='L'){
                    check[solutionX][solutionY] = true;
                    queue.add(new V(solutionX,solutionY,v.time+1));
                }
            }
        }
        return value;
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