package BaekJoon_1850;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        long a = sc.nextLong();
        long b = sc.nextLong();
        long c;
        while(true){
            c = a%b;
            if(c==0) break;
            long temp = a;

            a = b;
            b = temp%b;
        }
        StringBuilder sb = new StringBuilder();
        for(long i=0;i<b;i++){
            sb.append(1);
        }
        System.out.print(sb);
    }
}