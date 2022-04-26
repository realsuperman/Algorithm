package BaekJoon_2504;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        if(!check(str)){System.out.println(0); return;}

        Stack<String> stack = new Stack<>();
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)=='(' || str.charAt(i)=='['){
                stack.push(Character.toString(str.charAt(i)));
            }
            else if(str.charAt(i)==')'){
                String s1 = stack.pop();

                if(s1.equals("(")){
                    stack.push("2");
                }
                else{ // 숫자가 있다
                    int sum = 0;

                    while(!s1.equals("(")&&!s1.equals("[")){
                        sum+=Integer.parseInt(s1);
                        s1 = stack.pop();
                    }
                    stack.push(Integer.toString(sum*2));
                }
            }else if(str.charAt(i)==']'){
                String s1 = stack.pop();

                if(s1.equals("[")){
                    stack.push("3");
                }
                else{ // 숫자가 있다
                    int sum = 0;

                    while(!s1.equals("(")&&!s1.equals("[")){
                        sum+=Integer.parseInt(s1);
                        s1 = stack.pop();
                    }
                    stack.push(Integer.toString(sum*3));
                }
            }
        }

        int result = 0 ;
        while(stack.size()>0) result += Integer.parseInt(stack.pop());

        System.out.println(result);
    }

    public static boolean check(String str){
        Stack<Character> stack = new Stack<>();
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)=='(' || str.charAt(i)=='[') stack.push(str.charAt(i));
            else if(str.charAt(i)==')'){
                if(stack.size()==0 || stack.pop()!='(') return false;
            }
            else if(str.charAt(i)==']'){
                if(stack.size()==0 || stack.pop()!='[') return false;
            }
        }
        if(stack.size()>0) return false;
        return true;
    }
}