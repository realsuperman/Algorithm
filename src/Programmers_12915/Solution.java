package Programmers_12915;

import java.util.*;

class Solution {
    public String[] solution(String[] strings, int n) {
        String[] answer = new String[strings.length];
        List<Character> list = new ArrayList<>();
        for(String v : strings) list.add(v.charAt(n));
        Collections.sort(list);
        Arrays.sort(strings);

        int index = 0;
        for(char ch : list){
            for(int i=0;i<strings.length;i++){
                if(strings[i]==null) continue;

                if(ch==strings[i].charAt(n)){
                    answer[index++] = strings[i];
                    strings[i]=null;
                }
            }
        }
        return answer;
    }
}