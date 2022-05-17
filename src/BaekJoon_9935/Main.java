package BaekJoon_9935;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Character> stack = new Stack<>();
        String str = br.readLine();
        String boom = br.readLine();

        int index = 0;
        while(index<str.length()){
            if(boom.charAt(boom.length()-1) == str.charAt(index)){
                StringBuilder sb = new StringBuilder();
                sb.append(str.charAt(index));

                int v = 1;
                while(stack.size()>0){
                    if(v>=boom.length()) break;
                    sb.append(stack.pop());
                    v++;
                }
                if(!sb.reverse().toString().equals(boom)){
                    int i = 0;
                    while(i<sb.length()){
                        stack.push(sb.toString().charAt(i));
                        i++;
                    }
                }
            }else{
                stack.push(str.charAt(index));
            }
            index++;
        }
        StringBuilder sb = new StringBuilder();
        if(stack.size()>0){
            for(Character c : stack) sb.append(c);
            System.out.println(sb);
            return;
        }
        System.out.println("FRULA");
    }
}