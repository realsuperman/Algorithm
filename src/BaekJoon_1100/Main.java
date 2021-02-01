package BaekJoon_1100;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cnt = 0;

        for(int i=0;i<8;i++){
            String[] str = br.readLine().split("",8);
            for(int j=0;j<8;j++){
                if((i+j) %2 == 0 && str[j].equals("F")){
                    cnt++;
                }
            }
        }
        System.out.print(cnt);
    }
}