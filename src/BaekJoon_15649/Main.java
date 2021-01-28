package BaekJoon_15649;

import java.util.Scanner;

public class Main {
    static int n,m;
    static int[] array;
    static boolean[] check;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        array = new int[m];
        check = new boolean[n+1];
        backTracking(0);
    }
    public static void backTracking(int depth){
        if(depth == m){
            for(int i=0;i<depth;i++){
                System.out.print(array[i]+" ");
            }
            System.out.println();
            return;
        }

        for(int i=1;i<=n;i++){
            if(check[i]) continue;
            array[depth] = i;
            check[i] = true;
            backTracking(depth+1);
            check[i] = false;
        }

    }
}