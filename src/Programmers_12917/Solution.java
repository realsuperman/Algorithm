package Programmers_12917;

import java.util.*;

class Solution {
    public String solution(String s) {
        String answer = "";
        List<Character> list = new ArrayList<>();
        for(int i=0;i<s.length();i++) list.add(s.charAt(i));
        Collections.sort(list,Collections.reverseOrder());
        for(char ch : list ) answer+=ch;
        return answer;
    }
}