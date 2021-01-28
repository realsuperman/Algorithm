package BaekJoon_9012;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        Stack<Character> stack;
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());

        for(int c=0;c<n;c++){
            stack = new Stack<>();
            str = br.readLine();
            int badCnt = 0;
            int open = 0;
            int close = 0;

            for(int i=0;i<str.length();i++){
                if(str.charAt(i)=='(') open++;
                if(str.charAt(i)==')') close++;
            }
            if(open!=close){
                sb.append("NO").append('\n');
                continue;
            }


            for(int i=0;i<str.length();i++){
                if(str.charAt(i)=='('){
                    stack.push(str.charAt(i));
                }else if(str.charAt(i)==')'){
                    if(stack.isEmpty()){
                        badCnt++;
                        sb.append("NO").append('\n');
                        break;
                    }
                    char s = stack.pop();
                    if(s=='(' && str.charAt(i)!=')'){
                        badCnt++;
                    }
                }
                if(badCnt>0){
                    sb.append("NO").append('\n');
                    break;
                }
            }
            if(badCnt==0) sb.append("YES").append('\n');
        }
        System.out.print(sb);
    }
}