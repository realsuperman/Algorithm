package BaekJoon_12919;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String from = br.readLine();
        String to = br.readLine();
        Map<String,Integer> map = new HashMap<>();

        Queue<String> queue = new LinkedList<>();
        queue.add(to);
        map.put(to,1);

        while(!queue.isEmpty()){
            String value = queue.remove();
            if(from.equals(value)){System.out.println(1); return;}
            if(value.length()<from.length()) continue;

            StringBuilder sb = new StringBuilder();
            if(value.charAt(value.length()-1)=='A'){
                for(int i=0;i<value.length()-1;i++) sb.append(value.charAt(i));
            }
            if(map.get(sb.toString())==null){
                queue.add(sb.toString());
                map.put(sb.toString(),1);
            }

            sb = new StringBuilder();
            if(value.charAt(0)=='B'){
                for(int i=1;i<value.length();i++) sb.append(value.charAt(i));
            }
            sb.reverse();
            if(sb!=null && map.get(sb.toString())==null){
                queue.add(sb.toString());
                map.put(sb.toString(),1);
            }
        }
        System.out.println(0);
    }
}