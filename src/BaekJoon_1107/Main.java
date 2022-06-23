package BaekJoon_1107;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static int[] button = {1,1,1,1,1,1,1,1,1,1};
    public static String target;
    public static int result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        target = br.readLine();
        int breakButtonCnt = Integer.parseInt(br.readLine());
        if(breakButtonCnt>0) {
            String[] str = br.readLine().split(" ");
            for (int i = 0; i < str.length; i++) button[Integer.parseInt(str[i])] = 0;
        }
        result = Math.abs(100-Integer.parseInt(target));
        solutuon(0,"");
        System.out.println(result);
    }
    public static void solutuon(int size,String str){
        if(size==7) return;

        if(!str.equals("")) {
            int number = Math.abs(Integer.parseInt(target) - Integer.parseInt(str));
            number += str.length();
            if (number < result) result = number;
        }

        for(int i=0;i<10;i++){
            if(button[i]==0) continue;
            solutuon(size+1,str+""+i);
        }
    }
}