package BaekJoon_16120;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        Stack<Character> stack = new Stack<>();
        stack.push(str.charAt(0));

        int index = 1;
        while(index<str.length()){
            if(!stack.isEmpty() && stack.peek()=='A' && str.charAt(index)=='P'){
                List<Character> list = new ArrayList<>();
                stack.pop();

                int sw = 0;
                while(!stack.isEmpty()){
                    char ch = stack.pop();

                    if(ch=='P'){
                        sw++;
                    }else{
                        list.add(ch);
                        break;
                    }

                    if(sw>=2) break;
                }
                if(sw==2) stack.push('P');
                else{
                    for(int i=list.size()-1;i>=0;i--) stack.push(list.get(i));
                    stack.push('A'); stack.push('P');
                }
            }else stack.push(str.charAt(index));
            index++;
        }

        if(stack.size()==1 && stack.peek()=='P'){System.out.println("PPAP"); return;}
        System.out.println("NP");
    }
}