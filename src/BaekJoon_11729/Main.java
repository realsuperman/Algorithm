package BaekJoon_11729;

import java.util.Scanner;

public class Main {
    public static int cnt = 0;
    public static StringBuilder sb = new StringBuilder();
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int no = sc.nextInt();
        hanoi(no,1,3,2);
        System.out.print(cnt+"\n"+sb);
    }
    public static void hanoi(int n,int from,int to,int t) {
        cnt++;
        if(n == 1){
            sb.append(from+" "+to+"\n");
            return;
        }
        hanoi(n-1,from,t,to);
        sb.append(from+" "+to+"\n");
        hanoi(n-1,t,to,from);
    }
}