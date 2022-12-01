package BaekJoon_16234.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    static int N,sum,start,end;
    static int[][] array,temp;
    static boolean[][] check;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static List<V> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");

        N = Integer.parseInt(str[0]);
        start = Integer.parseInt(str[1]);
        end = Integer.parseInt(str[2]);
        array = new int[N][N];
        for(int i=0;i<N;i++){
            str = br.readLine().split(" ");
            for(int j=0;j<str.length;j++) array[i][j]= Integer.parseInt(str[j]);
        }

        int result = 0;
        temp = new int[N][N];
        copyArray();

        while(true){
            check = new boolean[N][N];
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    sum = 0;
                    if(!check[i][j]){
                        sum+=array[i][j];
                        check[i][j]=true;
                        list = new ArrayList<>();
                        list.add(new V(i,j));
                        bfs(i,j);
                        for(V v : list) array[v.x][v.y]=sum/list.size();
                    }
                }
            }
            if(!isDiff()) break;
            copyArray();
            result++;
        }

        System.out.println(result);
    }

    public static void bfs(int startX, int startY) {
        Queue<V> queue = new LinkedList<>();
        queue.add(new V(startX,startY));
        while(!queue.isEmpty()){
            V v = queue.remove();
            for(int i=0;i<4;i++){
                int x = dx[i]+v.x;
                int y = dy[i]+v.y;
                if(x>=N || x<0 || y>=N || y<0 || check[x][y] ) continue;

                if(start<=Math.abs(array[v.x][v.y]-array[x][y]) && end>=Math.abs(array[v.x][v.y]-array[x][y])){
                    sum+=array[x][y];
                    queue.add(new V(x,y));
                    check[x][y]=true;
                    list.add(new V(x,y));
                }
            }
        }
    }

    public static boolean isDiff(){
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(array[i][j]!=temp[i][j]) return true;
            }
        }
        return false;
    }

    public static void copyArray(){
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++) temp[i][j]=array[i][j];
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