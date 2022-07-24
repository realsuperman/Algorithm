package BaekJoon_17615;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String str = br.readLine();

        int[] min = new int[4];
        min[0] = rotate(0,'R',str);
        min[1] = rotate(0,'B',str);
        min[2] = rotate(str.length()-1,'R',str);
        min[3] = rotate(str.length()-1,'B',str);
        int value = min[0];
        for(int i=1;i<4;i++) if(value>min[i]) value=min[i];

        System.out.println(value);
    }

    public static int rotate(int start,char ball,String str){
        int result = 0;
        boolean chk = false;

        if(start==0){
            for(int i=0;i<str.length();i++){
                if(str.charAt(i)==ball){
                    if(chk) result++;
                }else chk =true;
            }
        }else{
            for(int i=str.length()-1;i>=0;i--){
                if(str.charAt(i)==ball){
                    if(chk) result++;
                }else chk =true;
            }
        }
        return result;
    }
}