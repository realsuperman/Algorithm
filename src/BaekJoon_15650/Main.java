package BaekJoon_15650;

import java.util.Scanner;

public class Main {
    static int n,m;
    static int[] array;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        array = new int[m];
        backTracking(1,0);
    }
    public static void backTracking(int at,int depth){
        if(depth == m){
            for(int i=0;i<depth;i++){
                System.out.print(array[i]+" ");
            }
            System.out.println();
            return;
        }

        for(int i=at;i<=n;i++){
            array[depth] = i;
            backTracking(i+1,depth+1);
        }

    }
}