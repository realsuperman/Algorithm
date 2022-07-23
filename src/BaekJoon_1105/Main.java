package BaekJoon_1105;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");

        String from = str[0];
        String to = str[1];
        if(Integer.parseInt(from) == Integer.parseInt(to)){
            int sum = 0;
            for(int i=0;i<from.length();i++) if(from.charAt(i)=='8') sum++;
            System.out.println(sum);
            return;
        }

        char[] result = new char[from.length()];
        for(int i=0;i<from.length();i++) result[i]='0';

        int index = 0;
        while (index<from.length()) {
            if(index>0){
                char ch = '0';
                boolean chk = false;
                for(int i=0;i<=9;i++){
                    if(i==8){ ch++; continue;}
                    result[index]= ch++;

                    String value = "";
                    for(int j=0;j<=index;j++) value+=result[j];


                    String f = "";
                    for(int j=0;j<=index;j++) f+=from.charAt(j);

                    if( (Integer.parseInt(value)>=Integer.parseInt(f))){
                        int len = value.length();

                        for(int j=len;j<from.length();j++) value+="0";
                        if(Integer.parseInt(value)<=Integer.parseInt(to)){
                            chk= true;
                            break;
                        }
                    }
                }
                if(!chk) result[index] = '8';
            }else {
                if (from.charAt(index) == '8') {
                    result[index] = '9';
                    String temp = "";
                    for (int i = 0; i < result.length; i++) temp += result[i];
                    if (Integer.parseInt(temp) > Integer.parseInt(to)) result[index] = '8';
                } else result[index] = from.charAt(index);
            }
            index++;
        }

        int sum = 0;
        for(int i=0;i<result.length;i++){
            if(result[i]=='8') sum++;
        }
        System.out.println(sum);
    }
}