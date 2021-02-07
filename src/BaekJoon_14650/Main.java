package BaekJoon_14650;

import java.util.Scanner;

public class Main {
    static int n;
    static String str="";
    static int cnt = 0;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        sam(0);
        System.out.println(cnt);
    }
    public static void sam(int value){
        if(value==n){
            int v=0;

            if(str.charAt(0) == '0') return;
            for(int i=0;i<str.length();i++){
                v+=Integer.parseInt(String.valueOf(str.charAt(i)));
            }
            if(v%3==0) cnt++;
            return;
        }
        for(int i=0;i<3;i++){
            str += i;
            sam(value+1);
            str=str.substring(0, str.length()-1);
        }

    }
}