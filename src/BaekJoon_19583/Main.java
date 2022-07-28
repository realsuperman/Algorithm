package BaekJoon_19583;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int start = Integer.parseInt(str[0].replaceAll(":",""));
        int mid = Integer.parseInt(str[1].replaceAll(":",""));
        int end = Integer.parseInt(str[2].replaceAll(":",""));

        Map<String,Integer> map = new HashMap<>();
        int result = 0;
        String input = "";
        while(true){
            input = br.readLine();
            if(input==null || input.length()==0 ) break;
            str = input.split(" ");

            //int time = Integer.parseInt(str[0].replaceAll(":",""));
            String[] temp = str[0].split(":");
            StringBuilder sb = new StringBuilder();
            sb.append(temp[0]); sb.append(temp[1]);

            int time = Integer.parseInt(sb.toString());
            String name = str[1];

            if(time<=start) map.put(name,1);
            else{
                if(time>=mid && time<=end){
                    if(map.get(name)!=null && map.get(name)>0){
                        map.put(name,0);
                        result++;
                    }
                }
                if(time>end) break;
            }
        }
        System.out.println(result);
    }
}