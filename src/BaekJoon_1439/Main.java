package BaekJoon_1439;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int zero = 0,one=0;

        int v;
        boolean check = false;

        v=0;
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)-48!=v){ check=true; break;}
        }
        if(!check){ System.out.println(0); return;}

        check = false;
        v=1;
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)-48!=v){ check=true; break;}
        }
        if(!check){ System.out.println(0); return;}

        v=-1;
        for(int i=0;i<str.length();i++){
            if(v==-1) v = str.charAt(i);
            else if(v!=str.charAt(i)){
                if(v=='0') zero++;
                else one++;
                v = str.charAt(i);
            }
        }


        if(v=='0') zero++;
        else one++;
        if(zero>one) System.out.println(one);
        else System.out.println(zero);
    }
}