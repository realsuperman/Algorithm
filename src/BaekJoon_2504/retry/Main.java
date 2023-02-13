package BaekJoon_2504.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        if(checkCorrectString(str)){
            System.out.println(solution(str));
        }else{
            System.out.println(0);
        }
    }

    private static int solution(String str) {
        int sum = 0;
        Stack<String> stack = new Stack<>();
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)=='(' || str.charAt(i)=='['){
                stack.push(String.valueOf(str.charAt(i)));
            }else if(str.charAt(i)==')'){
                List<Integer> list = new ArrayList<>();
                while (!stack.peek().equals("(")) {
                    list.add(Integer.valueOf(stack.pop()));
                }
                stack.pop();
                if (list.size() == 0)
                    stack.push("2");
                else {
                    int temp = 0;
                    for (int v : list)
                        temp += v;
                    stack.push(String.valueOf(temp * 2));
                }
            }else if(str.charAt(i)==']'){
                List<Integer> list = new ArrayList<>();
                while (!stack.peek().equals("[")) {
                    list.add(Integer.valueOf(stack.pop()));
                }
                stack.pop();
                if (list.size() == 0)
                    stack.push("3");
                else {
                    int temp = 0;
                    for (int v : list)
                        temp += v;
                    stack.push(String.valueOf(temp * 3));
                }
            }
        }
        while(!stack.isEmpty()){
            sum+=Integer.parseInt(stack.pop());
        }
        return sum;
    }

    private static boolean checkCorrectString(String str){
        Stack<Character> stack = new Stack<>();
        int openTag = 0;
        int closeTag = 0;
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)=='(' || str.charAt(i)=='['){
                openTag++;
                stack.push(str.charAt(i));
            }
            else if(str.charAt(i)==')'){
                if(stack.isEmpty()) return false;
                if(stack.pop()!='(') return false;
                closeTag++;
            }else if(str.charAt(i) == ']'){
                if(stack.isEmpty()) return false;
                if(stack.pop()!='[') return false;
                closeTag++;
            }

        }

        if(openTag!=closeTag) return false;
        return true;
    }
}