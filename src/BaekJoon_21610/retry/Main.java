package BaekJoon_21610.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N,M;
    static int[][] array;
    static int[] dx = {0,-1,-1,-1,0,1,1,1};
    static int[] dy = {-1,-1,0,1,1,1,0,-1};
    static List<V> clouds = new ArrayList<>();
    static boolean sw = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        array = new int[N][N];

        for(int i=0;i<N;i++){
            str = br.readLine().split(" ");
            for(int j=0;j<N;j++) array[i][j]= Integer.parseInt(str[j]);
        }

        while(M-->0){
            str = br.readLine().split(" ");
            solution(Integer.parseInt(str[0])-1,Integer.parseInt(str[1]));
        }

        int sum = 0;
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                sum+=array[i][j];
            }
        }
        System.out.println(sum);
    }

    public static void solution(int d, int s){
        if(!sw){
            clouds.add(new V(N-1,0));
            clouds.add(new V(N-1,1));
            clouds.add(new V(N-2,0));
            clouds.add(new V(N-2,1));
        }
        sw=true;

        boolean[][] check = new boolean[N][N]; // 중복 구름 생성 방지용

        while(s-->0){
            List<V> temp = new ArrayList<>();

            int size = clouds.size();
            int index = 0;
            while(size-->0) {
                V v = clouds.get(index++);
                int x = dx[d] + v.x;
                int y = dy[d] + v.y;
                if (x >= N) x = 0;
                else if (x < 0) x = N - 1;
                if (y >= N) y = 0;
                else if (y < 0) y = N - 1;
                temp.add(new V(x, y));
            }
            clouds = temp;
        }

        for(V v : clouds){
            array[v.x][v.y]++;
            check[v.x][v.y] = true;
        }
        int[][] temp = new int[N][N];
        for(int i=0;i<N;i++) for(int j=0;j<N;j++) temp[i][j]=array[i][j];

        for(V v : clouds) {
            int cnt = 0;
            for (int i = 1; i <= 7; i += 2) {
                int x = dx[i]+v.x;
                int y = dy[i]+v.y;
                if(x>=N || x<0 || y>=N || y<0 || temp[x][y]<=0) continue;
                cnt++;
            }
            array[v.x][v.y]+=cnt;
        }
        clouds = new ArrayList<>();

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(array[i][j]>=2 && !check[i][j]){
                    array[i][j]-=2;
                    clouds.add(new V(i,j));
                }
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