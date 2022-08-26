package Programmers_17684;

import java.util.*;

class Solution {
    public int[] solution(String msg) {
        List<Integer> list = new ArrayList<>();
        Map<String,Integer> map = new HashMap<>();
        int value = 1;
        for(char ch='A'; ch<='Z'; ch++) map.put(String.valueOf(ch),value++);

        StringBuilder temp = new StringBuilder();
        for(int i=0;i<msg.length();i++){
            StringBuilder from = new StringBuilder();
            from.append(msg.charAt(i));

            for(int j=i+1;j<msg.length();j++,i++){
                from.append(msg.charAt(j));
                if(map.get(from.toString())==null){
                    map.put(from.toString(),value++);
                    from.deleteCharAt(from.length()-1);
                    list.add(map.get(from.toString()));
                    i=j-1;
                    break;
                }
            }
            temp = from;
        }
        int index = 0;
        int[] answer = new int[list.size()+1];
        for(int v : list ) answer[index++]=v;
        answer[index] = map.get(temp.toString());
        return answer;
    }
}