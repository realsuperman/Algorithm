package BaekJoon_9663;

import java.util.Scanner;

public class Main2 {
    static boolean[][] array;
    static int cnt;

    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        array = new boolean[n][n];
        solution(0,n);
        System.out.println(cnt);
    }
    public static void solution(int start,int n){
        if(start==n){ cnt++; return;}

        for(int i=0;i<n;i++){
            array[start][i]=true;
            if(check(start,i,n)){ array[start][i]=false; continue;}
            solution(start+1,n);
            array[start][i]=false;
        }

    }
    public static boolean check(int start,int column,int n){
        for(int i=start-1;i>=0;i--){
            if(array[i][column]) return true;
        }

        int j = column-1;
        for(int i=start-1;i>=0;i--){
            if(j<0) break;
            if(array[i][j--]) return true;
        }

        j = column+1;
        for(int i=start-1;i>=0;i--){
            if(j>=n) break;
            if(array[i][j++]) return true;
        }

        j=column-1;
        for(int i=start+1;i<n;i++){
            if(j<0) break;
            if(array[i][j--]) return true;
        }

        j=column+1;
        for(int i=start+1;i<n;i++){
            if(j>=n) break;
            if(array[i][j++]) return true;
        }


        return false;
    }

}