package BaekJoon_4949;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();

        while(true){
            stack = new Stack<>();
            str = br.readLine();
            if(str.equals(".")) break;
            int badCnt = 0;
            int open = 0;
            int close = 0;

            for(int i=0;i<str.length();i++){
                if(str.charAt(i)=='('||str.charAt(i)=='[') open++;
                if(str.charAt(i)==')'||str.charAt(i)==']') close++;
            }
            if(open!=close){
                sb.append("no").append('\n');
                continue;
            }


            for(int i=0;i<str.length();i++){
                if(str.charAt(i)=='('||str.charAt(i)=='['){
                    stack.push(str.charAt(i));
                }else if(str.charAt(i)==')'||str.charAt(i)==']'){
                    if(stack.isEmpty()){
                        badCnt++;
                        sb.append("no").append('\n');
                        break;
                    }
                    char s = stack.pop();
                    if(s=='[' && str.charAt(i)!=']'){
                        badCnt++;
                    }if(s=='(' && str.charAt(i)!=')'){
                        badCnt++;
                    }
                }
                if(badCnt>0){
                    sb.append("no").append('\n');
                    break;
                }
            }
            if(badCnt==0) sb.append("yes").append('\n');
            //System.out.println(stack.peek());
        }
        System.out.print(sb);
    }
}