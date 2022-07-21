package BaekJoon_4889;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int num = 1;
        for(;;){
            String str = br.readLine();
            if(str.contains("-")) break;
            sb.append(num+". "+solution(str)+"\n");
            num++;
        }

        System.out.println(sb);
    }

    public static int solution(String str){
        Stack<Character> stack = new Stack<>();
        int cnt = 0;

        for(int i=0;i<str.length();i++){
            if(str.charAt(i)=='{'){
                stack.push(str.charAt(i));
            }else{ // 닫는괄호
                if(stack.size()>0){
                    char ch = stack.pop();
                    if(ch!='{') cnt++;
                }
                else {cnt++; stack.push('{');}
            }
        }
        cnt+=stack.size()/2;

        return cnt;
    }
}

