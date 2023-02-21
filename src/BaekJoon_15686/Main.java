package BaekJoon_15686;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static int N,M;
    static int[][] array;
    static int MIN_VALUE = Integer.MAX_VALUE;

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

        for(int i=1;i<=M;i++){
            dfs(0,i,new ArrayList<>(),0);
        }
        System.out.println(MIN_VALUE);
    }

    public static void dfs(int depth, int target, List<Integer> list, int start){
        if(depth==target){
            int[][] temp = new int[N][N];

            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++) temp[i][j]=array[i][j];
            }
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++) if(temp[i][j]==2) temp[i][j]=0;
            }

            if(depth==0) temp=array;

            for(int v : list){
                int x = v/N;
                int y = v%N;
                temp[x][y]=2;
            }

            int v = 0;
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    if(array[i][j]==1){
                        v+=minChickenDist(i,j,temp);
                    }
                }
            }
            MIN_VALUE = MIN_VALUE>v?v:MIN_VALUE;

            return;
        }

        for(int i=start;i<N*N;i++){
            int x = i/N;
            int y = i%N;
            if(array[x][y]==2){
                list.add(i);
                int size = list.size()-1;
                dfs(depth+1, target, list,i+1);
                list.remove(size);
            }
        }
    }

    public static int minChickenDist(int x, int y, int[][] array){
        int min = Integer.MAX_VALUE;
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(array[i][j]==2){
                    int v = Math.abs(x-i)+Math.abs(y-j);
                    min = min>v?v:min;
                }
            }
        }
        return min;
    }
}