package BaekJoon_20058.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N,L;
    static int[][] array;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = (int) Math.pow(2,Integer.parseInt(str[0]));
        L = Integer.parseInt(str[1]);
        array = new int[N][N];

        for(int i=0;i<N;i++){
            str = br.readLine().split(" ");
            for(int j=0;j<N;j++) array[i][j]= Integer.parseInt(str[j]);
        }

        str = br.readLine().split(" ");
        for(int i=0;i<str.length;i++) solution(Integer.parseInt(str[i]));

        int sum = 0;
        int maxCnt = 0;
        for(int i=0;i<N;i++) for(int j=0;j<N;j++) sum+=array[i][j];
        boolean[][] check = new boolean[N][N];

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(!check[i][j] && array[i][j]>0){
                    check[i][j]=true;
                    int v = area(i,j,check);
                    maxCnt = maxCnt<v?v:maxCnt;
                }
            }
        }

        System.out.println(sum);
        System.out.println(maxCnt);
    }

    private static void solution(int number){
        // 루프에서 rotate 호출
        int value = (int) Math.pow(2,number);
        for(int i=0;i<N;i+=value){
            for(int j=0;j<N;j+=value){
                rotate(number, i, j);
            }
        }

        int[][] temp = new int[N][N];

        // 얼음이 3칸 미만 접할시 얼음의 양이 줄어든다
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                int cnt = 0;
                for(int k=0;k<4;k++){
                    int x = dx[k]+i;
                    int y = dy[k]+j;
                    if(x<0 || x>=N || y<0 || y>=N || array[x][y]<1) continue;
                    cnt++;
                }
                temp[i][j] = array[i][j];
                if(cnt<3 && temp[i][j]>0) temp[i][j]--;
            }
        }
        array = temp;
    }

    private static void rotate(int value, int startX, int startY) {
        int loop = (int) Math.pow(2,value)-1;
        value = (int) Math.pow(2,value);

        while(value>0){
            int loopCnt = loop;

            while(loopCnt-->0) {
                int temp = array[startX][startY];
                int tempStartX = startX;
                int tempStartY = startY;

                int index;
                for (index = tempStartX; index < startX + value - 1; index++) array[index][tempStartY] = array[index + 1][tempStartY];
                tempStartX = index;
                for (index = tempStartY; index < startY + value - 1; index++) array[tempStartX][index] = array[tempStartX][index + 1];
                tempStartY = index;

                for (index = tempStartX; index > startX; index--) array[index][tempStartY] = array[index - 1][tempStartY];
                tempStartX = index;
                for (index = tempStartY; index > startY; index--) array[tempStartX][index] = array[tempStartX][index - 1];
                array[startX][startY + 1] = temp;

            }

            startX++;
            startY++;
            value-=2;
            loop-=2;
        }

    }

    private static int area(int startX,int startY,boolean[][] check){
        int cnt = 1;
        Queue<V> queue = new LinkedList<>();
        queue.add(new V(startX,startY));
        while(!queue.isEmpty()){
            V v = queue.remove();
            for(int i=0;i<4;i++){
                int x = dx[i]+v.x;
                int y = dy[i]+v.y;

                if(x<0 || x>=N || y<0 || y>=N || check[x][y] || array[x][y]<=0) continue;
                check[x][y]=true;
                queue.add(new V(x,y));
                cnt++;
            }
        }
        return cnt;
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