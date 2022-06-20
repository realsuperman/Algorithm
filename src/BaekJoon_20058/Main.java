package BaekJoon_20058;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    static int N;
    static int[][] array;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        int Q = Integer.parseInt(str[1]);
        int size = 1;
        for(int i=0;i<N;i++) size*=2;
        N = size;

        array = new int[N][N];
        for(int i=0;i<N;i++){
            str = br.readLine().split(" ");
            for(int j=0;j<str.length;j++){
                array[i][j] = Integer.parseInt(str[j]);
            }
        }
        str = br.readLine().split(" ");
        for(int i=0;i<str.length;i++) soltuion(Integer.parseInt(str[i]));
        int[] result = new int[2];
        result[1] = 0;

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                result[0]+=array[i][j];
                int ice = iceValue(i,j);
                if(array[i][j]>0){
                    int num = bfs(i,j);
                    if(num>result[1]) result[1]=num;
                }
            }
        }
        System.out.println(result[0]);
        System.out.println(result[1]);
    }
    public static void soltuion(int k){
        int[][] temp = new int[N][N];
        int divide = 1;
        for(int i=0;i<k;i++) divide*=2;

        int x = 0;
        int y = 0;
        while(true){
            List<Integer> rows = new ArrayList<>();
            for(int i=0;i<divide;i++){
                int value = 0;
                for(int j=0;j<divide;j++){
                    rows.add(array[x+i][y+value]);
                    value++;
                }
            }
            for(int i=0;i<divide;i++){
                int startY = (y+divide)-1-i;
                for(int j=0;j<divide;j++){
                    temp[x+j][startY] = rows.remove(0);
                }
            }
            y=y+divide;
            if(y>=N){
                x=x+divide;
                y=0;
            }
            if(x>=N) break;
        }

        Queue<V> queue = new LinkedList<>();
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(temp[i][j]<=0) continue;
                int iceCnt = 0;
                for(int index=0;index<4;index++){
                    int xLocation = i+dx[index];
                    int yLocation = j+dy[index];
                    if(xLocation>=N || xLocation<0 || yLocation>=N || yLocation<0) continue;
                    if(temp[xLocation][yLocation]>=1) iceCnt++;
                }
                if(iceCnt<3) queue.add(new V(i,j));
            }
        }
        while(!queue.isEmpty()){
            V v = queue.remove();
            temp[v.x][v.y]-=1;
        }
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                array[i][j]=temp[i][j];
            }
        }
    }

    static int iceValue(int x,int y){
        int sum = 0;
        for(int index=0;index<4;index++){
            int xLocation = x+dx[index];
            int yLocation = y+dy[index];
            if(xLocation>=N || xLocation<0 || yLocation>=N || yLocation<0) continue;
            if(array[xLocation][yLocation]>=1) sum+=array[xLocation][yLocation];
        }
        return sum;
    }

    static int bfs(int x,int y){
        int sum = 1;
        Queue<V> queue = new LinkedList<>();
        boolean[][] check = new boolean[N][N];
        queue.add(new V(x,y));
        check[x][y]=true;
        while(!queue.isEmpty()){
            V v = queue.remove();
            for(int i=0;i<4;i++){
                int xLocation = v.x+dx[i];
                int yLocation = v.y+dy[i];
                if(xLocation>=N || xLocation<0 || yLocation>=N || yLocation<0) continue;
                if(!check[xLocation][yLocation] && array[xLocation][yLocation]>=1){sum++; queue.add(new V(xLocation,yLocation)); check[xLocation][yLocation]=true;}
            }
        }

        return sum;
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