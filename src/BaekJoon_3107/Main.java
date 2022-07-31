package BaekJoon_3107;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String original = br.readLine();
        String[] str = original.split(":");
        List<String> list = new ArrayList<>();

        String[] array = new String[8];
        int index = 7;
        int target = str.length-1;
        if(original.charAt(original.length()-1)==':'&&original.charAt(original.length()-2)==':'){
            while(index-- > target) array[index] = "0000";
            index++;
        }

        while(index>=0){
            if(target >=0){
                array[index]=str[target];
                if(str[target].equals("")){
                    while(index-- > target) array[index] = "0000";
                    index++;
                }
            }
            index--;
            target--;
        }
        for(int i=0;i<8;i++) if(array[i]==null) array[i]="";

        for(int i=0;i<array.length;i++){
            StringBuilder temp = new StringBuilder();
            if(array[i].length()<4) for(int j=array[i].length();j<4;j++) temp.append("0");
            if(temp.length()>0){ for(int j=0;j<array[i].length();j++) temp.append(array[i].charAt(j)); list.add(temp.toString());}
            else list.add(array[i]);
        }

        StringBuilder sb = new StringBuilder();
        for(String s : list ) sb.append(s+":");
        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb);
    }
}