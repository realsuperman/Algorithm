package BaekJoon_1065;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main{
    static int[] array;
    static boolean[] check;
    static boolean value = false;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        if(N<10){ System.out.println(N); return;}
        int sum = 9;
        for(int i=10;i<=N;i++) sum+=hansuCheck(i);
        System.out.println(sum);
    }
    public static int hansuCheck(int num){
        String[] str = String.valueOf(num).split("");
        int diff = Integer.parseInt(str[1])-Integer.parseInt(str[0]);

        for(int i=2;i<str.length;i++){
            int from = Integer.parseInt(str[i-1]);
            int to = Integer.parseInt(str[i]);
            if( (to-from) != diff) return 0;
        }
        return 1;
    }
}