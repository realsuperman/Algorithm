package BaekJoon_3107.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(":");
        String[] result = new String[8];
        if(str.length<8){
            // ::이 있는경우
            List<String> list = new ArrayList<>();
            int index = -1;
            for(int i=0;i< str.length;i++){
                if(str[i].equals("")){
                    index = i;
                }
                list.add(str[i]);
            }
            if(index==-1){
                index = str.length;
            }

            while(list.size()<8) list.add(index,"0000");
            str = new String[8];
            for(index=0;index<8;index++) str[index] = list.get(index);
        }
        if(str.length>8){
            List<String> list = new ArrayList<>();
            for(String s : str) list.add(s);
            str = new String[8];
            for(int index=1;index<9;index++) str[index-1] = list.get(index);
        }

        int index = 0;
        for (String s : str) {
            StringBuilder sb = new StringBuilder();
            sb.append(s);
            while (sb.length() < 4) sb.insert(0, "0");
            result[index++] = sb.toString();
        }


        StringBuilder sb = new StringBuilder();
        for(String s : result) sb.append(s+":");
        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb);
    }
}