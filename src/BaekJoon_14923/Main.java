package BaekJoon_14923;

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
        str = br.readLine().split(" ");

        int startX = Integer.parseInt(str[0])-1;
        int startY = Integer.parseInt(str[1])-1;
        str = br.readLine().split(" ");
        int endX = Integer.parseInt(str[0])-1;
        int endY = Integer.parseInt(str[1])-1;

        for(int i=0;i<N;i++){
            str = br.readLine().split(" ");
            for(int j=0;j<M;j++){
                array[i][j] = Integer.parseInt(str[j]);
            }
        }
        boolean[][][] check = new boolean[2][N][M]; //0은 안부순세계 1은 부순세계
        Queue<V> queue = new LinkedList<>();
        queue.add(new V(startX,startY,0,false));
        check[0][startX][startY] = true;
        check[1][startX][startY] = true;

        while(!queue.isEmpty()){
            V v = queue.remove();
            if(v.x==endX && v.y==endY){System.out.println(v.breanCnt); return;}
            if(v.breakYn){ // 부순세계
                if(v.x+1<N && array[v.x+1][v.y]==0 && !check[1][v.x+1][v.y]){
                    check[1][v.x+1][v.y]=true;
                    queue.add(new V(v.x+1,v.y,v.breanCnt+1,true));
                }
                if(v.x-1>=0 && array[v.x-1][v.y]==0 && !check[1][v.x-1][v.y]){
                    check[1][v.x-1][v.y]=true;
                    queue.add(new V(v.x-1,v.y,v.breanCnt+1,true));
                }
                if(v.y+1<M && array[v.x][v.y+1]==0 && !check[1][v.x][v.y+1]){
                    check[1][v.x][v.y+1]=true;
                    queue.add(new V(v.x,v.y+1,v.breanCnt+1,true));
                }
                if(v.y-1>=0 && array[v.x][v.y-1]==0 && !check[1][v.x][v.y-1]){
                    check[1][v.x][v.y-1]=true;
                    queue.add(new V(v.x,v.y-1,v.breanCnt+1,true));
                }
            }else{ // 안부순세계
                if(v.x+1<N && !check[0][v.x+1][v.y]){
                    if(array[v.x+1][v.y]==0){
                        check[0][v.x+1][v.y]=true;
                        queue.add(new V(v.x+1,v.y,v.breanCnt+1,false));
                    }else{ // 부순세계로 이동
                        check[1][v.x+1][v.y]=true;
                        queue.add(new V(v.x+1,v.y,v.breanCnt+1,true));
                    }
                }
                if(v.x-1>=0 && !check[0][v.x-1][v.y]){
                    if(array[v.x-1][v.y]==0){
                        check[0][v.x-1][v.y]=true;
                        queue.add(new V(v.x-1,v.y,v.breanCnt+1,false));
                    }else{ // 부순세계로 이동
                        check[1][v.x-1][v.y]=true;
                        queue.add(new V(v.x-1,v.y,v.breanCnt+1,true));
                    }
                }
                if(v.y+1<M && !check[0][v.x][v.y+1]){
                    if(array[v.x][v.y+1]==0){
                        check[0][v.x][v.y+1]=true;
                        queue.add(new V(v.x,v.y+1,v.breanCnt+1,false));
                    }else{ // 부순세계로 이동
                        check[1][v.x][v.y+1]=true;
                        queue.add(new V(v.x,v.y+1,v.breanCnt+1,true));
                    }
                }
                if(v.y-1>=0 && !check[0][v.x][v.y-1]){
                    if(array[v.x][v.y-1]==0){
                        check[0][v.x][v.y-1]=true;
                        queue.add(new V(v.x,v.y-1,v.breanCnt+1,false));
                    }else{ // 부순세계로 이동
                        check[1][v.x][v.y-1]=true;
                        queue.add(new V(v.x,v.y-1,v.breanCnt+1,true));
                    }
                }
            }
        }
        System.out.println(-1);
    }
}

class V{
    int x;
    int y;
    int breanCnt;
    boolean breakYn;
    public V(int x,int y,int breanCnt, boolean breakYn){
        this.x=x;
        this.y=y;
        this.breanCnt=breanCnt;
        this.breakYn=breakYn;
    }
}