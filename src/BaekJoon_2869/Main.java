package BaekJoon_2869;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ",3);
        int a = Integer.parseInt(str[0]); int b = Integer.parseInt(str[1]); int v = Integer.parseInt(str[2]);
        int diff = a-b;

        if(a>=v){
            System.out.print(1);
        }else{
            double su1 = (v-a);
            double su2 = diff;
            double su3 = su1/su2;
            su3 = Math.ceil(su3);
            System.out.print((int)su3+1);
        }
    }
}