package BaekJoon_14442;

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
        int K = Integer.parseInt(str[2]);
        int[][] array = new int[N][M];
        boolean[][][] check = new boolean[N][M][K+1];

        for(int i=0;i<N;i++){
            str = br.readLine().split("");
            for(int j=0;j<str.length;j++){
                array[i][j] = Integer.parseInt(str[j]);
            }
        }

        Queue<V> queue = new LinkedList<>();
        queue.add(new V(0,0,1,0));
        for(int i=0;i<K;i++) check[0][0][i]=true;

        while(!queue.isEmpty()){
            V v = queue.remove();
            if(v.x==N-1 && v.y==M-1){
                System.out.println(v.value);
                return;
            }

            if(v.crash==K){ // 벽을 다 부순 경우
                if(v.x+1<N && array[v.x+1][v.y]==0 && !check[v.x+1][v.y][K]){
                    queue.add(new V(v.x+1,v.y,v.value+1,K));
                    check[v.x+1][v.y][K] = true;
                }
                if(v.x-1>=0 && array[v.x-1][v.y]==0 && !check[v.x-1][v.y][K]){
                    queue.add(new V(v.x-1,v.y,v.value+1,K));
                    check[v.x-1][v.y][K] = true;
                }
                if(v.y+1<M && array[v.x][v.y+1]==0 && !check[v.x][v.y+1][K]){
                    queue.add(new V(v.x,v.y+1,v.value+1,K));
                    check[v.x][v.y+1][K] = true;
                }
                if(v.y-1>=0 && array[v.x][v.y-1]==0 && !check[v.x][v.y-1][K]){
                    queue.add(new V(v.x,v.y-1,v.value+1,K));
                    check[v.x][v.y-1][K] = true;
                }
            }else{ // 벽을 다 부수지 않은 경우
                if(v.x+1<N && !check[v.x+1][v.y][v.crash]){
                    if(array[v.x+1][v.y]==1){
                        queue.add(new V(v.x+1,v.y,v.value+1,v.crash+1));
                    }else{
                        queue.add(new V(v.x+1,v.y,v.value+1,v.crash));
                    }
                    check[v.x+1][v.y][v.crash] = true;
                }
                if(v.x-1>=0 && !check[v.x-1][v.y][v.crash]){
                    if(array[v.x-1][v.y]==1){
                        queue.add(new V(v.x-1,v.y,v.value+1,v.crash+1));
                    }else{
                        queue.add(new V(v.x-1,v.y,v.value+1,v.crash));
                    }
                    check[v.x-1][v.y][v.crash] = true;
                }
                if(v.y+1<M && !check[v.x][v.y+1][v.crash]){
                    if(array[v.x][v.y+1]==1){
                        queue.add(new V(v.x,v.y+1,v.value+1,v.crash+1));
                    }else{
                        queue.add(new V(v.x,v.y+1,v.value+1,v.crash));
                    }
                    check[v.x][v.y+1][v.crash] = true;
                }
                if(v.y-1>=0 && !check[v.x][v.y-1][v.crash]){
                    if(array[v.x][v.y-1]==1){
                        queue.add(new V(v.x,v.y-1,v.value+1,v.crash+1));
                    }else{
                        queue.add(new V(v.x,v.y-1,v.value+1,v.crash));
                    }
                    check[v.x][v.y-1][v.crash] = true;
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
    int crash;
    public V(int x,int y,int value,int crash){
        this.x=x;
        this.y=y;
        this.value=value;
        this.crash=crash;
    }
}