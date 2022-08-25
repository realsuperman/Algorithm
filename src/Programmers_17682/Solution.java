package Programmers_17682;

import java.util.*;

class Solution {
    public int solution(String dartResult) {
        int index = 0;
        Stack<Integer> stack = new Stack<>();

        while(index<dartResult.length()){
            String number = String.valueOf(dartResult.charAt(index++));
            if(dartResult.charAt(index)=='0'){
                number = number+'0';
                index++;
            }
            String bouns = String.valueOf(dartResult.charAt(index++));
            String option = "";
            if( index<dartResult.length() && (dartResult.charAt(index)=='*' || dartResult.charAt(index)=='#')){
                option+=dartResult.charAt(index);
                index++;
            }
            int num = Integer.parseInt(number);
            if(bouns.equals("D")) num=num*num;
            else if(bouns.equals("T")) num=num*num*num;

            if(option.equals("")) stack.push(num);
            else if(option.equals("*")){
                if(stack.size()>0){int n = stack.pop(); n=n*2; stack.push(n);}
                stack.push(num*2);
            }else stack.push(-num);
        }

        int sum = 0;
        while(!stack.isEmpty()) sum+=stack.pop();
        return sum;
    }
}
