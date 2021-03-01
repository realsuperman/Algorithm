package BaekJoon_17404;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] array = new int[n][3];
        int[][] array2 = new int[n][3];
        String[] str;
        int min = 2147483647;

        for(int i=0;i<n;i++){
            str = br.readLine().split(" ");
            for(int j=0;j<3;j++){
                array[i][j] = Integer.parseInt(str[j]);
                array2[i][j] = Integer.parseInt(str[j]);
            }
        }

        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(i==j){
                    array2[1][j] = 2147483647;
                    continue;
                }
                array2[1][j] = array2[0][i]+array2[1][j];
            }
            for(int j=2;j<n;j++){
                for(int k=0;k<3;k++){
                    if(j==2){
                        //if(k==i) continue;
                        int v = min(array2,j-1,k);
                        array2[j][k] = array2[j][k]+v;
                    }else{
                        array2[j][k] = array2[j][k] + min(array2[j-1][0],array2[j-1][1],array2[j-1][2],k);
                    }
                }
            }
            //System.out.println(array2[n-1][0]+" "+array2[n-1][1]+" "+array2[n-1][2]);
            int v = min(array2[n-1][0],array2[n-1][1],array2[n-1][2],i);
            //System.out.println(v);
            if(min>v) min = v;
            init(array,array2);
        }
        System.out.print(min);
    }
    public static void init(int[][] from,int[][] to){
        for(int i=0;i<from.length;i++){
            for(int j=0;j<3;j++){
                to[i][j] = from[i][j];
            }
        }
    }
    public static int min(int a, int b,int c,int index){
        if(index == 0){
            if(b>c) return c;
            else return b;
        }else if(index == 1){
            if(a>c) return c;
            else return a;
        }else{
            if(a>b) return b;
            else return a;
        }
    }
    public static int min(int[][] array,int row,int column){
        int min = 2147483647;
        for(int i=0;i<3;i++){
            if(i==column) continue;
            if(min > array[row][i]) min = array[row][i];
        }
        return min;
    }
}

