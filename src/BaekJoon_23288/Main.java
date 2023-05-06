package BaekJoon_23288;

import java.io.*;
import java.util.*;
public class Main {
    static int N,M,direction,diceX,diceY;
    static int[][] array;
    static int[] value = {1,2,3,4,5,6}; // 앞, 위, 오, 왼, 아래, 바닥
    static int[] dx = {0,0,-1,1}; // 동, 서, 북, 남
    static int[] dy = {1,-1,0,0}; // 동, 서, 북, 남
    static int sum;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        int K = Integer.parseInt(str[2]);
        array = new int[N][M];
        for(int i=0;i<N;i++){
            str = br.readLine().split(" ");
            for(int j=0;j<M;j++) array[i][j]= Integer.parseInt(str[j]);
        }
        while(K-->0){
            move();
            bfs();
        }
        System.out.println(sum);
    }
    private static void bfs() {
        int count = 0;
        boolean[][] check = new boolean[N][M];
        Queue<V> queue = new LinkedList<>();
        check[diceX][diceY]=true;
        queue.add(new V(diceX,diceY));
        while(!queue.isEmpty()){
            count++;
            V v = queue.remove();
            for(int i=0;i<4;i++){
                int x = v.x+dx[i];
                int y = v.y+dy[i];
                if(x>=N || x<0 || y>=M || y<0 || check[x][y] || array[x][y]!=array[diceX][diceY]) continue;
                check[x][y]=true;
                queue.add(new V(x,y));
            }
        }
        count = count*array[diceX][diceY];
        sum+=count;
        if(value[5]>array[diceX][diceY]){
            // 이동 방향을 90도 시계방향으로 회전
            if(direction==0){ // 동
                direction=3;
            }else if(direction==1){ // 서
                direction=2;
            }else if(direction==2){ // 북
                direction=0;
            }else{ // 남
                direction=1;
            }
        }else if(value[5]<array[diceX][diceY]){
            // 이동 방향을 90도 반시계방향으로 회전
            if(direction==0){ // 동
                direction=2;
            }else if(direction==1){ // 서
                direction=3;
            }else if(direction==2){ // 북
                direction=1;
            }else{ // 남
                direction=0;
            }
        }
    }
    private static void move(){
        int x = diceX+dx[direction];
        int y = diceY+dy[direction];
        if(x>=N || x<0 || y>=M || y<0){
            if(direction==0){ // 동
                replaceLocation(1);
            }else if(direction==1){ // 서
                replaceLocation(0);
            }else if(direction==2){ // 북
                replaceLocation(3);
            }else{ // 남
                replaceLocation(2);
            }
        }else{
            diceX=x;
            diceY=y;
        }
        int[] temp = new int[6];
        if(direction==0){ // 동
            temp[0] = value[3];
            temp[1] = value[1];
            temp[2] = value[0];
            temp[3] = value[5];
            temp[4] = value[4];
            temp[5] = value[2];
        }else if(direction==1){ // 서
            temp[0] = value[2];
            temp[1] = value[1];
            temp[2] = value[5];
            temp[3] = value[0];
            temp[4] = value[4];
            temp[5] = value[3];
        }else if(direction==2){ // 북
            temp[0] = value[4];
            temp[1] = value[0];
            temp[2] = value[2];
            temp[3] = value[3];
            temp[4] = value[5];
            temp[5] = value[1];
        }else{ // 남
            temp[0] = value[1];
            temp[1] = value[5];
            temp[2] = value[2];
            temp[3] = value[3];
            temp[4] = value[0];
            temp[5] = value[4];
        }
        value = temp;
    }
    public static void replaceLocation(int updatedDirection){
        direction = updatedDirection;
        diceX = diceX+dx[updatedDirection];
        diceY = diceY+dy[updatedDirection];
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