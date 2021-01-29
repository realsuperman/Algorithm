package BaekJoon_15651;

import java.util.Scanner;

public class Main {
    static int n,m;
    static int[] array;
    static boolean[] check;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        array = new int[m];
        check = new boolean[n+1];
        backTracking(1,0);
        System.out.print(sb);
    }
    public static void backTracking(int su,int depth){
        if(depth == m){
            for(int i=0;i<depth;i++){
                sb.append(array[i]+" ");
            }
            sb.append("\n");
            return;
        }

        for(int i=su;i<=n;i++){
            array[depth] = i;
            backTracking(i,depth+1);
        }

    }
}