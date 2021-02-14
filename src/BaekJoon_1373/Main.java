package BaekJoon_1373;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        change(str);

        System.out.print(sb.reverse().toString());
    }
    public static void change(String str){
        if(str.length() == 0) sb.append(0);
        for(int i=str.length()-1;i>=0;i=i-3){
            if((i-2)>=0){ // 3자리인 경우
                sb.append(Integer.parseInt(String.valueOf(str.charAt(i)))*1 + Integer.parseInt(String.valueOf(str.charAt(i-1)))*2 + Integer.parseInt(String.valueOf(str.charAt(i-2)))*4);
            }else if((i-1)>=0){ // 2자리인 경우
                sb.append(Integer.parseInt(String.valueOf(str.charAt(i)))*1 + Integer.parseInt(String.valueOf(str.charAt(i-1)))*2);
            }else{ // 1자리인 경우
                sb.append(Integer.parseInt(String.valueOf(str.charAt(i)))*1);
            }
        }
    }
}
