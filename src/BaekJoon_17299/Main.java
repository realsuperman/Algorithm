package BaekJoon_17299;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine())-1;
        String[] str = br.readLine().split(" ");
        Map<Integer,Integer> map = new HashMap<>();
        for(String s : str){
            if(map.get(Integer.parseInt(s))==null){
                map.put(Integer.parseInt(s),1);
            }else{
                map.put(Integer.parseInt(s),map.get(Integer.parseInt(s))+1);
            }
        }
        Stack<Integer> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        sb.append("-1"+" ");

        stack.add(Integer.parseInt(str[N]));
        while(N>0){
            N--;
            while(!stack.isEmpty()) {
                if (map.get(Integer.parseInt(str[N])) < map.get(stack.peek())) {
                    sb.append(stack.peek()+" ");
                    stack.push(Integer.parseInt(str[N]));
                    break;
                }else{
                    stack.pop();
                }
            }
            if(stack.size()==0){ stack.push(Integer.parseInt(str[N])); sb.append("-1"+" ");}
        }
        str = sb.toString().split(" ");
        sb = new StringBuilder();
        for(int i=str.length-1;i>=0;i--) sb.append(str[i]+" ");
        System.out.println(sb.toString());
    }
}