package BaekJoon_2609;

import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = a*b;

        while(true){
            if(a%b==0) break;
            int temp = a;
            a = b;
            b = temp%b;
        }
        System.out.println(b);
        System.out.println(c/b);
    }
}