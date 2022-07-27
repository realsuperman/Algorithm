package BaekJoon_2234;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Main {
    static int N,M;
    static boolean[][] check;
    static int[] dx = {0,0,1,-1}; // 동서남북순
    static int[] dy = {1,-1,0,0}; // 동서남북순
    static int[][] array;
    static int[][] realArray;
    static Map<Integer,Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");

        N = Integer.parseInt(str[1]);
        M = Integer.parseInt(str[0]);
        array = new int[N][M];
        realArray = new int[N][M];

        for(int i=0;i<N;i++){
            str = br.readLine().split(" ");
            for(int j=0;j<str.length;j++) array[i][j]= Integer.parseInt(str[j]);
        }

        check = new boolean[N][M];
        int[] result = new int[2]; // 방의개수,넓은방
        result[1] = Integer.MIN_VALUE;

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(!check[i][j]){
                    check[i][j]=true;
                    result[0]++;
                    int cnt = roomCnt(i,j,result[0]);
                    if(result[1]<cnt) result[1]=cnt;
                    map.put(result[0],cnt);
                }
            }
        }

        System.out.println(result[0]);
        System.out.println(result[1]);
        System.out.println(simulation());
    }

    public static int roomCnt(int startX,int startY,int init){
        realArray[startX][startY]=init;
        Queue<V> queue = new LinkedList<>();
        queue.add(new V(startX,startY));

        int result = 1;
        while(!queue.isEmpty()){
            V v = queue.remove();
            boolean[] sw = new boolean[4];
            locationInit(array[v.x][v.y],sw);

            for(int i=0;i<4;i++){
                if(!sw[i]) continue;
                int x = v.x+dx[i];
                int y = v.y+dy[i];
                if(x<0 || x>=N || y<0|| y>=M || check[x][y]) continue;
                check[x][y]=true;
                realArray[x][y]=init;
                queue.add(new V(x,y));
                result++;
            }
        }
        return result;
    }

    public static int simulation() {
        int result = Integer.MIN_VALUE;
        int backNum,index;

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                boolean[] sw = new boolean[4];
                locationInit(array[i][j],sw);
                for(int k=0;k<4;k++) sw[k]=!sw[k];

                backNum = array[i][j];
                index = 0;
                while(index<4){
                    if(sw[index]){
                        int x = i+dx[index];
                        int y = j+dy[index];
                        if(x<0 || x>=N || y<0|| y>=M ){index++; continue;}
                        if(realArray[i][j]!=realArray[x][y]){
                            int value = map.get(realArray[x][y])+map.get(realArray[i][j]);
                            result = result < value ? value : result;
                        }
                    }
                    index++;
                }
                array[i][j]=backNum;
            }
        }
        return result;
    }

    public static void locationInit(int value,boolean[] sw){
        switch(value){
            case 0 : sw[0]=true; sw[1]=true; sw[2]=true; sw[3]=true; break;
            case 1 : sw[0]=true; sw[2]=true; sw[3]=true; break;
            case 2 : sw[0]=true; sw[1]=true; sw[2]=true; break;
            case 3 : sw[0]=true; sw[2]=true; break;
            case 4 : sw[1]=true; sw[2]=true; sw[3]=true; break;
            case 5 : sw[2]=true; sw[3]=true; break;
            case 6 : sw[1]=true; sw[2]=true; break;
            case 7 : sw[2]=true; break;
            case 8 : sw[0]=true; sw[1]=true; sw[3]=true; break;
            case 9 : sw[0]=true; sw[3]=true; break;
            case 10 : sw[0]=true; sw[1]=true; break;
            case 11 : sw[0]=true; break;
            case 12 : sw[1]=true; sw[3]=true; break;
            case 13 : sw[3]=true; break;
            case 14 : sw[1]=true; break;
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