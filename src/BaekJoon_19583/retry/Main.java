package BaekJoon_19583.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class Main {
    static int S,E,Q;
    public static void main(String[] args) throws IOException {
        int result = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        String[] start = str[0].split(":");
        String[] end = str[1].split(":");
        String[] term = str[2].split(":");
        S = Integer.parseInt(start[0]+start[1]);
        E = Integer.parseInt(end[0]+end[1]);
        Q = Integer.parseInt(term[0]+term[1]);
        Map<String, Boolean> map = new HashMap<>();
        String input = "";
        while(true){
            input = br.readLine();
            if(input==null || input.length()==0) break;
            str = input.split(" ");
            input = str[1]; // 이름
            str = str[0].split(":");
            int number = Integer.parseInt(str[0]+str[1]);
            if(number<=S) {
                map.put(input, false);
            }else if(number>=E && number<=Q && map.get(input)!=null){
                map.put(input,true);
            }
        }

        for(String s : map.keySet()){
            if(map.get(s)) result++;
        }
        System.out.println(result);
    }
}