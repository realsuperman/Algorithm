package BaekJoon_10026;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] array = new int[N][N];
        boolean[][] check = new boolean[N][N];

        for(int i=0;i<N;i++){
            String[] str = br.readLine().split("");
            for(int j=0;j<str.length;j++){
                if(str[j].equals("G")){
                    array[i][j] = 1;
                }else if(str[j].equals("B")){
                    array[i][j] = 2;
                }
            }
        }

        int answer1 = 0;
        int answer2 = 0;
        // 0은 Red 1은 Green 2는 Blue
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(!check[i][j]){
                    answer1++;
                    check[i][j]=true;
                    bfs(array,check,i,j,array[i][j],N);
                }
            }
        }

        for(int i=0;i<N;i++) {
            for (int j = 0; j < N; j++) {
                check[i][j] = false;
                if (array[i][j] == 1) array[i][j] = 0;
            }
        }

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(!check[i][j]){
                    answer2++;
                    check[i][j]=true;
                    bfs(array,check,i,j,array[i][j],N);
                }
            }
        }

        System.out.println(answer1+" "+answer2);
    }

    public static void bfs(int[][] array,boolean[][] check,int x,int y,int color,int N){
        Queue<V> queue = new LinkedList<>();
        queue.add(new V(x,y));
        while(!queue.isEmpty()){
            V v = queue.remove();
            if(v.x-1>=0 && !check[v.x-1][v.y] && color == array[v.x-1][v.y]){
                check[v.x-1][v.y]=true;
                queue.add(new V(v.x-1,v.y));
            }
            if(v.x+1<N && !check[v.x+1][v.y] && color == array[v.x+1][v.y]){
                check[v.x+1][v.y]=true;
                queue.add(new V(v.x+1,v.y));
            }
            if(v.y-1>=0 && !check[v.x][v.y-1] && color == array[v.x][v.y-1]){
                check[v.x][v.y-1]=true;
                queue.add(new V(v.x,v.y-1));
            }
            if(v.y+1<N && !check[v.x][v.y+1] && color == array[v.x][v.y+1]){
                check[v.x][v.y+1]=true;
                queue.add(new V(v.x,v.y+1));
            }
        }
    }
}

class V{
    int x;
    int y;
    public V(int x,int y){this.x=x; this.y=y;}
}