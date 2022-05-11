package BaekJoon_2206;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int M = Integer.parseInt(str[1]);
        int[][] array = new int[N][M];
        boolean[][][] check = new boolean[N][M][2];

        for(int i=0;i<N;i++){
            str = br.readLine().split("");
            for(int j=0;j<str.length;j++){
                array[i][j] = Integer.parseInt(str[j]);
            }
        }
        Queue<V> queue = new LinkedList<>();
        queue.add(new V(0,0,1,false));
        check[0][0][0] = true; check[0][0][1] = true;

        while(!queue.isEmpty()){
            V v = queue.remove();
            if(v.x==N-1 && v.y==M-1){
                System.out.println(v.value);
                return;
            }

            if(v.crash){ // 벽을 부순 경우
                if(v.x+1<N && array[v.x+1][v.y]==0 && !check[v.x+1][v.y][1]){
                    queue.add(new V(v.x+1,v.y,v.value+1,true));
                    check[v.x+1][v.y][1] = true;
                }
                if(v.x-1>=0 && array[v.x-1][v.y]==0 && !check[v.x-1][v.y][1]){
                    queue.add(new V(v.x-1,v.y,v.value+1,true));
                    check[v.x-1][v.y][1] = true;
                }
                if(v.y+1<M && array[v.x][v.y+1]==0 && !check[v.x][v.y+1][1]){
                    queue.add(new V(v.x,v.y+1,v.value+1,true));
                    check[v.x][v.y+1][1] = true;
                }
                if(v.y-1>=0 && array[v.x][v.y-1]==0 && !check[v.x][v.y-1][1]){
                    queue.add(new V(v.x,v.y-1,v.value+1,true));
                    check[v.x][v.y-1][1] = true;
                }
            }else{ // 벽을 안부순 경우
                if(v.x+1<N && !check[v.x+1][v.y][0]){
                    if(array[v.x+1][v.y]==1){
                        queue.add(new V(v.x+1,v.y,v.value+1,true));
                    }else{
                        queue.add(new V(v.x+1,v.y,v.value+1,false));
                    }
                    check[v.x+1][v.y][0] = true;
                }
                if(v.x-1>=0 && !check[v.x-1][v.y][0]){
                    if(array[v.x-1][v.y]==1){
                        queue.add(new V(v.x-1,v.y,v.value+1,true));
                    }else{
                        queue.add(new V(v.x-1,v.y,v.value+1,false));
                    }
                    check[v.x-1][v.y][0] = true;
                }
                if(v.y+1<M && !check[v.x][v.y+1][0]){
                    if(array[v.x][v.y+1]==1){
                        queue.add(new V(v.x,v.y+1,v.value+1,true));
                    }else{
                        queue.add(new V(v.x,v.y+1,v.value+1,false));
                    }
                    check[v.x][v.y+1][0] = true;
                }
                if(v.y-1>=0 && !check[v.x][v.y-1][0]){
                    if(array[v.x][v.y-1]==1){
                        queue.add(new V(v.x,v.y-1,v.value+1,true));
                    }else{
                        queue.add(new V(v.x,v.y-1,v.value+1,false));
                    }
                    check[v.x][v.y-1][0] = true;
                }
            }
        }
        System.out.println(-1);
    }
}
class V{
    int x;
    int y;
    int value;
    boolean crash;
    public V(int x,int y,int value,boolean crash){
        this.x=x;
        this.y=y;
        this.value=value;
        this.crash=crash;
    }
}