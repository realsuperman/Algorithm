package BaekJoon_17406.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N,M,K;
    static int[][] array;
    static V[] v;
    static int MIN_VALUE = Integer.MAX_VALUE;
    static boolean[] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        K = Integer.parseInt(str[2]);
        array = new int[N][M];
        v = new V[K];
        check = new boolean[K];

        for(int i=0;i<N;i++){
            str = br.readLine().split(" ");
            for(int j=0;j<M;j++) array[i][j]= Integer.parseInt(str[j]);
        }

        int index = 0;
        while(K-->0){
            str = br.readLine().split(" ");
            v[index++] = new V(Integer.parseInt(str[0])-1,Integer.parseInt(str[1])-1,Integer.parseInt(str[2]));
        }

        dfs(0, index, new ArrayList<>());
        System.out.println(MIN_VALUE);
    }

    public static void dfs(int currentDepth,int depth, List<Integer> list){
        if(currentDepth==depth){
            int[][] temp = new int[N][M];
            for(int i=0;i<N;i++) for(int j=0;j<M;j++) temp[i][j]=array[i][j];

            for(int index : list)  rotateArray(temp, v[index].r,v[index].c,v[index].s);
            minValue(temp);
            return;
        }
        for(int i=0;i<depth;i++){
            if(check[i]) continue;
            list.add(i);
            check[i]=true;
            int size = list.size()-1;
            dfs(currentDepth+1, depth, list);
            check[i]=false;
            list.remove(size);
        }
    }

    public static void rotateArray(int[][] target, int r, int c, int s){
        int startX = r-s;
        int startY = c-s;
        int endX = r+s;
        int endY = c+s;

        while(true){
            if(startX==endX && startY==endY) break;
            int temp = target[startX][startY];
            int index1=startX;
            int index2=startY;

            for(;index1<endX;index1++) target[index1][index2] = target[index1+1][index2];
            for(;index2<endY;index2++) target[index1][index2]=target[index1][index2+1];
            for(;index1>startX;index1--) target[index1][index2] = target[index1-1][index2];
            for(;index2>startY;index2--) target[index1][index2] = target[index1][index2-1];
            target[startX][startY+1]=temp;

            startX++;
            startY++;
            endX--;
            endY--;

        }
    }

    public static void minValue(int[][] target){
        for(int i=0;i<target.length;i++){
            int sum = 0;
            for(int j=0;j<target[i].length;j++) sum+=target[i][j];
            MIN_VALUE = MIN_VALUE>sum?sum:MIN_VALUE;
        }
    }
}

class V{
    int r;
    int c;
    int s;
    public V(int r,int c,int s){
        this.r=r;
        this.c=c;
        this.s=s;
    }
}